package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Event;
import Model.Person;

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
        String sql = "INSERT INTO event (eventID, associatedUsername, personID, latitude, longitude, country, city, eventType, year) VALUES(?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getAssociatedUsername());
            stmt.setString(3, event.getPersonID());
            stmt.setFloat(4, event.getLatitude());
            stmt.setFloat(5, event.getLongitude());
            stmt.setString(6, event.getCountry());
            stmt.setString(7, event.getCity());
            stmt.setString(8, event.getEventType());
            stmt.setInt(9, event.getYear());

            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting event into the database");
        }
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
        Event event;
        ResultSet rs;
        String sql = "SELECT * FROM event WHERE eventID = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, eventID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                event = new Event(rs.getString("eventID"), rs.getString("associatedUsername"), rs.getString("personID"), rs.getFloat("latitude"), rs.getFloat("longitude"), rs.getString("country"), rs.getString("city"), rs.getString("eventType"), rs.getInt("year"));
                return event;
            }
            else {
                return null;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding event in the database");
        }
    }

    /**
     * Gets all Events for a User by username
     *
     * @param username The username associated with the User whose Events to get
     * @return The list of Events associated with the User whose username was provided if it exists, else null
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public List<Event> findByAssociatedUsername(String username) throws DataAccessException {
        Event event;
        ResultSet rs;
        String sql = "SELECT * FROM event WHERE associatedUsername = ?;";

        List events = new ArrayList();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                event = new Event(rs.getString("eventID"), rs.getString("associatedUsername"), rs.getString("personID"), rs.getFloat("latitude"), rs.getFloat("longitude"), rs.getString("country"), rs.getString("city"), rs.getString("eventType"), rs.getInt("year"));
                events.add(event);

                while(rs.next()) {
                    event = new Event(rs.getString("eventID"), rs.getString("associatedUsername"), rs.getString("personID"), rs.getFloat("latitude"), rs.getFloat("longitude"), rs.getString("country"), rs.getString("city"), rs.getString("eventType"), rs.getInt("year"));
                    events.add(event);
                }

                return events;
            }
            else {
                return null;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding event in the database");
        }
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
     * Removes all Events from the Database for a specified username
     *
     * @param username The username associated with the User whose Events to delete
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public void deleteAllForUsername(String username) throws DataAccessException {
        ResultSet rs;
        String sql = "DELETE FROM event WHERE associatedUsername = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
//            rs = stmt.executeQuery();
//            if (rs.next()) {
//
//            }
//            else {
//
//            }
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the person table for username specified");
        }
    }

// Other functionality

}
