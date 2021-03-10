package com.hk.study.date;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2021年03月09日 9:29
 */
public class LocalDateStudy {

    /**
     * MMM: 显示中文月份
     * Locale.ENGLISH: 英文月份
     */
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd", Locale.ENGLISH).withZone(ZoneId.systemDefault());

    /**
     * Local Date -> Instant
     */
    public void localDateToInstant() {
        LocalDate localDate = LocalDate.parse("2021-01-08");
        Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        System.out.println(dateTimeFormatter.format(instant));
    }

    /**
     * LocalDateTime -> Instant
     */
    public void localDateTimeToInstant() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
//        LocalDateTime now = LocalDateTime.now();
        Instant instant = LocalDateTime.of(localDate, localTime).atZone(ZoneId.systemDefault()).toInstant();
        System.out.println(dateTimeFormatter.format(instant));
    }

}
