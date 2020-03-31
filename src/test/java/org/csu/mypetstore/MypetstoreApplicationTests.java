package org.csu.mypetstore;

import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.CatalogService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@MapperScan("org.csu.mypetstore.persistence")
class MypetstoreApplicationTests {

    @Autowired
    CatalogService catalogService;
    @Autowired
    CartService cartService;

    @Test
    void contextLoads() {
    }

    @Test
    void testCategory() {
        Category c=catalogService.getCategory("BIRDS");
        System.out.println(c.getCategoryId()+","+c.getName()+","+c.getDescription());
    }

    @Test
    void testProduct() {
        Product product = catalogService.getProduct("AV-CB-01");
        System.out.println(product.getDescription());
        String [] temp = product.getDescription().split("\"");
        product.setDescriptionImage("../css/"+temp[1]);
        product.setDescriptionText(temp[2].substring(1));
        System.out.println(product.getDescriptionImage());
        System.out.println(product.getDescriptionText());

    }

    @Test
    void testItem() {
        List<CartItem> itemList = cartService.getCartItemByUsername("j2ee");
        System.out.println(itemList.size());
    }

    @Test
    void testInvventory() {
        List<CartItem> itemList = cartService.getCartItemByUsername("j2ee");
        CartItem cartItem = itemList.get(0);
//        catalogService.updateInventoryQuantity(cartItem.getItemId(),1);
    }

}
