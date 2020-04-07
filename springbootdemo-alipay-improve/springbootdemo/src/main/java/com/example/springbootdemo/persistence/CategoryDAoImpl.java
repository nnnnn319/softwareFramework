package com.example.springbootdemo.persistence;

import com.example.springbootdemo.domain.Catagory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDAoImpl implements CategoryDAO {
    private static final String GET_CATAGORY_LIST=
            "SELECT CATID AS CatagoryId, NAME, DESCN AS description FROM Catagory";
    private static final String GET_CATAGORY =
            "SELECT CATID AS CatagoryId , NAME, DESCN AS description FROM Catagory WHERE CATID = ?";
    @Autowired
    private DataSource dataSource;
    @Override
    public List<Catagory> getCategoryList() {
        List<Catagory> CatagoryList=new ArrayList<Catagory>();
        try{
            Connection connection= dataSource.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(GET_CATAGORY_LIST);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Catagory Catagory = new Catagory();
                Catagory.setCategoryId(resultSet.getString(1));
                Catagory.setName(resultSet.getString(2));
                Catagory.setDescription(resultSet.getString(3));
                CatagoryList.add(Catagory);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CatagoryList;
    }

    @Override
    public Catagory getCategory(String CatagoryId) {
        Catagory Catagory =null;
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CATAGORY);
            preparedStatement.setString(1,CatagoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Catagory = new Catagory();
                Catagory.setCategoryId(resultSet.getString(1));
                Catagory.setName(resultSet.getString(2));
                Catagory.setDescription(resultSet.getString(3));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return Catagory;
    }
}
