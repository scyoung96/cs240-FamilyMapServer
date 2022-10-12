package Result;

import Model.Person;

/**
 * Result of the requested Persons for every member of the family tree of the current User's tree
 */
public class FamilyResult {
    /**
     * The members of the current User's family Tree
     */
    public Person[] data;
    /**
     * A message containing the details of the outcome of the request
     */
    private String message;
    /**
     * A boolean declaring whether the request succeeded (true) or failed (false)
     */
    private boolean success;

    /**
     * Creates a Result for the
     *
     * @param data An array that will store the Persons of the current User's family tree
     * @param message A message containing the details of the outcome of the request
     * @param success A boolean declaring whether the request succeeded (true) or failed (false)
     */
    public FamilyResult(Person[] data, String message, boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }
}
