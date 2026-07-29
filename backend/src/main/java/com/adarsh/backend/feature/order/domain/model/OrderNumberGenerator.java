package com.adarsh.backend.feature.order.domain.model;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public final class OrderNumberGenerator {

    private static final String PREFIX = "ORD";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.BASIC_ISO_DATE;

    private OrderNumberGenerator() {
    }

    public static String generate() {
        String date = LocalDate.now(Clock.systemDefaultZone()).format(DATE_FORMAT);
        String random = UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();

        return "%s-%s-%s".formatted(PREFIX, date, random);
    }
}