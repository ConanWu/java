package cn.wl.test;

import cn.wl.test.Hello;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by zjjwu on 2017/3/17.
 */
public class HelloTest {
    private static Hello hello = new Hello();
    @Before
    public void setUp() throws Exception {
        Hello hello = new Hello();
    }

    @Test
    public void sayHello() throws Exception {
        Assert.assertEquals("Hello",hello.sayHello());
    }

}