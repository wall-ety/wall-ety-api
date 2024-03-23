package com.hei.wallet.wallety.repository;

import com.hei.wallet.wallety.fjpa.FJPARepository;
import com.hei.wallet.wallety.model.Bank;
import org.springframework.stereotype.Repository;

import java.sql.Connection;

@Repository
public class BankRepository extends FJPARepository<Bank> {
    public BankRepository(Connection connection) {
        super(connection);
    }
}