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
import java.util.Objects;
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

    public Transfer() {
    }
    public Transfer(BigDecimal amount,
                    Instant createdAt,
                    Instant effectiveDate,
                    Text reason,
                    String status,
                    String label,
                    Account source,
                    Account destination
    ){
        this.amount = amount;
        this.createdAt = createdAt;
        this.effectiveDate = effectiveDate;
        this.reason = reason;
        this.status = status;
        this.label = label;
        this.source = source;
        this.destination = destination;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transfer transfer = (Transfer) o;
        return Objects.equals(amount, transfer.amount) && Objects.equals(createdAt, transfer.createdAt)&& Objects.equals(effectiveDate, transfer.effectiveDate)&& Objects.equals(reason, transfer.reason)&& Objects.equals(label, transfer.label) && Objects.equals(source, transfer.source) && Objects.equals(destination, transfer.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, createdAt,effectiveDate,reason,status,label, source, destination);
    }
}
