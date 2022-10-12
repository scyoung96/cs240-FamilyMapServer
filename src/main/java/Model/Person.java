package Model;

/**
 * A Person represents a life and its connections to others
 */
public class Person {
    /**
     * A unique ID associated with this Person
     */
    private String personID;
    /**
     * The username associated with this Person
     */
    private String associatedUsername;
    /**
     * The first name of this Person
     */
    private String firstName;
    /**
     * The last name of this Person
     */
    private String lastName;
    /**
     * The gender of this Person
     */
    private String gender;
    /**
     * The unique ID associated with the father of this Person
     */
    private String fatherID;
    /**
     * The unique ID associated with the mother of this Person
     */
    private String motherID;
    /**
     * The unique ID associated with the spouse of this Person
     */
    private String spouseID;

    /**
     * Creates a new Person
     *
     * @param personID The unique ID associated with this Person
     * @param associatedUsername The username associated with this Person
     * @param firstName The first name of this Person
     * @param lastName The last name of this Person
     * @param gender The gender of this Person
     * @param fatherID The unique ID associated with the father of this Person
     * @param motherID The unique ID associated with the mother of this Person
     * @param spouseID The unique ID associated with the spouse of this Person
     */
    public Person(String personID, String associatedUsername, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
        personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        personID = personID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }
}
