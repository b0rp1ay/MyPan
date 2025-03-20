package com.MyPan01.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class login extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        // 设置MIME类型和字符集
        response.setContentType("text/html;charset=utf-8");
        // 获取请求参数
        String username = request.getParameter("email");
        String password = request.getParameter("password");
        // 判断用户名和密码是否正确
        PrintWriter writer = response.getWriter();
        writer.write("登录成功"+username);

    }
}
