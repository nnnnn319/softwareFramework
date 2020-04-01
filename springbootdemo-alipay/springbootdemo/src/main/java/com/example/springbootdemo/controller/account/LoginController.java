package com.example.springbootdemo.controller.account;

import com.example.springbootdemo.domain.Account;
import com.example.springbootdemo.phone.GetMessageCode;
import com.example.springbootdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/login")
@SessionAttributes(value = {"username", "account", "iCode"})
public class LoginController {
    @Autowired
    private AccountService accountService;
    @PostMapping("/")
    public String login(Account account, @RequestParam("code") String code, Model model, HttpSession session) {
//        int iCode =(int) model.getAttribute("iCode");
//        System.out.println("session code "+iCode);
        System.out.println("form " + code);
        Account loginAccount = accountService.login(account);
        int iCode = (int)session.getAttribute("iCode");
        System.out.println("session "+iCode);
        //当验证码和密码同时有效时 允许登陆
        if(loginAccount != null && Integer.parseInt(code)==iCode){
//        if(loginAccount != null){
            System.out.println(loginAccount.getFirstName());
            model.addAttribute("username", account.getUsername());
            model.addAttribute("account", loginAccount);
            return "catalog/Main";
        }else {
            return "catalog/Item";
        }
    }
    @GetMapping("/code")
    @ResponseBody
    public String Identify(@RequestParam("param") String params, Model model, HttpSession session) throws IOException {
//        System.out.println("ffff");
        GetMessageCode code = new GetMessageCode();
        code.sendMessage(params);
        code.setIdentifyCode(1111);
        int iCode = code.getIdentifyCode();
        model.addAttribute("iCode", iCode);
        return "ok";
    }
}
