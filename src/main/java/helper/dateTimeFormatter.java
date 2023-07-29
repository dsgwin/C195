package helper;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Class that assists with convert DateTime and Timestamp objects
 */
public abstract class dateTimeFormatter {

    /**
     * Converts a Local Date and Time to Unix Timestamp
     * @param date Selected date
     * @param hour Selected hour
     * @param minute Selected Minute
     * @return Timestamp of selected date and time
     */
    public static Timestamp localToTimestamp(LocalDate date, String hour, String minute) {

        LocalDateTime ldt = LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), Integer.parseInt(hour), Integer.parseInt(minute));
        ZonedDateTime locZdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        ZonedDateTime utcZdt = locZdt.withZoneSameInstant(ZoneOffset.UTC);
        ZonedDateTime edtZdt = locZdt.withZoneSameInstant(ZoneId.of("America/New_York"));
        Timestamp timestamp = Timestamp.valueOf(locZdt.toLocalDateTime());
        return timestamp;
    }

    /**
     * Converts a LocalDateTime object to a unix timestamp
     * @param dateTime dateTime to convert
     * @return Timestamp of converted DateTime
     */
    public static Timestamp localToTimestamp(LocalDateTime dateTime) {
        ZonedDateTime locZdt = ZonedDateTime.of(dateTime, ZoneId.systemDefault());
        ZonedDateTime utcZdt = locZdt.withZoneSameInstant(ZoneOffset.UTC);
        ZonedDateTime edtZdt = locZdt.withZoneSameInstant(ZoneId.of("America/New_York"));
        Timestamp timestamp = Timestamp.valueOf(locZdt.toLocalDateTime());
        return timestamp;
    }

    /**
     * Converts a LocalDateTime object to a ZonedDateTime object in Eastern Time
     * @param dateTime DateTime to convert
     * @return ZonedDateTime in Eastern Time
     */
    public static ZonedDateTime localToEDT(LocalDateTime dateTime) {
        ZonedDateTime locZdt = ZonedDateTime.of(dateTime, ZoneId.systemDefault());
        ZonedDateTime edtZdt = locZdt.withZoneSameInstant(ZoneId.of("America/New_York"));
        return edtZdt;
    }

    /**
     * Converts a LocalDateTime object to a ZonedDateTime object in UTC
     * @param dateTime DateTime to convert
     * @return ZonedDateTime in UTC
     */
    public static ZonedDateTime localToUTC(LocalDateTime dateTime) {
        ZonedDateTime locZdt = ZonedDateTime.of(dateTime, ZoneId.systemDefault());
        ZonedDateTime utcZdt = locZdt.withZoneSameInstant(ZoneOffset.UTC);
        return utcZdt;
    }

    /**
     * Gets current time and converts to Timestamp
     * @return Timestamp of current LocalDateTime
     */
    public static Timestamp getCurrentTimestamp() {
        LocalDateTime currentTime = LocalDateTime.now();
        ZonedDateTime locZdt = ZonedDateTime.of(currentTime, ZoneId.systemDefault());
        ZonedDateTime utcZdt = locZdt.withZoneSameInstant(ZoneOffset.UTC);
        Timestamp timestamp = Timestamp.valueOf(utcZdt.toLocalDateTime());
        return timestamp;
    }

    /**
     * Formats local datetime to display Hour only.
     * @param date
     * @return String of HH format
     */
    public static String formatTimeHour(LocalDateTime date) {

        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("HH");

        return customFormatter.format(date);
    }

    /**
     * Formats local datetime to display Minutes only.
     * @param date
     * @return String of mm format
     */
    public static String formatTimeMinute(LocalDateTime date) {

        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("mm");

        return customFormatter.format(date);
    }

}
