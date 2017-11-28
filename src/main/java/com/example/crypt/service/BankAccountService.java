package com.example.crypt.service;

import com.example.crypt.api.dto.MessageDTO;
import com.example.crypt.api.dto.PaymentDTO;
import com.example.crypt.repository.BankAccount;
import com.example.crypt.repository.BankAccountRepository;
import com.example.crypt.utils.GenSignature;
import com.example.crypt.utils.RSACryptography;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Objects;

@Service
@Transactional
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private PrivateKey privateKey;


    public void generateKeyPair(int keySize, String algorithm) throws NoSuchAlgorithmException {
        GenSignature.generateKeyPair(keySize, algorithm);
    }

    public void decryptAndPayment(MessageDTO messageDTO)
            throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException,
            NoSuchAlgorithmException, NoSuchPaddingException, IOException {

        String plainMessage = RSACryptography.decrypt(privateKey, messageDTO.getMessage());
        System.out.println(plainMessage);
        PaymentDTO paymentDTO = new ObjectMapper().readValue(plainMessage, PaymentDTO.class);
        if (Objects.nonNull(paymentDTO) && StringUtils.isNotEmpty(paymentDTO.getPrimaryBankAccount())) {
            BankAccount bankAccount = bankAccountRepository
                    .findByPrimaryAccountNumber(paymentDTO.getPrimaryBankAccount())
                    .orElseThrow(() -> new EntityNotFoundException());
            double currentBalance = bankAccount.getBalance() - paymentDTO.getAmount();
            bankAccount.setBalance(currentBalance);
            bankAccountRepository.save(bankAccount);
        }
    }
}
