package helper;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

public abstract class dateTimeFormatter {

    public static Timestamp localToTimestamp(LocalDate date, String hour, String minute) {
        System.out.println("System Time: " + date.getYear() + date.getMonthValue() + date.getDayOfMonth() + Integer.parseInt(hour) + Integer.parseInt(minute));
        LocalDateTime ldt = LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), Integer.parseInt(hour), Integer.parseInt(minute));
        System.out.println("System Time: " + ldt);
        ZonedDateTime locZdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        System.out.println("System Zoned: " + locZdt + " - " + ZoneId.systemDefault());
        ZonedDateTime utcZdt = locZdt.withZoneSameInstant(ZoneOffset.UTC);
        System.out.println("UTC Zoned: " + utcZdt + " - " + ZoneId.of("UTC"));
        ZonedDateTime edtZdt = locZdt.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println("EDT Zoned: " + edtZdt + " - " + ZoneId.of("America/New_York"));
        Timestamp timestamp = Timestamp.valueOf(locZdt.toLocalDateTime());
        System.out.println("UTC Timestamp: " + timestamp);
        return timestamp;
    }

    public static Timestamp localToTimestamp(LocalDateTime dateTime) {
        ZonedDateTime locZdt = ZonedDateTime.of(dateTime, ZoneId.systemDefault());
        ZonedDateTime utcZdt = locZdt.withZoneSameInstant(ZoneOffset.UTC);
        ZonedDateTime edtZdt = locZdt.withZoneSameInstant(ZoneId.of("America/New_York"));
        Timestamp timestamp = Timestamp.valueOf(locZdt.toLocalDateTime());
        return timestamp;
    }

    public static ZonedDateTime localToEDT(LocalDateTime dateTime) {
        ZonedDateTime locZdt = ZonedDateTime.of(dateTime, ZoneId.systemDefault());
        ZonedDateTime edtZdt = locZdt.withZoneSameInstant(ZoneId.of("America/New_York"));
        return edtZdt;
    }

    public static Timestamp localToUTCTimestamp(LocalDateTime dateTime) {
        ZonedDateTime locZdt = ZonedDateTime.of(dateTime, ZoneId.systemDefault());
        ZonedDateTime utcZdt = locZdt.withZoneSameInstant(ZoneOffset.UTC);
        Timestamp timestamp = Timestamp.valueOf(utcZdt.toLocalDateTime());
        return timestamp;
    }

    public static String formatTimeHour(LocalDateTime date) {

        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("HH");

        return customFormatter.format(date);
    }
    public static String formatTimeMinute(LocalDateTime date) {

        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("mm");

        return customFormatter.format(date);
    }
}
