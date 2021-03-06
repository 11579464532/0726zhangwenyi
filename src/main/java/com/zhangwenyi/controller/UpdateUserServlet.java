package com.zhangwenyi.controller;

import com.zhangwenyi.dao.UserDao;
import com.zhangwenyi.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "UpdateUserServlet", value = "/updateUser")//url
public class UpdateUserServlet extends HttpServlet {
    Connection con =null;

    @Override
    public void init() throws ServletException {
        super.init();
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //write code
        //TODO 1 : forward to WEB-INF/views/updateUser.jsp
        //TODO 2 : create one jsp page - update User
        if (request.getParameter("id") != null){
            User user = new User();
            UserDao userDao = new UserDao();
            try {
                user = userDao.findById(con, Integer.parseInt(request.getParameter("id")));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (user == null) {
                request.setAttribute("message", "Please login");
            }
            request.setAttribute("user", user);
            request.getRequestDispatcher("WEB-INF/views/updateUserView.jsp").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //write code to update info - can update password, email, gender, birthDate
        con = (Connection) getServletContext().getAttribute("con");
        ///TODO 1: get all(6) request parameters
        //TODO 2: create an object of User Model
        //TODO 3: set all 6 request parameters values into User model - setXXX()
        //TODO 4: create an object of UserDao
        //TODO 5: call updateUser() in UserDao
        //TODO 6: forward to WEB-INF/views/userInfo.jsp
        int id =Integer.parseInt(request.getParameter("id"));//get id <input type="hidden" name="id" />
        String username =request.getParameter("username");//get Username <input type="text" name="username" />
        String password =request.getParameter("password");//get  password <input type="password" name="password" />
        String email =request.getParameter("email");//get <input type="text" name="email" />
        String gender =request.getParameter("gender");//get <input type="radio" name="gender"/>
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = format.parse(request.getParameter("birthdate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date birthDate = new java.sql.Date(date.getTime()); //get Birth Date <input type="text" name="birthDate" />
        User u=new User();
        u.setId(id);
        u.setUsername(username);
        u.setPassword(password);
        u.setEmail(email);
        u.setGender(gender);
        u.setBirthdate(birthDate);
        UserDao dao =new UserDao();
        try{
            dao.updateUser(con,u);
            User updateUser = dao.findById(con, id);
            HttpSession session = request.getSession();
            session.removeAttribute("user");
            session.setAttribute("user",updateUser);
            request.getRequestDispatcher("accountDetails").forward(request,response);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}