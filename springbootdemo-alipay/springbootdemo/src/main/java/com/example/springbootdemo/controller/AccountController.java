package com.example.springbootdemo.controller;

import com.example.springbootdemo.domain.Account;
import com.example.springbootdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class AccountController {
    @Autowired
    AccountService accountService;
    @PostMapping("/login")
    public String login(Account account, Model model){
        if(accountService.login(account) != null){
            model.addAttribute("username", account.getUsername());
            return "successful";
        }
        else {
            return "main";
        }
    }
    @GetMapping("/login")
    public String toLogin(){
        return "common/Error";
    }
    @GetMapping("/try")
    public String try_main(){
        return "catalog/Main";
    }
}
