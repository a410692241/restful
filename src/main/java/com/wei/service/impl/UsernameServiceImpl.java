package com.wei.service.impl;

import com.wei.service.UsernameServicce;
import org.springframework.stereotype.Service;

@Service
public class UsernameServiceImpl implements UsernameServicce {
    @Override
    public String greeting(String username) {
        return "hello" + username;

    }
}
