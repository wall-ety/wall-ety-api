package com.hei.wallet.wallety.controller;

import com.hei.wallet.wallety.endpoint.rest.controller.BankController;
import com.hei.wallet.wallety.exception.NotFoundException;
import com.hei.wallet.wallety.model.Bank;
import com.hei.wallet.wallety.repository.BankRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@SpringBootTest
public class BankControllerIT {
    private static final int MOCK_INSERT=5;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private BankController bankController;

    public static Bank generateBank(String id){
        return Bank
                .builder()
                .id(id)
                .name("name" + id)
                .authorizeCredits(false)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .firstWeekLoan(0.1f)
                .subsequentLoan(0.3f)
                .build();
    }

    @BeforeEach
    public void cleanAndAddCategories() throws SQLException {
        bankRepository.deleteAll();
        for (int i = 0; i < MOCK_INSERT; i++) {
            bankRepository.saveOrUpdate(generateBank(String.valueOf(i)));
        }
    }

    @AfterEach
    public void cleanCategories() throws SQLException {
        bankRepository.deleteAll();
    }

    @Test
    public void getAll(){
        Assertions.assertDoesNotThrow(() ->bankController.getAll());
        List<Bank> banks = bankController.getAll();
        Assertions.assertEquals(banks.size(), MOCK_INSERT);
        Assertions.assertTrue(banks.stream().anyMatch(bank -> bank.getId().equals("2")));
    }

    @Test
    public void getById(){
        Assertions.assertThrows(NotFoundException.class, () -> bankController.getById("NOT_FOUND_ID"));
        Bank banks = bankController.getById("1");
        Assertions.assertEquals(banks.getId(), "1");
    }

    @Test
    public void crupdateBank(){
        Bank toSave = generateBank("NEW_CLIENT");

        List<Bank> createLists = bankController.saveOrUpdateAll(
                List.of(toSave)
        );

        Assertions.assertEquals(createLists.size(), 1);
        Assertions.assertEquals(bankController.getAll().size(), MOCK_INSERT + 1);
        Assertions.assertEquals(createLists.get(0).getId(), toSave.getId());
        Assertions.assertEquals(createLists.get(0).getName(), toSave.getName());
        Assertions.assertEquals(createLists.get(0).getAuthorizeCredits(), toSave.getAuthorizeCredits());
        Assertions.assertEquals(createLists.get(0).getFirstWeekLoan(), toSave.getFirstWeekLoan());
        Assertions.assertEquals(createLists.get(0).getSubsequentLoan(), toSave.getSubsequentLoan());

        toSave.setAuthorizeCredits(true);
        List<Bank> updatedLists = bankController.saveOrUpdateAll(
            List.of(toSave)
        );

        Assertions.assertEquals(updatedLists.size(), 1);
        Assertions.assertEquals(bankController.getAll().size(), MOCK_INSERT + 1);
        Assertions.assertEquals(updatedLists.get(0).getId(), toSave.getId());
        Assertions.assertEquals(updatedLists.get(0).getName(), toSave.getName());
    }
}
