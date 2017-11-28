package com.example.crypt.config;

import com.example.crypt.utils.Constant;
import com.example.crypt.utils.RSACryptography;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

@Configuration
public class RSAConfiguration {

    @Bean
    public PrivateKey getPrivateKey() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        return RSACryptography.getPrivate(readAllBytes(Constant.PRIVATE_KEY_STORE));
    }

    @Bean
    public PublicKey getPublicKey() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        return RSACryptography.getPublic(readAllBytes(Constant.PUBLIC_KEY_STORE));
    }

    private byte[] readAllBytes(String fileName) throws IOException {
        Resource resource = new ClassPathResource(fileName);
        InputStream is = resource.getInputStream();
        return IOUtils.toByteArray(is);
    }
}
