package com.hei.wallet.wallety.model;

import com.hei.wallet.wallety.fjpa.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(tableName = "account")
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
    @ValueGetter
    private Client client;

    @Column
    @Relation
    @ValueGetter
    private Bank bank;

    // TODO: update fjpa to support non column fields:
    @Relation
    private BalanceHistory balance;
}