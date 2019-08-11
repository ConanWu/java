package com.spring.hello;

import com.spring.config.MainConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainConfiguration.class)
public class HelloSpringJava {
    @Autowired
    private HelloWorld helloWorld;

    @Test
    public void helloSpringTest() {
        helloWorld.setHello("Hello spring!!!");
        helloWorld.helloWorld();
    }
}
