package com.hei.wallet.wallety.endpoint.rest.model;

import com.hei.wallet.wallety.model.Category;
import com.hei.wallet.wallety.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction implements Serializable {
    private String id;
    private TransactionType type;
    private BigDecimal amount;
    private Instant transactionDatetime;
    private String label;
    private String reason;
    private Category category;
    private Account account;
}