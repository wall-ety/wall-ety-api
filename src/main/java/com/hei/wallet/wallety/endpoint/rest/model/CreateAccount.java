package com.hei.wallet.wallety.endpoint.rest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@NoArgsConstructor
@Data
public class CreateAccount implements Serializable {
    private String id;
    private String name;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean authorizeCredits;
    private String idClient;
    private String idBank;
}
