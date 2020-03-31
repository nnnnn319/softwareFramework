package com.example.springbootdemo.persistence;

import com.example.springbootdemo.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class AccountDAOImpl implements AccountDAO {
    private static String FINDCOUNT = "SELECT * FROM SIGNON WHERE USERNAME = ? and PASSWORD = ?";
    @Autowired
    private DataSource dataSource;
    public void testConnection(){
        try {
            System.out.println(dataSource.getConnection());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public Account findByUsernameAndPassword(Account account) {
        Account nowAccount = null;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FINDCOUNT);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                nowAccount = new Account();
                nowAccount.setUsername(account.getUsername());
                nowAccount.setPassword(account.getPassword());
            }
            preparedStatement.close();
            resultSet.close();
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return nowAccount;
    }
}
