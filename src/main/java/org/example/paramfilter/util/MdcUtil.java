package org.example.paramfilter.util;

import org.slf4j.MDC;

import java.util.UUID;

public class MdcUtil {
    // key 名，和日志模板中对应
    public static final String TRACE_ID = "traceId";

    /**
     * 生成 traceId 并放入 MDC
     */
    public static void addTraceId() {
        String traceId = UUID.randomUUID().toString().replace("-", "");
        MDC.put(TRACE_ID, traceId);
    }

    /**
     * 获取当前线程的 traceId
     */
    public static String getTraceId() {
        return MDC.get(TRACE_ID);
    }

    /**
     * 清除 MDC 内容（避免线程复用导致 traceId 串）
     */
    public static void clear() {
        MDC.clear();
    }
}