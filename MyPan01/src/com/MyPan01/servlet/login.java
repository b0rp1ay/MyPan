package com.MyPan01.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/login")
public class login extends BaseServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 设置MIME类型和字符集
        response.setContentType("text/html;charset=utf-8");
        WebContext webContext = new WebContext(JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(request,response));
        //// 获取请求参数
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //进行sql查询
        try(Statement statement = mysqlConnection.createStatement()){
            String sql = "SELECT * FROM users01 WHERE email = '" + email + "' and pwd = '" + password + "';";
            try(ResultSet resultSet = statement.executeQuery(sql)){
                if(resultSet.next()){
                    //登录成功
                    webContext.setVariable("login_errorMsg","登录成功"+email);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //渲染thymeleaf
        //WebContext webContext = new WebContext(JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(request,response));
        //webContext.setVariable("login_errorMsg","登录成功"+email);
        templateEngine.process("index",webContext,response.getWriter());
    }
}
