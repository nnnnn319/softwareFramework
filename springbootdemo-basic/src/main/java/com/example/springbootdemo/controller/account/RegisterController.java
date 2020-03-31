package com.example.springbootdemo.controller.account;

import com.example.springbootdemo.domain.Account;
import com.example.springbootdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/register")
@SessionAttributes(value = {"username", "account"})
public class RegisterController {
    @Autowired
    AccountService accountService;
    @GetMapping("/goto")
    public String toRegister(Model model) {
        List<String> languagePre = new ArrayList<String>();
        List<String> favCategory = new ArrayList<String>();
        languagePre.add("english");
        languagePre.add("chinese");
        languagePre.add("japanese");
        favCategory.add("CATS");
        favCategory.add("FISH");
        favCategory.add("DOGS");
        favCategory.add("REPTILES");
        favCategory.add("BIRDS");
        model.addAttribute("languagePre", languagePre);
        model.addAttribute("favCategory", favCategory);
        return "account/RegisterForm";
    }
    @PostMapping("/get")
    public String insertAccount(Account account, Model model) {
        model.addAttribute("username", account.getUsername());
        model.addAttribute("account", account);
        accountService.insertAccount(account);
        return "catalog/Main";
    }
}
