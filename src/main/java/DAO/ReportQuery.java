package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Month;

public abstract class ReportQuery {

    public static ObservableList<String> getAppointmentsByType() {

        ObservableList<String> list = FXCollections.observableArrayList();

        try{
            String sql = "SELECT MONTH(Start) as Month, Type, Count(Appointment_ID) as Total FROM Appointments GROUP BY Type, Month";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            String headerString = String.format("%-20s%-30s%-10s", "Month", "Type", "Total");

            list.add(headerString+"\n");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String month = Month.of(rs.getInt("Month")).name();
                String type = rs.getString("Type");
                int total = rs.getInt("Total");
                String inputString = String.format("%-20s%-30s%-10s", month, type, String.valueOf(total));
                list.add(inputString+"\n");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return list;
    }

    public static ObservableList<String> getContactSchedule(int contacId) {

        ObservableList<String> list = FXCollections.observableArrayList();

        try{
            String sql = "SELECT c.Contact_Name, a.* FROM appointments as a INNER JOIN contacts as c ON a.Contact_ID=c.Contact_ID WHERE c.Contact_ID=?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setInt(1, contacId);
            String headerString = String.format("%15s %-20s %-20s %-20s %-30s %-25s %-25s %-11s", "               ",
                    "Appointment ID", "Title", "Type", "Description", "Start", "End", "Customer ID");

            list.add(headerString+"\n");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String type = rs.getString("Type");
                String description = rs.getString("Description");
                String start = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(rs.getTimestamp("Start"));
                String end = new SimpleDateFormat("MM/dd/yyyy HH:mm").format(rs.getTimestamp("End"));
                int customerId = rs.getInt("Customer_ID");
                String inputString = String.format("%15s %-20s %-20s %-20s %-30s %-25s %-25s %-11s", "               ",
                        appointmentId, title, type, description, start, end, customerId);
                list.add(inputString+"\n");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return list;
    }

    public static ObservableList<String> getAppointmentsByCountry() {

        ObservableList<String> list = FXCollections.observableArrayList();

        try{
            String sql = "SELECT ct.Country, COUNT(a.Appointment_ID) AS Total FROM Appointments AS a\n" +
                         "INNER JOIN Customers AS c ON c.Customer_ID = a.Customer_ID\n" +
                         "INNER JOIN first_level_divisions AS d ON c.Division_ID = d.Division_ID\n" +
                         "INNER JOIN Countries AS ct ON d.Country_ID = ct.Country_ID\n" +
                         "GROUP BY ct.Country";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            String headerString = String.format("%-20s%-20s", "Country", "Total Appointments");

            list.add(headerString+"\n");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String country = rs.getString("Country");
                int total = rs.getInt("Total");

                String inputString = String.format("%-20s%-20s", country, total);
                list.add(inputString+"\n");
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return list;
    }

}
