package com.nttdata.lagm.report.proxy;

import com.nttdata.lagm.report.model.account.Credit;
import com.nttdata.lagm.report.util.RestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class CreditProxyImpl implements CreditProxy {
	private Logger LOGGER = LoggerFactory.getLogger(CreditProxyImpl.class);

	@Value("${config-eureka.base.credit.endpoint}")
	private String endpointCredit;

	@Autowired
	@Qualifier("wcLoadBalanced")
	private WebClient.Builder webClientBuilder;

	@Override
	public Flux<Credit> findAll() {
		return webClientBuilder
				.clientConnector(RestUtils.getDefaultClientConnector())
				.build()
				.get()
				.uri(endpointCredit)
				.retrieve()
				.bodyToFlux(Credit.class);
	}

	@Override
	public Mono<Credit> findById(String id) {
		LOGGER.info("CreditProxyImpl: findById id={}", id);
		Map<String,Object> params = new HashMap<>();
		params.put("id", id);
		return webClientBuilder
				.clientConnector(RestUtils.getDefaultClientConnector())
				.build()
				.get()
				.uri(endpointCredit + "/{id}", params)
				.retrieve()
				.bodyToMono(Credit.class);
	}

	@Override
	public Mono<Credit> findByAccountNumber(String accountNumber) {
		LOGGER.info("CreditProxyImpl: findByAccountNumber accountNumber={}", accountNumber);
		Map<String,Object> params = new HashMap<>();
		params.put("accountNumber", accountNumber);
		return webClientBuilder
				.clientConnector(RestUtils.getDefaultClientConnector())
				.build()
				.get()
				.uri(endpointCredit + "/accountNumber/{accountNumber}", params)
				.retrieve()
				.bodyToMono(Credit.class);

	}

	@Override
	public Flux<Credit> findByDni(String dni) {
		LOGGER.info("CreditProxyImpl: findByDni dni={}", dni);
		Map<String,Object> params = new HashMap<>();
		params.put("dni", dni);
		return webClientBuilder
				.clientConnector(RestUtils.getDefaultClientConnector())
				.build()
				.get()
				.uri(endpointCredit + "/dni/{dni}", params)
				.retrieve()
				.bodyToFlux(Credit.class);
	}
}
