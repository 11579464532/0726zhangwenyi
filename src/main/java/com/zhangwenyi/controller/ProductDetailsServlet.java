package com.zhangwenyi.controller;

import com.zhangwenyi.dao.ProductDao;
import com.zhangwenyi.model.Category;
import com.zhangwenyi.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductDetailsServlet", value = "/productDetails")
public class ProductDetailsServlet extends HttpServlet {
    Connection con = null;

    @Override
    public void init() throws ServletException {
        super.init();
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = req.getParameter("id") != null ? Integer.parseInt(req.getParameter("id")) : 0;
        ProductDao productDao = new ProductDao();
        if (id == 0) {
            return;
        }
        Category category = new Category();
        List<Category> categories = category.findAllCategory(con);
        req.setAttribute("categoryList", categories);

        try {
            Product product = productDao.findById(id, con);
            req.setAttribute("p", product);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.getRequestDispatcher("/WEB-INF/views/productDetails.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
