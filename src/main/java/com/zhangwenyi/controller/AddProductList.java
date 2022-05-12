package com.zhangwenyi.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.hanming.dao.ProductDao;
import com.hanming.model.Category;
import com.hanming.model.Product;
import  org.apache.log4j.Logger;

@WebServlet(name = "AddProductList", value = "/admin/addProduct")
@MultipartConfig(maxFileSize = 16277215)  // upload file's size up to 16MB
public class AddProductList extends HttpServlet {

    private Connection con = null;
    private static final Logger log = Logger.getLogger(AddProductList.class);

    @Override
    public void init() throws ServletException {
        super.init();
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Category category = new Category();
        List<Category> categoryList = category.findAllCategory(con);
        req.setAttribute("categoryList", categoryList);
        req.getRequestDispatcher("/WEB-INF/views/admin/addProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("productName");
        Double price = req.getParameter("price") != null ? Double.parseDouble(req.getParameter("price")) : 0.0;
        int categoryId = req.getParameter("categoryId") != null ? Integer.parseInt(req.getParameter("categoryId")) : 0;
        String productDescription = req.getParameter("productDescription");
        InputStream inputStream = null;
        Part picture = req.getPart("picture");
        if (picture != null) {
            System.out.println("file name: "+picture.getName()+" size "+picture.getSize()+" type "+picture.getContentType());
            inputStream = picture.getInputStream();
        }
        Product product = new Product();
        product.setProductName(productName);
        product.setPrice(price);
        product.setProductDescription(productDescription);
        product.setCategoryId(categoryId);

        ProductDao productDao = new ProductDao();
        int i = 0;
        try {
            i = productDao.save(product, inputStream, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
