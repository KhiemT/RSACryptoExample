package com.example.crypt.api;

import com.example.crypt.api.dto.MessageDTO;
import com.example.crypt.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/bankAccounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public void decryptAndPayment(@RequestBody MessageDTO messageDTO) throws IllegalBlockSizeException,
            NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IOException {
        bankAccountService.decryptAndPayment(messageDTO);
    }

    @RequestMapping("/generateKeyPair")
    public void generateKeyPair(@RequestParam(value="keySize") int keySize,
                                @RequestParam(value="algorithm") String algorithm) throws NoSuchAlgorithmException {
        this.bankAccountService.generateKeyPair(keySize, algorithm);
    }
}
