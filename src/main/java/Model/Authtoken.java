package Model;

/**
 * An Authtoken is used to identify a User after a successful login
 */
public class Authtoken {
    /**
     * A unique randomized token to associate with a username for this session
     */
    private String authtoken;
    /**
     * The username to associate this token to
     */
    private String username;

    /**
     * Creates a new Authtoken
     *
     * @param authtoken The unique randomized token to associate with a username for this session
     * @param username The username to associate this token to
     */
    public Authtoken(String authtoken, String username) {
        this.authtoken = authtoken;
        this.username = username;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
