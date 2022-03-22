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

import com.nttdata.lagm.report.dto.request.MovementRequestBetweenDatesDto;
import com.nttdata.lagm.report.model.movement.BankingMovement;

import reactor.core.publisher.Flux;

@Component
public class MovementProxyImpl implements MovementProxy {

	private Logger LOGGER = LoggerFactory.getLogger(MovementProxyImpl.class);

	@Value("${config-eureka.base.movement.endpoint}")
	private String endpointMovement;

	@Autowired
	@Qualifier("wcLoadBalanced")
	private WebClient.Builder webClientBuilder;

	@Override
	public Flux<BankingMovement> getMovementsBetweenDates(MovementRequestBetweenDatesDto movementRequestBetweenDatesDto) {
		Map<String, Object> params = new HashMap<>();
		params.put("from", movementRequestBetweenDatesDto.getFrom());
		params.put("to", movementRequestBetweenDatesDto.getTo());
		return webClientBuilder
			//.clientConnector(RestUtils.getDefaultClientConnector())
			.build()
			.get()
			.uri(endpointMovement + "/findBetweenDates/from/{from}/to/{to}", params)
			.retrieve()
			.bodyToFlux(BankingMovement.class);

	}
}