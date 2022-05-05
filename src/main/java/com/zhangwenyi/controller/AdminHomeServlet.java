package com.zhangwenyi.controller;

import com.zhangwenyi.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AdminHomeServlet", value = "/admin/home")
public class AdminHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        //TODO 1: get username from session
        if(session != null && session.getAttribute("user") != null) {
            //TODO 2: if username == admin - forward to /WEB-INF/views/admin/index.jsp
            User user = (User) session.getAttribute("user");
            if ("admin".equals(user.getUsername())) {
                request.getRequestDispatcher("../WEB-INF/views/admin/index.jsp").forward(request, response);
            } else {
                // if not admin
                session.invalidate(); // kill session
                request.setAttribute("message", "Unauthorized Access admin module !!!");
                request.getRequestDispatcher("../WEB-INF/views/index.jsp").forward(request, response);
            }
        } else {
            // TODO 3:else ask for login
            request.setAttribute("message", "Please login as admin!!!");
            request.getRequestDispatcher("../WEB-INF/views/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
