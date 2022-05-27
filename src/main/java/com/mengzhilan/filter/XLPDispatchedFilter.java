package com.mengzhilan.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Create by xlp on 2021/4/20
 *
 * 进行访问路径拦截，并进行相应的处理
 */
public class XLPDispatchedFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
