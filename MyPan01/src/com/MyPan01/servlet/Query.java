package com.MyPan01.servlet;

import com.MyPan01.model.User;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/query")
public class Query extends BaseServlet {
    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html;charset=UTF-8");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        WebContext webContext = new WebContext(JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req,resp));
        //webContext.setVariable("query_errorMsg","查询失败");
        List<User> users = new ArrayList<>();
        try(Statement statement = mysqlConnection.createStatement()){
            String sql = ("SELECT * FROM users01 WHERE email='" + email + "' and pwd='" + password + "';");
            try(ResultSet resultSet = statement.executeQuery(sql)){
                if (resultSet.next()) {
                    //回显数据
                    //webContext.setVariable("user.id",resultSet.getInt("id"));
                    //webContext.setVariable("user.username",resultSet.getString("username"));
                    //webContext.setVariable("user.email",resultSet.getString("email"));
                    users.add(new User(resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getString("email")));
                }else{
                    webContext.setVariable("query_errorMsg","查询失败");
                }
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            webContext.setVariable("query_errorMsg",e.getMessage());
        }
        webContext.setVariable("users",users);
        //webContext.setVariable("email",email);
        templateEngine.process("Query",webContext,resp.getWriter());
    }
}
