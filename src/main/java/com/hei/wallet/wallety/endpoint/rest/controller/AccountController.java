package com.hei.wallet.wallety.endpoint.rest.controller;

import com.hei.wallet.wallety.endpoint.rest.mapper.AccountMapper;
import com.hei.wallet.wallety.endpoint.rest.model.Account;
import com.hei.wallet.wallety.endpoint.rest.model.CreateAccount;
import com.hei.wallet.wallety.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountMapper accountMapper;
    private final AccountService accountService;

    @GetMapping("/accounts")
    public List<Account> getAll(){
        return accountService
                .findAll()
                .stream()
                .map(accountMapper::toApi)
                .toList();
    }

    @GetMapping("/accounts/{accountId}")
    public Account getById(@PathVariable String accountId){
        return accountMapper.toApi(accountService.findById(accountId));
    }

    @PutMapping("/accounts")
    public List<Account> createAccount(@RequestBody List<CreateAccount> accounts){
        return accountService.saveOrUpdateAll(
            accounts.stream().map(accountMapper::toDomain).toList()
        ).stream().map(accountMapper::toApi).toList();
    }
}
