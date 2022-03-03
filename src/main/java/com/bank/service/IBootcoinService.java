package com.bank.service;

import com.bank.entity.Bootcoin;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBootcoinService {
	
	Flux<Bootcoin> findAll();
	
	Mono<Bootcoin> create(Bootcoin bootcoin);
	
	Mono<Bootcoin> findById(String id);
	
	Mono<Bootcoin> findByDocumentNumber(String documentnumber);
	
	Mono<Bootcoin> update(String id, Bootcoin bootcoin);
}
