package helper;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public class dateTimeFormatter {

    public static String formatUTC(LocalDate date, String hour, String minute) {
        LocalDateTime ldt = LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), Integer.parseInt(hour), Integer.parseInt(minute));
        // obtain the ZonedDateTime version of LocalDateTime
        ZonedDateTime locZdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        // obtain the UTC ZonedDateTime of the ZonedDateTime version of LocalDateTime
        ZonedDateTime utcZdt = locZdt.withZoneSameInstant(ZoneOffset.UTC);
        // make it look good in 24 hour format sortable by yyyy-MM-dd HH:mm:ss  (we are going to ignore fractions beyond seconds
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return customFormatter.format(utcZdt);
    }

    public static String formatLocal(LocalDate date, String hour, String minute) {
        LocalDateTime ldt = LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), Integer.parseInt(hour), Integer.parseInt(minute));
        // obtain the ZonedDateTime version of LocalDateTime
        ZonedDateTime locZdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        // make it look good in 24 hour format sortable by yyyy-MM-dd HH:mm:ss  (we are going to ignore fractions beyond seconds
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return customFormatter.format(locZdt);
    }

    public static String dateToLocalTimeZone(Date date, TimeZone timeZone){
        /*DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern(date.toString());
        LocalDateTime LDT = customFormatter;
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = ZonedDateTime.of(date, zoneId);
        SimpleDateFormat test = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        test.setTimeZone(TimeZone.parseCustomTimeZone(zoneId));
        */
        String test = "test";
        return test;
    }
}
