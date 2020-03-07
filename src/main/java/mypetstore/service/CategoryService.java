package mypetstore.service;

import mypetstore.domain.Category;
import mypetstore.domain.Item;
import mypetstore.domain.Product;
import mypetstore.persistence.CatagoryDAO;
import mypetstore.persistence.ItemDAO;
import mypetstore.persistence.ProductDAO;
import mypetstore.persistence.impl.CategoryDAOImpl;
import mypetstore.persistence.impl.ItemDAOImpl;
import mypetstore.persistence.impl.ProductDAOImpl;

import java.util.List;

public class CategoryService {
    private CatagoryDAO category;
    private ProductDAO product;
    private ItemDAO itemDAO;
    public CategoryService(){
        category = new CategoryDAOImpl();
        product = new ProductDAOImpl();
        itemDAO =new ItemDAOImpl();
    }

    public List<Category> getCategoryList() {
        return category.getCategoryList();
    }

    public Category getCategory(String categoryId) {
        return category.getCategory(categoryId);
    }

    public Product getProduct(String productId) {
        return product.getProduct(productId);
    }

    public List<Product> getProductListByCategory(String categoryId) {
        return product.getProductListByCategory(categoryId);
    }

    // TODO enable using more than one keyword
    public List<Product> searchProductList(String keyword) {
        return product.searchProductList("%" + keyword.toLowerCase() + "%");
    }
    public List<Item> getItemListByProduct(String productId) {
        return itemDAO.getItemListByProduct(productId);
    }

    public Item getItem(String itemId) {
        return itemDAO.getItem(itemId);
    }

    public boolean isItemInStock(String itemId) {
        return itemDAO.getInventoryQuantity(itemId) > 0;
    }
}
