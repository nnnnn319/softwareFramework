package com.example.springbootdemo.controller.account;

import com.example.springbootdemo.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/logout")
@SessionAttributes(value = {"username", "account"})
public class LogoutController {
    @GetMapping("/")
    public String logout(Model model, HttpSession session){
        model.addAttribute("username", null);
        model.addAttribute("account", null);
        session.setAttribute("username", null);
        session.setAttribute("account", null);
        System.out.println(session.getAttribute("username"));
        System.out.println(model.toString());
        return "catalog/Main";
    }
}
