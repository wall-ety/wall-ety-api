package com.hei.wallet.wallety.service;

import com.hei.wallet.wallety.exception.InternalServerErrorException;
import com.hei.wallet.wallety.exception.NotFoundException;
import com.hei.wallet.wallety.model.Account;
import com.hei.wallet.wallety.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account saveOrUpdate(Account toSave){
        try{
            return accountRepository.saveOrUpdate(toSave);
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public Account findById(String id){
        try{
            Account account =  accountRepository.findById(id);
            if(account == null){
                throw new NotFoundException(String.format("Account with id=%s doesn't exist", id));
            }
            return account;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public List<Account> findAll(){
        try {
            return accountRepository.findAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public List<Account> saveOrUpdateAll(List<Account> accounts){
        try {
            return accountRepository.saveOrUpdateAll(accounts);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new InternalServerErrorException();
        }
    }
}
