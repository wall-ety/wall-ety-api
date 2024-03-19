package com.hei.wallet.wallety.model;

import com.hei.wallet.wallety.fjpa.annotation.Column;
import com.hei.wallet.wallety.fjpa.annotation.Entity;
import com.hei.wallet.wallety.fjpa.annotation.Id;
import com.hei.wallet.wallety.fjpa.annotation.Relation;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(tableName = "Client")
public class Client implements Serializable {
    @Id
    @Column
    private  String id;

    @Column(columnName = "last_name")
    private String lastName;

    @Column(columnName = "first_name")
    private String firstName;

    @Column
    private Date birthday;

    @Column(columnName = "month_salary")
    private BigDecimal monthSalary;

    @Column(columnName = "created_at")
    private Instant createdAt;

    @Column(columnName = "updated_at")
    private Instant updatedAt;

    @Relation
    private List<Account> accounts;

    public Client(){
    }

    public Client(String id,
                  String lastName,
                  String firstName,
                  Date birthday,
                  BigDecimal monthSalary,
                  Instant createdAt,
                  Instant updatedAt,
                  List<Account> accounts
    ){
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.monthSalary = monthSalary;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.accounts = accounts;

    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }
    public void stLastName() {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName() {
        this.firstName = firstName;
    }

    public Date getBirthdate() {
        return  birthday;
    }
    public void setBirthdate() {
        this.birthday = birthday;
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
        Client client = (Client) o;
        return Objects.equals(id , client.id) && Objects.equals(lastName , client.lastName) && Objects.equals(firstName , client.firstName) && Objects.equals(monthSalary, client.monthSalary) &&Objects.equals(createdAt, client.createdAt) && Objects.equals(updatedAt, client.updatedAt) && Objects.equals(accounts, client.accounts)
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, birthday, monthSalary, createdAt, updatedAt, accounts)
    }

    @ToString
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", monthSalary='" + monthSalary + '\'' +
                ", createdAt=" + createdAt + '\'' +
                ", updatedAt=" + updatedAt + '\'' +
                ", accounts=" + accounts + '\'' +
                '}';
    }
}
