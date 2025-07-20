package org.example.paramfilter.ReqFilter;

import org.example.paramfilter.pojo.ReqInfo;

public class ReqInfoContext {
    private static final ThreadLocal<ReqInfo> REQ_INFO_THREAD_LOCAL = new ThreadLocal<>();

    // 设置 ReqInfo
    public static void setReqInfo(ReqInfo reqInfo) {
        REQ_INFO_THREAD_LOCAL.set(reqInfo);
    }

    // 获取 ReqInfo
    public static ReqInfo getReqInfo() {
        return REQ_INFO_THREAD_LOCAL.get();
    }

    // 清除，避免内存泄漏
    public static void clear() {
        REQ_INFO_THREAD_LOCAL.remove();
    }
}
