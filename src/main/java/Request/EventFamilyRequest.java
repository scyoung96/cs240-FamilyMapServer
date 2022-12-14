package Request;

/**
 * Used when requesting all Events for every member of the current User's tree
 */
public class EventFamilyRequest {
    /**
     * The authtoken associated with the current session; used to determine the current User
     */
    private String authtoken;

    /**
     * Creates a Request for the Events current User's family tree
     *
     * @param authtoken The authtoken associated with the current session; used to determine the current User
     */
    public EventFamilyRequest(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getAuthtoken() {
        return authtoken;
    }
}
