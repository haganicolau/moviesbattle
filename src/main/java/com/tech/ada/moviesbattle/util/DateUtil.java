package com.tech.ada.moviesbattle.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class to handle dates.
 */
@Component
public class DateUtil {

    @Value("${client.format.date}")
    private String FORMAT_DATE;

    @Value("${client.format.datetime}")
    private String FORMAT_DATE_TIME;

    /**
     * Convert LocalDate in String. Format is the config properties
     * @param localDate localDate
     * @return string converted
     */
    public String localDateToString(LocalDate localDate) {
        if (FORMAT_DATE == null) {
            FORMAT_DATE = "MM-dd-yyyy";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
        return localDate.format(formatter);
    }

    /**
     * Convert String format in LocalDate. Format is the config properties
     * @param stringDate String
     * @return LocalDate converted
     */
    public LocalDate stringToLocalDate(String stringDate) {
        if (FORMAT_DATE == null) {
            FORMAT_DATE = "MM-dd-yyyy";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
        return LocalDate.parse(stringDate, formatter);
    }

    /**
     * Convert LocalDateTime in String. Format is the config properties
     * @param localDateTime LocalDateTime
     * @return string converted
     */
    public String localDateTimeToString(LocalDateTime localDateTime) {
        if (FORMAT_DATE_TIME == null) {
            FORMAT_DATE_TIME = "MM-dd-yyyy HH:mm:ss";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE_TIME);
        return localDateTime.format(formatter);
    }

    /**
     * Convert String format in LocalDateTime. Format is the config properties
     * @param date String
     * @return DateTimeFormatter converted
     */
    public LocalDateTime stringToLocalDateTime(String date) {
        if (FORMAT_DATE_TIME == null) {
            FORMAT_DATE_TIME = "MM-dd-yyyy HH:mm:ss";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE_TIME);
        return LocalDateTime.parse(date, formatter);
    }
}
