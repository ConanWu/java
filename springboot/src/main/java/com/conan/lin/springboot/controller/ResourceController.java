package com.conan.lin.springboot.controller;

import org.springframework.web.bind.annotation.*;

@RestController
//使用Get或Post
@RequestMapping(value = "user", method = RequestMethod.GET)
public class ResourceController {
    //无参方式
    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello SpringBoot!";
    }

    //传参方式
    //required表示是否必填
    //value表示url中参数名称
    //defaultValue表示默认值
    @GetMapping(value = "/login")
    public String login(@RequestParam(required = true, value = "userName", defaultValue = "sa") String userName, @RequestParam(required = true, value = "password") String password) {
        if (userName.equals("sa") && password.equals("123456")) {
            return "Welcome sa!";
        } else {
            return "Incorrect username or password！";
        }
    }
}
