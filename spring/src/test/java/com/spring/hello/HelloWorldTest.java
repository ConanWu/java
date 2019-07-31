package com.spring.hello;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/applicationContext.xml")
public class HelloWorldTest {

    @Autowired


    @Test
    public void testHelloWorld() {
        //1. 创建Spring 的IOC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("config/applicationContext.xml");

        //2. 从容器中获取 Bean 其实就是new 的过程
        HelloWorld helloWorld = (HelloWorld) ctx.getBean("helloWorld");
        // 也可以是，但不建议 HelloWorld helloWorld = ctx.getBean(HelloWorld.class);
        //3. 执行函数
        helloWorld.helloWorld();
    }



}
