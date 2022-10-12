package DataAccess;

/**
 * Exception thrown when an error occurs upon attempting to access data
 */
public class DataAccessException extends Exception {
    public DataAccessException() {
        System.out.println("Error: DataAccessException;");
    }
}
