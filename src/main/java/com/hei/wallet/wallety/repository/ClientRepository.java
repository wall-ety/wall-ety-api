package com.hei.wallet.wallety.repository;

import com.hei.wallet.wallety.fjpa.FJPARepository;
import com.hei.wallet.wallety.model.Client;
import org.springframework.stereotype.Repository;

import java.sql.Connection;

@Repository
public class ClientRepository extends FJPARepository<Client> {
    public ClientRepository(Connection connection) {
        super(connection);
    }
}
