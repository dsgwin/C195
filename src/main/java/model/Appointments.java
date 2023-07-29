package model;


import java.sql.Timestamp;

/**
 * Class for Appointments objects
 */
public class Appointments {

    /**
     * Constructor for appointment objects
     * @param appointmentId Sets Appointment ID
     * @param title Sets Title of Appointment
     * @param description Sets Description of Appointment
     * @param location Sets Location of Appointment
     * @param type Sets Type of Appointment
     * @param start Sets Start Time of Appointment
     * @param end Sets End Time of Appointment
     * @param customerId Sets Customer ID of Appointment
     * @param userId Sets User ID of Appointment
     * @param contactId Sets Contact of Appointment
     * @param contactName Sets Contact Name of Appointment
     */
    public Appointments(int appointmentId, String title, String description, String location, String type, Timestamp start, Timestamp end, int customerId, int userId, int contactId, String contactName) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
        this.contactName = contactName;
    }

    /**
     * Gets the appointment ID
     * @return integer of Appointment ID
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Gets title of Appointment
     * @return String of Title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets description of Appointment
     * @return String of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets Location of Appointment
     * @return String of location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Get Type of Appointment
     * @return String of Type
     */
    public String getType() {
        return type;
    }


    /**
     * Get Start Time of Appointment
     * @return Timestamp of Start Time
     */
    public Timestamp getStart() {
        return start;
    }

    /**
     * Get End Time of Appointment
     * @return Timestamp of Appointment
     */
    public Timestamp getEnd() {
        return end;
    }

    /**
     * Gets Customer ID of Appointment
     * @return integer of Customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Gets User ID of Appointment
     * @return integer of User ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Gets ContactID of Appointment
     * @return integer of contact ID
     */
    public int getContactId() {
        return contactId;
    }

    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private Timestamp start;
    private Timestamp end;
    private int customerId;
    private int userId;
    private int contactId;
    private String contactName;
}
