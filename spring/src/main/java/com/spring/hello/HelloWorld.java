package com.spring.hello;

import org.springframework.stereotype.Component;

@Component
public class HelloWorld {

    private String hello;


    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public void helloWorld() {
        System.out.println("Spring say :" + hello);
    }

}
