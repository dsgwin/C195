package helper;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class dateTimeFormatter {

    public static Timestamp localToUTCTimestamp(LocalDate date, String hour, String minute) {
        LocalDateTime ldt = LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), Integer.parseInt(hour), Integer.parseInt(minute));
        // obtain the ZonedDateTime version of LocalDateTime
        ZonedDateTime locZdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        // obtain the UTC ZonedDateTime of the ZonedDateTime version of LocalDateTime
        ZonedDateTime utcZdt = locZdt.withZoneSameInstant(ZoneOffset.UTC);
        // make it look good in 24 hour format sortable by yyyy-MM-dd HH:mm:ss  (we are going to ignore fractions beyond seconds
        System.out.println(utcZdt.toLocalDateTime());
        Timestamp timestamp = Timestamp.valueOf(utcZdt.toLocalDateTime());
        return timestamp;
    }

    public static String formatTime(LocalDateTime date) {

        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return customFormatter.format(date);
    }
}
