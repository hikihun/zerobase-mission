package com.sbpj.rms.config;

import com.sbpj.rms.config.jwt.Aes256Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Aes256UtilTest {

    @Test
    void encrypt() {
        String encrypt = Aes256Util.encrypt("zerobase backend school");
        System.out.println(encrypt);
        assertEquals(Aes256Util.decrypt(encrypt), "zerobase backend school");
    }

    @Test
    void decrypt() {

    }
}