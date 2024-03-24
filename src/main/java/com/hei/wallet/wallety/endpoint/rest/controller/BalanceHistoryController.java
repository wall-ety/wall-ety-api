package com.hei.wallet.wallety.endpoint.rest.controller;

import com.hei.wallet.wallety.endpoint.rest.mapper.BalanceInfoMapper;
import com.hei.wallet.wallety.endpoint.rest.mapper.BalanceMapper;
import com.hei.wallet.wallety.endpoint.rest.model.Balance;
import com.hei.wallet.wallety.endpoint.rest.model.BalanceInfo;
import com.hei.wallet.wallety.service.BalanceHistoryService;
import com.hei.wallet.wallety.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceHistoryController {
    private final BalanceHistoryService balanceHistoryService;
    private final LoanService loanService;
    private final BalanceMapper balanceMapper;
    private final BalanceInfoMapper balanceInfoMapper;

    @GetMapping("/accounts/{idAccount}/balances")
    public List<Balance> getAllByAccountId(@PathVariable String idAccount){
        return balanceHistoryService
                .getByAccountId(idAccount)
                .stream()
                .map(balanceMapper::toApi)
                .toList();
    }

    @GetMapping("/accounts/{idAccount}/balances/current")
    public BalanceInfo getCurrentAccountBalance(@PathVariable String idAccount){
        return balanceInfoMapper.toApi(loanService.getCurrentBalanceWithLoan(idAccount));
    }
}
