package com.zhangwenyi.lab2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "HanMingFilter", urlPatterns = "/lab2/*")
public class HanMingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("HanMingFilter --> before chain");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("HanMingFilter --> after chain");
    }

    @Override
    public void destroy() {

    }
}
