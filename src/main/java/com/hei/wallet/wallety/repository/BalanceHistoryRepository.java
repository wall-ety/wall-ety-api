package com.hei.wallet.wallety.repository;

import com.hei.wallet.wallety.fjpa.FJPARepository;
import com.hei.wallet.wallety.fjpa.StatementWrapper;
import com.hei.wallet.wallety.model.BalanceHistory;
import org.springframework.stereotype.Repository;

@Repository
public class BalanceHistoryRepository extends FJPARepository<BalanceHistory> {
    public BalanceHistoryRepository(StatementWrapper statementWrapper){
        super(BalanceHistory.class, statementWrapper);
    }
}
