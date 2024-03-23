package com.hei.wallet.wallety.endpoint.rest.mapper;

import com.hei.wallet.wallety.endpoint.rest.model.Account;
import com.hei.wallet.wallety.endpoint.rest.model.CreateAccount;
import com.hei.wallet.wallety.service.BalanceHistoryService;
import com.hei.wallet.wallety.service.BankService;
import com.hei.wallet.wallety.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
            .createdAt(account.getCreatedAt())
            .updatedAt(account.getUpdatedAt())
            .authorizeCredits(account.getAuthorizeCredits())
            .balance(account.getBalance() == null ? null : balanceMapper.toApi(account.getBalance()))
            .client(account.getClient())
            .bank(account.getBank())
            .build();
    }
    public com.hei.wallet.wallety.model.Account createToDomain(CreateAccount createAccount){
        return com.hei.wallet.wallety.model.Account
            .builder()
            .id(createAccount.getId())
            .ref(createAccount.getId())
            .name(createAccount.getName())
            .createdAt(createAccount.getCreatedAt())
            .updatedAt(createAccount.getUpdatedAt())
            .authorizeCredits(createAccount.isAuthorizeCredits())
            .balance(balanceHistoryService.getCurrentAccountBalance(createAccount.getId()))
            .client(clientService.findById(createAccount.getIdClient()))
            .bank(bankService.findById(createAccount.getIdBank()))
            .build();
    }
}