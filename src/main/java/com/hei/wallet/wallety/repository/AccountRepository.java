package com.hei.wallet.wallety.repository;

import com.hei.wallet.wallety.fjpa.FJPARepository;
import com.hei.wallet.wallety.model.Account;
import org.springframework.stereotype.Repository;

import java.sql.Connection;

@Repository
public class AccountRepository extends FJPARepository<Account> {
    public AccountRepository(Connection connection) {
        super(connection);
    }

}