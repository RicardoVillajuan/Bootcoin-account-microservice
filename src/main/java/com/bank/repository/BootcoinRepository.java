package com.bank.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bank.entity.Bootcoin;

import reactor.core.publisher.Mono;

public interface BootcoinRepository extends ReactiveMongoRepository<Bootcoin, String>{
	
	Mono<Bootcoin> findByDocumentnumber(String documentnumber);
}
