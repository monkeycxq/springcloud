package com.example.common.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
@Slf4j
public class LocalDateUtil {

    /**
     * Date 转 LocalDate
     * @param date
     * @return LocalDate
     */
    public static LocalDate dateToLocalDate(Date date){
        if(null == date) {
            return null;
        }
        log.info("===调用了日期转换======");
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * LocalDate 转 Date
     * @param localDate
     * @return Date
     */
    public static Date localDate2Date(LocalDate localDate) {
        if(null == localDate) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

}
