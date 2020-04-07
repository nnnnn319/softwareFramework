package com.example.demo.Controller;

import com.example.demo.domain.Account;
import com.example.demo.domain.CartItem;
import com.example.demo.domain.Item;
import com.example.demo.domain.Product;
import com.example.demo.service.AccountService;
import com.example.demo.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/account")
@SessionAttributes({"account","authenticated","username"})
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private CatalogService catalogService;
    //登陆页面
    @GetMapping("/viewSignOnForm")
    public String viewSignOnForm() {
        return "account/SignOnForm";
    }
    //登录
    @PostMapping("/login")
    public String login(String username, String password, Model model, String code_user) {
        Account account = accountService.getAccount(username, password);

        //验证码
//        if(code_user.equals("0")) {
//            String message = "Please enter a validation code!";
//            model.addAttribute("message",message);
//            return "account/SignOnForm";
//        }
//        if(!code_user.equals("1")) {
//            String message = "Wrong validation code!";
//            model.addAttribute("message",message);
//            return "account/SignOnForm";
//        }
        if (account == null) {
            String message = "Invalid username or password.  Signon failed.";
            model.addAttribute("message",message);
            return "account/SignOnForm";
        } else {
            account.setPassword(null);
            List<Product> myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
//            List<CartItem> cartItemList= cartService.getCartItemByUsername(username);
//            Item item = new Item();
//            String itemId = null;
//            CartItem cartItem = new CartItem();
//            for(int i=0;i<cartItemList.size();i++) {
//                cartItem = cartItemList.get(i);
//                itemId=cartItem.getItemId();
//                item = catalogService.getItem(itemId);
//                cartItem.setItem(item);
//            }
//            Cart cart = new Cart();
//            cart.setCartItems(cartItemList);
//            model.addAttribute("cart",cart);
            boolean authenticated = true;
            model.addAttribute("account", account);
//            model.addAttribute("myList",myList);
            model.addAttribute("authenticated",authenticated);
            model.addAttribute("username",account.getUsername());
            return "catalog/main";
        }
    }

}

