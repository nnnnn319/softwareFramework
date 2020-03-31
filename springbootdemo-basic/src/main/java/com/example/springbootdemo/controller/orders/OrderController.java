package com.example.springbootdemo.controller.orders;

import com.example.springbootdemo.domain.Account;
import com.example.springbootdemo.domain.Cart;
import com.example.springbootdemo.domain.LineItem;
import com.example.springbootdemo.domain.Order;
import com.example.springbootdemo.service.CartService;
import com.example.springbootdemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
@SessionAttributes(value = {"order"})
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;


    @GetMapping("/first")
    public String firstOrder(Model model){
        List<String> creditCard_types = new ArrayList<String>();
        creditCard_types.add("Visa");
        creditCard_types.add("MasterCard");
        creditCard_types.add("American Express");
        model.addAttribute("credit", creditCard_types);
        return "orders/FirstOrderPage";
    }
    @PostMapping("/second")
    public String secondOrder(Order order, Model model, HttpSession session){
        Account account = (Account) session.getAttribute("account");
        Cart cart = (Cart)session.getAttribute("cart");
        order.initOrder(account, cart);
        model.addAttribute("order", order);
        return "orders/SecondOrderPage";
    }
    @GetMapping("/third")
    public String third(Model model, HttpSession session){
        orderService.insertOrder((Order)session.getAttribute("order"));
        Cart cart = (Cart)session.getAttribute("cart");
        List<LineItem>lineItems = new ArrayList<LineItem>();
        lineItems = ((Order)session.getAttribute("order")).getLineItems();
        for(int i=0;i<lineItems.size();i++){
            System.out.println("remove"+lineItems.get(i).getItemId());
            cart.removeItemById(lineItems.get(i).getItemId());
            cartService.RemoveCart(lineItems.get(i).getItemId());
        }
        return "orders/FinalOrderPage";
    }
    @GetMapping("/view")
    public String viewLists(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        List<Order> orderList = orderService.getOrdersByUsername(username);
//        for(int i=0;i<orderList.size();i++){
//            System.out.println(i+orderList.get(i).getUsername());
//        }
        model.addAttribute("orderList",orderList);
        return "orders/ListOrders";
    }
    @GetMapping("/specific")
    public String viewOrder(int orderId, Model model) {
        Order order = orderService.getOrder(orderId);
        model.addAttribute("order", order);
        return "orders/ViewOrder";
    }
}
