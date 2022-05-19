package com.zhangwenyi.controller;

import com.zhangwenyi.dao.UserDao;
import com.zhangwenyi.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    private Connection con;

    @Override
    public void init() throws ServletException {
        super.init();

        con = (Connection) getServletContext().getAttribute("con");//name of attibute
        //please check the video live demo#4

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //when method =post -request come in doPost

        //get all data - from Request
        User user = new User();
        user.setUsername(request.getParameter("username"));//get Username <input type="text" name="username" />
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        user.setGender(request.getParameter("gender"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date = format.parse(request.getParameter("birthdate"));
            java.sql.Date birthdate = new java.sql.Date(date.getTime());
            user.setBirthdate(birthdate);
            UserDao userDao = new UserDao();
            boolean saveUser = userDao.saveUser(con, user);
            if (saveUser) {
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
            } else {
                System.out.println("register error!");
                request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request, response);
            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }
}
