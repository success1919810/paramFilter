package org.example.paramfilter.ReqFilter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.paramfilter.pojo.ReqInfo;
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
        BodyReaderHttpServletRequestWrapper wrapper =
                new BodyReaderHttpServletRequestWrapper(httpRequest);
        MdcUtil.addTraceId();
        //封装上下文信息
        ReqInfo reqInfo = new ReqInfo();
        reqInfo.setIp(wrapper.getRemoteAddr());
        reqInfo.setMethod(wrapper.getMethod());
        reqInfo.setUrl(wrapper.getRequestURI());
        reqInfo.setUserAgent(wrapper.getHeader("User-Agent"));
        reqInfo.setTraceId(MdcUtil.getTraceId());
        reqInfo.setBody(wrapper.getBodyString());
        ReqInfoContext.setReqInfo(reqInfo);


        //追踪日志
        log.info("请求方式为：{}",reqInfo.getMethod());
        log.info("请求IP为：{}",reqInfo.getIp());
        log.info("请求URL为：{}",reqInfo.getUrl());
        log.info("请求traceId为：{}",reqInfo.getTraceId());
        log.info("请求体为：{}",reqInfo.getBody());


        try {
                chain.doFilter(wrapper, response);
            }
        finally {
            //清除ReqInfoContext,防止污染
            ReqInfoContext.clear();
            // 清除 MDC，避免线程复用污染
            MdcUtil.clear();
        }
    }

    @Override
    public void destroy() {
        System.out.println("销毁 DemoFilter");
    }
}
