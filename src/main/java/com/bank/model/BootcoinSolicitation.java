package com.bank.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BootcoinSolicitation {
	
	private String id;
	private String idbootcoinsubmit;
	private String idbootcoinreceive;
	private String paymentmethod;
	private Double ammount;
	private Boolean estatus=false;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime creationdate;
}
