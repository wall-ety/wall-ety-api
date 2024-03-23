package com.hei.wallet.wallety.endpoint.rest.mapper;

import com.hei.wallet.wallety.endpoint.rest.model.Account;
import com.hei.wallet.wallety.endpoint.rest.model.CreateAccount;
import com.hei.wallet.wallety.service.BalanceHistoryService;
import com.hei.wallet.wallety.service.BankService;
import com.hei.wallet.wallety.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class AccountMapper {

    private final BankService bankService;
    private final BalanceHistoryService balanceHistoryService;
    private final ClientService clientService;
    private final BalanceMapper balanceMapper;

    public Account toApi(com.hei.wallet.wallety.model.Account account){
        return Account
            .builder()
            .id(account.getId())
            .ref(account.getId())
            .name(account.getName())
            .createdAt(Instant.now())
            .updatedAt(Instant.now())
            .authorizeCredits(account.getAuthorizeCredits())
            .balance(balanceMapper.toApi(account.getBalance()))
            .client(account.getClient())
            .bank(account.getBank())
            .build();
    }
    public com.hei.wallet.wallety.model.Account toDomain(CreateAccount createAccount){
        return com.hei.wallet.wallety.model.Account
            .builder()
            .id(createAccount.getId())
            .ref(createAccount.getId())
            .name(createAccount.getName())
            .createdAt(Instant.now())
            .updatedAt(Instant.now())
            .authorizeCredits(createAccount.isAuthorizeCredits())
            .balance(balanceHistoryService.getCurrentAccountBalance(createAccount.getId()))
            .client(clientService.findById(createAccount.getIdClient()))
            .bank(bankService.findById(createAccount.getIdBank()))
            .build();
    }
}