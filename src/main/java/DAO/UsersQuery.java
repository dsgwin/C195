package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersQuery {

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
}