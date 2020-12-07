package com.xrca.java8.date;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.chrono.MinguoDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author xrca
 * @description Java8新的日期API
 * @date 2020/12/7 10:44
 **/
public class DateAPI {

    /**
     * @author xrca
     * @description 旧日期API
     * @date 2020/12/7 22:31
     **/
    @Test
    public void testOldAPI() {
        Date date = new Date();
        System.out.println(date);

        // 从1990年算起，增加指定参年数
        System.out.println(new Date(111, 2, 1));

        Calendar calendar = Calendar.getInstance();

        // 清除所设置，将日历拨到1970.01.01 00:00:00
        calendar.clear();
        System.out.println(calendar.getTime());

        calendar.set(Calendar.YEAR, 1840);

        System.out.println(calendar.getTime());

        System.out.println(TimeZone.getDefault().getDisplayName());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(dateFormat.format(calendar.getTime()));
    }

    /**
     * @author xrca
     * @description 该类的实例是一个不可变对象，它只提供了简单的日期，并不含当天的时间信息。另外，它也不附带任何与时区相关的信息。
     * @date 2020/12/7 11:08
     **/
    @Test
    public void testLocalDate() {
        // 手动创建
        LocalDate localDate = LocalDate.of(2020, 12, 7);
        // 静态工厂方法获取当前日期
        LocalDate localDate2 = LocalDate.now();
        // parse字符串，默认格式为yyyy-MM-dd
        LocalDate localDate3 = LocalDate.parse("2020-12-09");
        System.out.println(localDate);
        System.out.println(localDate2);
        System.out.println(localDate3);

        System.out.println("年份：" + localDate.getYear());
        System.out.println("月份：" + localDate.getMonth());
        System.out.println("日：" + localDate.getDayOfMonth());
        System.out.println("今年的第几天：" + localDate.getDayOfYear());
        System.out.println("周几：" + localDate.getDayOfWeek().getValue());
        System.out.println("是否闰年：" + localDate.isLeapYear());
        System.out.println("本月有几天：" + localDate.lengthOfMonth());
        System.out.println("今年有几天：" + localDate.lengthOfYear());

        // TemporalField定义了时间的字段，如月份，小时，分钟等，ChronoField枚举类为具体实现
        TemporalField temporalField = ChronoField.PROLEPTIC_MONTH;

        System.out.println(localDate.get(ChronoField.YEAR));
        System.out.println(localDate.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(localDate.get(ChronoField.DAY_OF_MONTH));
        System.out.println(localDate.get(ChronoField.DAY_OF_WEEK));
        System.out.println(localDate.get(ChronoField.DAY_OF_YEAR));


    }

    /**
     * @author xrca
     * @description LocalTime类，不可变类，就提供时分秒等时间，并未提供年月日等数据
     * @date 2020/12/7 22:36
     **/
    @Test
    public void testLocalTime() {
        // 手动创建
        LocalTime localTime = LocalTime.of(9, 12, 7);
        //静态工厂方法获取当前日期
        LocalTime localTime2 = LocalTime.now();
        //
        LocalTime localTime3 = LocalTime.parse("10:30:12.222");
        System.out.println(localTime);
        System.out.println(localTime2);
        System.out.println(localTime3);

        System.out.println("小时：" + localTime2.getHour());
        System.out.println("分钟：" + localTime2.getMinute());
        System.out.println("秒：" + localTime2.getSecond());
        System.out.println("纳秒：" + localTime2.getNano());
        System.out.println("今天的秒数：" + localTime2.toSecondOfDay());
        System.out.println("今天的纳秒数：" + localTime2.toNanoOfDay());

        // TemporalField定义了时间的字段，如月份，小时，分钟等，ChronoField枚举类为具体实现
        TemporalField temporalField = ChronoField.PROLEPTIC_MONTH;

        System.out.println("=================================================================");

        System.out.println(localTime2.getLong(ChronoField.HOUR_OF_DAY));
        System.out.println(localTime2.getLong(ChronoField.HOUR_OF_AMPM));
        System.out.println(localTime2.getLong(ChronoField.MINUTE_OF_HOUR));
        System.out.println(localTime2.getLong(ChronoField.MINUTE_OF_DAY));
        System.out.println(localTime2.getLong(ChronoField.SECOND_OF_MINUTE));
        System.out.println(localTime2.getLong(ChronoField.SECOND_OF_DAY));
        System.out.println(localTime2.getLong(ChronoField.NANO_OF_SECOND));
        System.out.println(localTime2.getLong(ChronoField.NANO_OF_DAY));
    }

    /**
     * @author xrca
     * @description 这个复合类名叫LocalDateTime，是LocalDate和LocalTime的合体。它同时表示了日期和时间，但不带有时区信息，
     * 你可以直接创建，也可以通过合并日期和时间对象构造
     * @date 2020/12/7 11:38
     **/
    @Test
    public void testLocalDateTime() {

        LocalDateTime localDateTime1 = LocalDateTime.now();
        LocalDateTime localDateTime2 = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        LocalDateTime localDateTime3 = LocalDate.now().atTime(11, 40, 20, 213);
        LocalDateTime localDateTime4 = LocalDate.now().atTime(LocalTime.now());
        LocalDateTime localDateTime5 = LocalTime.now().atDate(LocalDate.now());

        System.out.println(localDateTime1);
        System.out.println(localDateTime2);
        System.out.println(localDateTime3);
        System.out.println(localDateTime4);
        System.out.println(localDateTime5);

        System.out.println("==========================================================");

        System.out.println(localDateTime1.toLocalDate());
        System.out.println(localDateTime1.toLocalTime());

    }

    @Test
    public void testInstant() {
        long i = 10_0_0_00L;
        System.out.println(i);
        Double d = 100.313213;

        System.out.println(Instant.ofEpochSecond(3).toString());

        System.out.println(Instant.ofEpochSecond(3, 10000).toString());
        System.out.println(Instant.ofEpochSecond(3, -10000).toString());
        System.out.println(Instant.now());
        System.out.println(Instant.now().toEpochMilli());
        System.out.println(Instant.now().getEpochSecond());
    }

    @Test
    public void testDurationAndPeriod() {
        LocalTime localTime1 = LocalTime.of(9, 30);
        LocalTime localTime2 = LocalTime.of(13,55);
        Duration duration = Duration.between(localTime1, localTime2);
        System.out.println(duration.toDays());
        System.out.println(duration.toHours());
        System.out.println(duration.toMinutes());
        System.out.println(duration.getSeconds());

        System.out.println("=================================================================");
        LocalDate localDate1 = LocalDate.of(1994, 6, 4);
        LocalDate localDate2 = LocalDate.now();
        Period period = Period.between(localDate1, localDate2);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.toTotalMonths());
        System.out.println(period.getDays());


        System.out.println("******************************************************************");
        Duration threeMinutes1 = Duration.ofMinutes(3);
        Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);

