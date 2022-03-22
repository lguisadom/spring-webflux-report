package com.nttdata.lagm.report.dto.response;

import com.nttdata.lagm.report.model.account.BankAccount;
import com.nttdata.lagm.report.model.customer.Customer;
import lombok.Data;

@Data
public class DebitCardBalanceExtraData {
    private String cvv;
    private String expiryDate;
    private Customer customer;
    private BankAccount mainBankAccount;
}
