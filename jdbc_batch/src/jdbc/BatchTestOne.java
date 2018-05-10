package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 使用传统的jdbc进行数据的插入：
 * （1）利用for循环进行插入
 *  缺点：效率低，要频繁获取session，频繁获取连接
 */
public class BatchTestOne {

    public static void main(String [] args) throws SQLException, IOException, ClassNotFoundException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        //获取数据库连接
        connection=JdbcUtil.getConnection();
        //将默认的自动提交设置为手动提交
        JdbcUtil.begin(connection);

        String sql="insert into t_user(username,address) values(?,?)";

        preparedStatement=connection.prepareStatement(sql);

        long beginTime=System.currentTimeMillis();

        for (int i=0;i<10000;i++){
            preparedStatement.setString(1,"user"+(i+1));
            preparedStatement.setString(2,"address"+(i+1));
            preparedStatement.executeUpdate();
        }

        JdbcUtil.commit(connection);

        long endTime=System.currentTimeMillis();

        System.out.println("Total time:"+(endTime-beginTime));//Total time:1682

    }
}
