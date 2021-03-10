package com.hk.study.date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2021年02月02日 15:57
 */
@SpringBootTest
public class InstantTestTest {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyy-MM-dd").withZone(ZoneId.systemDefault());

    /**
     * 计算两个时间的时间差, 保留一位小数
     */
    @Test
    public void calculateBetween() {
        Instant now = Instant.now();
        Instant fromDate = Instant.now()
                .plus(45, ChronoUnit.MINUTES)
                .plus(2, ChronoUnit.HOURS);
        long between = ChronoUnit.MILLIS.between(now, fromDate);
        System.out.println(between);
        float minute = 60 * 60 * 1000;
        Float afloat = Float.valueOf(between / minute);
        String b = String.format("%.1f", afloat);
        System.out.println(b);
        System.out.println("Result:" + afloat);
    }

    /**
     * Local Date -> Instant
     */
    @Test
    void localDateToInstant() {
        LocalDate localDate = LocalDate.parse("2021-01-08");
        Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        System.out.println(dateTimeFormatter.format(instant));
    }

    /**
     * Instant -> Local Date/ Local Date Time
     */
    @Test
    void calculateTime() {
        ZonedDateTime zonedDateTime = Instant.now().atZone(ZoneId.systemDefault());
        LocalDate localDate = zonedDateTime.toLocalDate();
        LocalTime localTime = zonedDateTime.toLocalTime().withHour(20);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }

    /**
     * 获取当月星期六的个数
     */
    @Test
    void getDaysOfMonth() {
        /* 获取本月中的最后一个星期六*/
//        LocalDate lastSat = Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().with(TemporalAdjusters.lastDayOfMonth());
        LocalDate lastSat = LocalDate.of(2021, 9, 30);
        Integer sat = getDayCount(lastSat, DayOfWeek.SATURDAY);
        Integer sun = getDayCount(lastSat, DayOfWeek.SUNDAY);
        int i = lastSat.getDayOfMonth() - sat - sun;
        System.out.println(i);
    }

    private Integer getDayCount(LocalDate date, DayOfWeek dayOfWeek) {
        date = date.with(TemporalAdjusters.lastInMonth(dayOfWeek));
        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth());
        /**
         * 设置calendar计算一周的起始日期为星期(默认为星期日: 0-7)
         *      e.g. 星期日: 1  星期一: 2
         */
        calendar.setFirstDayOfWeek(2);
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }
}