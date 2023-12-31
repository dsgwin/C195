package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * @author
 * Duncan Gwin
 * dgwin4@wgu.edu
 * 008698673
 */

/**
 * Class that manages all SQL Queries related to Contacts
 */
public class ContactsQuery {

    /**
     * Gets all Contacts from SQL database
     * @return an observable list of contact objects
     */
    public static ObservableList<Contacts> getAllContacts(){
        ObservableList<Contacts> contactList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * from contacts";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int contactId = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String emailAddress = rs.getString("Email");
                Contacts C = new Contacts(contactId, contactName, emailAddress);
                contactList.add(C);

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return  contactList;
    }
}
