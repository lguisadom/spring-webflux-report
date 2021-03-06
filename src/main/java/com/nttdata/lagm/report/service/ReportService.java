package com.nttdata.lagm.report.service;

import com.nttdata.lagm.report.dto.request.MovementRequestBetweenDatesDto;
import com.nttdata.lagm.report.dto.response.ConsolidatedProductResponseDto;
import com.nttdata.lagm.report.dto.response.DebitCardBalanceDto;
import com.nttdata.lagm.report.model.movement.BankingMovement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReportService {
    Flux<ConsolidatedProductResponseDto> getConsolidatedProductReport(String dni);
    Mono<DebitCardBalanceDto> getPrincipalBalance(String cardNumber);
    Flux<BankingMovement> getMovementsBetweenDates(MovementRequestBetweenDatesDto movementRequestBetweenDatesDto);

}
