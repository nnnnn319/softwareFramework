package com.example.demo.persistence;

import com.example.demo.domain.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMapper {
    Account getAccountByUsernameAndPassword(Account account);

    String getPasswordByUsername(String username);

    String getPhoneNumByUsername(String username);
}
