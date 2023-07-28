package model;


public class Users {

    public static int currentUserId;
    public static String currentUserName;

    public Users(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }


    private int userId;
    private String userName;
    private String password;


}
