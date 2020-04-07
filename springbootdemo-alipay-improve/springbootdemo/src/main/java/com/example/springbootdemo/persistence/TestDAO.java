package com.example.springbootdemo.persistence;

import com.example.springbootdemo.domain.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDAO {
    Account findByUsernameAndPassword(Account account);
}
