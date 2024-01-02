package com.million.multi_factor_auth.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthAccountKeyGenerate {

    public String generateAccountKey() {
        return KeyGenerator.generateKey(32);
    }

    public String bcryptGeneratedAccountKey(String accountKey){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(accountKey);
    }

}
