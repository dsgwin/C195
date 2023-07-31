package model;
/**
 * @author
 * Duncan Gwin
 * dgwin4@wgu.edu
 * 008698673
 */

/**
 * Class for Users objects
 */
public class Users {

    public static int currentUserId;
    public static String currentUserName;

    /**
     * Constructor for User Objects
     * @param userId sets UserId
     * @param userName Sets Username
     * @param password Sets user Password
     */
    public Users(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /**
     * Gets User ID
     * @return integer of User ID
     */
    public int getUserId() {
        return userId;
    }

    private int userId;
    private String userName;
    private String password;


}
