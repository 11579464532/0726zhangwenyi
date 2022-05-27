package com.zhangwenyi.lab1;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LifeCycleServlet extends HttpServlet {
    private int num;

    public LifeCycleServlet() {
        System.out.println("I Am from default constructor");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LifeCycleServlet -- service");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print("<h1>2020211001000719 --- Han Ming</h1><br><br>");
        out.println("<h2>Since loading, this servlet<br>has been accessed " + num++ + " times.</h2>");
    }

    @Override
    public void destroy() {
        System.out.println("LifeCycleServlet -- destroy");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        num = 1;
        System.out.println("LifeCycleServlet -- init");
    }
}
