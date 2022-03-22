package com.nttdata.lagm.report.proxy;

import com.nttdata.lagm.report.model.card.DebitCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class DebitCardProxyImpl implements DebitCardProxy {
	
	private Logger LOGGER = LoggerFactory.getLogger(DebitCardProxyImpl.class);
	
	@Value("${config-eureka.base.debitCard.endpoint}")
	private String endpointAccount;
	
	@Autowired
	@Qualifier("wcLoadBalanced")
	private WebClient.Builder webClientBuilder;
	
	@Override
	public Mono<DebitCard> findByCardNumber(String cardNumber) {
		Map<String, Object> params = new HashMap<>();
		params.put("cardNumber", cardNumber);
		return webClientBuilder
				//.clientConnector(RestUtils.getDefaultClientConnector())
				.build()
				.get()
				.uri(endpointAccount + "/cardNumber/{cardNumber}", params)
				.retrieve()
				.bodyToMono(DebitCard.class);
		
	}
}
