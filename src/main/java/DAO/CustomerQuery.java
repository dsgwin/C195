package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;

import java.sql.*;

/**
 * @author
 * Duncan Gwin
 * dgwin4@wgu.edu
 * 008698673
 */

/**
 * Class that manages all SQL queries related to Customers
 */
public class CustomerQuery {

    /**
     * Gets all customers from database
     * @return an observable list of customer objects
     */
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

    /**
     * Inserts new customer into database
     * @param customerName Sets Customer Name
     * @param address Sets Address
     * @param postalCode Sets postalCode
     * @param phone Sets Phone Number
     * @param divisionId Sets First Level Division ID
     * @param user Sets User that added/updated customer
     * @return an integer of the number of rows affected. Should be 1 for a successful insert
     * @throws SQLException if an SQL exception occurs on insert
     */
    public static int insert(String customerName, String address, String postalCode, String phone, int divisionId, String user) throws SQLException {

        Timestamp lastUpdate = helper.dateTimeFormatter.getCurrentTimestamp();

        String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID, Created_By, Last_Updated_By, Last_Update) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2,address);
        ps.setString(3,postalCode);
        ps.setString(4,phone);
        ps.setInt(5,divisionId);
        ps.setString(6, user);
        ps.setString(7, user);
        ps.setTimestamp(8, lastUpdate);
        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    /**
     * Updates an existing customer in the database
     * @param customerId Sets new CustomerID
     * @param customerName Sets new Customer Name
     * @param address Sets new Address
     * @param postalCode Sets new Postal Code
     * @param phone Sets new Phone Number
     * @param divisionId Sets new First Level Division ID
     * @param userName Sets Username of user who last updated the customer
     * @return an integer of number of rows affected. Should be 1 for a successful update.
     * @throws SQLException if an SQL Exception occurs on update
     */
    public static int update(int customerId, String customerName, String address, String postalCode, String phone, int divisionId, String userName) throws SQLException {

        Timestamp lastUpdate = helper.dateTimeFormatter.getCurrentTimestamp();

        String sql = "UPDATE customers SET Customer_Name=(?), Address=(?), Postal_Code=(?), Phone=(?), Division_ID=(?), Last_Updated_By=(?), Last_Update=(?) WHERE Customer_ID=(?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2,address);
        ps.setString(3,postalCode);
        ps.setString(4,phone);
        ps.setInt(5,divisionId);
        ps.setString(6,userName);
        ps.setTimestamp(7, lastUpdate);
        ps.setInt(8,customerId);
        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }


    /**
     * Deletes customer from databased
     * @param customerId CustomerID of customer to deleted
     * @return integer of number of rows affected. Should be 1 for a succesful deletion.
     * @throws SQLException if an SQL Exception occurs on deletion.
     */
    public static int delete(int customerId) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID=(?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
        }

}
