package com.hei.wallet.wallety.model;

import com.hei.wallet.wallety.fjpa.annotation.Column;
import com.hei.wallet.wallety.fjpa.annotation.Entity;
import com.hei.wallet.wallety.fjpa.annotation.Id;
import com.hei.wallet.wallety.fjpa.annotation.Relation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(tableName = "balance_history")
public class BalanceHistory implements Serializable {
    @Id
    @Column
    private String id;

    @Column
    private BigDecimal amount;

    @Column(columnName = "created_at")
    private Instant createdAt;

    @Column
    @Relation
    private Account account;
}