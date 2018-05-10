package jdbc;

import java.sql.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JdbcUtil {
    //处理数据库事务
    // (1)提交事务
    public static void commit(Connection connection){
        if (connection!=null){
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //事务的回滚
    public static void rollback(Connection connection){
        if (connection!=null){
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //事务的开始（这里修改自动提交为手动提交）
    public static void begin(Connection connection){
        if (connection!=null){
            try {
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取数据库连接
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        Properties properties=new Properties();
        //速度快的方式
        //new fileinputStream();
        InputStream is=JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        //jdbc文件内容以流的方法加载到properties文件中
        properties.load(is);

        String driver=properties.getProperty("driver");
        String jdbcUrl=properties.getProperty("jdbcUrl");
        String username=properties.getProperty("username");
        String password=properties.getProperty("password");

        Class.forName(driver);

        return  DriverManager.getConnection(jdbcUrl,username,password);
    }

    /**
     * 通用的关闭资源的方法
     * @param connection
     * @param statement
     * @param resultSet
     */
    public static void closeResources(Connection connection, Statement statement, ResultSet resultSet){
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
