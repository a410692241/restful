package com.wei.filter;

import javax.servlet.*;
import java.io.IOException;

public class TimeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("这只是一个过滤器!");
    }
}
