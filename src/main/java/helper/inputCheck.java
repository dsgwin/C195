package helper;

import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class inputCheck {

    public Boolean customerCheck(){
        Boolean successfulCheck = false;


        return successfulCheck;
    }

    public Boolean businessHoursCheck(LocalDateTime appointmentStart, LocalDateTime appointmentEnd){
        Boolean betweenBusinessHours = false;
        LocalTime businessOpen = LocalTime.of(8,00);
        LocalTime businessClose = LocalTime.of(22, 00);

        LocalTime appointmentStartTime = appointmentStart.toLocalTime();
        LocalTime appointmentEndTime = appointmentEnd.toLocalTime();

        if((appointmentStartTime.isAfter(businessOpen) && appointmentStartTime.isBefore(businessClose)) && (appointmentEndTime.isAfter(businessOpen) && appointmentEndTime.isBefore(businessClose))){
            betweenBusinessHours = true;
        }

        return betweenBusinessHours;
    }
}
