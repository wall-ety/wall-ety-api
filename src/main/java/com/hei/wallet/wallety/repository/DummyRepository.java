package com.hei.wallet.wallety.repository;

import com.hei.wallet.wallety.fjpa.FJPARepository;
import com.hei.wallet.wallety.model.Dummy;
import org.springframework.stereotype.Repository;

import java.sql.Connection;

@Repository
public class DummyRepository extends FJPARepository<Dummy> {
    public DummyRepository(Connection connection) {
        super(connection);
    }
}
