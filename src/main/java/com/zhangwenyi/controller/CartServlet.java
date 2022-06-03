package com.zhangwenyi.controller;

import com.zhangwenyi.dao.ProductDao;
import com.zhangwenyi.model.Item;
import com.zhangwenyi.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
    private Connection con = null;

    @Override
    public void init() throws ServletException {
        super.init();
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            if (req.getParameter("action") == null) {
                displayCart(req, resp);
            } else if (req.getParameter("action").equals("add")) {
                try {
                    buy(req, resp);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } else if (req.getParameter("action").equals("remove")) {
                remove(req, resp);
            }
        } else resp.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    private void buy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        int id = request.getParameter("productId") != null ? Integer.parseInt(request.getParameter("productId")) : 0;
        int quantity = request.getParameter("quantity") != null ? Integer.parseInt(request.getParameter("quantity")) : 1;
        ProductDao dao = new ProductDao();
        if (session.getAttribute("cart") == null) {
            List<Item> cart = new ArrayList<Item>();
            Product product = dao.findById(id, con);
            cart.add(new Item(product, quantity));
            session.setAttribute("cart", cart);
        } else {
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            int index = isExisting(id, cart);
            if (index == -1) {
                cart.add(new Item(dao.findById(id, con), 1));
            } else {
                int qu = cart.get(index).getQuantity() + 1;
                cart.get(index).setQuantity(qu);
            }
            session.setAttribute("cart", cart);
        }
        response.sendRedirect(request.getContextPath() + "/cart");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        int id = 0;
        if (request.getParameter("productId") != null) {
            id = Integer.parseInt(request.getParameter("productId"));
        }
        int index = isExisting(id, cart);
        cart.remove(index);
        session.setAttribute("cart", cart);
        response.sendRedirect(request.getContextPath() + "/cart");
    }

    private int isExisting(int id, List<Item> cart){
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProduct().getProductId() == id) {
                return i;
            }
        }
        return -1;
    }

    private void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message", "Your Cart");
        request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
    }
}
