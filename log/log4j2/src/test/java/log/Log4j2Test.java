package log;

import com.Apple;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class Log4j2Test {
    private static Logger logger = LogManager.getLogger(Log4j2Test.class);

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
