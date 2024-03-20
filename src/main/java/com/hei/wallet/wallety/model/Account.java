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
import java.util.Objects;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(tableName = "Account")
public class Account implements Serializable {
    @Id
    @Column
    private String id;

    @Column
    private String ref;

    @Column
    private String name;

    @Column(columnName = "authorize_credits")
    private Boolean authorizeCredits;
    @Column(columnName = "created_at")
    private Instant createdAt;

    @Column(columnName = "updated_at")
    private Instant updatedAt;

    @Column
    @Relation
    private Client client;

    @Column
    @Relation
    private Bank bank;

    public Account(){
    }
    public Account(String id,
                   String ref,
                   String name,
                   Boolean authorizeCredits,
                   Instant createdAt,
                   Instant updatedAt,
                   Client client,
                   Bank bank
    ){
        this.id = id;
        this.ref = ref;
        this.name = name;
        this.authorizeCredits = authorizeCredits;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.bank = bank;
        this.client =client;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }
    public void setRef(String ref) {
        this.ref = ref;
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

    public Client getClient() {
        return client;
    }
    public void setClient(Client client){
        this.client = client;
    }

    public Bank getBank() {
        return bank;
    }
    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(ref, account.ref) && Objects.equals(name, account.name)  && Objects.equals(createdAt, account.createdAt) && Objects.equals(updatedAt, account.updatedAt) && Objects.equals(client, account.client) && Objects.equals(bank, account.bank)
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, ref, name, createdAt, updatedAt ,authorizeCredits, client, bank);
    }
}
