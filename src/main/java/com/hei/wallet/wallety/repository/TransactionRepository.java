package com.hei.wallet.wallety.repository;

import com.hei.wallet.wallety.exception.InternalServerErrorException;
import com.hei.wallet.wallety.fjpa.Attribute;
import com.hei.wallet.wallety.fjpa.FJPARepository;
import com.hei.wallet.wallety.fjpa.StatementWrapper;
import com.hei.wallet.wallety.model.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TransactionRepository extends FJPARepository<Transaction> {
    public TransactionRepository(StatementWrapper statementWrapper) {
        super(Transaction.class, statementWrapper);
    }

    @Override
    protected Object getAttributeValue(Transaction transaction, Attribute attribute) {
        return switch (attribute.getFieldName()){
            case "category" -> transaction.getCategory().getId();
            case "account" -> transaction.getAccount().getId();
            default -> super.getAttributeValue(transaction, attribute);
        };
    }

    @Override
    protected Transaction mapResultSetToInstance(ResultSet resultSet, List<Class<?>> excludes) {
        Transaction transaction = super.mapResultSetToInstance(resultSet, excludes);

        try{
            AccountRepository accountRepository = new AccountRepository(statementWrapper);
            CategoryRepository categoryRepository = new CategoryRepository(statementWrapper);

            transaction.setAccount(
                accountRepository.findById(resultSet.getString("account"))
            );

            transaction.setCategory(
                    categoryRepository.findById(resultSet.getString("category"))
            );

            return transaction;
        }catch (SQLException error){
            System.out.println(error.getMessage());
            throw new InternalServerErrorException();
        }
    }
}