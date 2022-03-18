package com.nttdata.lagm.report.proxy;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.lagm.report.model.account.BankAccount;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AccountProxyImpl implements AccountProxy {
	
	private Logger LOGGER = LoggerFactory.getLogger(AccountProxyImpl.class);
	
	@Value("${config-eureka.base.account.endpoint}")
	private String endpointAccount;
	
	@Autowired
	@Qualifier("wcLoadBalanced")
	private WebClient.Builder webClientBuilder;
	
	private WebClient webClient = WebClient.create();

	@Override
	public Flux<BankAccount> findAll() {
		return webClientBuilder
				//.clientConnector(RestUtils.getDefaultClientConnector())
				.build()
				.get()
				.uri(endpointAccount)
				.retrieve()
				.bodyToFlux(BankAccount.class);
	}
	
	@Override
	public Mono<BankAccount> findById(String id) {
		LOGGER.info("AccountProxyImpl: findById id={}", id);
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return webClientBuilder
				//.clientConnector(RestUtils.getDefaultClientConnector())
				.build()
				.get()
				.uri(endpointAccount + "/{id}", params)
				.retrieve()
				.bodyToMono(BankAccount.class);
		
	}
	
	@Override
	public Mono<BankAccount> findByAccountNumber(String accountNumber) {
		LOGGER.info("AccountProxyImpl: findByAccountNumber accountNumber={}", accountNumber);
		Map<String, Object> params = new HashMap<>();
		params.put("accountNumber", accountNumber);
		return webClientBuilder
				//.clientConnector(RestUtils.getDefaultClientConnector())
				.build()
				.get()
				.uri(endpointAccount + "/accountNumber/{accountNumber}", params)
				.retrieve()
				.bodyToMono(BankAccount.class);
		
	}

	@Override
	public Mono<BankAccount> update(BankAccount bankAccount) {
		LOGGER.info("AccountProxyImpl: update bankAccount={}", bankAccount);
		return webClient.put()
				.uri(endpointAccount)
				.body(Mono.just(bankAccount), BankAccount.class)
				// .accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(BankAccount.class);
	}

	@Override
	public Flux<BankAccount> findAllByDni(String dni) {
		LOGGER.info("AccountProxyImpl: findAllByDni dni={}", dni);
		Map<String, Object> params = new HashMap<>();
		params.put("dni", dni);
		return webClientBuilder
				//.clientConnector(RestUtils.getDefaultClientConnector())
				.build()
				.get()
				.uri(endpointAccount + "/dni/{dni}", params)
				.retrieve()
				.bodyToFlux(BankAccount.class);
	}

	@Override
	public Flux<BankAccount> findAllByAccountNumberAndDni(String accountNumber, String dni) {
		LOGGER.info("AccountProxyImpl: findAllByAccountNumberAndDni accountNumber={}, dni={}", 
				accountNumber, dni);
		Map<String, Object> params = new HashMap<>();
		params.put("accountNumber", accountNumber);
		params.put("dni", dni);
		return webClientBuilder
				//.clientConnector(RestUtils.getDefaultClientConnector())
				.build()
				.get()
				.uri(endpointAccount + "/accountNumber/{accountNumber}/dni/{dni}", params)
				.retrieve()
				.bodyToFlux(BankAccount.class);
	}
}
