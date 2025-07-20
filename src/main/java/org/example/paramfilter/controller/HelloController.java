package org.example.paramfilter.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.paramfilter.ReqFilter.ReqInfoContext;
import org.example.paramfilter.pojo.ReqInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @PostMapping("/echo")
    public String echo(@RequestBody String body) {
        ReqInfo reqInfo = ReqInfoContext.getReqInfo();
        log.info("Controller 中获取 traceId: {}", reqInfo.getTraceId());
        log.info("Controller 收到的 body:{}",body);
        return "Echo: " + body;
    }
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Filter!";
    }
}