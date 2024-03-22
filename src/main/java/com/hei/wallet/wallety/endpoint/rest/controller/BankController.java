package com.hei.wallet.wallety.endpoint.rest.controller;

import com.hei.wallet.wallety.model.Bank;
import com.hei.wallet.wallety.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;

    @GetMapping("/banks")
    public List<Bank> getAll(){
        return bankService.findAll();
    }

    @GetMapping("/banks/{bankId}")
    public Bank getById(@PathVariable String bankId){
        return bankService.findById(bankId);
    }

    @PutMapping("/banks")
    public List<Bank> saveOrUpdateAll(@RequestBody List<Bank> banks){
        return bankService.saveOrUpdateAll(banks);
    }
}
