package com.zhangwenyi.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "HelloFilter", urlPatterns = {"/hello"})
public class HelloFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("i am in HelloFilter --> init()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // request com here - before go to servlet
        System.out.println("i am in HelloFilter --> doFilter() before servlet");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("i am in HelloFilter --> doFilter() after servlet");
    }

    @Override
    public void destroy() {
        System.out.println("i am in HelloFilter --> destroy()");
    }
}
