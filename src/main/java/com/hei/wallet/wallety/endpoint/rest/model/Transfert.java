package com.hei.wallet.wallety.endpoint.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transfert {
    private BigDecimal amount;
    private Instant created_at;
    private Instant effective_date;
    private String reason;
    private String status;
    private String label;
}