        Period tenDays = Period.ofDays(10);
        Period threeWeek = Period.ofWeeks(3);
        System.out.println(threeWeek.getDays());

        Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
        System.out.println(twoYearsSixMonthsOneDay.getDays());
    }

    /**
     * @author xrca
     * @description 操作、解析日期
     * @date 2020/12/7 14:16
     **/
    @Test
    public void testOperate() {
        LocalDate now = LocalDate.of(2019, 11, 29);
        LocalDate threeDayLater1 = now.withDayOfYear(3);
        LocalDate threeDayLater2 = now.withDayOfMonth(3);
        System.out.println(threeDayLater1);
        System.out.println(threeDayLater2);
        LocalDate month = now.withMonth(2);
        System.out.println(month);

        System.out.println("================================");
        // 注意with和minus、plus的区别
        LocalTime time = LocalTime.now();
        time = time.plusHours(4);
        System.out.println(time);

        // TemporalAdjuster
        //截至目前，你所看到的所有日期操作都是相对比较直接的。有的时候，你需要进行一些更加
        //复杂的操作，比如，将日期调整到下个周日、下个工作日，或者是本月的最后一天。
        now = now.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(now);

        time = time.with(LocalTime.now());
        System.out.println(time);


        //
        LocalDate someDay = LocalDate.of(2020, 12, 31);
        LocalDate nextWorkingDay = someDay.with(new NextWorkingDay());
        System.out.println(nextWorkingDay);

        // lambda
        System.out.println(someDay.with(temporal -> {
            int dayOfWeek = temporal.get(ChronoField.DAY_OF_WEEK);
            int days = 1;
            if (dayOfWeek % 7 == 5) {
                days = 3;
            } else if (dayOfWeek % 7 == 6) {
                days = 2;
            }
            return temporal.plus(days, ChronoUnit.DAYS);
        }));

    }

    /**
     * @author xrca
     * @description 格式化日期
     * @date 2020/12/7 14:59
     **/
    @Test
    public void testDateFormat() {
        // Date -> String
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(localDate.format(DateTimeFormatter.ISO_DATE));

        LocalTime localTime = LocalTime.now();
        System.out.println(localTime.format(DateTimeFormatter.ISO_TIME));
        System.out.println(localTime.format(DateTimeFormatter.ISO_LOCAL_TIME));

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        // String -> Date
        LocalDate ld1 = LocalDate.parse("2020-12-07", DateTimeFormatter.ISO_DATE);
        System.out.println(ld1);

        // 自定义
        LocalDate today = LocalDate.now();
        DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        System.out.println(today.format(myFormatter));
        System.out.println(LocalDate.parse("1949年10月01日", myFormatter));

        DateTimeFormatter chinaFormatter = DateTimeFormatter.ofPattern("yyyy/MMMM/dd", Locale.CHINA);
        System.out.println(LocalDate.now().format(chinaFormatter));

    }

    /**
     * @author xrca
     * @description 时区
     * @date 2020/12/7 15:14
     **/
    @Test
    public void testZone() {
        System.out.println(TimeZone.getDefault());

        ZoneId zoneId = TimeZone.getDefault().toZoneId();

        LocalDate localDate = LocalDate.now();
        ZonedDateTime zonedDate = localDate.atStartOfDay(zoneId);
        System.out.println(zonedDate);

        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        System.out.println(zonedDateTime);
        //System.out.println(localDateTime.toInstant((ZoneOffset) zoneId));


        Instant now = Instant.now();
        ZonedDateTime instant = now.atZone(zoneId);
        System.out.println(instant);
        System.out.println(LocalDateTime.ofInstant(now, zoneId));


        LocalDateTime chinaToday = LocalDateTime.now();
        OffsetDateTime offsetDateTime = OffsetDateTime.of(chinaToday, ZoneOffset.of("+08:00"));
        System.out.println(offsetDateTime);


        System.out.println(MinguoDate.from(chinaToday));

    }

}

/**
 * @author xrca
 * @description 获取下一个工作日
 * @date 2020/12/7 14:16
 **/
class NextWorkingDay implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        int dayOfWeek = temporal.get(ChronoField.DAY_OF_WEEK);
        int days = 1;
        if (dayOfWeek % 7 == 5) {
            days = 3;
        } else if (dayOfWeek % 7 == 6) {
            days = 2;
        }
        return temporal.plus(days, ChronoUnit.DAYS);
    }
}

