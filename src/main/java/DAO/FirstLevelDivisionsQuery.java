package DAO;

import helper.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivisions;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class FirstLevelDivisionsQuery {

    public static ObservableList<FirstLevelDivisions> getAllDivisions() {

        ObservableList<FirstLevelDivisions> firstLevelDivisionsList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * from first_level_divisions";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");
                FirstLevelDivisions D= new FirstLevelDivisions(divisionId, divisionName, countryId);
                firstLevelDivisionsList.add(D);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return firstLevelDivisionsList;
    }
}
