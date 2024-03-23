package com.hei.wallet.wallety.endpoint.rest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class CreateAccount {
    private String id;
    private String name;
    private Instant createdAt;
    private Instant updateAt;
    private boolean authorizeCredits;
    private String idClient;
    private String idBank;
}
