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

    public static int insert(String customerName, String address, String postalCode, String phone, int divisionId, String user) throws SQLException {
        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID, Created_By, Last_Updated_By) VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2,address);
        ps.setString(3,postalCode);
        ps.setString(4,phone);
        ps.setInt(5,divisionId);
        ps.setString(6, user);
        ps.setString(7, user);
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


    public static int delete(int customerId) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID=(?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
        }

}
