package model;


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

    public int getCountryId() {
        return countryId;
    }

    @Override
    public String toString() {
        return divisionName;
    }
}
