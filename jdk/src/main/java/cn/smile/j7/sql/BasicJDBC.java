package cn.smile.j7.sql;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Smile on 2018/11/14.
 */
public class BasicJDBC {

    public static void printSqlError(SQLException e) {
        int errCode = e.getErrorCode();
        String sqlState = e.getSQLState();
        String message = e.getMessage();
        String localMsg = e.getLocalizedMessage();

        System.out.println("Error Code = " + errCode);
        System.out.println("SQL State  = " + sqlState);
        System.out.println("Message    = " + message);
        System.out.println("Local Msg  = " + localMsg);
    }
    public static void main(String[] args) {

        final String DRIVER = "com.mysql.jdbc.Driver";
        final String DB_ADDR = "jdbc:mysql://localhost:3306/studydb";
        final String USER = "root";
        final String PASS = "smile#2002";
        Connection conn;

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /** 设置超时 + 连接 + 关闭Autocommit **/
        DriverManager.setLoginTimeout(1);
        int newAge = 0;

        try {
            conn = (Connection) DriverManager.getConnection(DB_ADDR, USER, PASS);
            conn.setAutoCommit(false);

            /** 设置语句执行超时 + 查询 **/
            String sql = "select * from user where userId='18500000003'";
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setQueryTimeout(1);
            ResultSet rs = pstmt.executeQuery();   /**查不到不会抛出异常**/

            /** 输出 **/
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
            rs.close();
            pstmt.close();

            /** 更新 **/

            newAge++;
            /*String sql2 = "update user set age='" + newAge + "' where userId='18500000003'";
            pstmt = (PreparedStatement) conn.prepareStatement(sql2);
            pstmt.setQueryTimeout(10);*/

            String sql2 = "update user set age=? where userId=?";
            pstmt = (PreparedStatement) conn.prepareStatement(sql2);
            pstmt.setInt(1, newAge);
            pstmt.setString(2, "123"); //"18500000003");
            pstmt.setQueryTimeout(10);

            /** 更新不到不会抛出异常，只是返回值为0 **/
            int i = pstmt.executeUpdate();
            System.out.println("resutl: " + i);
            pstmt.close();

            /** 提交 **/
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            printSqlError(e);
        }
    }


    /** Insert **/
    /*
    int i = 0;
    String sql = "insert into students (Name,Sex,Age) values(?,?,?)";
    PreparedStatement pstmt;
    try {
        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        pstmt.setString(1, student.getName());
        pstmt.setString(2, student.getSex());
        pstmt.setString(3, student.getAge());
        i = pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return i;
    */


    /** Update **/
    /*
    int i = 0;
    String sql = "update students set Age='" + student.getAge() + "' where Name='" + student.getName() + "'";
    PreparedStatement pstmt;
    try {
        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        i = pstmt.executeUpdate();
        System.out.println("resutl: " + i);
        pstmt.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return i;
    */

    /** Delete **/
    /*
    int i = 0;
    String sql = "delete from students where Name='" + name + "'";
    PreparedStatement pstmt;
    try {
        pstmt = (PreparedStatement) conn.prepareStatement(sql);
        i = pstmt.executeUpdate();
        System.out.println("resutl: " + i);
        pstmt.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return i;
    */
}
