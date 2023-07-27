package helper;

import DAO.AppointmentsQuery;
import javafx.collections.ObservableList;
import model.Appointments;

import java.time.*;

public abstract class inputCheck {

    public static String appointmentCheck(String customerId, String userId){

        String alertText = null;

        try {
            int customerInt = Integer.parseInt(customerId);
        }
        catch (Exception e) {
            alertText = "Customer ID Field Invalid. Must be an Integer.";
        }
        try {
            int userInt = Integer.parseInt(userId);
        }
        catch (Exception e) {

            alertText = "User ID Field Invalid. Must be an Integer.";

        }

        return alertText;
    }

    public static int overlapCheck(int customerId, LocalDateTime newStartTime, LocalDateTime newEndTime){

        int overlap = 0;
        ObservableList<Appointments> appointments = AppointmentsQuery.getCustomerAppointments(customerId);
        for(Appointments appointment : appointments){
            LocalDateTime appointmentStart = appointment.getStart().toLocalDateTime();
            LocalDateTime appointmentEnd = appointment.getEnd().toLocalDateTime();

            if((newStartTime.isAfter(appointmentStart) && newStartTime.isBefore(appointmentEnd)) ||
                    (newEndTime.isAfter(appointmentStart) && newEndTime.isBefore(appointmentEnd)))
            {
                overlap++;
            }

        }
        return overlap;
    }

    public static Boolean businessHoursCheck(LocalDateTime appointmentStart, LocalDateTime appointmentEnd){
        Boolean betweenBusinessHours = false;
        LocalTime businessOpen = LocalTime.of(7,59);
        LocalTime businessClose = LocalTime.of(22, 01);

        ZonedDateTime businessOpenEDT = ZonedDateTime.of(LocalDate.now(), businessOpen, ZoneId.of("America/New_York"));

        LocalTime appointmentStartTimeEDT = helper.dateTimeFormatter.localToEDT(appointmentStart).toLocalTime();
        LocalTime appointmentEndTimeEDT = helper.dateTimeFormatter.localToEDT(appointmentEnd).toLocalTime();

        if(appointmentStartTimeEDT.isAfter(businessOpen) && appointmentEndTimeEDT.isBefore(businessClose)){
            betweenBusinessHours = true;
        }
        else{
            betweenBusinessHours = false;
        }

        return betweenBusinessHours;
    }


}
