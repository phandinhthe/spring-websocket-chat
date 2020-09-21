package com.terry.phan.chat.system.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {
    private static final DateTimeFormatter defaultFormat = DateTimeFormatter.ISO_TIME;
    public static String getCurrentTime() {
        return getCurrentTimeByFormat(defaultFormat);
    }

    public static String getCurrentTimeByFormat(DateTimeFormatter formatter) {
        return formatter.format(LocalDateTime.now());
    }
}
