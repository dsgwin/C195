package model;

import java.sql.Date;
import java.sql.Timestamp;
/**
 * @author
 * Duncan Gwin
 * dgwin4@wgu.edu
 * 008698673
 */

/**
 * Class for Customers objects
 */
public class Customers  {

    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private Date createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    private int divisionId;
    private String division;
    private String country;

    /**
     * Constructor for Customers objects
     * @param customerId sets Customer ID
     * @param customerName sets Customer Name
     * @param address sets Address
     * @param postalCode sets Postal Code
     * @param phoneNumber sets Phone Number
     * @param createDate Sets Created Date/Time
     * @param createdBy sets Created by User
     * @param lastUpdate sets Last Update date/time
     * @param lastUpdatedBy sets last updated by User
     * @param divisionId sets Division ID
     * @param division sets Division Name
     * @param country sets Country Name
     */

    public Customers(int customerId, String customerName, String address, String postalCode, String phoneNumber, Date createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy, int divisionId, String division, String country) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionId = divisionId;
        this.division = division;
        this.country = country;


    }

    /**
     * Gets Customer ID
     * @return integer of Customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets Customer ID
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets Customer Name
     * @return string of customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Gets Customer Address
     * @return string of customer address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets postal code
     * @return string of postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Gets Customer Phone Number
     * @return string of customer phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets Division ID
     * @return integer of Division ID
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Gets Country Name
     * @return string of Country Name
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets Country Name
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }




}
