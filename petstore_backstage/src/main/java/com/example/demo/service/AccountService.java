package com.example.demo.service;

import com.example.demo.domain.Account;
import com.example.demo.persistence.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountMapper accountMapper;


    public Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return accountMapper.getAccountByUsernameAndPassword(account);
    }

    public boolean Login(String username, String password){
        String password_real = accountMapper.getPasswordByUsername(username);
        if (password.equals(password_real)){
            return true;
        }
        else
            return false;
    }
}
