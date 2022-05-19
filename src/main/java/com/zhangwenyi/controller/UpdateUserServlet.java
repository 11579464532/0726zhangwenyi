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

@WebServlet(name = "UpdateUserServlet", value = "/updateUser")
public class UpdateUserServlet extends HttpServlet {
    Connection con = null;

    @Override
    public void init() throws ServletException {
        super.init();
        /// TODO 1: GET 4 CONTEXT PARAM - DRIVER , URL , USERNAME , PASSWORD
        // TODO 2: GET JDBC connection
        //only one one
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //when user click updateUser - method is get
        request.getSession(false).invalidate();
        request.setAttribute("message", "you are successfully logged out!");
        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //when method =post -request come in doPost
        //get all data - from Request
        User user = new User();
        User user1 = (User) request.getSession().getAttribute("user");

        user.setId(user1.getId());
        user.setUsername(request.getParameter("username"));//get Username <input type="text" name="username" />
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        user.setGender(request.getParameter("gender"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
        try {
            java.util.Date date = format.parse(request.getParameter("birthDate"));
            java.sql.Date birthdate = new java.sql.Date(date.getTime());
            user.setBirthdate(birthdate);
            UserDao userDao = new UserDao();
            int i = userDao.updateUser(con, user);
            if (i > 0) {
                request.getRequestDispatcher("WEB-INF/views/index.jsp");
            } else {
                System.out.println("Update user error!");
                request.getRequestDispatcher("WEB-INF/views/userInfo.jsp");
            }
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }
}
