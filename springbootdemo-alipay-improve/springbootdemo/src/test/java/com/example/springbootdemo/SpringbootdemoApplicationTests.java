package com.example.springbootdemo;

import com.example.springbootdemo.domain.Account;
import com.example.springbootdemo.persistence.AccountDAO;
import com.example.springbootdemo.persistence.AccountDAOImpl;
import com.example.springbootdemo.persistence.TestDAO;
import com.example.springbootdemo.service.TestAccountService;
import com.example.springbootdemo.time.TimeDelay;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;


@SpringBootTest
@MapperScan("com.example.springbootdemo.persistence")

class SpringbootdemoApplicationTests {
//    @Autowired
//    private TestAccountService testAccountService;
    @Test
    void contextLoads() throws IOException {
//        int x;//定义两变量
//        Random ne=new Random();//实例化一个random的对象ne
//        x=ne.nextInt(9999-1000+1)+1000;//为变量赋随机值1000-9999
//        System.out.println(x);//输出
        TimeDelay timeDelay = new TimeDelay();
        timeDelay.delayRun();
        System.out.println("delay");
    }

}
