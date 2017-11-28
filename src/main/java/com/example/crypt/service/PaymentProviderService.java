package com.example.crypt.service;

import com.example.crypt.api.dto.PaymentDTO;
import com.example.crypt.utils.RSACryptography;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

@Service
public class PaymentProviderService {
    @Autowired
    private PublicKey publicKey;

    public String encryptPaymentMessage(PaymentDTO paymentDTO)
            throws NoSuchAlgorithmException, BadPaddingException, NoSuchPaddingException,
            IllegalBlockSizeException, InvalidKeyException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(paymentDTO);
        return RSACryptography.encrypt(publicKey, jsonInString);
    }
}
