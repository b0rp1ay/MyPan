//package com.MyPan01.mysql;
//
//import java.sql.*;
//import java.util.Scanner;
//
//public class input {
//    // MySQL 连接信息
//    private static final String URL = "jdbc:mysql://localhost:3306/user_db?useSSL=false&serverTimezone=UTC";
//    private static final String USER = "root";  // 你的数据库用户名
//    private static final String PASSWORD = "yourpassword";  // 你的数据库密码
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("请输入用户名:");
//        String username = scanner.nextLine();
//
//        System.out.println("请输入密码:");
//        String password = scanner.nextLine();
//
//        registerUser(username, password);
//    }
//
//    public static void registerUser(String username, String password) {
//        // 构造 SQL 语句（⚠️ 存在 SQL 注入风险）
//        String sql = "INSERT INTO users (username, password) VALUES ('" + username + "', '" + password + "')";
//
//        try (
//                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//                Statement stmt = conn.createStatement()
//        ) {
//            int rows = stmt.executeUpdate(sql); // 执行 SQL 语句
//            if (rows > 0) {
//                System.out.println("✅ 注册成功！");
//            } else {
//                System.out.println("❌ 注册失败！");
//            }
//        } catch (SQLIntegrityConstraintViolationException e) {
//            System.out.println("❌ 该用户名已被注册！");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
//
