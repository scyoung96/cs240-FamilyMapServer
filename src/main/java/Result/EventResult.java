package Result;

/**
 * Result of the requested single Event with the specified ID
 */
public class EventResult {
    /**
     * The username associated with the Event requested
     */
    private String associatedUsername;
    /**
     * The unique ID associated with the Event requested
     */
    private String eventID;
    /**
     * The unique ID associated with the Person associated with the Event requested
     */
    private String personID;
    /**
     * The geographical latitude of the location of the Event requested
     */
    private float latitude;
    /**
     * The geographical longitude of the location of the Event requested
     */
    private float longitude;
    /**
     * The country in which the requested Event occurred
     */
    private String country;
    /**
     * The city in which the requested Event occurred
     */
    private String city;
    /**
     * A short description of what the requested Event represents
     */
    private String eventType;
    /**
     * The year in which the requested Event occurred
     */
    private int year;
    /**
     * A message containing the details of the outcome of the request
     */
    private String message;
    /**
     * A boolean declaring whether the request succeeded (true) or failed (false)
     */
    private boolean success;

    /**
     * Creates a Result for the request to find a single Event in the Database
     *
     * @param associatedUsername The username associated with the Event requeste
     * @param eventID The unique ID associated with the Event requested
     * @param personID The unique ID associated with the Person associated with the Event requested
     * @param latitude The geographical latitude of the location of the Event requested
     * @param longitude The geographical longitude of the location of the Event requested
     * @param country The country in which the requested Event occurred
     * @param city The city in which the requested Event occurred
     * @param eventType A short description of what the requested Event represents
     * @param year The year in which the requested Event occurred
     * @param message A message containing the details of the outcome of the request
     * @param success A boolean declaring whether the request succeeded (true) or failed (false)
     */
    public EventResult(String associatedUsername, String eventID, String personID, float latitude, float longitude, String country, String city, String eventType, int year, String message, boolean success) {
        this.associatedUsername = associatedUsername;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
        this.message = message;
        this.success = success;
    }
}
