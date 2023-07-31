package model;
/**
 * @author
 * Duncan Gwin
 * dgwin4@wgu.edu
 * 008698673
 */

/**
 * Class for Contacts Objects
 */
public class Contacts {

    /**
     * Constructor for Contacts Objects
     * @param contactId Sets contactId
     * @param contactName sets contactName
     * @param email sets contact Email Address
     */
    public Contacts(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * Gets Contact ID
     * @return integer of Contact ID
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Gets Contact Name
     * @return String Name of Contact
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Used by ComboBox to convert Contact object to String
     * @return String of contactName
     */
    public String toString(){
        return contactName;
    }

    private int contactId;
    private String contactName;
    private String email;

}
