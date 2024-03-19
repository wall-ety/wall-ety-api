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
@Entity(tableName = "Transaction")
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

    public Transaction(
            String id,
            String label,
            BigDecimal amount,
            Instant transactionDatetime,
            TransactionType type,
            Category category,
            Account account
    ) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.label = label;
        this.transactionDatetime = transactionDatetime;
        this.category = category;
        this.account = account;
    }
    public Transaction() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Instant getTransactionDatetime() {
        return transactionDatetime;
    }

    public void setTransactionDatetime(Instant transactionDatetime) {
        this.transactionDatetime = transactionDatetime;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(label, that.label) && Objects.equals(amount, that.amount) && Objects.equals(transactionDatetime, that.transactionDatetime) && type == that.type && Objects.equals(category, that.category) && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, amount, transactionDatetime, type, category, account);
    }

}
