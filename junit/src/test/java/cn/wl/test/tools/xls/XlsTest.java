package cn.wl.test.tools.xls;


import org.junit.Test;

import java.io.File;
import java.io.InputStream;

public class XlsTest {

    @Test
    public void readXlsTest() {
//        InputStream inputStream = this.getClass().getResourceAsStream("testData/test1.xlsx");
        File file = new File("E:\\moving\\GIT\\java-test-e2e\\junit\\src\\main\\resources\\testData\\test1.xlsx");
        XlsToMap.getXlsRowSource(file);


    }
}
