package com.example.springbootdemo.controller.alipay;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirController {
    @GetMapping("/ali")
    public String getali(){
        return "ali";
    }
}
