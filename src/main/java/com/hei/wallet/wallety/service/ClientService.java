package com.hei.wallet.wallety.service;

import com.hei.wallet.wallety.exception.InternalServerErrorException;
import com.hei.wallet.wallety.exception.NotFoundException;
import com.hei.wallet.wallety.model.Client;
import com.hei.wallet.wallety.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Client saveOrUpdate(Client toSave){
        try{
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
        try {
        return clientRepository.saveOrUpdateAll(clients);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new InternalServerErrorException();
        }
    }
}