package com.nttdata.lagm.report.proxy;

import com.nttdata.lagm.report.model.card.DebitCard;
import reactor.core.publisher.Mono;

public interface DebitCardProxy {
	public Mono<DebitCard> findByCardNumber(String cardNumber);
}
