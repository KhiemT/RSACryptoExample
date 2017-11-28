package com.example.crypt.config;

import com.example.crypt.repository.BankAccount;
import com.example.crypt.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class BankAccountConfiguration implements CommandLineRunner {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public void run(String... strings) throws Exception {
        createBankAccountIfNotFound("60161331926819", 1000);
        createBankAccountIfNotFound("60161331926820", 2000);
    }

    private final void createBankAccountIfNotFound(final String primaryBankAccount, final double balance) {
        if(!bankAccountRepository.findByPrimaryAccountNumber(primaryBankAccount).isPresent()) {
            BankAccount bankAccount = new BankAccount();
            bankAccount.setPrimaryAccountNumber(primaryBankAccount);
            bankAccount.setBalance(balance);
            bankAccountRepository.save(bankAccount);
        }
    }
}
