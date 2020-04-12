package cn.wl.test.utils;

import cn.wl.test.Calculator;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DateTest {

    @Test
    public void calendarTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 3, 2,18,0,0);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String tsStr = "2011-05-09 06:49:45";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

        String tsStr1 = "06:49:45";
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("H:mm:ss");
        LocalTime localDate1 = LocalTime.parse(tsStr, dateTimeFormatter);
        LocalDateTime localDate = LocalDateTime.parse(tsStr, dateTimeFormatter);
        try {
            ts = Timestamp.valueOf(tsStr);
            System.out.println(ts);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("aadf");
    }
}
