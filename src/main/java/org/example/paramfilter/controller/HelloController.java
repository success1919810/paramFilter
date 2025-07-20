package org.example.paramfilter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @PostMapping("/echo")
    public String echo(@RequestBody String body) {
//        System.out.println("Controller 收到的 body: " + body);
        log.info("Controller 收到的 body:{}",body);
        log.info("traceId追查成功");
        return "Echo: " + body;
    }
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Filter!";
    }
}