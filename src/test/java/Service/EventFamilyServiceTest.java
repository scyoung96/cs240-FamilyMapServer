package Service;

import DataAccess.*;
import Request.*;
import Result.*;
import Model.*;

import java.sql.Connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventFamilyServiceTest {
    private Database db;

    UserDao testUserDao;
    PersonDao testPersonDao;
    EventDao testEventDao;
    AuthtokenDao testAuthtokenDao;

    User testUser;
    Person testPerson;
    Event testEvent;
    Authtoken testAuthtoken;

    EventFamilyService testService;
    EventFamilyRequest testRequest;
    EventFamilyResult testResult;


    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        Connection connection = db.openConnection("database/FamilyMapDatabase.sqlite");

        testUserDao = new UserDao(connection);
        testUserDao.deleteAll();
        testPersonDao = new PersonDao(connection);
        testPersonDao.deleteAll();
        testEventDao = new EventDao(connection);
        testEventDao.deleteAll();
        testAuthtokenDao = new AuthtokenDao(connection);
        testAuthtokenDao.deleteAll();

        testUser = new User("testUsername", "testPassword", "testEmail", "First", "Last", "m", "123456");
        testPerson = new Person("testPerson", "testUsername", "testFirstName", "testLastName", "m", "testFatherID", "testMotherID", "testSpouseID");
        testEvent = new Event("testEventID", "testUsername", "testPersonID", (float)0.0, (float)0.0, "testCountry", "testCity", "testEventType", 0);
        testAuthtoken = new Authtoken("testAuthtoken", "testUsername");

        testService = new EventFamilyService();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
//        db.closeConnection(false);
    }

    @Test
    public void EventFamilyServicePass() throws DataAccessException {
        // insert the info into the database
        testUserDao.create(testUser);
        testPersonDao.create(testPerson);
        testEventDao.create(testEvent);
        testAuthtokenDao.create(testAuthtoken);

        // close the database so the service can use it
        db.closeConnection(true);


        testRequest = new EventFamilyRequest("testAuthtoken");
        testResult = testService.EventFamilyService(testRequest);

        // create a User, Person, Event, and Authtoken in the database, then perform the clear and attempt to find them
        assertTrue(testResult.isSuccess());
    }

    @Test
    public void EventFamilyServiceFail() throws DataAccessException {
        // insert the info into the database
        testUserDao.create(testUser);
        testPersonDao.create(testPerson);
        testEventDao.create(testEvent);
        testAuthtokenDao.create(testAuthtoken);

        // close the database so the service can use it
        db.closeConnection(false);


        testRequest = new EventFamilyRequest("wrongAuthtoken");
        testResult = testService.EventFamilyService(testRequest);

        // create a User, Person, Event, and Authtoken in the database, then perform the clear and attempt to find them
        assertFalse(testResult.isSuccess());
    }
}
