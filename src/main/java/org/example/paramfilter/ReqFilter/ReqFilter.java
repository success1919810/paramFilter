package org.example.paramfilter.ReqFilter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "myFilter")
//如果你想要自动注入的话，这里写@Compone会导致过滤器注册两边
public class ReqFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("初始化 DemoFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 把 ServletRequest 转成 HttpServletRequest，用来获取更多信息
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // 打印请求方法和 URI
        System.out.println("请求方法: " + httpRequest.getMethod());
        System.out.println("请求地址: " + httpRequest.getRequestURI());

        // 记录开始时间
        long startTime = System.currentTimeMillis();

        // 继续执行请求链，放行到下一个过滤器或控制器
        chain.doFilter(request, response);

        // 记录结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("请求耗时: " + (endTime - startTime) + " ms");
    }

    @Override
    public void destroy() {
        System.out.println("销毁 DemoFilter");
    }
}
