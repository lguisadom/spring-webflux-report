package com.nttdata.lagm.report.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovementRequestBetweenDatesDto {
    private String from;
    private String to;
}
