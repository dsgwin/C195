package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public abstract class AppointmentsQuery {

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

    public static int delete(int appointmentId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID=(?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointmentId);
        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    public static int deleteCustomerAppointments(int customerId) throws SQLException {

            String sql = "DELETE FROM appointments WHERE Customer_ID=(?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1, customerId);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

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

    public static ObservableList<Appointments> getUserAppointments(int user) {

        ObservableList<Appointments> alist = FXCollections.observableArrayList();

        try{
            String sql = "SELECT a.*, c.Contact_Name FROM appointments as a INNER JOIN contacts as c ON a.Contact_ID=c.Contact_ID WHERE a.User_ID=(?))";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, user);

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
