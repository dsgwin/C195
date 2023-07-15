package model;

import java.sql.Date;
import java.sql.Timestamp;

public class FirstLevelDivisions {

    private int divisionId;
    private String divisionName;
    private int countryId;

    public FirstLevelDivisions(int divisionId, String divisionName, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public int getCountryId() {
        return countryId;
    }



}
