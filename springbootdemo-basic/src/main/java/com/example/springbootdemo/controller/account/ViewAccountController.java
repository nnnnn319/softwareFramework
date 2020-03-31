package com.example.springbootdemo.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/account")
public class ViewAccountController {
    @GetMapping("/fix")
    public String fixAccount(Model model) {
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
        return "account/ViewAccount";
    }
}
