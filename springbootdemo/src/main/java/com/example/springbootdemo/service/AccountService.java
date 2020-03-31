package com.example.springbootdemo.service;

import com.example.springbootdemo.domain.Account;

public interface AccountService {
    Account login(Account account);
    void insertAccount(Account account);
    public void updateAccount(Account account);
}
