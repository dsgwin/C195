package helper;

import DAO.AppointmentsQuery;
import javafx.collections.ObservableList;
import model.Appointments;

import java.time.LocalDateTime;
import java.time.LocalTime;

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

    public static Boolean overlapCheck(int customerId, LocalDateTime startTime, LocalDateTime endTime){

        Boolean overlap = false;
        ObservableList<Appointments> appointments = AppointmentsQuery.getCustomerAppointments(customerId);
        for(Appointments appointment : appointments){
            LocalDateTime appointmentStart = appointment.getStart().toLocalDateTime();
            LocalDateTime appointmentEnd = appointment.getEnd().toLocalDateTime();

            if(startTime.isBefore(appointmentEnd) || endTime.isAfter(appointmentStart)){
                overlap = true;
            }
        }
        return overlap;
    }

    public static Boolean businessHoursCheck(LocalDateTime appointmentStart, LocalDateTime appointmentEnd){
        Boolean betweenBusinessHours = false;
        LocalTime businessOpen = LocalTime.of(8,00);
        LocalTime businessClose = LocalTime.of(22, 00);

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
