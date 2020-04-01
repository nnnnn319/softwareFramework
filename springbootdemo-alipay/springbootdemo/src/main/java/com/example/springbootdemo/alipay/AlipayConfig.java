package com.example.springbootdemo.alipay;

public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2016102400750503";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCIf8cvsHbetW+SyamyTM8X802zF8XZphn6Jl7pU+aqah7/pBXaZHiJXBxHhzovp/b1nhCBEElDN5yX0USbVlh+3ZWUhNvlfe3bp2cCknqolf8tGjHfjbIDkweN6ryO+rom84+AlAq+EaY8qR6B4fpk2achQsG7XjrRgowg8i5LfRgCjemCEl/SOKfseHuaJd9EIPeRWm9lb/2V2GEW/oB1GuzyMbFxby/ygo5OdaCOf7V3Pae/MCxan/+DRIir+Yfr1p7uGbz2bOQ478zTc1invu8TQ9nwdK/5iWuxO4jThe/C9Z7VZWeGAIkYmFk6pIHb4ezuds1w9kWDj2cUPdPlAgMBAAECggEAV4qThYtIATcdkmeRVM+b9cFNsLFra1XyzRK0xjHP+rVKoitq9ICBKZ8QZ/NbJtszMH0qGowGYHyXCmrk9DbhVKPp3bGDkeA8nyXyk5y728kvOY7WM4idhVtTefn06kH1BywneY/4f8Oz+K2B6kyymOFaJaO5ueAKGvn9wtdvR2u9nPXPWCmXTMG3pNBIqTmbcafOIRjid/0cugW8sou5kPPhT3m13OrEoq4urPKhBj7z5ErDAEk4SPnTZRrvN90FJOWBw/GYFcclGovlY/tgIWR4h9mx/PyVp8ysyx3hNFiTCkpg7yIoV0oiH9d0WCgEXkffHCbbFY/dTy9NEzIzQQKBgQD7LSm91Y7yddU7lfd9wJ5/59FTuoPhR0qeACGjNQ9KFq7Mlw7s3w01f6G6iUoWqmp1tPW5zmHid9HaISi8Q1KHGyORLqwACUXyvKFAuylyaAFSr5jUQN5JjKl8d3/zIz3mBGFaxLeoylfkLmWZseNlXpjlmnaEfk8+aLaaVPoqNQKBgQCLHtZEQZvr3Q4h0H7sh9gelwx3bRWfEB8eswaX02x3wtHHfvk+xcwDyHqH8hyfYT3nZ7Uot01AtL2EG1zTzd95/CclM1I1KVVWU2bCIffhezmsd4jg5MZDadI+tgKgDC4L6FEEQWLD3vOkoPQakA4QiyuzBqk8EDq6oqLiCY648QKBgQCe5yqSK2oc4Aj/glepmdNsTcczNjs6qTR+ksy1CNtcXs9FJYfOuSoNH2q8rSBPCI8DWnFelD9+25jKa9HukJTEXpn4vlMYTsWO1GEbp4yIEV5M5kREe6llVHThBmTO+Auv2jORDgS25xQFiojCXDTlK5yFHVw/Y/1UGUD0kDAXxQKBgEj6nTXjIPMC3CX65IoZ3NJ3NE/1xlDRuTF2gm/ktdLBPSCkyAUY56PYaAamiENHVJzud0brmTfnWD9Tx0dIiQHJt5M5L9e5Xg3R5GbUQN9kr+V8My6edC7q9SJ2gjwJB6KxdMaBsljDvRXQZR0GEoq1JvBb/y0F6nj7VeyzTM5RAoGBAPCdcmcHwcsENYhFRX8I1ac4ZAquFj+mBnHuO3l70hwrQRlhsW8lsxTt6xgyPsLUaldg3R+ECLBdRnwGD8U0w6Mtgs+Tcra30BGour+OxPBBp10vs1uMBTQUqT1o6F1u7gvA8PWM6XRp69jwn8PF+fx1wkKQg7jl61P7DyS1vr9W";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyxBdO++YLL/hDZsY5/+xLuXtzwObtAnYUQqi0ZbR//K9EG4S4ViMDpjVVgIKwFT8J3d4heMVuf0F6vB5juBR+SUWjMlp59iLaljn7TQaCbpvuyORaHiX9gJwlQUVNoFKTxcGudqOMDm8Y52mwCcFeEjiLxamA6mouCqWLQjcV1cDW5Qg8U79UyCVHgcOE0EyJGNAA9gEYq3C1whfju+W7VhlA/IC08a4XQmz6Sv1i5qqLIxF0pIJRpVdo2IW2TjjkHtzKcQ1iuimfdMBECVmh0vfR8DAPwXx1KycnyKAXdDV80ElKjV2hCqejgt9zjTr/uzdvsHQcjTdRO+aO274RwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/common/Error.html";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://localhost:8080/order/third";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";
}