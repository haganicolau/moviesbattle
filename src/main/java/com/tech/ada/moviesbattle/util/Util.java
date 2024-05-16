package com.tech.ada.moviesbattle.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Util {

    private static final Logger LOG = LoggerFactory.getLogger(Util.class);

    /**
     * The hashPassword method is responsible for hashing a given password using the SHA-256 algorithm.
     * @param password
     * @return
     */
    public String hashPassword(String password) {
        LOG.info("Decrypt/encrypt user password.");
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte bytes : hashedBytes) {
                sb.append(String.format("%02x", bytes));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            LOG.error("Error when trying Decrypt/encrypt user password.", ex);
            return null;
        }
    }
}
