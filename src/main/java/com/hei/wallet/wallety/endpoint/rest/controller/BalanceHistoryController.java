package com.hei.wallet.wallety.endpoint.rest.controller;

import com.hei.wallet.wallety.endpoint.rest.mapper.BalanceMapper;
import com.hei.wallet.wallety.endpoint.rest.model.Balance;
import com.hei.wallet.wallety.service.BalanceHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceHistoryController {
    private final BalanceHistoryService balanceHistoryService;
    private final BalanceMapper balanceMapper;

    @GetMapping("/accounts/{idAccount}/balances")
    public List<Balance> getAllByAccountId(@PathVariable String idAccount){
        return balanceHistoryService
                .getByAccountId(idAccount)
                .stream()
                .map(balanceMapper::toApi)
                .toList();
    }

    @GetMapping("/accounts/{idAccount}/balances/current")
    public Balance getCurrentAccountBalance(@PathVariable String idAccount){
        return balanceMapper.toApi(
            balanceHistoryService.getCurrentAccountBalance(idAccount)
        );
    }
}
