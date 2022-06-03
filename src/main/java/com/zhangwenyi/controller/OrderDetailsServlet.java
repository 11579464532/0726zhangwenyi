package com.zhangwenyi.controller;

import com.zhangwenyi.dao.OrderDao;
import com.zhangwenyi.model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "OrderDetailsServlet", value = "/orderDetails")
public class OrderDetailsServlet extends HttpServlet {
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
        int orderId = request.getParameter("orderId") != null ? Integer.parseInt(request.getParameter("orderId")) : 0;
        Item item = new Item();
        OrderDao dao = new OrderDao();
        List<Item> items = dao.findItemsByOrderId(con, orderId);
        //Log.info(items.tostringC)
        request.setAttribute("itemList", items);
        String path = "orderDetails.jsp";
        //Log.infopath）；
        request.getRequestDispatcher(path).forward(request, response);
    }
}
