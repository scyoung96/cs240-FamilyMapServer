package DataAccess;

import Model.Person;
import java.sql.Connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonDaoTest {
    private Database db;
    private Person testPerson;
    private PersonDao testPersonDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        testPerson = new Person("123456", "testAssociatedUsername", "testFirst", "testLast", "m", "111111", "222222", "333333");
        Connection connection = db.openConnection("database/FamilyMapDatabase.sqlite");
        testPersonDao = new PersonDao(connection);
        testPersonDao.deleteAll();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

// Insert
    @Test
    public void createPass() throws DataAccessException {
        testPersonDao.create(testPerson);
        Person checkCreate = testPersonDao.findByPersonID("123456");

        // Check to make sure that the Person was inserted successfully by inserting, then querying it and making sure the result isn't null
        assertNotNull(checkCreate);
    }

    @Test
    public void createFail() throws DataAccessException {
        testPersonDao.create(testPerson);

        // personID is marked as unique in the db, so attempting to insert the same Person multiple times should throw an error
        assertThrows(DataAccessException.class, () -> testPersonDao.create(testPerson));
    }

// Query
    @Test
    public void findByIDPass() throws DataAccessException {
        testPersonDao.create(testPerson);
        Person newPerson = new Person("234567", "newAssociatedUsername", "newFirst", "newLast", "m", "444444", "555555", "666666");
        testPersonDao.create(newPerson);

        Person findTestPerson = testPersonDao.findByPersonID("123456");
        Person findNewPerson = testPersonDao.findByPersonID("234567");

        // Insert 2 users into the db and make sure we can find each of them by their ID
        assertNotNull(findTestPerson);
        assertNotNull(findNewPerson);
    }

    @Test
    public void findByIDFail() throws DataAccessException {
        testPersonDao.create(testPerson);

        Person findTestPerson = testPersonDao.findByPersonID("234567");

        // Insert a user with ID "123456" and then search for a user with a different ID
        assertNull(findTestPerson);
    }

// Update

// Delete
    @Test
    public void clearTest() throws DataAccessException {
        testPersonDao.create(testPerson);
        Person findTestPerson = testPersonDao.findByPersonID("123456");

        // first, make sure insert works
        assertNotNull(findTestPerson);

        testPersonDao.deleteAll();

        findTestPerson = testPersonDao.findByPersonID("123456");

        // after the clear, make sure searching that worked previously no longer works
        assertNull(findTestPerson);
    }
}
