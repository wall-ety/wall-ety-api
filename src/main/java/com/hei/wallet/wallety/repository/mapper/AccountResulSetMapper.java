package com.hei.wallet.wallety.repository.mapper;

import com.hei.wallet.wallety.fjpa.ReflectEntity;
import com.hei.wallet.wallety.fjpa.ResultSetMapper;
import com.hei.wallet.wallety.model.Account;
import com.hei.wallet.wallety.repository.BalanceHistoryRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountResulSetMapper extends ResultSetMapper<Account> {
    protected final BalanceHistoryRepository balanceHistoryRepository;
    public AccountResulSetMapper(ReflectEntity<Account> reflectEntity, BalanceHistoryRepository balanceHistoryRepository) {
        super(reflectEntity);
        this.balanceHistoryRepository = balanceHistoryRepository;
    }

    @Override
    public Account mapResultSetToInstance(ResultSet resultSet, List<Class<?>> excludes, boolean isUpdate) {
        Account account = super.mapResultSetToInstance(resultSet, excludes, isUpdate);
        try {
            account.setBalance(balanceHistoryRepository.getAccountCurrentBalance(
                account.getId()
            ));
            return account;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}