package com.hei.wallet.wallety.repository;

import com.hei.wallet.wallety.exception.InternalServerErrorException;
import com.hei.wallet.wallety.fjpa.Attribute;
import com.hei.wallet.wallety.fjpa.FJPARepository;
import com.hei.wallet.wallety.fjpa.StatementWrapper;
import com.hei.wallet.wallety.model.Account;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AccountRepository extends FJPARepository<Account> {
    public AccountRepository(StatementWrapper statementWrapper) {
        super(Account.class, statementWrapper);
    }

    @Override
    protected Object getAttributeValue(Account account, Attribute attribute) {
        return switch (attribute.getFieldName()) {
            case "client" -> account.getClient().getId();
            case "bank" -> account.getBank().getId();
            case "balance" -> account.getBalance().getId();
            default -> super.getAttributeValue(account, attribute);
        };
    }

    @Override
    protected Account mapResultSetToInstance(ResultSet resultSet, List<Class<?>> excludes) {
        Account account = super.mapResultSetToInstance(resultSet, excludes);
        BankRepository bankRepository = new BankRepository(statementWrapper);
        ClientRepository clientRepository = new ClientRepository(statementWrapper);

        try{
            account.setClient(
                clientRepository.findById(resultSet.getString("client"))
            );

            account.setBank(
                bankRepository.findById(resultSet.getString("bank"))
            );

            if(!excludes.contains(BalanceHistoryRepository.class)){
                BalanceHistoryRepository balanceHistoryRepository = new BalanceHistoryRepository(statementWrapper);
                account.setBalance(balanceHistoryRepository.getAccountCurrentBalance(account.getId()));
            }

            return account;
        }catch (SQLException error){
            System.out.println(error.getMessage());
            throw new InternalServerErrorException();
        }
    }
}
