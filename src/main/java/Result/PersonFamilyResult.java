package Result;

import Model.Person;

import java.util.List;

/**
 * Result of the requested Persons for every member of the family tree of the current User's tree
 */
public class PersonFamilyResult {
    /**
     * The members of the current User's family Tree
     */
    public List<Person> data;
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
    public PersonFamilyResult(List<Person> data, String message, boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public void setData(List<Person> data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
