package helper;

import DAO.AppointmentsQuery;
import DAO.CustomerQuery;
import DAO.UsersQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.Customers;
import model.Users;

import java.time.*;
/**
 * @author
 * Duncan Gwin
 * dgwin4@wgu.edu
 * 008698673
 */

/**
 * Class to perform input validation and exception handling
 */
public class inputCheck {

    /**
     * Checks that valid integers are included on required fields for Appointment objects
     * @param customerId customerId to check
     * @param userId userId to check
     * @return a string with relevant alert message based on input check
     */
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

    /**
     * Checks if customer exists in database
     * @param customerId customerId to search in Database
     * @return Boolean value on success or failure of customer check
     */
    public static Boolean customerCheck(int customerId){
        Boolean customerExists = false;
        ObservableList<Customers> customerList = CustomerQuery.getAllCustomers();
        for(Customers customer : customerList){
            if(customer.getCustomerId() == customerId){
                customerExists=true;
            }
        }
        return  customerExists;
    }

    /**
     * Checks if a user exists in the database
     * @param userId userId to search in database
     * @return Boolean value on success or failure of user check
     */
    public static Boolean userCheck(int userId){
        Boolean userExists = false;
        ObservableList<Users> userList = UsersQuery.getAllUsers();
        for(Users user : userList){
            if(user.getUserId() == userId){
                userExists=true;
            }
        }
        return  userExists;
    }

    /**
     * Checks if appointment times overlap with existing appointments for customer
     * @param customerId customerId to check
     * @param appointmentId appointmentId of new or updated appointment
     * @param newStartTime new appointment start time
     * @param newEndTime new appointment end time
     * @return integer value of number of appointments where overlap exists. 0 will be returned if no overlap exists
     */
    public static ObservableList<Appointments> overlapCheck(int customerId, int appointmentId, LocalDateTime newStartTime, LocalDateTime newEndTime){

        int overlap = 0;
        ObservableList<Appointments> appointments = AppointmentsQuery.getCustomerAppointments(customerId);
        //Modify to make error message more specific
        ObservableList<Appointments> conflictingAppointments = FXCollections.observableArrayList();
        for(Appointments appointment : appointments){
            LocalDateTime appointmentStart = appointment.getStart().toLocalDateTime();
            LocalDateTime appointmentEnd = appointment.getEnd().toLocalDateTime();

            if(appointmentId != appointment.getAppointmentId())
            if((newStartTime.isAfter(appointmentStart) && newStartTime.isBefore(appointmentEnd)) ||
                    (newEndTime.isAfter(appointmentStart) && newEndTime.isBefore(appointmentEnd)))
            {
                conflictingAppointments.add(appointment);
            }

        }
        return conflictingAppointments;
    }

    /**
     * Checks if appointment is within business hours in Eastern Time
     * @param appointmentStart appointment start time to check
     * @param appointmentEnd appointment end time to check
     * @return Boolean value of whether appointment is within business hours.
     */
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
