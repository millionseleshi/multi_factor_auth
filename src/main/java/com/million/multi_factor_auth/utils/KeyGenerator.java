package com.million.multi_factor_auth.utils;

import java.security.SecureRandom;

public class KeyGenerator {

    private static final String ALPHANUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom secureRandom = new SecureRandom();
 
    private KeyGenerator() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
 
    public static String generateKey(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(ALPHANUMERIC.charAt(secureRandom.nextInt(ALPHANUMERIC.length())));
        return sb.toString();
    }

}
