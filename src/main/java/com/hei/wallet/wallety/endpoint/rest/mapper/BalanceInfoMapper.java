package com.hei.wallet.wallety.endpoint.rest.mapper;

import com.hei.wallet.wallety.endpoint.rest.model.BalanceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BalanceInfoMapper {
    private final BalanceMapper balanceMapper;

    public BalanceInfo toApi(com.hei.wallet.wallety.model.BalanceInfo balanceInfo){
        return BalanceInfo
                .builder()
                .loanInterest(balanceInfo.getLoanInterest())
                .balance(balanceMapper.toApi(balanceInfo.getBalance()))
                .loan(balanceInfo.getLoan())
                .build();
    }
}
