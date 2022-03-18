package com.nttdata.lagm.report.util;

import com.nttdata.lagm.report.dto.response.ConsolidatedProductResponseDto;
import com.nttdata.lagm.report.model.account.BankAccount;
import com.nttdata.lagm.report.model.account.BankProduct;

public class Converter {
    public static ConsolidatedProductResponseDto convertBankAccountToDto(BankProduct bankProduct) {
        ConsolidatedProductResponseDto dto = new ConsolidatedProductResponseDto();
        dto.setId(bankProduct.getId());
        dto.setAccountNumber(bankProduct.getAccountNumber());
        dto.setCci(bankProduct.getCci());
        dto.setAmount(bankProduct.getAmount());
        dto.setStatus(bankProduct.getStatus());
        dto.setCustomer(bankProduct.getCustomer());
        return dto;
    }
}
