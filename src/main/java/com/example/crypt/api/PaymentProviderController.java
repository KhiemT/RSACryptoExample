package com.example.crypt.api;

import com.example.crypt.api.dto.PaymentDTO;
import com.example.crypt.service.PaymentProviderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/payments")
public class PaymentProviderController {

    @Autowired
    private PaymentProviderService paymentProviderService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String encryptedPaymentMessage(@RequestBody PaymentDTO paymentDTO)
            throws IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, JsonProcessingException {
        return paymentProviderService.encryptPaymentMessage(paymentDTO);
    }
}
