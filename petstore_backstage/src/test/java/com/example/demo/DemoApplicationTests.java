package com.example.demo;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.example.demo.phone.IdentifyCode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    IdentifyCode identifyCode = new IdentifyCode();
    @Test
    void contextLoads() throws ClientException {
//        identifyCode.setNewcode();
//        String code = Integer.toString(identifyCode.getNewcode());
//        SendSmsResponse sendSms =identifyCode.sendSms("13835649105",code);//填写你需要测试的手机号码
//        System.out.println("短信接口返回的数据----------------");
//        System.out.println("Code=" + sendSms.getCode());
//        System.out.println("Message=" + sendSms.getMessage());
//        System.out.println("RequestId=" + sendSms.getRequestId());
//        System.out.println("BizId=" + sendSms.getBizId());
    }

}
