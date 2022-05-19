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

@WebServlet(name = "ShopServlet", value = "/shop")
public class ShopServlet extends HttpServlet {
    Connection con = null;

    @Override
    public void init() throws ServletException {
        super.init();
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category category = new Category();
        List<Category> categories = category.findAllCategory(con);
        req.setAttribute("categoryList", categories);
        ProductDao productDao = new ProductDao();
        List<Product> productList = null;
        try {
            if (req.getParameter("categoryId") == null) {
                productList = productDao.findAll(con);
            } else {
                int categoryId = Integer.parseInt(req.getParameter("categoryId"));
                productList = productDao.findByCategoryId(categoryId, con);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("productList", productList);
        req.getRequestDispatcher("/WEB-INF/views/shop.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
