package com.hei.wallet.wallety.service;

import com.hei.wallet.wallety.exception.InternalServerErrorException;
import com.hei.wallet.wallety.exception.NotFoundException;
import com.hei.wallet.wallety.model.BalanceHistory;
import com.hei.wallet.wallety.repository.BalanceHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class BalanceHistoryService {
    private final BalanceHistoryRepository balanceHistoryRepository;

    public BalanceHistory saveOrUpdate(BalanceHistory toSave){
        try{
            return balanceHistoryRepository.saveOrUpdate(toSave);
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public BalanceHistory findById(String id){
        try{
            BalanceHistory balanceHistory =  balanceHistoryRepository.findById(id);
            if(balanceHistory == null){
                throw new NotFoundException(String.format("BalanceHistory with id=%s doesn't exist", id));
            }
            return balanceHistory;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public List<BalanceHistory> findAll(){
        try {
            return balanceHistoryRepository.findAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public List<BalanceHistory> saveOrUpdateAll(List<BalanceHistory> balanceHistories){
        try {
            return balanceHistoryRepository.saveOrUpdateAll(balanceHistories);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public List<BalanceHistory> getByAccountId(String accountId){
        try{
            return balanceHistoryRepository.findByAccountId(accountId);
        }catch (SQLException error){
            System.out.println(error.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public BalanceHistory getCurrentAccountBalance(String accountId){
        try{
            return balanceHistoryRepository.getAccountCurrentBalance(accountId);
        }catch (SQLException error){
            System.out.println(error.getMessage());
            throw new InternalServerErrorException();
        }
    }
}
