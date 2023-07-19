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
            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, " +
                         "customers.Division_ID as Division_ID, customers.Create_Date, customers.Created_By, " +
                         "customers.Last_Update,customers.Last_Updated_By, Division " +
                         "FROM customers " +
                         "INNER JOIN first_level_divisions " +
                         "ON customers.Division_ID=first_level_divisions.Division_ID;";

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
                String division = rs.getString("Division");

                Customers C = new Customers(customerId, customerName, address, postalCode, phoneNumber, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionId, division);
                customerlist.add(C);

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return customerlist;


    };

    public static int insert(String customerName, String address, String postalCode, String phone, int divisionId) throws SQLException {
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2,address);
        ps.setString(3,postalCode);
        ps.setString(4,phone);
        ps.setInt(5,divisionId);
        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    public static int update(int customerId, String customerName, String address, String postalCode, String phone, int divisionId) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name=(?), Address=(?), Postal_Code=(?), Phone=(?), Division_ID=(?) WHERE Customer_ID=(?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2,address);
        ps.setString(3,postalCode);
        ps.setString(4,phone);
        ps.setInt(5,divisionId);
        ps.setInt(6,customerId);
        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }


    public static int delete(String customerId) throws SQLException {
        String sql = "DELETE FROM customers (Customer_ID) VALUES(?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
        }
/*
    public static Customers getCustomer(int customerID) throws SQLException {

        String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, c.Division_ID as Division_ID, c.Create_Date, c.Created_By, c.Last_Update, c.Last_Updated_By, Division, countries.Country " +
        "FROM customers as c INNER JOIN first_level_divisions as d ON c.Division_ID=d.Division_ID INNER JOIN countries ON countries.Country_ID=d.Country_ID WHERE Customer_ID = (?);" ;
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setInt(1, customerID);
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
            String division = rs.getString("Division");

            Customers customer = new Customers(customerId, customerName, address, postalCode, phoneNumber, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionId, division);

        }
        return customer;
    }*/

}
