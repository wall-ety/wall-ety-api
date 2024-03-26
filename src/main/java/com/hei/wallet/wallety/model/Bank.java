package com.hei.wallet.wallety.model;

import com.hei.wallet.wallety.fjpa.annotation.Column;
import com.hei.wallet.wallety.fjpa.annotation.Entity;
import com.hei.wallet.wallety.fjpa.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(tableName = "bank")
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
    private boolean authorizeCredits;

    @Column(columnName = "first_week_loan")
    private float firstWeekLoan;

    @Column(columnName = "subsequent_loan")
    private float subsequentLoan;

    public boolean getAuthorizeCredits() {
        return authorizeCredits;
    }
}
