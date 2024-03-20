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
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(tableName = "Category")
public class Category implements Serializable {
    @Id
    @Column
    private String id;

    @Column
    private String name;

    @Column
    private CategoryType type;

    @Column(columnName = "created_at")
    private Instant createdAt;

    @Column(columnName = "updated_at")
    private Instant updatedAt;

    @Relation
    private List<Transaction> transactions;

    public Category() {
    }

    public Category(String id,
                    String name,
                    CategoryType type,
                    Instant createdAt,
                    Instant updatedAt,
                    List<Transaction> transactions
    ) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.transactions = transactions;
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


    public CategoryType getType() {

        return type;
    }
    public void setType(CategoryType type) {

        this.type = type;
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


    public List<Transaction> getTransactions() {

        return transactions;
    }
    public void setTransactions(List<Transaction> transactions) {

        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(name, category.name) && type == category.type && Objects.equals(createdAt, category.createdAt) && Objects.equals(updatedAt, category.updatedAt) && Objects.equals(transactions, category.transactions)
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, createdAt, updatedAt ,transactions);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", createdAt=" + createdAt + '\'' +
                ", updatedAt=" + updatedAt + '\'' +
                ", transactions=" + transactions + '\'' +
                '}';
    }
}
