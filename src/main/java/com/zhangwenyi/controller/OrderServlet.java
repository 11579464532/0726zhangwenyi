package com.zhangwenyi.controller;

import com.zhangwenyi.dao.OrderDao;
import com.zhangwenyi.model.Item;
import com.zhangwenyi.model.Order;
import com.zhangwenyi.model.Payment;

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
import java.util.HashSet;
import java.util.List;

@WebServlet(name = "OrderServlet", value = "/order")
public class OrderServlet extends HttpServlet {
    private Connection con = null;

    @Override
    public void init() throws ServletException {
        super.init();
        con = (Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Payment> payments = Payment.findAllPayment(con);
        req.setAttribute("paymentTypeList", payments);
        req.getRequestDispatcher("/WEB-INF/views/order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int customerId = req.getParameter("customerId") != null ? Integer.parseInt(req.getParameter("customerId")) : 0;
        int paymentId = req.getParameter("paymentId") != null ? Integer.parseInt(req.getParameter("paymentId")) : 0;
        String firstName = req.getParameter("firstName");
        String LastName = req.getParameter("lastName");
        String phone = req.getParameter("phone");
        String address1 = req.getParameter("address1");
        String address2 = req.getParameter("address2");
        String postalCode = req.getParameter("postalCode");
        String state = req.getParameter("state");
        String city = req.getParameter("city");
        String country = req.getParameter("country");
        String notes = req.getParameter("notes");
        double orderTotal = req.getParameter("orderTotal") != null ? Double.parseDouble(req.getParameter("orderTotal")) : 0.0;

        String message = null;
        if (customerId == 0 || paymentId == 0 || firstName == null || firstName.trim().length() == 0 || phone == null ||
                phone.trim().length() == 0 || address1 == null || address1.trim().length() == 0 ||
                postalCode == null || postalCode.trim().length() == 0) {
            message = "Error!, Enter Required(*) Info.";
            req.setAttribute("message", message);
            List<Payment> paymentTypeList = Payment.findAllPayment(con);
            req.setAttribute("paymentTypeList", paymentTypeList);
            String path = "/WEB-INF/views/order.jsp";
            req.getRequestDispatcher(path).forward(req, resp);
        } else {
            Order o = new Order();
            o.setCustomerId(customerId);
            o.setPaymentId(paymentId);
            o.setFirstName(firstName);
            o.setLastName(LastName);
            o.setPhone(phone);
            o.setAddress1(address1);
            o.setAddress2(address2);
            o.setCity(city);
            o.setCountry(country);
            o.setState(state);
            o.setNotes(notes);
            o.setPostalCode(postalCode);
            o.setOrderTotal(orderTotal);
            o.setPaymentId(paymentId);
            HttpSession session = req.getSession(false);
            if (session != null && session.getAttribute("cart") != null) {
                ArrayList<Item> cartItems = (ArrayList<Item>) session.getAttribute("cart");
                o.setOrderDetails(new HashSet<Item>(cartItems));
            }

            OrderDao dao = new OrderDao();
            int n = 0;
            try {
                n = dao.save(con, o);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if (n > 0) {
                String path = "/WEB-INF/views/orderSuccess.jsp";
                req.getRequestDispatcher(path).forward(req, resp);
            }
        }
    }//end post
}