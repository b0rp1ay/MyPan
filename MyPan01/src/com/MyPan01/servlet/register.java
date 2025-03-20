package com.MyPan01.servlet;
//编写注册逻辑

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.*;

@WebServlet("/register")
public class register extends HttpServlet {
    private final String DB_url = "jdbc:mysql://localhost:3306/users";
    private final String DBusername = "borplay";
    private final String DBpassword = "30568024";
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        //writer.write("hhhhh");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try(
                Connection connection = DriverManager.getConnection(DB_url,DBusername,DBpassword)){
                try(Statement statement = connection.createStatement()){
                    String sql = "INSERT INTO users01 (username,pwd,email) VALUES ('"+username+"','"+password+"','"+email+"');";//此处可以优化，用PreparedStatement
                    int rows = statement.executeUpdate(sql);
                    if(rows>0){
                        writer.write("注册成功！");
                    }else{
                        writer.write("注册失败！");
                    }
                }
            }

        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}