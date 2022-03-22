package com.nttdata.lagm.report.proxy;

import com.nttdata.lagm.report.dto.request.MovementRequestBetweenDatesDto;
import com.nttdata.lagm.report.model.movement.BankingMovement;

import reactor.core.publisher.Flux;

public interface MovementProxy {
	Flux<BankingMovement> getMovementsBetweenDates(MovementRequestBetweenDatesDto movementRequestBetweenDatesDto);
}
