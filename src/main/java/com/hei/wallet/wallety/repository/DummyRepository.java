package com.hei.wallet.wallety.repository;

import com.hei.wallet.wallety.fjpa.FJPARepository;
import com.hei.wallet.wallety.fjpa.StatementWrapper;
import com.hei.wallet.wallety.model.Dummy;
import org.springframework.stereotype.Repository;

@Repository
public class DummyRepository extends FJPARepository<Dummy> {
    public DummyRepository(StatementWrapper statementWrapper) {
        super(Dummy.class, statementWrapper);
    }
}
