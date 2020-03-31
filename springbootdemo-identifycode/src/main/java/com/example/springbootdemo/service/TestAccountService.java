package com.example.springbootdemo.service;

import com.example.springbootdemo.domain.Account;
import com.example.springbootdemo.persistence.TestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TestAccountService {
    @Autowired
    private TestDAO testDAO;
    public Account login(Account account){
        return testDAO.findByUsernameAndPassword(account);
    }
}
