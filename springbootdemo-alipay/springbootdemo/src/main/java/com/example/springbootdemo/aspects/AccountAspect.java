package com.example.springbootdemo.aspects;

import com.example.springbootdemo.domain.Account;
import com.example.springbootdemo.domain.Dairy;
import com.example.springbootdemo.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
//使用Aspect添加日志功能 主要记录登陆 点击商品 退出
public class AccountAspect {
    @Autowired
    private LogService logService;
    private String username = null;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Pointcut("execution(* com.example.springbootdemo.controller.account.LoginController.*(..))")
    public void LogCut(){}
    @AfterReturning("LogCut()")
    public void AfterAccount(JoinPoint joinPoint) {

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        username = request.getParameter("username");
        Dairy dairy = new Dairy(username, df.format(new Date()), "Login");
        logService.insertLog(dairy);
//        System.out.println("request"+request.getParameter("username"));
//        System.out.println("after"+ Arrays.toString(joinPoint.getArgs()));
//        System.out.println("params"+joinPoint.getArgs().toString());
    }
    @Pointcut("execution(* com.example.springbootdemo.controller.account.LogoutController.*(..))")
    public void LogOut(){}
    @AfterReturning("LogOut()")
    public void AfterLogOut(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        Dairy dairy = new Dairy(username, df.format(new Date()), "Logout");
        logService.insertLog(dairy);
    }

    @Pointcut("execution(* com.example.springbootdemo.controller.product.ShowProduct.*(..))")
    public void clickProduct(){}
    @AfterReturning("clickProduct()")
    public void AfterClick(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        String categoryId = request.getParameter("categoryId");
        Dairy dairy = new Dairy(username, df.format(new Date()), "Click "+categoryId);
        logService.insertLog(dairy);
    }
}
