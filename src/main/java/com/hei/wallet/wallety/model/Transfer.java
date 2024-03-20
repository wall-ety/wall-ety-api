package com.hei.wallet.wallety.model;

import com.hei.wallet.wallety.fjpa.annotation.Column;
import com.hei.wallet.wallety.fjpa.annotation.Entity;
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
@Entity(tableName = "Transfer")
public class Transfer implements Serializable {
    @Column
    private BigDecimal amount;

    @Column(columnName = "created_at")
    private Instant createdAt;

    @Column(columnName = "effective_date")
    private Instant effectiveDate;

    @Column
    private Text reason;

    @Column
    private String status;

    @Column
    private  String label;

    @Column
    @Relation
    private Account source;

    @Column
    @Relation
    private Account destination;

}
