package cn.smile.j7.sql;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Created by Smile on 2018/11/14.
 */
public class BasicJDBCLock {

    public static void main(String[] args) {

        final String DRIVER = "com.mysql.jdbc.Driver";
        final String DB_ADDR = "jdbc:mysql://localhost:3306/studydb";
        final String USER = "root";
        final String PASS = "smile#2002";
        Connection conn;
        try {
            Class.forName(DRIVER);

            /** 设置超时 + 连接 + 关闭Autocommit **/
            DriverManager.setLoginTimeout(1);
            conn = (Connection) DriverManager.getConnection(DB_ADDR, USER, PASS);
            conn.setAutoCommit(false);

            /** 设置语句执行超时 + 查询 **/
            String sql = "select * from user where userId='18500000003'";
            PreparedStatement pstmt = (PreparedStatement)conn.prepareStatement(sql);
            pstmt.setQueryTimeout(1);
            ResultSet rs = pstmt.executeQuery();

            /** 输出 **/
            int newAge = 0;
            int col = rs.getMetaData().getColumnCount();
            System.out.println("============================");
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if (i == 2) {
                        newAge = Integer.valueOf(rs.getString(i)).intValue();
                    }
                }
                System.out.println("");
            }
            System.out.println("============================");
            pstmt.close();

            /** 更新 **/
            newAge++;
            String sql2 = "update user set age='" + newAge + "' where userId='18500000003'";
            pstmt = (PreparedStatement) conn.prepareStatement(sql2);
            int i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);
            pstmt.close();

            /** 模拟行锁 **/
            for (int j=0; j<1000; j++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            /** 提交 **/
            conn.commit();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
