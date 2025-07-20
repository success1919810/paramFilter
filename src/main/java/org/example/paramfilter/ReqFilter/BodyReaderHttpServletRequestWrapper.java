package org.example.paramfilter.ReqFilter;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.*;

public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private final byte[] requestBody;

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);

        // 将请求体读取为 byte[]
        try (InputStream is = request.getInputStream()) {
            this.requestBody = is.readAllBytes(); // Java 9+，或用 ByteArrayOutputStream 替代
        }
    }

    // 重写 getInputStream()，返回包装后的流
    @Override
    public ServletInputStream getInputStream() {
        ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);

        return new ServletInputStream() {
            @Override
            public int read() {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return bais.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true; // 简化处理
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                // 不做异步处理
            }
        };
    }

    // 重写 getReader() 方法
    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    /**
     * 提供外部获取 body 内容的接口
     */
    public String getBodyString() {
        return new String(requestBody);
    }
}