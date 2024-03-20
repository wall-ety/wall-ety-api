package com.hei.wallet.wallety.repository;

import com.hei.wallet.wallety.fjpa.FJPARepository;
import com.hei.wallet.wallety.fjpa.StatementWrapper;
import com.hei.wallet.wallety.model.Transfer;
import org.springframework.stereotype.Repository;

@Repository
public class TransferRepository extends FJPARepository<Transfer> {
    public TransferRepository(StatementWrapper statementWrapper) {
        super(Transfer.class, statementWrapper);
    }
}
