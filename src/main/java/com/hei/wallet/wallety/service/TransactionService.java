package com.hei.wallet.wallety.service;

import com.hei.wallet.wallety.exception.BadRequestException;
import com.hei.wallet.wallety.exception.InternalServerErrorException;
import com.hei.wallet.wallety.exception.NotFoundException;
import com.hei.wallet.wallety.model.*;
import com.hei.wallet.wallety.repository.TransactionRepository;
import com.hei.wallet.wallety.utils.GenUUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final BalanceHistoryService balanceHistoryService;

    public Transaction saveOrUpdate(Transaction toSave){
        try{
            return transactionRepository.saveOrUpdate(toSave);
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public Transaction findById(String id){
        try{
            Transaction transaction =  transactionRepository.findById(id);
            if(transaction == null){
                throw new NotFoundException(String.format("Transaction with id=%s doesn't exist", id));
            }
            return transaction;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public List<Transaction> findByAccountId(String accountId){
        try{
            return transactionRepository.findByField("account", accountId);
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public List<Transaction> findAll(){
        try {
            return transactionRepository.findAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public List<Transaction> saveOrUpdateAll(List<Transaction> transactions){
        try {
            return transactionRepository.saveOrUpdateAll(transactions);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public Transaction doTransaction(Transaction transaction){
        try{
            Transaction lastTransaction = transactionRepository.findById(transaction.getId());
            if(lastTransaction != null){
                return lastTransaction;
            }
            Account targetAccount = transaction.getAccount();

            // 'cause some category work ony for DEBIT or CREDIT
            validateTransactionCategory(transaction);

            // check if the target account have enough money (includes credits if available)
            validateSufficientFunds(targetAccount, transaction);

            balanceHistoryService.saveOrUpdate(getBalanceHistory(transaction, targetAccount));
            return saveOrUpdate(saveOrUpdate(transaction));
        }catch(SQLException error){
            System.out.println(error.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public List<Transaction> doTransactions(List<Transaction> transactions){
        List<Transaction> results = new ArrayList<>();
        for(Transaction transaction: transactions){
            results.add(doTransaction(transaction));
        }
        return results;
    }

    private static void validateTransactionCategory(Transaction transaction) {
        CategoryType transactionCategoryType = transaction.getCategory().getType();
        TransactionType transactionType = transaction.getType();

        if (
            (transactionCategoryType == CategoryType.DEBIT && transactionType == TransactionType.CREDIT) ||
            (transactionCategoryType == CategoryType.CREDIT && transactionType == TransactionType.DEBIT)
        ) {
            throw new BadRequestException(String.format(
                "The given category works only for transactions with type %s",
                transactionCategoryType
            ));
        }
    }

    private static void validateSufficientFunds(Account target, Transaction transaction){
        if(
            transaction.getType().equals(TransactionType.DEBIT) &&
            !isThereEnoughMoney(target, transaction.getAmount())
        ){
            throw new BadRequestException("The destination account doesn't have enough money");
        }
    }

    private static boolean isThereEnoughMoney(Account account, BigDecimal amount){
        BigDecimal currentBalance = account.getBalance().getAmount();
        if(account.getAuthorizeCredits()){
            currentBalance = currentBalance.add(getCreditsValue(currentBalance));
        }
        return currentBalance.compareTo(amount) >= 0;
    }

    private static BigDecimal getCreditsValue(BigDecimal amount){
        final int PERCENT_VALUE = 3;
        final int SCALE=2;
        return amount.divide(BigDecimal.valueOf(PERCENT_VALUE), SCALE, RoundingMode.HALF_UP);
    }

    private static BalanceHistory getBalanceHistory(Transaction transaction, Account targetAccount) {
        BalanceHistory newBalance = new BalanceHistory(
                GenUUID.getUUID(),
                targetAccount.getBalance().getAmount(),
                transaction.getTransactionDatetime(),
                targetAccount
        );

        BigDecimal amountToAdd = transaction.getAmount();
        if(transaction.getType().equals(TransactionType.DEBIT)){
            amountToAdd = amountToAdd.negate();
        }
        newBalance.setAmount(newBalance.getAmount().add(amountToAdd));
        return newBalance;
    }
}