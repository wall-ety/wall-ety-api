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
import java.util.Objects;
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

    public BalanceHistory() {
    }
    public BalanceHistory(String id,
                          BigDecimal amount,
                          Instant createdAt,
                          Account account
    ) {
        this.id = id;
        this.amount = amount;
        this.createdAt = createdAt;
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BalanceHistory balance = (BalanceHistory) o;
        return Objects.equals(id, balance.id) && Objects.equals(amount, balance.amount) && Objects.equals(createdAt, balance.createdAt) && Objects.equals(account, balance.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, createdAt, account);
    }
}
