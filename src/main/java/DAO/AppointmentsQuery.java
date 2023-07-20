package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AppointmentsQuery {

    public static ObservableList<Appointments> getAllAppointments() {

        ObservableList<Appointments> alist = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * from appointments";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String appointmentTitle = rs.getString("Title");
                String appointmentDescription = rs.getString("Description");
                String appointmentLocation = rs.getString("Location");
                String appointmentType = rs.getString("Type");
                Date appointmentStart = rs.getDate("Start");
                Date appointmentEnd = rs.getDate("End");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");
                int contactId = rs.getInt("Contact_ID");
                Appointments A = new Appointments(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, appointmentStart, appointmentEnd, customerId, userId, contactId);
                alist.add(A);

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return alist;
    }

    public static int insert(String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType,  Date appointmentStart, Date appointmentEnd, int customerId, int userId, int contactId, String userName) throws SQLException {

            String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Created_By, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, appointmentTitle);
            ps.setString(2, appointmentDescription);
            ps.setString(3, appointmentLocation);
            ps.setString(4, appointmentType);
            ps.setDate(5, appointmentStart);
            ps.setDate(6, appointmentEnd);
            ps.setString(7, userName);
            ps.setString(8, userName);
            ps.setInt(9, customerId);
            ps.setInt(10, userId);
            ps.setInt(11, contactId);

            int rowsAffected = ps.executeUpdate();



        return rowsAffected;
    }

    public static int update(int appointmentId, String appointmentTitle, String appointmentDescription, String appointmentLocation, String appointmentType,  Date appointmentStart, Date appointmentEnd, int customerId, int userId, int contactId, String userName) throws SQLException {

        String sql = "UPDATE appointments SET Title=(?), Description=(?), Location=(?), Type=(?), Start=(?), End=(?), Last_Updated_By=(?), Customer_ID=(?), User_ID=(?), Contact_ID=(?) WHERE Appointment_ID=(?)";

        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, appointmentTitle);
        ps.setString(2, appointmentDescription);
        ps.setString(3, appointmentLocation);
        ps.setString(4, appointmentType);
        ps.setDate(5, appointmentStart);
        ps.setDate(6, appointmentEnd);
        ps.setString(7, userName);
        ps.setInt(8, customerId);
        ps.setInt(9, userId);
        ps.setInt(10, contactId);
        ps.setInt(11, appointmentId);

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



}