package com.nttdata.lagm.report.dto.response;

import com.nttdata.lagm.report.model.customer.Customer;
import lombok.Data;

@Data
public class ConsolidatedProductResponseDto {
    private String id;
    private String accountNumber;
    private String cci;
    private Customer customer;
    private String amount;
    private Boolean status;
}
