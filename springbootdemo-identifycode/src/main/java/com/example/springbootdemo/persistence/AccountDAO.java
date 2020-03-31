package com.example.springbootdemo.persistence;

import com.example.springbootdemo.domain.Account;

public interface AccountDAO {
    Account findByUsernameAndPassword(Account account);
    void testConnection();
}
