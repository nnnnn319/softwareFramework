package com.example.springbootdemo.controller.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.example.springbootdemo.alipay.AlipayConfig;
import com.example.springbootdemo.domain.Cart;
import com.example.springbootdemo.domain.LineItem;
import com.example.springbootdemo.domain.Order;
import com.example.springbootdemo.service.CartService;
import com.example.springbootdemo.service.OrderService;
import com.example.springbootdemo.time.TimeDelay;
import org.apache.http.HttpResponse;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Controller
public class PayController {
    @Autowired
    OrderService orderService;
    @Autowired
    CartService cartService;
    @GetMapping("/pay")
    public void payController(Model model, HttpSession httpSession, HttpServletResponse response) throws IOException {
        orderService.insertOrder((Order)httpSession.getAttribute("order"));
        Cart cart = (Cart)httpSession.getAttribute("cart");
        List<LineItem> lineItems = new ArrayList<LineItem>();
        lineItems = ((Order)httpSession.getAttribute("order")).getLineItems();
        for(int i=0;i<lineItems.size();i++){
            System.out.println("remove"+lineItems.get(i).getItemId());
            cart.removeItemById(lineItems.get(i).getItemId());
            cartService.RemoveCart(lineItems.get(i).getItemId());
        }

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Order order = (Order)httpSession.getAttribute("order");
                String orderStatus = orderService.selectStatus(((Order)httpSession.getAttribute("order")).getOrderId());
                if (orderStatus.equals("p")){
                    System.out.println("already pay do nothing");
                }
                else {
                    System.out.println("no pay return store");
                    List<LineItem> lineItems_order = order.getLineItems();
                    for (int i=0; i<lineItems_order.size(); i++){
                        orderService.updateInventoryQuantity(lineItems_order.get(i).getItemId(), -lineItems_order.get(i).getQuantity());
                        System.out.println(lineItems_order.get(i).getItemId() + " " + lineItems_order.get(i).getQuantity());
                    }
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 1000*60);


        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no =  String.valueOf  ( ( (Order)httpSession.getAttribute("order")).getOrderId());
        //付款金额，必填
        String total_amount =  String.valueOf  ( ( (Order)httpSession.getAttribute("order")).getTotalPrice());
        //订单名称，必填
        String subject = "Pet Store";
        //商品描述，可空
        String body = "pets";
        String username = "rnhixr4241@sandbox.com";
        String password = "111111";

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"username\":\"" + username + "\","
                + "\"password\":\"" + password + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");



        //请求
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }
    @RequestMapping("/ali")
    public String toTest(){
        return "ali";
    }
    @GetMapping("/error")
    public String error(){
        return "common/Error";
    }
    @GetMapping("/pay/order")
    public String toAlreadyPay(HttpSession session){
        orderService.updateOrderStatus("p", ((Order)session.getAttribute("order")).getOrderId());
        return "orders/FinalOrderPage";
    }
    @GetMapping("/repay")
    public void rePay(int orderId, HttpSession httpSession, HttpServletResponse response) throws IOException {
        Order repayOrder = orderService.getOrder(orderId);
        httpSession.setAttribute("order", repayOrder);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Order order = (Order)httpSession.getAttribute("order");
                String orderStatus = orderService.selectStatus(((Order)httpSession.getAttribute("order")).getOrderId());
                if (orderStatus.equals("p")){
                    System.out.println("already pay do nothing");
                }
                else {
                    System.out.println("no pay return store");
                    List<LineItem> lineItems_order = order.getLineItems();
                    for (int i=0; i<lineItems_order.size(); i++){
                        orderService.updateInventoryQuantity(lineItems_order.get(i).getItemId(), -lineItems_order.get(i).getQuantity());
                        System.out.println(lineItems_order.get(i).getItemId() + " " + lineItems_order.get(i).getQuantity());
                    }
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 1000*60*24);


        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no =  String.valueOf  ( ( (Order)httpSession.getAttribute("order")).getOrderId());
        //付款金额，必填
        String total_amount =  String.valueOf  ( ( (Order)httpSession.getAttribute("order")).getTotalPrice());
        //订单名称，必填
        String subject = "Pet Store";
        //商品描述，可空
        String body = "pets";
        String username = "rnhixr4241@sandbox.com";
        String password = "111111";

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"username\":\"" + username + "\","
                + "\"password\":\"" + password + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");



        //请求
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();

    }
}
