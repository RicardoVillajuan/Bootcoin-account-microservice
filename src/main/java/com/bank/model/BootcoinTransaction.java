package com.bank.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BootcoinTransaction {
	
	
	private String idbootcoin;
	private String paymentmethod;//Yanki
	private String typetransaction;//copmrando, vendiendo
	private String typebalance;
	private Double ammount;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime creationdate;
	
}