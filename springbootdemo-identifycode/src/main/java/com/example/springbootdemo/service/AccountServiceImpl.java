package com.example.springbootdemo.service;

import com.example.springbootdemo.domain.Account;
import com.example.springbootdemo.persistence.AccountDAO;
import com.example.springbootdemo.persistence.MBAccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private MBAccountDAO mbAccountDAO;
    @Override
    public Account login(Account account) {
        return mbAccountDAO.findByUsernameAndPassword(account);
    }

    @Override
    public void insertAccount(Account account)  {
        mbAccountDAO.insertAccount(account);
        mbAccountDAO.insertProfile(account);
        mbAccountDAO.insertSignon(account);
    }

    @Override
    public void updateAccount(Account account)  {
        mbAccountDAO.updateAccount(account);
        mbAccountDAO.updateProfile(account);
        mbAccountDAO.updateSignon(account);

        if (account.getPassword() != null && account.getPassword().length() > 0) {
            mbAccountDAO.updateSignon(account);
        }
    }
}
