package Result;

/**
 * Result of the requested fill for the current 'User's' tree
 */
public class FillResult {
    /**
     * A message containing the details of the outcome of the request
     */
    private String message;
    /**
     * A boolean declaring whether the request succeeded (true) or failed (false)
     */
    private boolean success;

    /**
     * Creates a Result for the fill of the User's tree
     *
     * @param message A message containing the details of the outcome of the request
     * @param success A boolean declaring whether the request succeeded (true) or failed (false)
     */
    public FillResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
