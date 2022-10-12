package Request;

/**
 * Used when requesting a login for the specified username and password
 */
public class LoginRequest {
    /**
     * The username of the User to log in
     */
    private String username;
    /**
     * The password of the User to log in
     */
    private String password;

    /**
     * Creates a request for the login of the User
     *
     * @param username The username of the User to log in
     * @param password The password of the User to log in
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
