package com.hei.wallet.wallety.repository;

import com.hei.wallet.wallety.fjpa.FJPARepository;
import com.hei.wallet.wallety.fjpa.StatementWrapper;
import com.hei.wallet.wallety.model.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository extends FJPARepository<Account> {
    public AccountRepository(StatementWrapper statementWrapper) {
        super(Account.class, statementWrapper);
    }
}
