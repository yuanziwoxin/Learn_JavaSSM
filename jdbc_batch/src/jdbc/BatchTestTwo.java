package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 使用传统的jdbc进行插入：
 * （2）使用批处理进行插入
 * 缺点：代码与SQL耦合较高，代码量大
 */
public class BatchTestTwo {

    public static void main(String [] args) throws SQLException, IOException, ClassNotFoundException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;

        connection=JdbcUtil.getConnection();

        JdbcUtil.begin(connection);//设置auto-commit为false

        String sql="insert into t_user(username,address) values(?,?)";

        preparedStatement=connection.prepareStatement(sql);

        long beginTime=System.currentTimeMillis();

        for (int i=0;i<10000;i++){
            preparedStatement.setString(1,"username"+(i+1));
            preparedStatement.setString(2,"site"+(i+1));
            preparedStatement.addBatch();//先把数据插入到批中

            //再批量插入数据（一批插入1000条数据）
            if ((i+1)%1000==0){
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }
        }

        JdbcUtil.commit(connection);

        long endTime=System.currentTimeMillis();

        System.out.println("Total Time:"+(endTime-beginTime));//1348
    }
}
