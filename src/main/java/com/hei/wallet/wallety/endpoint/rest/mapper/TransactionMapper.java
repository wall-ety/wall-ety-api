package com.hei.wallet.wallety.endpoint.rest.mapper;

import com.hei.wallet.wallety.endpoint.rest.model.CreateTransaction;
import com.hei.wallet.wallety.endpoint.rest.model.Transaction;
import com.hei.wallet.wallety.service.AccountService;
import com.hei.wallet.wallety.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionMapper {
    private final AccountService accountService;
    private final CategoryService categoryService;
    private final AccountMapper accountMapper;

    public Transaction toApi(com.hei.wallet.wallety.model.Transaction transaction){
        return Transaction
            .builder()
            .id(transaction.getId())
            .label(transaction.getLabel())
            .amount(transaction.getAmount())
            .type(transaction.getType())
            .reason(transaction.getReason())
            .category(transaction.getCategory())
            .transactionDatetime(transaction.getTransactionDatetime())
            .account(accountMapper.toApi(accountService.findById(transaction.getAccount().getId())))
            .build();
    }

    public com.hei.wallet.wallety.model.Transaction createTransactionToDomain(CreateTransaction transaction){
        return com.hei.wallet.wallety.model.Transaction
                .builder()
                .id(transaction.getId())
                .label(transaction.getLabel())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .reason(transaction.getReason())
                .transactionDatetime(transaction.getTransactionDatetime())
                .category(categoryService.findById(transaction.getIdCategory()))
                .account(accountService.findById(transaction.getIdAccount()))
                .build();
    }
}
