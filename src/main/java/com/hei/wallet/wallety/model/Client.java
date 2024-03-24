package com.hei.wallet.wallety.model;

import com.hei.wallet.wallety.fjpa.annotation.Column;
import com.hei.wallet.wallety.fjpa.annotation.Entity;
import com.hei.wallet.wallety.fjpa.annotation.Id;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(tableName = "client")
public class Client implements Serializable {
    @Id
    @Column
    private  String id;

    @Column(columnName = "last_name")
    private String lastName;

    @Column(columnName = "first_name")
    private String firstName;

    @Column
    private LocalDate birthdate;

    @Column(columnName = "month_salary")
    private BigDecimal monthSalary;

    @Column(columnName = "created_at")
    private Instant createdAt;

    @Column(columnName = "updated_at")
    private Instant updatedAt;
}
