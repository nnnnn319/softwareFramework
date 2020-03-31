package org.csu.mypetstore.controller;

import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.OrderService;
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
@SessionAttributes({"order","shippingAddressRequired","confirmed","orderList"})
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private CartService cartService;

    @GetMapping("/listOrders")
    public String listOrders(Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        List<Order> orderList = orderService.getOrdersByUsername(account.getUsername());
        model.addAttribute("orderList",orderList);
        return "order/listOrders";
    }

    @GetMapping("/viewNewOrderForm")
    public String newOrderForm(Model model, HttpSession session) {
        Account account = (Account)session.getAttribute("account");
        Cart cart = (Cart)session.getAttribute("cart");
        boolean authenticated=(Boolean)session.getAttribute("authenticated");
        model.addAttribute("order",null);
        model.addAttribute("shippingAddressRequired",false);
        model.addAttribute("confirmed",false);
        model.addAttribute("orderList",null);
        session.setAttribute("order",null);
//        session.setAttribute("shippingAddressRequired",false);
        session.setAttribute("confirmed",false);
        session.setAttribute("orderList",null);

        if(account == null || !authenticated ) {
            String message ="You must sign on before attempting to check out.  Please sign on and try checking out again.";
            model.addAttribute("message",message);
            return "account/signOnForm";
        }
        else if(cart !=null){

            //页面多选框
            List<String> creditCard_types = new ArrayList<String>();
            creditCard_types.add("Visa");
            creditCard_types.add("MasterCard");
            creditCard_types.add("American Express");
            model.addAttribute("credit", creditCard_types);

            Order order= new Order();
            order.initOrder(account, cart);
            model.addAttribute("order", order);
            return "order/newOrderForm";
        }
        else {

            String message ="An order could not be created because a cart could not be found.";
            model.addAttribute("message",message);
            return "common/error";
        }
    }

    @PostMapping("/viewConfirmOrder")
    public String newOrder(Model model, HttpSession session) {
        Boolean shippingAddressRequired = (Boolean) model.getAttribute("shippingAddressRequired");
//        Boolean shippingAddressRequired = (Boolean)session.getAttribute("shippingAddressRequired");
        Boolean confirmed = (Boolean)session.getAttribute("confirmed");
        Order order = (Order)session.getAttribute("order");
        if(shippingAddressRequired) {
            model.addAttribute("shippingAddressRequired",false);
            session.setAttribute("shippingAddressRequired",false);
            return "order/shippingForm";
        }
        else if (!confirmed) {
            return "order/ConfirmOrder";
        }
        else if (order!= null) {
            orderService.insertOrder(order);
            Cart cart = (Cart) session.getAttribute("cart");
            session.setAttribute("cart",null);
            model.addAttribute("cart",cart);
            String message ="Thank you, your order has been submitted.";
            model.addAttribute("message",message);
            return "order/viewOrder";
        } else {
            String message ="An error occurred processing your order (order was null).";
            model.addAttribute("message",message);
            return "common/error";
        }
    }

    @GetMapping("/viewOrder")
    public String viewOrder(Model model, HttpSession session) {
        String username =(String) session.getAttribute("username");
        Boolean shippingAddressRequired = (Boolean)session.getAttribute("shippingAddressRequired");
        Account account = (Account) session.getAttribute("account");
        Order order = (Order) session.getAttribute("order");
        orderService.insertOrder(order);
        Cart cart = (Cart)session.getAttribute("cart");

        List<LineItem>lineItems = ((Order)session.getAttribute("order")).getLineItems();

        for(int i=0;i<lineItems.size();i++){
            cart.removeItemById(lineItems.get(i).getItemId());
            CartItem cartItem = new CartItem();
            cartItem.setItemId(lineItems.get(i).getItemId());
            cartItem.setCartId(username);
            cartService.removeCartItem(cartItem);
        }
        if (account.getUsername().equals(order.getUsername())) {
            return "order/viewOrder";
        } else {
            model.addAttribute("order",null);
            session.setAttribute("order",null);
            String message ="You may only view your own orders.";
            model.addAttribute("message",message);
            return "common/error";
        }
    }
}
