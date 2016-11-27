package davditran.waterresources.model;

import java.io.Serializable;

/**
 * Created by David on 11/24/2016.
 */

public class User implements Serializable {
    //Basic Information
    private String username;
    private String password;
    private String accountType;
    private String email;

    private static final long serialVersionUID = 1L;


    //Advanced Information
    private String address = "";
    private String title = "";

    /**
     * Constructs new User
     */
    public User() {}

    /**
     * Constructs new User
     * @param username User's username
     * @param password User's password
     */
    public User(String username, String password, String accountType, String email) {
        setUsername(username);
        setPassword(password);
        setAccountType(accountType);
        setEmail(email);
        setAddress(this.address);
        setTitle(this.title);
    }

    //BASIC INFORMATION --------------------------------------------------------
    /**
     * Sets username
     * @param username given username
     */
    private void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets password
     * @param password given password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the account type
     * @return AccountType the user account type
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets account type
     * @param accountType given title
     */
    private void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    //ADVANCED INFORMATION -----------------------------------------------------
    /**
     * Sets email
     * @param email given email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets address
     * @param address given address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets title
     * @param title given title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets title
     * @return title
     */
    public String getTitle() {
        return title;
    }

}
