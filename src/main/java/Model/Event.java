package Model;

/**
 * An Event constitutes a significant place and date for a Person for a specified reason
 */
public class Event {
    /**
     * A unique ID associated with this Event
     */
    private String eventID;
    /**
     * The username associated with this Event
     */
    private String associatedUsername;
    /**
     * The unique ID of the Person associated with this Event
     */
    private String personID;
    /**
     * The geographical latitude of the location of this Event
     */
    private float latitude;
    /**
     * The geographical longitude of the location of this Event
     */
    private float longitude;
    /**
     * The country in which this Event occurred
     */
    private String country;
    /**
     * The city in which this Event occurred
     */
    private String city;
    /**
     * A short description of what this Event represents
     */
    private String eventType;
    /**
     * The year in which this Event occurred
     */
    private int year;

    /**
     * Creates a new Event
     *
     * @param eventID The unique ID associated with this Event
     * @param associatedUsername The username associated with this Event
     * @param personID The unique ID of the Person associated with this Event
     * @param latitude The geographical latitude of the location of this Event
     * @param longitude The geographical longitude of the location of this Event
     * @param country The country in which this Event occurred
     * @param city The city in which this Event occurred
     * @param eventType A short description of what this Event represents
     * @param year The year in which this Event occurred
     */
    public Event(String eventID, String associatedUsername, String personID, float latitude, float longitude, String country, String city, String eventType, int year) {
        this.eventID = eventID;
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
