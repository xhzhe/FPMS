package com.fpms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author : YongBiao Liao
 * @date   : 2019/6/12 9:36
 */
@SpringBootApplication
public class FpmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FpmsApplication.class, args);
    }

}
