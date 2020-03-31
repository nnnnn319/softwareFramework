package com.example.springbootdemo.persistence;

import com.example.springbootdemo.domain.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface MBAccountDAO {
    Account findByUsernameAndPassword(Account account);
    void insertAccount(Account account);
    void insertProfile(Account account);
    void insertSignon(Account account);
    void updateAccount(Account account);
    void updateProfile(Account account);
    void updateSignon(Account account);
}
