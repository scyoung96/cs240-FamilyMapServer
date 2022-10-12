package Result;

/**
 * Result of the requested single Person with the specified ID
 */
public class PersonResult {
    /**
     * The username associated with the Person requested
     */
    private String associatedUsername;
    /**
     * The unique ID associated with the Person requested
     */
    private String personID;
    /**
     * The first name of the Person requested
     */
    private String firstName;
    /**
     * The last name of the Person requested
     */
    private String lastName;
    /**
     * The gender of the Person requested
     */
    private String gender;
    /**
     * The unique ID of the father of the Person requested
     */
    private String fatherID;
    /**
     * The unique ID of the mother of the Person requested
     */
    private String motherID;
    /**
     * The unique ID of the spouse of the Person requested
     */
    private String spouseID;
    /**
     * A message containing the details of the outcome of the request
     */
    private String message;
    /**
     * A boolean declaring whether the request succeeded (true) or failed (false)
     */
    private boolean success;

    /**
     * Creates a Result for the request to find a single Person in the Database
     *
     * @param associatedUsername The username associated with the Person requested
     * @param personID The unique ID associated with the Person requested
     * @param firstName The first name of the Person requested
     * @param lastName The last name of the Person requested
     * @param gender The gender of the Person requested
     * @param fatherID The unique ID of the father of the Person requested
     * @param motherID The unique ID of the mother of the Person requested
     * @param spouseID The unique ID of the spouse of the Person requested
     * @param message A message containing the details of the outcome of the request
     * @param success A boolean declaring whether the request succeeded (true) or failed (false)
     */
    public PersonResult(String associatedUsername, String personID, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID, String message, boolean success) {
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
        this.message = message;
        this.success = success;
    }
}
