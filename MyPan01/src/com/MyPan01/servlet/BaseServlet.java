// src/com/servlet/BaseServlet.java
package com.MyPan01.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//实现Thymeleaf的初始化和mysql的连接
public abstract class BaseServlet extends HttpServlet {
    protected TemplateEngine templateEngine;
    protected Connection mysqlConnection;

    @Override
    public void init() throws ServletException {
        super.init();
        //初始化thymeleaf
        WebApplicationTemplateResolver resolver = new WebApplicationTemplateResolver(
                JakartaServletWebApplication.buildApplication(getServletContext())
        );
        //设置模板解析器的路径
        resolver.setPrefix("/WEB-INF/templates/");
        //设置解析的后缀
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            mysqlConnection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/users", "borplay", "123456");
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Failed to initialize MySQL connection", e);
        }
    }
    @Override//便于关闭mysql
    public void destroy() {
        try {
            mysqlConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to close MySQL connection", e);
        }
    }
}