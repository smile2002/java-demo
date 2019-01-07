package cn.smile.j7.sql;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Smile on 2018/11/14.
 */
public class DataSourceDemo {
    public static void main(String[] args) {
        /** 方式一：读配置文件（文件名固定使用 c3p0-config.xml）**/
        ComboPooledDataSource ds = new ComboPooledDataSource("mysql");

        /** 方式二：手工设置 **/
        /*ComboPooledDataSource ds2 = new ComboPooledDataSource();
        try {
            ds2.setDriverClass("com.mysql.jdbc.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
            System.exit(1);
        }
        ds2.setJdbcUrl("");
        ds2.setUser("");
        ds2.setPassword("");
        ds2.setInitialPoolSize(5);
        ds2.setMaxPoolSize(10);
        ds2.setMaxIdleTime(20000);
        */

        int i, c;

        Connection[] connections = new Connection[100];

        try {
            for (c=0; c<4; c++) {
                connections[c] = ds.getConnection();
                /** 首次 getConnection 触发按配置建立所有的初始连接 **/
                System.out.println("get connection : " + c);
            }

            for (c=0; c<4; c++) {
                Connection conn = connections[c];
                conn.setAutoCommit(false);

                /** 设置语句执行超时 + 查询 **/
                String sql = "select * from user where userId='18500000003'";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setQueryTimeout(1);
                ResultSet rs = pstmt.executeQuery();

                /** 输出 **/
                int newAge = 0;
                int col = rs.getMetaData().getColumnCount();
                System.out.println("============================");
                while (rs.next()) {
                    for (i = 1; i <= col; i++) {
                        System.out.print(rs.getString(i) + "\t");
                    }
                    System.out.println("");
                }
                System.out.println("============================");
                rs.close();
                pstmt.close();

                /** Connection 的实现类为 NewProxyConnection
                    其close() 不会关闭物理连接，将归还连接池 **/
                conn.close();

                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
