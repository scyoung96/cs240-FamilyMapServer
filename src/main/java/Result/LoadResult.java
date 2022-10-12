package Result;

/**
 * Result of the requested load of 'User', 'Person', and 'Event' data into the Database
 */
public class LoadResult {
    /**
     * A message containing the details of the outcome of the request
     */
    private String message;
    /**
     * A boolean declaring whether the request succeeded (true) or failed (false)
     */
    private boolean success;

    /**
     * Creates a Result for the load
     * @param message A message containing the details of the outcome of the request
     * @param success A boolean declaring whether the request succeeded (true) or failed (false)
     */
    public LoadResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
