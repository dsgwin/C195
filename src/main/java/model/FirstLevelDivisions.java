package model;
/**
 * @author
 * Duncan Gwin
 * dgwin4@wgu.edu
 * 008698673
 */

/**
 * Class for FirstLevelDivision objects
 */
public class FirstLevelDivisions {

    private int divisionId;
    private String divisionName;
    private int countryId;

    /**
     * Constructor for FirstLevelDivision Objects
     * @param divisionId Sets Division ID
     * @param divisionName Sets Division Name
     * @param countryId Sets Country ID
     */
    public FirstLevelDivisions(int divisionId, String divisionName, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }

    /**
     * Gets Division ID
     * @return integer of Division ID
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Gets Country ID
     * @return integer of Country ID
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Used to convert FirstLevelDivision object to string
     * @return string of divisionName
     */
    @Override
    public String toString() {
        return divisionName;
    }
}
