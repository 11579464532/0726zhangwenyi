package com.zhangwenyi.controller;

import com.zhangwenyi.dao.OrderDao;
import com.zhangwenyi.model.Order;
import com.zhangwenyi.model.Payment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "AdminOrderListServlet", value = "/admin/orderList")
public class AdminOrderListServlet extends HttpServlet {
    private Connection con = null;

    @Override
    public void init() throws ServletException {
        super.init();
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Payment> paymentTypeList = Payment.findAllPayment(con);
        OrderDao orderDao = new OrderDao();
        List<Order> orderList = orderDao.findAll(con);
        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("/WEB-INF/views/admin/orderList.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
