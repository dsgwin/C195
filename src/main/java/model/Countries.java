package model;

/**
 * Class for Countries objects
 */
public class Countries {

    private int countryId;
    private String countryName;

    /**
     * Constructor for Countries objects
     * @param countryId sets Country ID
     * @param countryName Sets Country Name
     */
    public Countries(int countryId, String countryName) {
        setCountryId(countryId);
        setCountryName(countryName);
    }

    /**
     * Gets Country ID
     * @return integer of Country ID
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Sets Country ID
     * @param countryId
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Sets country name
     * @param countryName
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Used by combo boxes to convert countries objects to string value
     * @return string of countryName
     */
    public String toString() {
        return countryName;
    }

}
