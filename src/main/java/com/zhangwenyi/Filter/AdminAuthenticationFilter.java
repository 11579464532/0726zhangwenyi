package com.zhangwenyi.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/admin/*")
public class AdminAuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        String loginURI = httpRequest.getContextPath() + "/admin/login";
        boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
        boolean isLoginPage = httpRequest.getRequestURI().endsWith("login");
        if (isLoggedIn && (isLoginRequest || isLoginPage)) {
            //the admin is already login ande he is trying login again
            //then forward to the admin homepage
            servletRequest.getRequestDispatcher("/admin/home").forward(httpRequest, httpResponse);//go to home

        } else if (isLoggedIn || isLoginRequest) {

            filterChain.doFilter(servletRequest, servletResponse);//go to next destination
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");//go to login
        }
    }

    @Override
    public void destroy() {

    }
}
