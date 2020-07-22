package io.github.gmy.sp.loctrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.gmy.sp.loctrack.mapper.AccountMapper;

@Service
public class LoginService {
    private final AccountMapper accountMapper;

    @Autowired
    public LoginService(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public boolean login(String phone, String pwd) {
        return accountMapper.authenticate(phone, pwd) != null;
    }
}
