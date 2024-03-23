package com.hei.wallet.wallety.endpoint.rest.controller;

import com.hei.wallet.wallety.endpoint.rest.mapper.TransactionMapper;
import com.hei.wallet.wallety.endpoint.rest.model.CreateTransaction;
import com.hei.wallet.wallety.endpoint.rest.model.Transaction;
import com.hei.wallet.wallety.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @GetMapping("/accounts/{idAccount}/transactions")
    public List<Transaction> getAllByAccountId(@PathVariable String idAccount){
        return transactionService
                .findByAccountId(idAccount)
                .stream()
                .map(transactionMapper::toApi)
                .toList();
    }

    @PostMapping("/transactions")
    public List<Transaction> createTransactions(@RequestBody List<CreateTransaction> transactions){
        return transactionService
                .doTransactions(
                        transactions.stream().map(transactionMapper::createTransactionToDomain).toList()
                )
                .stream()
                .map(transactionMapper::toApi)
                .toList();
    }
}
