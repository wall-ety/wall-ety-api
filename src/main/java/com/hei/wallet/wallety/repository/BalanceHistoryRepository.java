package com.hei.wallet.wallety.repository;

import com.hei.wallet.wallety.endpoint.rest.model.Balance;
import com.hei.wallet.wallety.exception.InternalServerErrorException;
import com.hei.wallet.wallety.fjpa.Attribute;
import com.hei.wallet.wallety.fjpa.FJPARepository;
import com.hei.wallet.wallety.fjpa.StatementWrapper;
import com.hei.wallet.wallety.model.Account;
import com.hei.wallet.wallety.model.BalanceHistory;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@Repository
public class BalanceHistoryRepository extends FJPARepository<BalanceHistory> {
    private final static String DEFAULT_ID="DEFAULT_ID";

    public BalanceHistoryRepository(StatementWrapper statementWrapper){
        super(BalanceHistory.class, statementWrapper);
    }

    @Override
    public List<BalanceHistory> findByField(String fieldName, Object fieldValue, List<Class<?>> excludes) throws SQLException {
        return super.findByField(fieldName, fieldValue, excludes, " ORDER BY \"created_at\" DESC ");
    }

    public List<BalanceHistory> findByAccountId(String accountId) throws SQLException {
        return findByField("account", accountId);
    }

    public BalanceHistory getAccountCurrentBalance(String accountId) throws SQLException {
        List<BalanceHistory> histories = findByField("account", accountId, List.of(Account.class));
        return histories.isEmpty() ? getDefaultBalance() : histories.get(0);
    }

    public static BalanceHistory getDefaultBalance(){
        return new BalanceHistory(DEFAULT_ID, BigDecimal.ZERO, Instant.now(), null);
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
                    accountRepository.findById(
                        resultSet.getString("account"),
                        List.of(Balance.class)
                    )
                );
            }catch (SQLException error){
                System.out.println(error.getMessage());
                throw new InternalServerErrorException();
            }
        }

        return balanceHistory;
    }
}