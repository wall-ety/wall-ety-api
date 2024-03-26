package com.hei.wallet.wallety.controller;

import com.hei.wallet.wallety.endpoint.rest.controller.AccountController;
import com.hei.wallet.wallety.exception.BadRequestException;
import com.hei.wallet.wallety.exception.NotFoundException;
import com.hei.wallet.wallety.model.Account;
import com.hei.wallet.wallety.repository.AccountRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@SpringBootTest
public class AccountControllerIT {
    private static final int MOCK_INSERT = 5;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountController accountController;

    public static Account generateAccount(String id) {
        return Account
                .builder()
                .id(id)
                .name("Account" + id)
                .authorizeCredits(false)
                .updatedAt(Instant.now())
                .createdAt(Instant.now())
                .balance(BigDecimal.valueOf(1000))
                .build();
    }

    @BeforeEach
    public void cleanAndAddAccounts() {
        accountRepository.deleteAll();
        for (int i = 0; i < MOCK_INSERT; i++) {
            accountRepository.saveOrUpdate(generateAccount(String.valueOf(i)));
        }
    }

    @AfterEach
    public void cleanAccounts() {
        accountRepository.deleteAll();
    }

    @Test
    public void getAll() {
        Assertions.assertDoesNotThrow(() -> accountController.getAll());
        List<Account> accounts = accountController.getAll();
        Assertions.assertEquals(accounts.size(), MOCK_INSERT);
        Assertions.assertTrue(accounts.stream().anyMatch(account -> account.getId().equals("2")));
    }

    @Test
    public void getById() {
        Assertions.assertThrows(NotFoundException.class, () -> accountController.getById("NOT_FOUND_ID"));
        Account account = accountController.getById("1");
        Assertions.assertEquals(account.getId(), "1");
    }

    @Test
    public void crupdateAccount() {
        Account toSave = generateAccount("NEW_ACCOUNT");

        List<Account> createLists = accountController.createAccount(List.of(toSave));

        Assertions.assertEquals(createLists.size(), 1);
        Assertions.assertEquals(accountController.getAll().size(), MOCK_INSERT + 1);
        Assertions.assertEquals(createLists.get(0).getId(), toSave.getId());
        Assertions.assertEquals(createLists.get(0).getName(), toSave.getName());
        Assertions.assertEquals(createLists.get(0).getBalance(), toSave.getBalance());

        toSave.setBalance(BigDecimal.valueOf(1500));
        List<Account> updatedLists = accountController.createAccount(List.of(toSave));

        Assertions.assertEquals(updatedLists.size(), 1);
        Assertions.assertEquals(accountController.getAll().size(), MOCK_INSERT + 1);
        Assertions.assertEquals(updatedLists.get(0).getId(), toSave.getId());
        Assertions.assertEquals(updatedLists.get(0).getName(), toSave.getName());
        Assertions.assertEquals(updatedLists.get(0).getBalance(), toSave.getBalance());
    }

}
