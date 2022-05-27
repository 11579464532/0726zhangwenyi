package com.zhangwenyi.lab1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/MyDearServletURL")
public class MyDearServletURL extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String cl = req.getParameter("class");
        String id = req.getParameter("id");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>MyDearServletURL</TITLE>");
        out.println("<meta http-equiv=\"refresh\" charset='utf-8' >");
        out.println("</HEAD>");
        out.println("  <BODY>");
        out.print("<div align=\"center\">");
        out.print("<h1>2020211001000719 --- Han Ming</h1><br><br>");
        out.print("<h2>This is MyDearServletURL</h2>");
        out.print("name: " + name + "<br>");
        out.print("class: " + cl + "<br>");
        out.print("id: " + id + "<br>");
        out.print("</div>");
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }
}
