package com.hei.wallet.wallety.endpoint.rest.model;

import com.hei.wallet.wallety.model.Bank;
import com.hei.wallet.wallety.model.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.Instant;

@Builder
@AllArgsConstructor
public class Account {
    private String id;
    private String ref;
    private String name;
    private Boolean authorizeCredits;
    private Instant createdAt;
    private Instant updatedAt;
    private Client client;
    private Bank bank;
    private Balance balance;
}