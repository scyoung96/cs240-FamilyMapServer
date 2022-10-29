package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import Model.Event;

/**
 * Inserts, queries, updates, and deletes Events
 */
public class EventDao {
    /**
     * The connection this DAO will utilize to access the Database
     */
    private final Connection connection;

    /**
     * Establishes the connection to the Database
     *
     * @param connection The connection this DAO will utilize to access the Database
     */
    public EventDao(Connection connection) {
        this.connection = connection;
    }

// Insert
    /**
     * Inserts an Event into the Database
     *
     * @param event The Event to insert into the Database
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public void create(Event event) throws DataAccessException {

    }

// Query
    /**
     * Gets an Event from the Database by eventID
     *
     * @param eventID The eventID associated with the Event to get
     * @return The Event associated with the eventID if one exists, else null
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public Event findByEventID(String eventID) throws DataAccessException {

        return null;
    }

    /**
     * Gets all Events for a User by username
     *
     * @param username The username associated with the User whose Events to get
     * @return The list of Events associated with the User whose username was provided if it exists, else null
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public List<Event> findAllForUsername(String username) throws DataAccessException {

        return null;
    }

// Update
    /**
     * Replaces an Event in the Database with another
     *
     * @param oldEvent The Event to be replaced
     * @param newEvent The Event to replace the old Event
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public void update(Event oldEvent, Event newEvent) throws DataAccessException {

    }

// Delete
    /**
     * Removes an Event from the Database
     *
     * @param event The Event to remove from the Database
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public void delete(Event event) throws DataAccessException {

    }

    /**
     * Removes all Events from the Database
     *
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public void deleteAll() throws DataAccessException {
        String sql = "DELETE FROM event";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the event table");
        }
    }

    /**
     * Removes all Events for a User by username
     *
     * @param username The username associated with the User whose Events to delete
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public void deleteAllForUsername(String username) throws DataAccessException {

    }

// Other functionality

}
