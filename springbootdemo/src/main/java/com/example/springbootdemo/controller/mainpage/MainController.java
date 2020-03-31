package com.example.springbootdemo.controller.mainpage;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class MainController {
    @GetMapping("/")
    public String toMain(){
        return "catalog/Main";
    }
    @GetMapping("/signon")
    public String toSign(){
        return "account/SignonForm";
    }
}
