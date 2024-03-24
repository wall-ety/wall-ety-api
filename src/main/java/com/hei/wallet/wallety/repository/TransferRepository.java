package com.hei.wallet.wallety.repository;

import com.hei.wallet.wallety.fjpa.FJPARepository;
import com.hei.wallet.wallety.model.Transfer;
import org.springframework.stereotype.Repository;

import java.sql.Connection;

@Repository
public class TransferRepository extends FJPARepository<Transfer> {
    public TransferRepository(Connection connection) {
        super(connection);
    }
}
