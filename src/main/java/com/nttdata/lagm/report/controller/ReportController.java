package com.nttdata.lagm.report.controller;

import com.nttdata.lagm.report.dto.response.ConsolidatedProductResponseDto;
import com.nttdata.lagm.report.dto.response.DebitCardBalanceDto;
import com.nttdata.lagm.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/consolidated/dni/{dni}")
    @ResponseStatus(HttpStatus.OK)
    private Flux<ConsolidatedProductResponseDto> getConsolidatedProductReport(@PathVariable("dni") String dni) {
        return reportService.getConsolidatedProductReport(dni);
    }

    @GetMapping("/balance/cardNumber/{cardNumber}")
    @ResponseStatus(HttpStatus.OK)
    private Mono<DebitCardBalanceDto> getPrincipalBalance(@PathVariable("cardNumber") String cardNumber) {
        return reportService.getPrincipalBalance(cardNumber);
    }
}
