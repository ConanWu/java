package cn.wl.test.jaxb;

import org.junit.Test;

import java.util.Date;

public class ConvertTest {

    @Test
    public void test1() {
        // 创建需要转换的对象
        User user = new User(1, "Steven", "@sun123", new Date(), 1000.0);
        System.out.println("---将对象转换成string类型的xml Start---");
        // 将对象转换成string类型的xml
        String str = XMLUtil.convertToXml(user);
        // 输出
        System.out.println(str);
        System.out.println("---将对象转换成string类型的xml End---");
        System.out.println();
        System.out.println("---将String类型的xml转换成对象 Start---");
        User userTest = (User) XMLUtil.convertXmlStrToObject(User.class, str);
        System.out.println(userTest);
        System.out.println("---将String类型的xml转换成对象 End---");
    }

    @Test
    public void test2() {
        // 创建需要转换的对象
        User user = new User(1, "Steven", "@sun123", new Date(), 1000.0);

        String path = "D:\\user.xml";
        System.out.println("---将对象转换成File类型的xml Start---");
        XMLUtil.convertToXml(user, path);
        System.out.println("---将对象转换成File类型的xml End---");
        System.out.println();
        System.out.println("---将File类型的xml转换成对象 Start---");
        User user2 = (User) XMLUtil.convertXmlFileToObject(User.class, path);
        System.out.println(user2);
        System.out.println("---将File类型的xml转换成对象 End---");
    }
}
