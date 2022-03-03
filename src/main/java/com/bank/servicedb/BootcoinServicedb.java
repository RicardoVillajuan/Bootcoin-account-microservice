package com.bank.servicedb;

import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.bank.entity.Bootcoin;
import com.bank.model.BootcoinSolicitation;
import com.bank.model.BootcoinTransaction;
import com.bank.model.PayAmmount;
import com.bank.repository.BootcoinRepository;
import com.bank.service.IBootcoinService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class BootcoinServicedb implements IBootcoinService{

	private final KafkaTemplate<String, PayAmmount> kafkaTemplate;
	private final KafkaTemplate<String, BootcoinTransaction> kafkatemplateTransaction; 
	
	private final BootcoinRepository repoBootcoin;
	
	@Override
	public Flux<Bootcoin> findAll() {
		// TODO Auto-generated method stub
		return repoBootcoin.findAll() ;
	}

	@Override
	public Mono<Bootcoin> create(Bootcoin bootcoin) {
		bootcoin.setBootcoin(0.0);
		bootcoin.setCreationdate(LocalDateTime.now());
		
		// TODO Auto-generated method stub
		return repoBootcoin.save(bootcoin);
	}
	
	@Override
	public Mono<Bootcoin> findById(String id) {
		// TODO Auto-generated method stub
		return repoBootcoin.findById(id);
	}

	@Override
	public Mono<Bootcoin> findByDocumentNumber(String documentnumber) {
		// TODO Auto-generated method stub
		return repoBootcoin.findByDocumentnumber(documentnumber);
	}

	@Override
	public Mono<Bootcoin> update(String id, Bootcoin bootcoin) {
		// TODO Auto-generated method stub
		return findById(id).flatMap(e->{
			bootcoin.setId(e.getId());
			return repoBootcoin.save(bootcoin);
		});
	}
	
	/**
	 * Segundo paso
	 * @param bootcoinSolicitation
	 */
	@KafkaListener(topics = "bootcoinsolicitation")
	public void consume(BootcoinSolicitation bootcoinSolicitation) {
		System.out.println("consumidor Bootcoind :" + bootcoinSolicitation.toString());
		Mono<Bootcoin> bootcoin=repoBootcoin.findById(bootcoinSolicitation.getIdbootcoinreceive());
		bootcoin.map(e->{
			PayAmmount pay=new PayAmmount();
			pay.setId(e.getId());
			pay.setAmmount(bootcoinSolicitation.getAmmount());
			pay.setIdyankiaccount(e.getIdyankiaccount());
			kafkaTemplate.send("envioyunki",pay);
			
			Double valuebootcoint=15.0;
			Double bootcoinacum=valuebootcoint/bootcoinSolicitation.getAmmount();
			
			Mono<Bootcoin> bootcoinsubmit=repoBootcoin.findById(bootcoinSolicitation.getIdbootcoinsubmit());
			bootcoinsubmit.map(b->{
				
				
				b.setBootcoin(b.getBootcoin()+bootcoinacum);
				update(b.getId(), b).subscribe();
				
				e.setBootcoin(e.getBootcoin()-bootcoinacum);
				update(e.getId(), e).subscribe();
				
				BootcoinTransaction transactionB=new BootcoinTransaction();
				transactionB.setCreationdate(LocalDateTime.now());
				transactionB.setIdbootcoin(b.getId());
				transactionB.setPaymentmethod("YANKI");
				transactionB.setTypebalance("BOOTCOIN");
				transactionB.setAmmount(bootcoinacum);
				transactionB.setTypetransaction("BUY");
				
				kafkatemplateTransaction.send("transfer",transactionB);
				BootcoinTransaction transactionE=new BootcoinTransaction();
				transactionE.setCreationdate(LocalDateTime.now());
				transactionE.setIdbootcoin(b.getId());
				transactionE.setPaymentmethod("YANKI");
				transactionE.setTypebalance("BOOTCOIN");
				transactionE.setAmmount(bootcoinacum);
				transactionE.setTypetransaction("SOLD");
				
				kafkatemplateTransaction.send("transfer",transactionE);
				return b;
			
			}).subscribe();
			
			
			
			return e;
		}).subscribe();
		// kafkaTemplate.send("yunkisubmit", "Enviado desde el account");
	}
	
}
