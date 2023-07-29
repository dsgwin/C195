package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.*;
import java.time.ZonedDateTime;

/**
 * Class that manages all SQL queries related to Appointments
 */
public class AppointmentsQuery {

    /**
     * Gets all appointments from the MySQL database
     * @return an Observable List of appointment objects
     */
    public static ObservableList<Appointments> getAllAppointments() {

        ObservableList<Appointments> alist = FXCollections.observableArrayList();

        try{
            String sql = "SELECT a.*, c.Contact_Name FROM appointments as a INNER JOIN contacts as c ON a.Contact_ID=c.Contact_ID";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");

                //Get LocalDateTime Objects and convert to ZonedDateTime
                Timestamp appointmentStart = rs.getTimestamp("Start");
                Timestamp appointmentEnd = rs.getTimestamp("End");


                Appointments A = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customerId, userId, contactId, contactName);
                alist.add(A);

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return alist;
    }

    /**
     * Inserts new appointment into MySQL database
     * @param appointmentTitle Sets the Appointment Title
     * @param appointmentDescription Sets the Appointment description
     * @param appointmentLocation Sets the Appointment Location
     * @param appointmentType Sets the Appointment Type
     * @param appointmentStart Sets the Appointment Start Date/Time
     * @param appointmentEnd Sets the Appointment End Date/Time
     * @param customerId Sets the Customer ID
     * @param userId Sets the User ID
     * @param contactId Sets the Contact ID
     * @param userName Sets the User Name that is updating and creating the appointment
     * @return an integer of the number of Rows affected. Should be 1 for a successful insert.
     * @throws SQLException if an SQL Exception occurs on insert
     */
    public static int insert(String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType,  Timestamp appointmentStart, Timestamp appointmentEnd, int customerId, int userId, int contactId, String userName) throws SQLException {

            String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Created_By, Last_Updated_By, Customer_ID, User_ID, Contact_ID, Last_Update) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            Timestamp lastUpdate = helper.dateTimeFormatter.getCurrentTimestamp();

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, appointmentTitle);
            ps.setString(2, appointmentDescription);
            ps.setString(3, appointmentLocation);
            ps.setString(4, appointmentType);
            ps.setTimestamp(5, appointmentStart);
            ps.setTimestamp(6, appointmentEnd);
            ps.setString(7, userName);
            ps.setString(8, userName);
            ps.setInt(9, customerId);
            ps.setInt(10, userId);
            ps.setInt(11, contactId);
            ps.setTimestamp(12, lastUpdate);

            int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    /**
     * Updates an existing appointment in the SQL database
     * @param appointmentId Sets the new Appointment ID
     * @param appointmentTitle Sets the new Appointment Title
     * @param appointmentDescription Sets the new Appointment Description
     * @param appointmentLocation Sets the new Appointment Location
     * @param appointmentType Sets the new Appointment Type
     * @param appointmentStart Sets the new Start Date/Time
     * @param appointmentEnd Sets the new End Date/Time
     * @param customerId Sets the new Customer ID
     * @param userId Sets the new user ID
     * @param contactId Sets the new Contact ID
     * @param userName Sets the user name of the Last Updated By
     * @return an integer of the number of rows affected by update. Should be 1 for a successful update.
     * @throws SQLException if an SQL exception occurs on update
     */
    public static int update(int appointmentId, String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType,  Timestamp appointmentStart, Timestamp appointmentEnd, int customerId, int userId, int contactId, String userName) throws SQLException {

        String sql = "UPDATE appointments SET Title=(?), Description=(?), Location=(?), Type=(?), Start=(?), End=(?), Last_Updated_By=(?), Customer_ID=(?), User_ID=(?), Contact_ID=(?), Last_Update=(?) WHERE Appointment_ID=(?)";

        Timestamp lastUpdate = helper.dateTimeFormatter.getCurrentTimestamp();

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, appointmentTitle);
        ps.setString(2, appointmentDescription);
        ps.setString(3, appointmentLocation);
        ps.setString(4, appointmentType);
        ps.setTimestamp(5, appointmentStart);
        ps.setTimestamp(6, appointmentEnd);
        ps.setString(7, userName);
        ps.setInt(8, customerId);
        ps.setInt(9, userId);
        ps.setInt(10, contactId);
        ps.setTimestamp(11, lastUpdate);
        ps.setInt(12, appointmentId);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    /**
     * Deletes an appointment from the database
     * @param appointmentId Appointment ID of the appointment to be deleted
     * @return an integer of the number of rows affected. Should be 1 on a successful delete
     * @throws SQLException
     */
    public static int delete(int appointmentId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID=(?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointmentId);
        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    /**
     * Deletes all customer appointments based on matching the customer ID
     * @param customerId Customer ID of the appointments to be deleted
     * @return an integer of the number of rows affected.
     * @throws SQLException if an SQL exception occurs on delete.
     */
    public static int deleteCustomerAppointments(int customerId) throws SQLException {

            String sql = "DELETE FROM appointments WHERE Customer_ID=(?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, customerId);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    /**
     * Gets all appointments between a date/time range
     * @param filterStart Start date/time of filter
     * @param filterEnd End date/time of filter
     * @return an observable list of appointments between the date range
     */
    public static ObservableList<Appointments> getAppointmentsFiltered(ZonedDateTime filterStart, ZonedDateTime filterEnd) {

        ObservableList<Appointments> alist = FXCollections.observableArrayList();

        try{
            String sql = "SELECT a.*, c.Contact_Name FROM appointments as a INNER JOIN contacts as c ON a.Contact_ID=c.Contact_ID WHERE Start BETWEEN (?) AND (?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setDate(1, Date.valueOf(filterStart.toLocalDate()));
            ps.setDate(2, Date.valueOf(filterEnd.toLocalDate()));

            ResultSet rs = ps.executeQuery();



            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");

                //Get LocalDateTime Objects and convert to ZonedDateTime
                Timestamp appointmentStart = rs.getTimestamp("Start");
                Timestamp appointmentEnd = rs.getTimestamp("End");


                Appointments A = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customerId, userId, contactId, contactName);
                alist.add(A);

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return alist;
    }

    /**
     * Gets all appointments for a selected user.
     * @param user userID of the appointments to be queried
     * @param startRange Start date/time of appointments to be queried
     * @param endRange Start date/time of appointments to be queried
     * @return an observable list of appointments for a given user
     */
    public static ObservableList<Appointments> getUserAppointments(int user, Timestamp startRange, Timestamp endRange){

        ObservableList<Appointments> alist = FXCollections.observableArrayList();

        try {
            String sql = "SELECT a.*, c.Contact_Name FROM appointments as a INNER JOIN contacts as c ON a.Contact_ID=c.Contact_ID WHERE User_ID=? AND Start BETWEEN ? and ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, user);
            ps.setTimestamp(2, startRange);
            ps.setTimestamp(3, endRange);

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");

                //Get LocalDateTime Objects and convert to ZonedDateTime
                Timestamp appointmentStart = rs.getTimestamp("Start");
                Timestamp appointmentEnd = rs.getTimestamp("End");


                Appointments A = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customerId, userId, contactId, contactName);
                alist.add(A);

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return alist;
    }

    /**
     * gets all appointments for a customer
     * @param customer Customer ID of appointments to be queried
     * @return an observable list of all appointments for a given customer
     */
    public static ObservableList<Appointments> getCustomerAppointments(int customer) {

        ObservableList<Appointments> alist = FXCollections.observableArrayList();

        try{
            String sql = "SELECT a.*, c.Contact_Name FROM appointments as a INNER JOIN contacts as c ON a.Contact_ID=c.Contact_ID WHERE a.Customer_ID=(?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, customer);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");

                //Get LocalDateTime Objects and convert to ZonedDateTime
                Timestamp appointmentStart = rs.getTimestamp("Start");
                Timestamp appointmentEnd = rs.getTimestamp("End");


                Appointments A = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customerId, userId, contactId, contactName);
                alist.add(A);

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return alist;
    }



}
