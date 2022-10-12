package Result;

/**
 * Result of the requested register for a new 'User'
 */
public class RegisterResult {
    /**
     * The authtoken to associate with the newly created User for this session
     */
    private String authtoken;
    /**
     * The username of the newly created User
     */
    private String username;
    /**
     * The unique ID of the newly created User
     */
    private String personID;
    /**
     * A message containing the details of the outcome of the request
     */
    private String message;
    /**
     * A boolean declaring whether the request succeeded (true) or failed (false)
     */
    private boolean success;

    /**
     * Creates a Result for the creation of a new User
     *
     * @param authtoken The authtoken to associate with the newly created User for this session
     * @param username The username of the newly created User
     * @param personID The unique ID of the newly created User
     * @param message A message containing the details of the outcome of the request
     * @param success A boolean declaring whether the request succeeded (true) or failed (false)
     */
    public RegisterResult(String authtoken, String username, String personID, String message, boolean success) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        this.message = message;
        this.success = success;
    }
}
