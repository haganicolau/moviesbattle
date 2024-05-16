package com.tech.ada.moviesbattle.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class) // Para JUnit 5
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UtilTest {

    @Autowired
    private Util util;

    @Test
    void hashPassword_ShouldReturnHashedPassword() throws NoSuchAlgorithmException {
        String password = "password";
        String passEncrypted = util.hashPassword(password);
        assertEquals("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", passEncrypted);
    }


}