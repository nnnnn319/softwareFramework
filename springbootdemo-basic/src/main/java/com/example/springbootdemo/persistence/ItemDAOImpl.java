package com.example.springbootdemo.persistence;

import com.example.springbootdemo.SpringbootdemoApplication;
import com.example.springbootdemo.domain.Item;
import com.example.springbootdemo.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ItemDAOImpl implements ItemDAO {
    private static final String GET_ITEM = "select  I.ITEMID, LISTPRICE, UNITCOST, SUPPLIER AS supplierId, I.PRODUCTID AS \"product.productId\", NAME AS \"product.name\", DESCN AS \"product.description\", CATEGORY AS \"product.categoryId\", STATUS, ATTR1 AS attribute1, ATTR2 AS attribute2, ATTR3 AS attribute3, ATTR4 AS attribute4, ATTR5 AS attribute5, QTY AS quantity from ITEM I, INVENTORY V, PRODUCT P where P.PRODUCTID = I.PRODUCTID and I.ITEMID = V.ITEMID and I.ITEMID = ?";
    private static final String UPDATE = "UPDATE INVENTORY SET QTY = QTY - ? WHERE ITEMID = ?";
    private static final String GET_INVENTORY="SELECT QTY AS value FROM INVENTORY WHERE ITEMID = ?";
    private static final String GET_LIST = "SELECT I.ITEMID, LISTPRICE, UNITCOST, SUPPLIER AS supplierId, I.PRODUCTID AS \"product.productId\", NAME AS \"product.name\", DESCN AS \"product.description\",CATEGORY AS \"product.categoryId\",  STATUS, ATTR1 AS attribute1,  ATTR2 AS attribute2, ATTR3 AS attribute3,ATTR4 AS attribute4, ATTR5 AS attribute5   FROM ITEM I, PRODUCT P  WHERE P.PRODUCTID = I.PRODUCTID   AND I.PRODUCTID = ?";

    @Autowired
    private DataSource dataSource;
    @Override
    public void updateInventoryQuantity(Map<String, Object> param) {

    }

    @Override
    public int getInventoryQuantity(String itemId) {
        return 0;
    }

    @Override
    public List<Item> getItemListByProduct(String productId) {
        List<Item> itemList=new ArrayList<Item>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_LIST);
            pStatement.setString(1,productId);
            ResultSet resultSet = pStatement.executeQuery();
//            System.out.println("get");
            while (resultSet.next()){
//                System.out.println("get");
                Item item = new Item();
                item.setItemId(resultSet.getString(1));
                item.setListPrice(resultSet.getBigDecimal(2));
                item.setUnitCost(resultSet.getBigDecimal(3));
                item.setSupplierId(resultSet.getInt(4));
                Product product =new Product();
                product.setProductId(resultSet.getString(5));
                product.setName(resultSet.getString(6));
                product.setDescription(resultSet.getString(7));
                product.setCategoryId(resultSet.getString(8));
                item.setProduct(product);
                item.setStatus(resultSet.getString(9));
                item.setAttribute1(resultSet.getString(10));
                item.setAttribute2(resultSet.getString(11));
                item.setAttribute3(resultSet.getString(12));
                item.setAttribute4(resultSet.getString(13));
                item.setAttribute5(resultSet.getString(14));
                itemList.add(item);
            }
            resultSet.close();
            pStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public Item getItem(String itemId) {
        Item item = null;
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_ITEM);
            pStatement.setString(1,itemId);
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                item = new Item();
                item.setItemId(resultSet.getString(1));
                item.setListPrice(resultSet.getBigDecimal(2));
                item.setUnitCost(resultSet.getBigDecimal(3));
                item.setSupplierId(resultSet.getInt(4));
                Product product =new Product();
                product.setProductId(resultSet.getString(5));
                product.setName(resultSet.getString(6));
                product.setDescription(resultSet.getString(7));
                product.setCategoryId(resultSet.getString(8));
                item.setProduct(product);
                item.setStatus(resultSet.getString(9));
                item.setAttribute1(resultSet.getString(10));
                item.setAttribute2(resultSet.getString(11));
                item.setAttribute3(resultSet.getString(12));
                item.setAttribute4(resultSet.getString(13));
                item.setAttribute5(resultSet.getString(14));
                item.setQuantity(resultSet.getInt(15));
            }
            resultSet.close();
            pStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void updateInventoryQuantity(String itemId, int quantity) {

    }
}
