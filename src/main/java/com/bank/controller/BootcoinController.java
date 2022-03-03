package com.bank.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.Bootcoin;
import com.bank.servicedb.BootcoinServicedb;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bootcoin")
public class BootcoinController {
	
	private final BootcoinServicedb serviceBootcoin;
	
	@PostMapping
	public Mono<Bootcoin> depositAccount(@RequestBody Bootcoin bootcoin){
		
		return serviceBootcoin.create(bootcoin);
	}
}
