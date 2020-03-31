package org.csu.mypetstore.controller;

import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.CatalogService;
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
@RequestMapping("/account")
@SessionAttributes({"account","myList","authenticated","username","cart"})
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private CartService cartService;

    @GetMapping("/viewNewAccountForm")
    public String viewNewAccountForm() {
        return "account/newAccountForm";
    }

    @GetMapping("/viewHelp")
    public String viewHelp() {
        return "common/help";
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        Account account = accountService.getAccount(username, password);
        if (account == null) {
            String message = "Invalid username or password.  Signon failed.";
            model.addAttribute("message",message);
            return "account/SignOnForm";
        } else {
            account.setPassword(null);
            List<Product> myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
            List<CartItem> cartItemList= cartService.getCartItemByUsername(username);
            Item item = new Item();
            String itemId = null;
            CartItem cartItem = new CartItem();
            for(int i=0;i<cartItemList.size();i++) {
                cartItem = cartItemList.get(i);
                itemId=cartItem.getItemId();
                item = catalogService.getItem(itemId);
                cartItem.setItem(item);
            }
            Cart cart = new Cart();
            cart.setCartItems(cartItemList);
            model.addAttribute("cart",cart);
            boolean authenticated = true;
            model.addAttribute("account", account);
            model.addAttribute("myList",myList);
            model.addAttribute("authenticated",authenticated);
            model.addAttribute("username",account.getUsername());
            return "catalog/main";
        }
    }

    @GetMapping("/viewSignOnForm")
    public String viewSignOnForm() {
        return "account/SignOnForm";
    }

    @GetMapping("/viewEditAccountForm")
    public String viewEditAccountForm(Model model,HttpSession session) {
        Account account=(Account)session.getAttribute("account");
        if(account ==null) {
            String message ="Please login first";
            model.addAttribute("message",message);
            return "account/SignOnForm";
        }
        else{
            return "account/editAccountForm";
        }
    }

    @GetMapping("/viewSignOut")
    public String viewSignOut(Model model,HttpSession session) {
        model.addAttribute("account", null);
        model.addAttribute("myList",null);
        model.addAttribute("authenticated",false);
        model.addAttribute("username",null);
        session.setAttribute("account", null);
        session.setAttribute("myList",null);
        session.setAttribute("authenticated",false);
        session.setAttribute("username",null);
        return "catalog/main";
    }

}
