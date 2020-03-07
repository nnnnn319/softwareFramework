package mypetstore.persistence.impl;

import mypetstore.domain.Diary;
import mypetstore.persistence.DBUtil;
import mypetstore.persistence.DiaryDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DiaryDAOImpl implements DiaryDAO {
    private static final String INSERT_DIARY = "INSERT INTO DIARY(USERNAME,DATE,OPERATION) VALUES(?,?,?)";
    @Override
    public void insertDiary(Diary diary) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(INSERT_DIARY);
            pStatement.setString(1,diary.getUsername());
            pStatement.setString(2,diary.getDate());
            pStatement.setString(3,diary.getOperation());
            pStatement.executeUpdate();
            DBUtil.closePrepardStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
