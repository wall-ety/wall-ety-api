package com.hei.wallet.wallety.endpoint.rest.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreateTransfer implements Serializable {
    private BigDecimal amount;
    private Instant created_at;
    private Instant effective_date;
    private String reason;
    private String status;
    private String label;

}
