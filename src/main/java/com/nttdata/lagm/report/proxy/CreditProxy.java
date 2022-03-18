package com.nttdata.lagm.report.proxy;

import com.nttdata.lagm.report.model.account.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditProxy {
	public Flux<Credit> findAll();
	public Mono<Credit> findById(String id);
	public Mono<Credit> findByAccountNumber(String accountNumber);
	public Flux<Credit> findByDni(String dni);
}
