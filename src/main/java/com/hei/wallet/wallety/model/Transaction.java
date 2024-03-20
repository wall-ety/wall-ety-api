package com.hei.wallet.wallety.model;

import com.hei.wallet.wallety.fjpa.annotation.Column;
import com.hei.wallet.wallety.fjpa.annotation.Entity;
import com.hei.wallet.wallety.fjpa.annotation.Id;
import com.hei.wallet.wallety.fjpa.annotation.Relation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(tableName = "transaction")
public class Transaction implements Serializable {
    @Id
    @Column
    private String id;

    @Column
    private TransactionType  type;

    @Column
    private BigDecimal amount;

    @Column(columnName = "transaction_datetime")
    private Instant transactionDatetime;

    @Column
    private String label;

    @Column
    private Text reason;

    @Column
    @Relation
    private Category category;

    @Column
    @Relation
    private Account account;

}
