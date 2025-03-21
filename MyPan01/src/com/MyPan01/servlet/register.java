package com.MyPan01.servlet;
//编写注册逻辑

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/register")
public class register extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        resp.setContentType("text/html;charset=UTF-8");
        WebContext webContext = new WebContext(JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req,resp));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        try{
            try (Statement statement = mysqlConnection.createStatement()) {
                String sql_if_exist = "SELECT * FROM users01 WHERE username = '" + username + "'" + "or email='" + email + "';";
                ResultSet resultSet = statement.executeQuery(sql_if_exist);
                if (resultSet.next()) {
                    webContext.setVariable("register_errorMsg","用户名或邮箱已存在");
                    //之后进行Thymeleaf渲染
                } else {
                    String sql = "INSERT INTO users01 (username,pwd,email) VALUES ('" + username + "','" + password + "','" + email + "');";//此处可以优化，用PreparedStatement
                    int rows = statement.executeUpdate(sql);
                    if (rows > 0) {
                        webContext.setVariable("register_errorMsg","注册成功"+username);
                        //清空register_errorMsg中的信息
                        webContext.setVariable("register_errorMsg",null);
                        //之后进行Thymeleaf渲染
                    } else {
                        PrintWriter writer = resp.getWriter();
                        webContext.setVariable("register_errorMsg","注册失败");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Thymeleaf渲染
        templateEngine.process("index",webContext,resp.getWriter());
    }
}