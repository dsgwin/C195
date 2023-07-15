package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;
import model.Customers;

import java.sql.*;

public abstract class CustomerQuery {

    public static ObservableList<Customers> getAllCustomers(){
        ObservableList<Customers> customerlist = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * from customers";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode = rs.getString("Postal_Code");
                String phoneNumber = rs.getString("Phone");
                Date createDate = rs.getDate("Create_Date");
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdate = rs.getTimestamp("Last_Update");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                int divisionId = rs.getInt("Division_ID");

                Customers C = new Customers(customerId, customerName, address, postalCode, phoneNumber, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionId);
                customerlist.add(C);

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return customerlist;


    };

    // BROKEN - Division_ID required for Insert
    public static int insert(String customerName, String address, String postalCode) throws SQLException {
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code) VALUES(?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2,address);
        ps.setString(3,postalCode);
        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }
}
