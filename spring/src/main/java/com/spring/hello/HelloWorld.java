package com.spring.hello;

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
