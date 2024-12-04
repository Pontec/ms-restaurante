package com.pioriko.ms_restaurante.agregates.util;

import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class DateTimeFormat {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

}
