package com.hei.wallet.wallety.repository;

import com.hei.wallet.wallety.fjpa.FJPARepository;
import com.hei.wallet.wallety.fjpa.StatementWrapper;
import com.hei.wallet.wallety.model.Client;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository extends FJPARepository<Client> {
    public ClientRepository(StatementWrapper statementWrapper) {
        super(Client.class, statementWrapper);
    }
}
