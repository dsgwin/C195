package helper;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

public abstract class dateTimeFormatter {

    public static Timestamp localToTimestamp(LocalDate date, String hour, String minute) {

        LocalDateTime ldt = LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), Integer.parseInt(hour), Integer.parseInt(minute));
        ZonedDateTime locZdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        ZonedDateTime utcZdt = locZdt.withZoneSameInstant(ZoneOffset.UTC);
        ZonedDateTime edtZdt = locZdt.withZoneSameInstant(ZoneId.of("America/New_York"));
        Timestamp timestamp = Timestamp.valueOf(locZdt.toLocalDateTime());
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

    public static ZonedDateTime localToUTC(LocalDateTime dateTime) {
        ZonedDateTime locZdt = ZonedDateTime.of(dateTime, ZoneId.systemDefault());
        ZonedDateTime utcZdt = locZdt.withZoneSameInstant(ZoneOffset.UTC);
        return utcZdt;
    }

    public static Timestamp getCurrentTimestamp() {
        LocalDateTime currentTime = LocalDateTime.now();
        ZonedDateTime locZdt = ZonedDateTime.of(currentTime, ZoneId.systemDefault());
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

    public static String formatDate(LocalDate date) {

        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return customFormatter.format(date);
    }


}
