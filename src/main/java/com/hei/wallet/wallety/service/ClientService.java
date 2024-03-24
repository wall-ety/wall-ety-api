package com.hei.wallet.wallety.service;

import com.hei.wallet.wallety.exception.BadRequestException;
import com.hei.wallet.wallety.exception.InternalServerErrorException;
import com.hei.wallet.wallety.exception.NotFoundException;
import com.hei.wallet.wallety.model.Client;
import com.hei.wallet.wallety.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private static int MINIMUM_AGE=22;

    public static void verifyAge(LocalDate birthDate) throws IllegalArgumentException {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);

        if (period.getYears() < MINIMUM_AGE) {
            throw new BadRequestException("Minimum age is " + MINIMUM_AGE);
        }
    }

    public Client saveOrUpdate(Client toSave){
        try{
            verifyAge(toSave.getBirthdate());
            return clientRepository.saveOrUpdate(toSave);
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public Client findById(String id){
        try{
            Client category =  clientRepository.findById(id);
            if(category == null){
                throw new NotFoundException(String.format("Client with id=%s doesn't exist", id));
            }
            return category;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public List<Client> findAll(){
        try {
            return clientRepository.findAll();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new InternalServerErrorException();
        }
    }

    public List<Client> saveOrUpdateAll(List<Client> clients){
        List<Client> results = new ArrayList<>();
        for(Client client: clients){
            results.add(saveOrUpdate(client));
        }
        return results;
    }
}