package Request;

/**
 * Used when registering a new User
 */
public class RegisterRequest {
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
     * Creates a Request for creation of a new User
     *
     * @param username The password associated with this user
     * @param password The password associated with this user
     * @param email The unique email address associated with this user
     * @param firstName The last name of the account owner
     * @param lastName The last name of the account owner
     * @param gender The gender of the account owner
     */
    public RegisterRequest(String username, String password, String email, String firstName, String lastName, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }
}
