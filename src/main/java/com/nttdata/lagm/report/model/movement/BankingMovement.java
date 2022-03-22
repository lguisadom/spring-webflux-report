package com.nttdata.lagm.report.model.movement;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class BankingMovement {
    private String id;
	private String bankProductId;
	private BankingMovementType bankingMovementType;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime date;
	private String accountNumber;
	private String bankingFee;
	private BigDecimal amount;
	private BigDecimal commision;
	private BigDecimal finalAmount;
}
