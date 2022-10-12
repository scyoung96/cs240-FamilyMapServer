package Model;

/**
 * A User is an entity recognized internally used to identify a login
 */
public class User {
    /**
     * The unique username identifying this User
     */
    private String username;
    /**
     * The password associated with this User
     */
    private String password;
    /**
     * The unique email address associated with this User
     */
    private String email;
    /**
     * The first name of the account owner
     */
    private String firstName;
    /**
     * The last name of the account owner
     */
    private String lastName;
    /**
     * The gender of the account owner
     */
    private String gender;
    /**
     * A unique ID associated with this User
     */
    private String personID;

    /**
     * Creates a new User
     *
     * @param username The password associated with this user
     * @param password The password associated with this user
     * @param email The unique email address associated with this user
     * @param firstName The last name of the account owner
     * @param lastName The last name of the account owner
     * @param gender The gender of the account owner
     * @param personID A unique ID associated with this user
     */
    public User(String username, String password, String email, String firstName, String lastName, String gender, String personID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
