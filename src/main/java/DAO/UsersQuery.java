package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

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
 * Manages all SQL queries related to Users
 */
public class UsersQuery {

    /**
     * Gets all Users from the SQL database
     * @return an observable list of Users objects
     */
    public static ObservableList<Users> getAllUsers() {

        ObservableList<Users> userList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * from users";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);


            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int userId = rs.getInt("User_ID");
                String username = rs.getString("User_Name");
                String password = rs.getString("Password");

                Users user = new Users(userId, username, password);
                userList.add(user);

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return userList;
    }

    /**
     * Checks for UserName and Password presence in database to verify login
     * @param loginUserName Username to check
     * @param loginPassword Password to check
     * @return Boolean value of true for successful or false for failed login.
     * @throws SQLException if SQL Exception occurs related to query execution.
     */
    public static Boolean userLoginQuery(String loginUserName, String loginPassword) throws SQLException {
        Boolean loginResult = false;

        String sql = "SELECT User_Name, Password, User_ID from users WHERE User_Name=(?)";
        PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
        ps.setString(1, loginUserName);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int userId = rs.getInt("User_ID");
            String userPassword = rs.getString("Password");
            String userName = rs.getString("User_Name");

            if (loginPassword.equals(userPassword)) {
                loginResult = true;
                Users.currentUserName = userName;
                Users.currentUserId = userId;
            } else {
                loginResult = false;
            }
        }
        return loginResult;
    }
}
