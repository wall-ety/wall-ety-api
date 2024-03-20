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
    private Client client;

    @Column
    @Relation
    private Bank bank;

}
