package Request;

/**
 * Used when requesting a single Person associated with the current User
 */
public class PersonRequest {
    /**
     * The unique ID associated with the Person to find
     */
    private String personID;
    /**
     * The authtoken associated with the current session; used to determine the current User
     */
    private String authtoken;

    /**
     * Creates a Request for a single Person
     *
     * @param personID The unique ID associated with the Person to find
     * @param authtoken The authtoken associated with the current session; used to determine the current User
     */
    public PersonRequest(String personID, String authtoken) {
        this.personID = personID;
        this.authtoken = authtoken;
    }
}
