package com.hei.wallet.wallety.endpoint.rest.model;

import com.hei.wallet.wallety.model.Bank;
import com.hei.wallet.wallety.model.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account implements Serializable {
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