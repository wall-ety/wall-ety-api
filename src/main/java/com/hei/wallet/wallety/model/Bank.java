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
import java.time.Instant;
import java.util.List;
import java.util.Objects;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(tableName = "Bank")
public class Bank implements Serializable {
    @Id
    @Column
    private String id;

    @Column
    private String name;

    @Column(columnName = "created_at")
    private Instant createdAt;

    @Column(columnName = "updated_at")
    private Instant updatedAt;

    @Column(columnName = "authorize_credits")
    private Boolean authorizeCredits;

    @Column(columnName = "first_week_loan")
    private Float firstWeekLoan;

    @Column(columnName = "subsequent_loan")
    private Float subsequentLoan;

    @Relation
    private List<Account> accounts;

    public Bank() {
    }

    public Bank (String id,
                        String name,
                        Instant createdAt,
                        Instant updatedAt,
                        Boolean authorizeCredits,
                        Float firstWeekLoan,
                        Float subsequentLoan,
                        List<Account> accounts
    ){
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.authorizeCredits = authorizeCredits;
        this.firstWeekLoan = firstWeekLoan;
        this.subsequentLoan = subsequentLoan;
        this.accounts = accounts;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getAuthorizeCredits() {
        return authorizeCredits;
    }
    public void setAuthorizeCredits(Boolean authorizeCredits) {
        this.authorizeCredits = authorizeCredits;
    }

    public Float getFirstWeekLoan() {
        return firstWeekLoan;
    }
    public void setFirstWeekLoan(Float firstWeekLoan) {
        this.firstWeekLoan = firstWeekLoan;
    }

    public Float getSubsequentLoan() {
        return subsequentLoan;
    }
    public void setSubsequentLoan(Float subsequentLoan) {
        this.subsequentLoan = subsequentLoan;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
    public void setTransactions(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(id, bank.id) && Objects.equals(name, bank.name) && Objects.equals(createdAt, bank.createdAt) && Objects.equals(updatedAt, bank.updatedAt) && Objects.equals(accounts, bank.accounts)
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdAt, updatedAt ,firstWeekLoan, subsequentLoan,accounts);
    }
}
