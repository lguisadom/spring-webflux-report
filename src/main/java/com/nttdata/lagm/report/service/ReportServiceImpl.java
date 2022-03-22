package com.nttdata.lagm.report.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.lagm.report.dto.request.MovementRequestBetweenDatesDto;
import com.nttdata.lagm.report.dto.response.ConsolidatedProductResponseDto;
import com.nttdata.lagm.report.dto.response.DebitCardBalanceDto;
import com.nttdata.lagm.report.model.movement.BankingMovement;
import com.nttdata.lagm.report.proxy.AccountProxy;
import com.nttdata.lagm.report.proxy.CreditProxy;
import com.nttdata.lagm.report.proxy.DebitCardProxy;
import com.nttdata.lagm.report.proxy.MovementProxy;
import com.nttdata.lagm.report.util.Converter;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private AccountProxy accountProxy;

    @Autowired
    private CreditProxy creditProxy;

    @Autowired
    private DebitCardProxy debitCardProxy;

    @Autowired
    private MovementProxy movementProxy;

    @Override
    public Flux<ConsolidatedProductResponseDto> getConsolidatedProductReport(String dni) {
        return Flux.merge(getConsolidatedAccount(dni),getConsolidatedCredit(dni));

    }

    @Override
    public Mono<DebitCardBalanceDto> getPrincipalBalance(String cardNumber) {
        return debitCardProxy.findByCardNumber(cardNumber)
        .flatMap(debitCard -> {
            return Mono.just(Converter.convertDebitCardToDto(debitCard));
        });
    }

    @Override
    public Flux<BankingMovement> getMovementsBetweenDates(MovementRequestBetweenDatesDto movementRequestBetweenDatesDto) {
        return movementProxy.getMovementsBetweenDates(movementRequestBetweenDatesDto);
    }

    private Flux<ConsolidatedProductResponseDto> getConsolidatedAccount(String dni) {
        return accountProxy.findAllByDni(dni)
            .flatMap(account -> {
                return Flux.just(Converter.convertBankAccountToDto(account));
            });
    }

    private Flux<ConsolidatedProductResponseDto> getConsolidatedCredit(String dni) {
        return creditProxy.findByDni(dni)
            .flatMap(credit -> {
                return Flux.just(Converter.convertBankAccountToDto(credit));
            });
    }
}
