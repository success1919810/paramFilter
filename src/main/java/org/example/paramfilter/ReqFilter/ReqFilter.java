package org.example.paramfilter.ReqFilter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.paramfilter.util.MdcUtil;


import java.io.IOException;

@Slf4j
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
        MdcUtil.addTraceId();

        try {
            if ("POST".equalsIgnoreCase(httpRequest.getMethod())) {
                BodyReaderHttpServletRequestWrapper wrapper =
                        new BodyReaderHttpServletRequestWrapper(httpRequest);

//                System.out.println("请求体内容: " + wrapper.getBodyString());
                  log.info("请求体内容:{}",wrapper.getBodyString());

                chain.doFilter(wrapper, response);
            } else {
                chain.doFilter(request, response);
            }
        } finally {
            // 清除 MDC，避免线程复用污染
            MdcUtil.clear();
        }
    }

    @Override
    public void destroy() {
        System.out.println("销毁 DemoFilter");
    }
}
