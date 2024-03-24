package com.hei.wallet.wallety.controller;

import com.hei.wallet.wallety.endpoint.rest.controller.ClientController;
import com.hei.wallet.wallety.exception.BadRequestException;
import com.hei.wallet.wallety.exception.NotFoundException;
import com.hei.wallet.wallety.model.Client;
import com.hei.wallet.wallety.repository.ClientRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class ClientControllerIT {
    private static final int MOCK_INSERT=5;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientController clientController;

    public static Client generateClient(String id){
        return Client
                .builder()
                .id(id)
                .firstName("John" + id)
                .lastName("Doe" + id)
                .createdAt(Instant.now())
                .monthSalary(BigDecimal.valueOf(500))
                .updatedAt(Instant.now())
                .birthdate(LocalDate.of(2000, 1, 1))
                .build();
    }

    @BeforeEach
    public void cleanAndAddCategories() throws SQLException {
        clientRepository.deleteAll();
        for (int i = 0; i < MOCK_INSERT; i++) {
            clientRepository.saveOrUpdate(generateClient(String.valueOf(i)));
        }
    }

    @AfterEach
    public void cleanCategories() throws SQLException {
        clientRepository.deleteAll();
    }

    @Test
    public void getAll(){
        Assertions.assertDoesNotThrow(() ->clientController.getAll());
        List<Client> clients = clientController.getAll();
        Assertions.assertEquals(clients.size(), MOCK_INSERT);
        Assertions.assertTrue(clients.stream().anyMatch(client -> client.getId().equals("2")));
    }

    @Test
    public void getById(){
        Assertions.assertThrows(NotFoundException.class, () -> clientController.getById("NOT_FOUND_ID"));
        Client clients = clientController.getById("1");
        Assertions.assertEquals(clients.getId(), "1");
    }

    @Test
    public void tooYoungValidator(){
        final String newClientId = "NEW_CLIENT";
        LocalDate updatedBirthdate = LocalDate.of(2000, 1, 1);
        Client client = generateClient(newClientId);
        client.setBirthdate(LocalDate.now());

        Assertions.assertThrows(BadRequestException.class, ()->clientController.saveOrUpdateAll(List.of(client)));
        Assertions.assertThrows(NotFoundException.class, ()-> clientController.getById(newClientId));

        client.setBirthdate(updatedBirthdate);
        Assertions.assertDoesNotThrow(()->clientController.saveOrUpdateAll(List.of(client)));

        Client updatedClient = clientController.getById(newClientId);
        Assertions.assertEquals(updatedClient.getBirthdate(), updatedBirthdate);
    }

    @Test
    public void crupdateClient(){
        Client toSave = generateClient("NEW_CLIENT");

        List<Client> createLists = clientController.saveOrUpdateAll(
            List.of(toSave)
        );

        Assertions.assertEquals(createLists.size(), 1);
        Assertions.assertEquals(clientController.getAll().size(), MOCK_INSERT + 1);
        Assertions.assertEquals(createLists.get(0).getId(), toSave.getId());
        Assertions.assertEquals(createLists.get(0).getFirstName(), toSave.getFirstName());
        Assertions.assertEquals(createLists.get(0).getLastName(), toSave.getLastName());
        Assertions.assertEquals(createLists.get(0).getMonthSalary(), toSave.getMonthSalary());

        toSave.setMonthSalary(BigDecimal.valueOf(800));
        List<Client> updatedLists = clientController.saveOrUpdateAll(
                List.of(toSave)
        );

        Assertions.assertEquals(updatedLists.size(), 1);
        Assertions.assertEquals(clientController.getAll().size(), MOCK_INSERT + 1);
        Assertions.assertEquals(updatedLists.get(0).getId(), toSave.getId());
        Assertions.assertEquals(updatedLists.get(0).getFirstName(), toSave.getFirstName());
        Assertions.assertEquals(updatedLists.get(0).getLastName(), toSave.getLastName());
        Assertions.assertEquals(updatedLists.get(0).getMonthSalary(), toSave.getMonthSalary());
    }
}