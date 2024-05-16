package com.tech.ada.moviesbattle.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateUtilTest {

    private DateUtil dateUtil = new DateUtil();

    @Test
    public void testLocalDateToString() {
        LocalDate date = LocalDate.of(2022, 5, 20);
        String expected = "05-20-2022";
        String result = dateUtil.localDateToString(date);
        assertEquals(expected, result);
    }

    @Test
    public void testStringToLocalDate() {
        String stringDate = "05-20-2022";
        LocalDate expected = LocalDate.of(2022, 5, 20);
        LocalDate result = dateUtil.stringToLocalDate(stringDate);
        assertEquals(expected, result);
    }

    @Test
    public void testLocalDateTimeToString() {
        LocalDateTime dateTime = LocalDateTime.of(2022, 5, 20, 12, 30);
        String expected = "05-20-2022 12:30:00";
        String result = dateUtil.localDateTimeToString(dateTime);
        assertEquals(expected, result);
    }

    @Test
    public void testStringToLocalDateTime() {
        String stringDateTime = "05-20-2022 12:30:00";
        LocalDateTime expected = LocalDateTime.of(2022, 5, 20, 12, 30);
        LocalDateTime result = dateUtil.stringToLocalDateTime(stringDateTime);
        assertEquals(expected, result);
    }
}
