package Request;

/**
 * Used to fill in a tree of 'Persons' for the current 'User'
 */
public class FillRequest {
    /**
     * The username of the User whose tree should be filled
     */
    private String username;
    /**
     * The number of generations to fill
     */
    private int generations;
    /**
     * Creates a request for the fill of the User's tree
     *
     * @param username The username of the User whose tree should be filled
     * @param generations The number of generations to fill
     */
    public FillRequest(String username, int generations) {
        this.username = username;
        this.generations = generations;
    }

    public String getUsername() {
        return username;
    }

    public int getGenerations() {
        return generations;
    }
}
