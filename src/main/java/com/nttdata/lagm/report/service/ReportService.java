package com.nttdata.lagm.report.service;

import com.nttdata.lagm.report.dto.response.ConsolidatedProductResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReportService {
    Flux<ConsolidatedProductResponseDto> getConsolidatedProductReport(String dni);
}
