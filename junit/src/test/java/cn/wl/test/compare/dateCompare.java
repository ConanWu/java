package cn.wl.test.compare;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class dateCompare {

    @Test
    public void dateTest() {
        String date1 = "2019/05/10";
        String date2 = "05/10/2019";
        System.out.println(date1);
    }

    @Test
    public void dateToCalendarTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
    }

    @Test
    public void calendarToDateTest() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        System.out.println(date);
    }

    @Test
    public void stringToDateTest() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse("2019-05-21");
    }

    @Test
    public void dateToStringTest() {
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format("2019-05-21");
    }
}
