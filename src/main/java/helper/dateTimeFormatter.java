package helper;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class dateTimeFormatter {

    public static Timestamp localToUTCTimestamp(LocalDate date, String hour, String minute) {
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

    public static Timestamp localToUTCTimestamp(LocalDateTime dateTime) {
        System.out.println("System Time: " + dateTime);
        ZonedDateTime locZdt = ZonedDateTime.of(dateTime, ZoneId.systemDefault());
        System.out.println("System Zoned: " + locZdt + " - " + ZoneId.systemDefault());
        ZonedDateTime utcZdt = locZdt.withZoneSameInstant(ZoneOffset.UTC);
        System.out.println("UTC Zoned: " + utcZdt + " - " + ZoneId.of("UTC"));
        ZonedDateTime edtZdt = locZdt.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println("EDT Zoned: " + edtZdt + " - " + ZoneId.of("America/New_York"));
        Timestamp timestamp = Timestamp.valueOf(locZdt.toLocalDateTime());
        System.out.println("UTC Timestamp: " + timestamp);
        return timestamp;
    }

    public static String formatTime(LocalDateTime date) {

        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return customFormatter.format(date);
    }
}
