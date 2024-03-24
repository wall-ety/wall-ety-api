package com.hei.wallet.wallety.repository;

import com.hei.wallet.wallety.fjpa.FJPARepository;
import com.hei.wallet.wallety.model.Account;
import com.hei.wallet.wallety.repository.mapper.AccountResulSetMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;

@Repository
public class AccountRepository extends FJPARepository<Account> {
    public AccountRepository(Connection connection, BalanceHistoryRepository balanceHistoryRepository) {
        super(connection);
        setResultSetMapper(
            new AccountResulSetMapper(this.getReflectEntity(), balanceHistoryRepository)
        );
    }
}