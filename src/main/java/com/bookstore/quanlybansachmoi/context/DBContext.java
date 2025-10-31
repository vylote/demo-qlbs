package com.bookstore.quanlybansachmoi.context;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {

    // Thông tin CSDL của bạn
    private static final String SERVER_NAME = "VLT\\VYLOTE"; // Dùng 2 dấu \\
    private static final String DB_NAME = "QLBanSach"; // Tên CSDL
    private static final String USER = "sa";
    private static final String PASSWORD = "3821"; // <-- THAY MẬT KHẨU 'sa' CỦA BẠN

    public static Connection getConnection() {
        String url = "jdbc:sqlserver://" + SERVER_NAME +
                ";databaseName=" + DB_NAME +
                ";encrypt=false;trustServerCertificate=true;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}