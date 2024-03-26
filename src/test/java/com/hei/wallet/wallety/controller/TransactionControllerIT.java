package com.hei.wallet.wallety.controller;

import com.hei.wallet.wallety.endpoint.rest.controller.TransactionController;
import com.hei.wallet.wallety.model.Transaction;
import com.hei.wallet.wallety.repository.TransactionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@SpringBootTest
public class TransactionControllerIT {
    private static final int MOCK_INSERT=5;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionController transactionController;

    public static Transaction generateTransaction(String id){
        return Transaction
                .builder()
                .id(id)
                .reason("reason")
                .label("text")
                .amount(BigDecimal.valueOf(4000))
                .transactionDatetime(Instant.now())
                .category()
                .account()
                .build();
    }
    @BeforeEach
    public void cleanAndAddCategories() throws SQLException {
        TransactionRepository.deleteAll();
        for (int i = 0; i < MOCK_INSERT; i++) {
            transactionRepository.saveOrUpdate(generateTransaction(String.valueOf(i)));
        }
    }

    @AfterEach
    public void cleanCategories() throws SQLException {
        transactionRepository.deleteAll();
    }

    @Test
    public void getById(){
        Assertions.assertDoesNotThrow(() ->transactionController.getAllByAccountId("NOT FOUND"));
        List<Transaction> transactions = transactionController.getAllByAccountId("1");
        Assertions.assertEquals(transactions.size(), MOCK_INSERT);
        Assertions.assertTrue(transactions.stream().anyMatch(transaction -> transaction.getId().equals("2")));
    }
}
