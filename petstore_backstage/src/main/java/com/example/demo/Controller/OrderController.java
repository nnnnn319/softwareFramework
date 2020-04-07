package com.example.demo.Controller;

import com.example.demo.domain.Account;
import com.example.demo.domain.LineItem;
import com.example.demo.domain.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order")
@SessionAttributes({"order","orderList"})
public class OrderController
{
    @Autowired
    private OrderService orderService;

    //查询、修改、删除订单（Order）和发货
    @GetMapping("/viewOrderList")
    public String viewOrderList(Model model, HttpSession session){
        Account account = (Account) session.getAttribute("account");
        if(account == null) {
            String message = "Please login first";
            model.addAttribute("message",message);
            return "account/signOnForm";
        }
        List<Order> orderList = orderService.getOrdersByUsername(account.getUsername());
        model.addAttribute("orderList",orderList);
        return "order/orderList";
    }

    //通过Order Id查看订单详细情况
    @GetMapping("/viewOrder")
    public String viewOrder(Model model, HttpSession session,int orderId) {
      Order order = orderService.getOrder(orderId);
      model.addAttribute("order");
      session.setAttribute("order",order);
      return "order/viewOrder";
    }

    //删除
    @GetMapping("/delete")
    public String delete(Model model) {
        String message = "Delete successfully";
        model.addAttribute("message",message);
        return "order/orderList";
    }

    //发货
    @GetMapping("/send")
    public String send(Model model) {
        String message = "Send successfully";
        model.addAttribute("message",message);
        return "order/orderList";
    }

    //修改
    @GetMapping("/modify")
    public String modify(Model model) {
        Order order =(Order)model.getAttribute("order");

        //如果订单状态为已发货,不能修改
        if(!order.getStatus().equals("P")){
            int orderId = order.getOrderId();
            String message = "This order "+orderId+" has been shipped and cannot be modified";
            model.addAttribute("message",message);
            return "order/viewOrder";
        }
        return "order/modifyOrder";
    }

    //确认修改
    @PostMapping("/sure")
    public String sure(Model model) {
        //成功修改
        return "order/viewOrder";
    }

    //取消修改
    @GetMapping("/cancel")
    public String cancel(Model model) {
        return "order/viewOrder";
    }
}