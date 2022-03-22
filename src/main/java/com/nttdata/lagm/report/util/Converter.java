package com.nttdata.lagm.report.util;

import com.nttdata.lagm.report.dto.response.ConsolidatedProductResponseDto;
import com.nttdata.lagm.report.dto.response.DebitCardBalanceDto;
import com.nttdata.lagm.report.dto.response.DebitCardBalanceExtraData;
import com.nttdata.lagm.report.model.account.BankAccount;
import com.nttdata.lagm.report.model.account.BankProduct;
import com.nttdata.lagm.report.model.card.DebitCard;

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

    public static DebitCardBalanceDto convertDebitCardToDto(DebitCard debitCard) {
        DebitCardBalanceDto dto = new DebitCardBalanceDto();
        dto.setCardNumber(debitCard.getCardNumber());
        dto.setAmount(debitCard.getMainBankAccount().getAmount());
        DebitCardBalanceExtraData extraData = new DebitCardBalanceExtraData();
        extraData.setCvv(debitCard.getCvv());
        extraData.setExpiryDate(debitCard.getExpiryDate());
        extraData.setCustomer(debitCard.getCustomer());
        extraData.setMainBankAccount(debitCard.getMainBankAccount());
        dto.setExtraData(extraData);
        return dto;
    }
}
