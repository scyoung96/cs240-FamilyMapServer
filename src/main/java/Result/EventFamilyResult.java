package Result;

import Model.Event;

import java.util.List;

/**
 * Result of the requested Events for every member of the current User's tree
 */
public class EventFamilyResult {
    /**
     * The Events of the current User's family Tree
     */
    private List<Event> data;
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
     * @param data An array that will store the Events of the current User's family tree
     * @param message A message containing the details of the outcome of the request
     * @param success A boolean declaring whether the request succeeded (true) or failed (false)
     */
    public EventFamilyResult(List<Event> data, String message, boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public void setData(List<Event> data) {
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
