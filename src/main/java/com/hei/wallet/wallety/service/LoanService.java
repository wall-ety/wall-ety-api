package com.hei.wallet.wallety.service;

import com.hei.wallet.wallety.model.Account;
import com.hei.wallet.wallety.model.BalanceHistory;
import com.hei.wallet.wallety.model.BalanceInfo;
import com.hei.wallet.wallety.model.Bank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final AccountService accountService;

    public static float getCurrentLoanInterestValue(Bank bank, BalanceHistory history){
        Instant last7Days = Instant.now().minus(Duration.ofDays(7));
        if(history.getCreatedAt().isBefore(last7Days)){
            return bank.getFirstWeekLoan();
        }
        return bank.getSubsequentLoan();
    }

    public static BigDecimal getLoanValue(BigDecimal currentAmount,  Instant date, float loanInterest){
        Duration duration = Duration.between(date, Instant.now());
        long days = duration.toDays();
        return currentAmount.multiply(BigDecimal.valueOf(loanInterest)).multiply(new BigDecimal(days));
    }

    public BalanceInfo getCurrentBalanceWithLoan(String idAccount){
        Account account = accountService.findById(idAccount);
        BigDecimal currentAmount = account.getBalance().getAmount();
        boolean useCredits = currentAmount.compareTo(BigDecimal.ZERO) < 0;
        float loanInterest = useCredits ? getCurrentLoanInterestValue(
            account.getBank(),
            account.getBalance()
        ) : account.getBank().getFirstWeekLoan();

        BigDecimal loan = !useCredits ?BigDecimal.ZERO :
                getLoanValue(
                        currentAmount.negate(),
                        account.getBalance().getCreatedAt(), loanInterest
                );
        return BalanceInfo
            .builder()
                .loan(loan)
                .loanInterest(loanInterest)
                .balance(account.getBalance())
            .build();
    }
}
