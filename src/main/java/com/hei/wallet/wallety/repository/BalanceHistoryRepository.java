package com.hei.wallet.wallety.repository;

import com.hei.wallet.wallety.exception.InternalServerErrorException;
import com.hei.wallet.wallety.fjpa.Attribute;
import com.hei.wallet.wallety.fjpa.FJPARepository;
import com.hei.wallet.wallety.fjpa.StatementWrapper;
import com.hei.wallet.wallety.model.Account;
import com.hei.wallet.wallety.model.BalanceHistory;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BalanceHistoryRepository extends FJPARepository<BalanceHistory> {
    public BalanceHistoryRepository(StatementWrapper statementWrapper){
        super(BalanceHistory.class, statementWrapper);
    }

    @Override
    public List<BalanceHistory> findAll() throws SQLException {
        return findAll("ORDER BY \"createdAt\" DESC", List.of());
    }

    public List<BalanceHistory> findByAccountId(String accountId) throws SQLException {
        return findByField("account", accountId);
    }

    public BalanceHistory getAccountCurrentBalance(String accountId) throws SQLException {
        List<BalanceHistory> histories = findByAccountId(accountId);
        return histories.isEmpty() ? null : histories.get(0);
    }

    @Override
    protected Object getAttributeValue(BalanceHistory balanceHistory, Attribute attribute) {
        if(attribute.getFieldName().equals("account")){
            return balanceHistory.getAccount().getId();
        }
        return super.getAttributeValue(balanceHistory, attribute);
    }

    @Override
    protected BalanceHistory mapResultSetToInstance(ResultSet resultSet, List<Class<?>> excludes) {
        BalanceHistory balanceHistory = super.mapResultSetToInstance(resultSet, excludes);

        if(!excludes.contains(Account.class)){
            AccountRepository accountRepository = new AccountRepository(statementWrapper);
            try{
                balanceHistory.setAccount(
                    accountRepository.findById(resultSet.getString("account"))
                );
            }catch (SQLException error){
                System.out.println(error.getMessage());
                throw new InternalServerErrorException();
            }
        }

        return balanceHistory;
    }
}