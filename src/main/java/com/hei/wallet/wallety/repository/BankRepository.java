package com.hei.wallet.wallety.repository;

import com.hei.wallet.wallety.fjpa.FJPARepository;
import com.hei.wallet.wallety.fjpa.StatementWrapper;
import com.hei.wallet.wallety.model.Bank;
import org.springframework.stereotype.Repository;

@Repository
public class BankRepository extends FJPARepository<Bank> {
    public BankRepository(StatementWrapper statementWrapper) {
        super(Bank.class , statementWrapper);
    }
}
