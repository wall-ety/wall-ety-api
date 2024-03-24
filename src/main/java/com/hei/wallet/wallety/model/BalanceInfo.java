package com.hei.wallet.wallety.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class BalanceInfo implements Serializable {
    private BalanceHistory balance;
    private BigDecimal loan;
    private float loanInterest;
}
