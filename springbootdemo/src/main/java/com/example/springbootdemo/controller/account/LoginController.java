package com.example.springbootdemo.controller.account;

import com.example.springbootdemo.domain.Account;
import com.example.springbootdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/login")
@SessionAttributes(value = {"username", "account"})
public class LoginController {
    @Autowired
    private AccountService accountService;
    @PostMapping("/")
    public String login(Account account, Model model) {
        Account loginAccount = accountService.login(account);
        if(loginAccount != null){
            System.out.println(loginAccount.getFirstName());
            model.addAttribute("username", account.getUsername());
            model.addAttribute("account", loginAccount);
            return "catalog/Main";
        }else {
            return "catalog/Item";
        }
    }
}
