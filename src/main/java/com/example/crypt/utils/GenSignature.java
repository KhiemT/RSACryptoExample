package com.example.crypt.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import static com.example.crypt.utils.Constant.PRIVATE_KEY_STORE;
import static com.example.crypt.utils.Constant.PUBLIC_KEY_STORE;

public class GenSignature {
    private static final Logger LOGGER = LoggerFactory.getLogger(RSACryptography.class);

    private static PrivateKey privateKey;
    private static PublicKey publicKey;

    public static void generateKeyPair(int keySize, String algorithm)
                                                throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        //SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        SecureRandom random = SecureRandom.getInstanceStrong();
        keyPairGenerator.initialize(keySize, random);
        KeyPair keyPair =  keyPairGenerator.genKeyPair();
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();
        writeOutPublicAndPrivateKey();
    }
    private static void writeOutPublicAndPrivateKey() {
        try {
            writeToFile(PUBLIC_KEY_STORE, publicKey.getEncoded());
            writeToFile(PRIVATE_KEY_STORE, privateKey.getEncoded());
        } catch (IOException e) {
            LOGGER.debug("Having exception " + e.getMessage());
        }
    }
    private static void writeToFile(String path, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();
        try (FileOutputStream fos = new FileOutputStream(f)) {
            fos.write(key);
            fos.flush();
        }
    }
}
