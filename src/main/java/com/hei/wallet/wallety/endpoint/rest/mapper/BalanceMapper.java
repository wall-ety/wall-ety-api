package com.hei.wallet.wallety.endpoint.rest.mapper;

import com.hei.wallet.wallety.endpoint.rest.model.Balance;
import com.hei.wallet.wallety.model.BalanceHistory;
import org.springframework.stereotype.Component;

@Component
public class BalanceMapper {

    public Balance toApi(BalanceHistory balance){
        return Balance
            .builder()
            .id(balance.getId())
            .amount(balance.getAmount())
            .createdAt(balance.getCreatedAt())
            .build();
    }
}