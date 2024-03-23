package com.hei.wallet.wallety.endpoint.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Balance {
    private String id;
    private BigDecimal amount;
    private Instant createdAt;
}