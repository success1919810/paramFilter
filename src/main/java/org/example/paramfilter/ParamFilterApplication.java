package org.example.paramfilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ParamFilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParamFilterApplication.class, args);
    }

}
