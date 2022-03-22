package com.nttdata.lagm.report.dto.response;

import lombok.Data;

@Data
public class DebitCardBalanceDto {
    private String cardNumber;
    private String amount;
    private DebitCardBalanceExtraData extraData;
}
