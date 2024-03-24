package com.hei.wallet.wallety.repository;

import com.hei.wallet.wallety.fjpa.FJPARepository;
import com.hei.wallet.wallety.model.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TransactionRepository extends FJPARepository<Transaction> {
    public TransactionRepository(Connection connection) {
        super(connection);
    }

    @Override
    public List<Transaction> findByField(String fieldName, Object fieldValue, List<Class<?>> excludes) throws SQLException {
        return super.findByField(fieldName, fieldValue, excludes, " ORDER BY @transactionDatetime DESC ");
    }
}