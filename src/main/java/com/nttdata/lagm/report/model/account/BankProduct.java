package com.nttdata.lagm.report.model.account;

import com.nttdata.lagm.report.model.customer.Customer;
import lombok.Data;

@Data
public class BankProduct {
	private String id;
	private String accountNumber;
	private String cci;
	private Customer customer;
	private String amount;
	private Boolean status;
}