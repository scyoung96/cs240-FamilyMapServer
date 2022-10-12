package Request;

/**
 * Used when requesting all Persons for every member of the current User's tree
 */
public class FamilyRequest {
    /**
     * The authtoken associated with the current session; used to determine the current User
     */
    private String authtoken;

    /**
     * Creates a Request for the family tree of the current User
     *
     * @param authtoken The authtoken associated with the current session; used to determine the current User
     */
    public FamilyRequest(String authtoken) {
        this.authtoken = authtoken;
    }
}
