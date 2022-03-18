package com.nttdata.lagm.report.model.account;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Credit extends BankProduct {
	private String creditLimit;
	
}
