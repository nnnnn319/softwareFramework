package mypetstore.persistence.impl;

import mypetstore.domain.Cart;
import mypetstore.domain.CartItem;
import mypetstore.domain.Item;
import mypetstore.domain.Product;
import mypetstore.persistence.CartDAO;
import mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartDAOImpl implements CartDAO {
    private static final String INSERT_CART = "INSERT INTO CART (USERNAME, ITEMID, PRODUCTID, DESCN, INSTOCK, QUANTITY, LISTPRICE, TOTALPRICE) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
    private static final String GET_CART = "SELECT ITEMID, PRODUCTID, DESCN, INSTOCK, QUANTITY, LISTPRICE, TOTALPRICE  FROM CART WHERE USERNAME = ?";
    private static final String REMOVE = "DELETE FROM CART WHERE ITEMID = ?";
    private static final String UPDATE = "UPDATE CART SET QUANTITY = ? WHERE ITEMID = ?";
    private static final String SELECT_ITEM = "SELECT ITEMID FROM CART WHERE USERNAME=?";
    private static final String INCREASE_QUANTITY = "SELECT QUANTITY FROM CART WHERE ITEMID = ?";
    @Override
    public void insertIntoCart(CartItem cartItem) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(INSERT_CART);
            pStatement.setString(1,cartItem.getUsername());
            pStatement.setString(2,cartItem.getItem().getItemId());
            pStatement.setString(3,cartItem.getItem().getProductId());
//            String description = cartItem.getItem().getAttribute1()+cartItem.getItem().getAttribute2()
//                    +cartItem.getItem().getAttribute3()+cartItem.getItem().getAttribute4()+cartItem.getItem().getAttribute5();
            String description = " ";
            List<String> list_description = new ArrayList<>();
            list_description.add(cartItem.getItem().getAttribute1());
            list_description.add(cartItem.getItem().getAttribute2());
            list_description.add(cartItem.getItem().getAttribute3());
            list_description.add(cartItem.getItem().getAttribute4());
            list_description.add(cartItem.getItem().getAttribute5());
            for(int i=0;i<5;i++){
                if(list_description.get(i) != null){
                    description=description+list_description.get(i);
                }
                else
                    break;
            }
            pStatement.setString(4,description);
            pStatement.setBoolean(5,cartItem.isInStock());
            pStatement.setInt(6,cartItem.getQuantity());
            pStatement.setBigDecimal(7,cartItem.getItem().getListPrice());
            pStatement.setBigDecimal(8,cartItem.getTotal());
            pStatement.executeUpdate();
            DBUtil.closePrepardStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public List<CartItem> getCartItem(String username) {
        List<CartItem> cartItems = new ArrayList<>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_CART);
            pStatement.setString(1,username);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()){
                CartItem cartItem = new CartItem();
                Item item = new Item();
                cartItem.setUsername(username);
                item.setItemId(resultSet.getString(1));
                item.setProductId(resultSet.getString(2));
                Product product = new Product();
                product.setProductId(resultSet.getString(2));
                item.setProduct(product);
                item.setAttribute1(resultSet.getString(3));
                if(resultSet.getInt(4)==1){
                    cartItem.setInStock(true);
                }
                else {
                    cartItem.setInStock(false);
                }
                cartItem.setQuantity(resultSet.getInt(5));
                System.out.println("aaa"+cartItem.getQuantity());
                item.setQuantity(resultSet.getInt(5));
                item.setListPrice(resultSet.getBigDecimal(6));
                cartItem.setItem(item);
                cartItems.add(cartItem);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePrepardStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cartItems;
    }

    @Override
    public void removeCartItem(String itemId) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(REMOVE);
            pStatement.setString(1,itemId);
            pStatement.executeUpdate();
            DBUtil.closePrepardStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateCart(String itemId,int quantity){
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(UPDATE);
            pStatement.setInt(1,quantity);
            pStatement.setString(2,itemId);
            pStatement.executeUpdate();
            System.out.println("update");
            DBUtil.closePrepardStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String selectItem(String username,String itemId) {
        String result = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(SELECT_ITEM);
            pStatement.setString(1,username);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                if(itemId==resultSet.getString(1)){
                    result=itemId;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void increaseItemQuantity(String itemId) {
        try{
            int quantity=0;
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(INCREASE_QUANTITY);
            pStatement.setString(1,itemId);
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                quantity = resultSet.getInt(1)+1;
            }
            updateCart(itemId,quantity);
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePrepardStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
