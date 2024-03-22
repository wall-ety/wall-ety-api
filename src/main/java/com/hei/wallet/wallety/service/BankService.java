package com.hei.wallet.wallety.service;

import com.hei.wallet.wallety.exception.InternalServerErrorException;
import com.hei.wallet.wallety.model.Bank;
import com.hei.wallet.wallety.repository.BankRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class BankService {
    private final BankRepository bankRepository;

    public Bank saveOrUpdate(Bank toSave){
        try{
            return bankRepository.saveOrUpdate(toSave);
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public Bank findById(String id){
        try{
            return bankRepository.findById(id);
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public List<Bank> findAll(){
        try {
            return bankRepository.findAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public List<Bank> saveOrUpdateAll(List<Bank> banks){
        try {
            return bankRepository.saveOrUpdateAll(banks);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new InternalServerErrorException();
        }
    }
}
