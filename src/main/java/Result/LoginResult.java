package Result;

/**
 * Result of the requested login for the specified username and password, including a generated authtoken
 */
public class LoginResult {
    /**
     * The authtoken to associate with this session if successful
     */
    private String authtoken;
    /**
     * The username of the User to log in
     */
    private String username;
    /**
     * The unique ID of the Person associated with the User to log in
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
     * Creates a Result for the login of the User
     *
     * @param authtoken The authtoken to associate with this session if successful
     * @param username The username of the User to log in
     * @param personID The unique ID of the Person associated with the User to log in
     * @param message A message containing the details of the outcome of the request
     * @param success A boolean declaring whether the request succeeded (true) or failed (false)
     */
    public LoginResult(String authtoken, String username, String personID, String message, boolean success) {
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
        this.message = message;
        this.success = success;
    }
}
