package com.zhangwenyi.controller;

import com.zhangwenyi.dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "GetImgServlet", value = "/getImg")
public class GetImgServlet extends HttpServlet {
    Connection con = null;

    @Override
    public void init() throws ServletException {
        super.init();
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        ProductDao productDao = new ProductDao();
        int id = 0;
        if (req.getParameter("id") != null) {
            id = Integer.parseInt(req.getParameter("id"));
        }
        try {
            byte[] imgByte = new byte[0];
            imgByte = productDao.getPictureById(id, con);
            if (imgByte != null) {
                resp.setContentType("image/gif");
                ServletOutputStream outputStream = resp.getOutputStream();
                outputStream.write(imgByte);
                outputStream.flush();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
