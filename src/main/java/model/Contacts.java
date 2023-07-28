package model;

public class Contacts {

    public Contacts(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }

    public int getContactId() {
        return contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public String toString(){
        return contactName;
    }

    private int contactId;
    private String contactName;
    private String email;

}
