package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

}
