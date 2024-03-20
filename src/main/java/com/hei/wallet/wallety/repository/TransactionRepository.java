package com.hei.wallet.wallety.repository;

import com.hei.wallet.wallety.fjpa.FJPARepository;
import com.hei.wallet.wallety.fjpa.StatementWrapper;
import com.hei.wallet.wallety.model.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepository extends FJPARepository<Transaction> {
    public TransactionRepository(StatementWrapper statementWrapper) {
        super(Transaction.class, statementWrapper);
    }
}
