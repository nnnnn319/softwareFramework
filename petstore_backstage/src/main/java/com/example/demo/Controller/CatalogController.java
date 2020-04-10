package com.example.demo.Controller;

import com.example.demo.domain.Account;
import com.example.demo.domain.Category;
import com.example.demo.domain.Item;
import com.example.demo.domain.Product;
import com.example.demo.images.ChooseImage;
import com.example.demo.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.model.IModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/catalog")
@SessionAttributes({"categoryList","productList","category", "categoryId","itemList","product", "item"})
public class CatalogController {
    @Autowired
    private CatalogService catalogService;

    //首页
    @GetMapping("/view")
    public String index() {
        return "catalog/main";
    }

    //新增、修改、查询、删除Category页面
    @GetMapping("/viewCategoryList")
    public String categoryList(Model model, HttpSession session) {
        Account account = (Account)session.getAttribute("account");
        if(account == null) {
            String message = "Please login first";
            model.addAttribute("message",message);
            return "account/signOnForm";
        }
        List<Category> categoryList = catalogService.getCategoryList();
        processCategoryDescription(categoryList);
        model.addAttribute("categoryList",categoryList);
        return "catalog/categoryList";
    }

    //删除Category
    @GetMapping("/categoryDelete")
    public String categoryDelete(Model model, String categoryId) {
        catalogService.deleteCategory(categoryId);
        String message = "Delete Successfully";
        model.addAttribute("message",message);
        List<Category> categoryList = catalogService.getCategoryList();
        processCategoryDescription(categoryList);
        model.addAttribute("categoryList",categoryList);
        return "catalog/categoryList";
    }

    //添加Category
    @GetMapping("/categoryAdd")
    public String categoryAdd() {
        return "catalog/newCategory";
    }

    //确定添加Category
    @PostMapping("/categoryAdd_sure")
        public String categoryAdd_sure(Model model, String description, String name){
        String catId = name.toUpperCase();
        catalogService.addCategory(description, name, catId);
        //成功添加
        String message = "Add Successfully";
        model.addAttribute("message",message);
        return "catalog/categoryList";
    }

