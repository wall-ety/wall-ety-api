package com.hei.wallet.wallety.service;

import com.hei.wallet.wallety.exception.BadRequestException;
import com.hei.wallet.wallety.exception.InternalServerErrorException;
import com.hei.wallet.wallety.exception.NotFoundException;
import com.hei.wallet.wallety.model.Account;
import com.hei.wallet.wallety.model.BalanceHistory;
import com.hei.wallet.wallety.model.Transaction;
import com.hei.wallet.wallety.model.TransactionType;
import com.hei.wallet.wallety.repository.TransactionRepository;
import com.hei.wallet.wallety.utils.GenUUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final BalanceHistoryService balanceHistoryService;

    public static boolean isThereEnoughMoney(Account account, BigDecimal amount){
        BigDecimal currentBalance = account.getBalance().getAmount();
        if(account.getAuthorizeCredits()){
            currentBalance = currentBalance.add(getCredits(currentBalance));
        }
        return currentBalance.compareTo(amount) >= 0;
    }

    public static BigDecimal getCredits(BigDecimal amount){
        return amount.divide(BigDecimal.valueOf(3), 2, RoundingMode.HALF_UP);
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
            if(transactionRepository.findById(transaction.getId()) != null){
                throw new BadRequestException("Transaction with the same id already exist");
            }

            Account targetAccount = transaction.getAccount();
            if(
                transaction.getType().equals(TransactionType.DEBIT) &&
                !isThereEnoughMoney(targetAccount, transaction.getAmount())
            ){
                throw new BadRequestException("The destination account doesn't have enough money");
            }

            balanceHistoryService.saveOrUpdate(getBalanceHistory(transaction, targetAccount));
            return transactionRepository.saveOrUpdate(transaction);
        }catch(SQLException error){
            System.out.println(error.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public List<Transaction> doTransactions(List<Transaction> transactions){
        for(Transaction transaction: transactions){
            doTransaction(transaction);
        }
        return transactions;
    }

}