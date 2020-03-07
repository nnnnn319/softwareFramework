package mypetstore.persistence.impl;

import mypetstore.domain.LineItem;
import mypetstore.persistence.DBUtil;
import mypetstore.persistence.LineItemDAO;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LineItemDAOImpl implements LineItemDAO {
    private static final String GET_LINEITEM_LIST = "SELECT ORDERID, LINENUM AS lineNumber, ITEMID, QUANTITY, UNITPRICE FROM LINEITEM WHERE ORDERID = ?";
    private static final String INSERT_LINEITEM = "INSERT INTO LINEITEM (ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE) VALUES (?, ?, ?, ?, ?)";
    @Override
    public List<LineItem> getLineItemsByOrderId(int orderId) {
        List<LineItem> lineItems = new ArrayList<>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_LINEITEM_LIST);
            pStatement.setInt(1,orderId);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()){
                LineItem lineItem = new LineItem();
                lineItem.setOrderId(resultSet.getInt(1));
                lineItem.setLineNumber(resultSet.getInt(2));
                lineItem.setItemId(resultSet.getString(3));
                lineItem.setQuantity(resultSet.getInt(4));
                lineItem.setUnitPrice(resultSet.getBigDecimal(5));
                lineItems.add(lineItem);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePrepardStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return lineItems;
    }

    @Override
    public void insertLineItem(LineItem lineItem) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(INSERT_LINEITEM);
            pStatement.setString(1,String.valueOf(lineItem.getOrderId()));
            pStatement.setString(2,String.valueOf(lineItem.getLineNumber()));
            pStatement.setString(3,lineItem.getItemId());
            pStatement.setString(4,String.valueOf(lineItem.getQuantity()));
            pStatement.setString(5,String.valueOf(lineItem.getUnitPrice()));
            pStatement.executeUpdate();
            DBUtil.closePrepardStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
