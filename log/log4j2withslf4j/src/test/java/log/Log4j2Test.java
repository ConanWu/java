package log;

import com.Apple;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4j2Test {
    private static Logger logger = LoggerFactory.getLogger(Log4j2Test.class);

    @Test
    public void log4j2Test() {
        logger.error("error!!!");
        logger.info("info!!!");
        logger.info("info!!!");
        logger.info("info!!!");
        logger.info("info!!!");
        Apple apple=new Apple();
        apple.logTest1();

    }
}