    //尝试文件上传
    @PostMapping("/fileAdd")
    public String fileAdd(@RequestParam(value = "file") MultipartFile file, String description, String name, Model model, HttpServletRequest request) {
        //处理文件
        Map<String,Object> map = new HashMap<>(2);
        if(file.getSize()>1024102410){
            map.put("code",500);
            map.put("message","文件过大，请上传10M以内的文件");
            System.out.println("文件上传失败");
        }
        String path = request.getContextPath();
        String basePath = request.getScheme() +" ?/" +request.getServerName() + ": "+ request.getServerPort() + path;
        if(file.isEmpty()){
            throw new RuntimeException("文件为空");
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String filePath = "D://tie//administer//petstore_backstage//src//main//resources//static//css//images//";
        File dest = new File(filePath + fileName);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try{
            file.transferTo(dest);
        }catch (IOException e){
            e.printStackTrace();
        }
        String filename ="/test/" + fileName;
//        model.addAttribute("filename",filename);
//输出文件的名称以及文件的大小
        System.out.println("文件:"+fileName+"的大小是:"+dest.length()/1024+"KB");
//输出相应的访问路径
        System.out.println(basePath + "/test/"+ fileName);
    //插入数据库
        String catId = name.toUpperCase();
        String descn = "<image src=\"../images/"+fileName+"\"><font size=\"5\" color=\"blue\">"+ name+"</font>";
        catalogService.addCategory(descn, name, catId);
        return "catalog/categoryList";
    }



    //取消
    @GetMapping("CategoryCancel")
    public String category_cancel(Model model){
        List<Category> categoryList = catalogService.getCategoryList();
        processCategoryDescription(categoryList);
        model.addAttribute("categoryList",categoryList);
        return "catalog/categoryList";
    }

    //修改Category
    @GetMapping("/CategoryModify")
    public String categoryModify(Model model,String categoryId) {
        Category category = catalogService.getCategory(categoryId);
        model.addAttribute("category",category);
        model.addAttribute("categoryId", categoryId);
       return "catalog/CategoryModify";
    }

    //确认修改Category
    @PostMapping("/categoryModify_sure")
    public String categoryModify_sure(Model model, String name, HttpSession session,
                                      @RequestParam(value = "description") MultipartFile file, HttpServletRequest request) {
        ChooseImage chooseImage = new ChooseImage();
        String fileName = chooseImage.choose(file, request);
        String descn = "<image src=\"../images/"+fileName+"\"><font size=\"5\" color=\"blue\">"+ name+"</font>";
        String categoryId = (String) session.getAttribute("categoryId");
        catalogService.updateCategoryName(name, descn, categoryId);
        //成功修改
        String message = "Modify Successfully";
        model.addAttribute("message",message);
        //重新读取列表
        List<Category> categoryList = catalogService.getCategoryList();
        processCategoryDescription(categoryList);

        model.addAttribute("categoryList",categoryList);
        return "catalog/categoryList";
    }

    //查看Product
    @GetMapping("/viewCategory")
    public String viewCategory(String categoryId, Model model) {
        if (categoryId != null) {
            List<Product> productList = catalogService.getProductListByCategory(categoryId);
            Category category = catalogService.getCategory(categoryId);
            processProductDescription(productList);
            model.addAttribute("productList", productList);
            model.addAttribute("category", category);
        }
        return "catalog/category";
    }

    //删除Product
    @GetMapping("/productDelete")
    public String productDelete(Model model, String productId) {
        catalogService.deleteProduct(productId);
        //成功删除
        String message = "Delete Successfully";
        model.addAttribute("message",message);
        return "catalog/category";
    }

    //添加product
    @GetMapping("/productAdd")
    public String productAdd() {
        return "catalog/newProduct";
    }

    //确定添加product
    @PostMapping("/productAdd_sure")
    public String productAdd_sure(Model model, HttpSession session, String productId, String name, String descriptionText, @RequestParam(value = "descriptionImage") MultipartFile file, HttpServletRequest request){
        String categoryId =((Category)session.getAttribute("category")).getCategoryId();
        System.out.println("categoryId "+categoryId);
        //获取文件
//        Map<String,Object> map = new HashMap<>(2);
//        if(file.getSize()>1024102410){
//            map.put("code",500);
//            map.put("message","文件过大，请上传10M以内的文件");
//            System.out.println("文件上传失败");
//        }
//        String path = request.getContextPath();
//        String basePath = request.getScheme() +" ?/" +request.getServerName() + ": "+ request.getServerPort() + path;
//        if(file.isEmpty()){
//            throw new RuntimeException("文件为空");
//        }
//        String fileName = file.getOriginalFilename();
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));
//        String filePath = "D://tie//administer//petstore_backstage//src//main//resources//static//css//images//";
//        File dest = new File(filePath + fileName);
//        if(!dest.getParentFile().exists()){
//            dest.getParentFile().mkdirs();
//        }
//        try{
//            file.transferTo(dest);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        String filename ="/test/" + fileName;
////输出文件的名称以及文件的大小
//        System.out.println("文件:"+fileName+"的大小是:"+dest.length()/1024+"KB");
////输出相应的访问路径
//        System.out.println(basePath + "/test/"+ fileName);
        ChooseImage chooseImage = new ChooseImage();
        String fileName = chooseImage.choose(file, request);
        String descn = "<image src=\"../images/"+fileName;
        catalogService.addProduct(productId, categoryId, name, descn+descriptionText);
        //成功添加
        String message = "Add Successfully";
        model.addAttribute("message",message);
        return "catalog/category";
    }

    //取消
    @GetMapping("ProductCancel")
    public String product_cancel(Model model){
        List<Category> categoryList = catalogService.getCategoryList();
        processCategoryDescription(categoryList);
        model.addAttribute("categoryList",categoryList);
        return "catalog/category";
    }

    //修改Product
    @GetMapping("/ProductModify")
    public String productModify(Model model,String productId) {
        Product product = catalogService.getProduct(productId);
        processProductDescription(product);
        model.addAttribute("product",product);
        return "catalog/ProductModify";
    }

    //确认修改Product
    @PostMapping("/productModify_sure")
    public String productModify_sure(Model model, String productId,
                                     String name, String descriptionText, @RequestParam(value = "descriptionImage") MultipartFile file, HttpServletRequest request) {
        ChooseImage chooseImage = new ChooseImage();
        String fileName = chooseImage.choose(file, request);
        String descn = "<image src=\"../images/"+fileName;
        catalogService.updateProduct(productId, name, descn+descriptionText);
        //成功修改
        String message = "Modify Successfully";
        model.addAttribute("message",message);
        //重新查询

        return "catalog/category";
    }

    //查看Item
    @GetMapping("/viewProduct")
    public String viewProduct(String productId, Model model) {
        if (productId != null) {
            List<Item> itemList = catalogService.getItemListByProduct(productId);
            for (int i=0; i<itemList.size(); i++){
                itemList.get(i).setQuantity(catalogService.getQuantity(itemList.get(i).getItemId()));
            }
            Product product = catalogService.getProduct(productId);
            processProductDescription(product);
            model.addAttribute("product", product);
            model.addAttribute("itemList", itemList);
        }
        return "catalog/product";
    }

    //删除Item
    @GetMapping("/itemDelete")
    public String itemDelete(Model model, String itemId) {
        catalogService.deleteItem(itemId);
        //成功删除
        String message = "Delete Successfully";
        model.addAttribute("message",message);
        return "catalog/product";
    }

    //添加item
    @GetMapping("/itemAdd")
    public String itemAdd() {
        return "catalog/newItem";
    }

    //确定添加item
    @PostMapping("/itemAdd_sure")
    public String itemAdd_sure(Model model, Item item, HttpSession session){
        item.setProductId(((Product)session.getAttribute("product")).getProductId());
        item.setSupplierId(1);
        catalogService.addItem(item);
        //成功添加
        String message = "Add Successfully";
        model.addAttribute("message",message);
        return "catalog/product";
    }

    //取消
    @GetMapping("ItemCancel")
    public String item_cancel(Model model){
        return "catalog/product";
    }

    //修改Item
    @GetMapping("/ItemModify")
    public String itemModify(Model model,String itemId) {
        Item item = catalogService.getItem(itemId);
        item.setQuantity(catalogService.getQuantity(itemId));
        model.addAttribute("item",item);
        return "catalog/ItemModify";
    }

    //确认修改Item
    @PostMapping("/itemModify_sure")
    public String itemModify_sure(Model model, String itemId, String attribute1,
                                  BigDecimal listPrice, int quantity) {
        catalogService.updateItem(itemId, attribute1, listPrice);
        catalogService.updateQuantity(itemId, quantity);
        //成功修改
        String message = "Modify Successfully";
        model.addAttribute("message",message);
        return "catalog/product";
    }

    private void processProductDescription(Product product){
        String [] temp = product.getDescription().split("\"");
        product.setDescriptionImage("../css/"+temp[1]);
        product.setDescriptionText(temp[2].substring(1));
    }

    private void processCategoryDescription(List<Category> categoryList){
        for(Category category : categoryList) {
            processCategoryDescription(category);
        }
    }

    private void processCategoryDescription(Category category){
        String [] temp = category.getDescription().split("\"");
        category.setDescription("../css/"+temp[1]);
    }

    private void processProductDescription(List<Product> productList){
        for(Product product : productList) {
            processProductDescription(product);
        }
    }
}
