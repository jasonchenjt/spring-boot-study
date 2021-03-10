package com.hk.study.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2021年03月09日 9:26
 */
public class InstantStudy {

    /**
     * MMM: 显示中文月份
     * Locale.ENGLISH: 英文月份
     */
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd", Locale.ENGLISH).withZone(ZoneId.systemDefault());

    /**
     * 计算两个Instant的时间差
     */
    public void calculateBetween() {
        Instant now = Instant.now();
        Instant fromDate = Instant.now()
                .plus(45, ChronoUnit.MINUTES)
                .plus(2, ChronoUnit.HOURS);
        long millisBetween = ChronoUnit.MILLIS.between(now, fromDate);
        long hoursBetween = ChronoUnit.HOURS.between(now, fromDate);
        long secondsBetween = ChronoUnit.SECONDS.between(now, fromDate);
    }

    /**
     * Instant -> Local Date/Time
     */
    public void toLocalDateTime() {
        Instant now = Instant.now();
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());
        LocalDate localDate = zonedDateTime.toLocalDate();
        LocalTime localTime = zonedDateTime.toLocalTime();
    }
}
