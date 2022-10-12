package Request;

/**
 * Used when requesting a single Event associated with the current User
 */
public class EventRequest {
    /**
     * The unique ID associated with the Event to find
     */
    private String eventID;
    /**
     * The authtoken associated with the current session; used to determine the current User
     */
    private String authtoken;

    /**
     * Creates a Request for a single Event
     *
     * @param eventID The unique ID associated with the Event to find
     * @param authtoken The authtoken associated with the current session; used to determine the current User
     */
    public EventRequest(String eventID, String authtoken) {
        this.eventID = eventID;
        this.authtoken = authtoken;
    }
}
