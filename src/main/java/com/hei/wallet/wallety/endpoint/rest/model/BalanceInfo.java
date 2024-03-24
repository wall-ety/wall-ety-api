package com.hei.wallet.wallety.endpoint.rest.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceInfo implements Serializable {
    private Balance balance;
    private BigDecimal loan;
    private float loanInterest;
}
