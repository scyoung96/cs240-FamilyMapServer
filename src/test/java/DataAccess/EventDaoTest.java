package DataAccess;

import Model.Event;
import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventDaoTest {
    private Database db;
    private Event testEvent;
    private EventDao testEventDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        testEvent = new Event("testEventID", "testAssociatedUsername", "testPersonID", (float)0.0, (float)0.0, "testCountry", "testCity", "testEventType", 0);
        Connection connection = db.openConnection("database/FamilyMapDatabase.sqlite");
        testEventDao = new EventDao(connection);
        testEventDao.deleteAll();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

// Insert
    @Test
    public void createPass() throws DataAccessException {
        testEventDao.create(testEvent);
        Event checkCreate = testEventDao.findByEventID("testEventID");

        // Check to make sure that the Event was inserted successfully by inserting, then querying it and making sure the result isn't null
        assertNotNull(checkCreate);
    }

    @Test
    public void createFail() throws DataAccessException {
        testEventDao.create(testEvent);

        // eventID is  marked as unique in the db, so attempting to insert the same Event multiple times should throw an error
        assertThrows(DataAccessException.class, () -> testEventDao.create(testEvent));
    }

// Query
    @Test
    public void findByEventIDPass() throws DataAccessException {
        testEventDao.create(testEvent);

        Event findEvent = testEventDao.findByEventID("testEventID");

        // Insert event into the db and make sure we can find it by its ID
        assertNotNull(findEvent);
    }

    @Test
    public void findByEventIDFail() throws DataAccessException {
        testEventDao.create(testEvent);

        Event findEvent = testEventDao.findByEventID("wrongEventID");

        // Insert event into the db and make sure we can't find it by an incorrect ID
        assertNull(findEvent);
    }

    @Test
    public void findByAssociatedUsernamePass() throws DataAccessException {
        testEventDao.create(testEvent);

        Event testEvent2 = testEvent;
        testEvent2.setEventID("testEventID2");
        testEvent2.setAssociatedUsername("testAssociatedUsername2");
        testEventDao.create(testEvent2);

        List<Event> findEvents = testEventDao.findByAssociatedUsername("testAssociatedUsername");
        List<Event> findEvents2 = testEventDao.findByAssociatedUsername("testAssociatedUsername2");


        // Insert events into the db and make sure we can find each of the events by their associatedUsernames
        assertNotNull(findEvents);
        assertNotNull(findEvents2);
    }

    @Test
    public void findByAssociatedUsernameFail() throws DataAccessException {
        testEventDao.create(testEvent);

        Event testEvent2 = testEvent;
        testEvent2.setEventID("testEventID2");
        testEvent2.setAssociatedUsername("testUsername2");
        testEventDao.create(testEvent2);

        List<Event> findEvents = testEventDao.findByAssociatedUsername("wrongUsername");

        // Insert events with various associatedUsernames into the db and make sure we can't find them by their associatedUsername
        assertNull(findEvents);
    }


// Update

// Delete
    @Test
    public void deleteAllTest() throws DataAccessException {
        testEventDao.create(testEvent);
        Event findEvent = testEventDao.findByEventID("testEventID");

        // first, make sure insert works
        assertNotNull(findEvent);

        testEventDao.deleteAll();

        findEvent = testEventDao.findByEventID("testEventID");

        // after the clear, make sure searching that worked previously no longer works
        assertNull(findEvent);
    }

    @Test
    public void deleteAllForUsernameTest() throws DataAccessException {
        testEventDao.create(testEvent);

        Event testEvent2 = testEvent;
        testEvent2.setEventID("testEventID2");
        testEvent2.setAssociatedUsername("testAssociatedUsername2");
        testEventDao.create(testEvent2);

        List<Event> findEvent = testEventDao.findByAssociatedUsername("testAssociatedUsername");

        // first, make sure insert works
        assertNotNull(findEvent);

        testEventDao.deleteAllForUsername("testAssociatedUsername");

        findEvent = testEventDao.findByAssociatedUsername("testAssociatedUsername");
        List<Event> findEvent2 = testEventDao.findByAssociatedUsername("testAssociatedUsername2");

        // after the clear, make sure searching that worked previously no longer works but that searching for the event with a different associatedUsername still works
        assertNull(findEvent);
        assertNotNull(findEvent2);
    }

// Other functionality

}
