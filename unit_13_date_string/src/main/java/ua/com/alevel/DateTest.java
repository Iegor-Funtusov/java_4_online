package ua.com.alevel;

import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

    public void test() {
        Date date = new Date();
        System.out.println("date = " + date);
        date = new Date(Long.MAX_VALUE);
        System.out.println("date = " + date);
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println("offsetDateTime = " + offsetDateTime);
        long time = System.currentTimeMillis();
        System.out.println("time = " + time);
        date = new Date(1675364480606L);
        System.out.println("date = " + date);
        long time1 = date.getTime();

        boolean before = date.before(new Date());

        Calendar calendar = Calendar.getInstance();
        System.out.println("calendar = " + calendar);
        calendar.setTime(new Date());
    }
}
