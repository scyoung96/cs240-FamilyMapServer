package Service;

import DataAccess.*;

import java.sql.Connection;

import Model.Authtoken;
import Model.Event;
import Model.Person;
import Model.User;
import Result.ClearResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClearServiceTest {
    private Database db;

    UserDao testUserDao;
    PersonDao testPersonDao;
    EventDao testEventDao;
    AuthtokenDao testAuthtokenDao;

    User testUser;
    Person testPerson;
    Event testEvent;
    Authtoken testAuthtoken;

    ClearService testService;
    ClearResult testResult;


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
        testPerson = new Person("testPerson", "testAssociatedUsername", "testFirstName", "testLastName", "m", "testFatherID", "testMotherID", "testSpouseID");
        testEvent = new Event("testEventID", "testAssociatedUsername", "testPersonID", (float)0.0, (float)0.0, "testCountry", "testCity", "testEventType", 0);
        testAuthtoken = new Authtoken("testAuthtoken", "testUsername");

        testService = new ClearService();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    public void ClearServicePass() throws DataAccessException {
        // insert the info into the database
        testUserDao.create(testUser);
        testPersonDao.create(testPerson);
        testEventDao.create(testEvent);
        testAuthtokenDao.create(testAuthtoken);

        // close the database so the service can use it
        db.closeConnection(true);

        testResult = testService.ClearService();

        // reopen the database for querying
        db = new Database();
        Connection connection = db.openConnection("database/FamilyMapDatabase.sqlite");

        testUserDao = new UserDao(connection);
        testPersonDao = new PersonDao(connection);
        testEventDao = new EventDao(connection);
        testAuthtokenDao = new AuthtokenDao(connection);

        User findTestUser = testUserDao.findUserByUsername("testUsername");
        Person findTestPerson = testPersonDao.findByPersonID("testPersonID");
        Event findTestEvent = testEventDao.findByEventID("testEventID");
        String findAuthtoken = testAuthtokenDao.findAuthtokenByUsername("testUsername");

        // create a User, Person, Event, and Authtoken in the database, then perform the clear and attempt to find them
        assertNull(findTestUser);
        assertNull(findTestPerson);
        assertNull(findTestEvent);
        assertNull(findAuthtoken);
    }

    @Test
    public void ClearServiceEmptyPass() throws DataAccessException {
        // insert nothing into the database

        // close the database so the service can use it
        db.closeConnection(true);

        testResult = testService.ClearService();

        // reopen the database for querying
        db = new Database();
        Connection connection = db.openConnection("database/FamilyMapDatabase.sqlite");

        testUserDao = new UserDao(connection);
        testPersonDao = new PersonDao(connection);
        testEventDao = new EventDao(connection);
        testAuthtokenDao = new AuthtokenDao(connection);

        User findTestUser = testUserDao.findUserByUsername("testUsername");
        Person findTestPerson = testPersonDao.findByPersonID("testPersonID");
        Event findTestEvent = testEventDao.findByEventID("testEventID");
        String findAuthtoken = testAuthtokenDao.findAuthtokenByUsername("testUsername");

        // create a User, Person, Event, and Authtoken in the database, then perform the clear and attempt to find them
        assertNull(findTestUser);
        assertNull(findTestPerson);
        assertNull(findTestEvent);
        assertNull(findAuthtoken);
    }
}
