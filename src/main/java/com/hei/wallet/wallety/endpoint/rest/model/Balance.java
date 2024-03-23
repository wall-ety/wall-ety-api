package com.hei.wallet.wallety.endpoint.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Balance implements Serializable {
    private String id;
    private BigDecimal amount;
    private Instant createdAt;
}