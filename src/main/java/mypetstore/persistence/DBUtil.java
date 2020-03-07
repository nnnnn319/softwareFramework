package mypetstore.persistence;

import java.sql.*;

public class DBUtil {
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url="jdbc:mysql://127.0.0.1:3306/mypetstore";
    private static String username="root";
    private static String password="123456";
    public static Connection getConnection()
            throws ClassNotFoundException , SQLException {
        Connection connection=null;
        try{
            Class.forName(driver);
            connection=DriverManager.getConnection(url,username,password);
            if(!connection.isClosed()){
//                System.out.println("成功");
            }

        }catch (ClassNotFoundException e){
            System.out.println("安装失败");
        }catch (SQLException e){
            System.out.println("连接失败");
        }
        return connection;
    }

    public static void closeStatement(Statement statement)
            throws SQLException {
        statement.close();
    }

    public static void closeResultSet(ResultSet resultSet)
            throws SQLException{
        resultSet.close();
    }

    public static void closePrepardStatement(PreparedStatement preparedStatement)
            throws SQLException{
        preparedStatement.close();
    }

    public static void closeConnection(Connection connection)
            throws Exception{
        if(connection!=null){
            connection.close();
        }
    }
    public static void main(String []args)
            throws SQLException, ClassNotFoundException {
        DBUtil.getConnection();

    }

}
