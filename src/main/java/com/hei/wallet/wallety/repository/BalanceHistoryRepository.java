package com.hei.wallet.wallety.repository;

import com.hei.wallet.wallety.fjpa.FJPARepository;
import com.hei.wallet.wallety.model.Account;
import com.hei.wallet.wallety.model.BalanceHistory;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@Repository
public class BalanceHistoryRepository extends FJPARepository<BalanceHistory> {
    private final static String DEFAULT_ID="DEFAULT_ID";

    public BalanceHistoryRepository(Connection connection) {
        super(connection);
    }

    @Override
    public List<BalanceHistory> findByField(String fieldName, Object fieldValue, List<Class<?>> excludes) throws SQLException {
        return super.findByField(fieldName, fieldValue, excludes, " ORDER BY @createdAt DESC ");
    }

    public List<BalanceHistory> findByAccountId(String accountId) throws SQLException {
        return findByField("@account", accountId);
    }

    public BalanceHistory getAccountCurrentBalance(String accountId) throws SQLException {
        List<BalanceHistory> histories = findByField("@account", accountId, List.of(Account.class));
        return histories.isEmpty() ? getDefaultBalance() : histories.get(0);
    }

    public static BalanceHistory getDefaultBalance(){
        return new BalanceHistory(DEFAULT_ID, BigDecimal.ZERO, Instant.now(), null);
    }
}