package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.*;

public abstract class CustomerQuery {

    public static ObservableList<Customers> getAllCustomers(){
        ObservableList<Customers> customerlist = FXCollections.observableArrayList();

        try{
            String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, c.Division_ID as Division_ID, " +
                    "c.Create_Date, c.Created_By, c.Last_Update, c.Last_Updated_By, Division, countries.Country " +
                    "FROM customers  AS c " +
                    "INNER JOIN first_level_divisions AS d ON c.Division_ID=d.Division_ID " +
                    "INNER JOIN countries ON d.Country_ID = countries.Country_ID;";

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
                String country = rs.getString("Country");

                Customers C = new Customers(customerId, customerName, address, postalCode, phoneNumber, createDate, createdBy, lastUpdate, lastUpdatedBy, divisionId, division, country);
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

    public static int update(int customerId, String customerName, String address, String postalCode, String phone, int divisionId, String userName) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name=(?), Address=(?), Postal_Code=(?), Phone=(?), Division_ID=(?), Last_Updated_By=(?) WHERE Customer_ID=(?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2,address);
        ps.setString(3,postalCode);
        ps.setString(4,phone);
        ps.setInt(5,divisionId);
        ps.setString(6,userName);
        ps.setInt(7,customerId);
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
