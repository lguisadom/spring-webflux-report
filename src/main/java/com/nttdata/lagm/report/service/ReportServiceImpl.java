package com.nttdata.lagm.report.service;

import com.nttdata.lagm.report.dto.response.ConsolidatedProductResponseDto;
import com.nttdata.lagm.report.model.account.BankAccount;
import com.nttdata.lagm.report.model.account.Credit;
import com.nttdata.lagm.report.proxy.AccountProxy;
import com.nttdata.lagm.report.proxy.CreditProxy;
import com.nttdata.lagm.report.proxy.CustomerProxy;
import com.nttdata.lagm.report.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private AccountProxy accountProxy;

    @Autowired
    private CustomerProxy customerProxy;

    @Autowired
    private CreditProxy creditProxy;

    @Override
    public Flux<ConsolidatedProductResponseDto> getConsolidatedProductReport(String dni) {
        ConsolidatedProductResponseDto consolidatedProduct = new ConsolidatedProductResponseDto();
        return Flux.merge(getConsolidatedAccount(dni),getConsolidatedCredit(dni));

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
