package com.hei.wallet.wallety.endpoint.rest.model;

import com.hei.wallet.wallety.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class CreateTransaction implements Serializable {
    private String id;
    private TransactionType type;
    private String reason;
    private BigDecimal amount;
    private Instant transactionDatetime;
    private String label;
    private String idAccount;
    private String idCategory;
}
