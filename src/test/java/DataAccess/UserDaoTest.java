package DataAccess;

import Model.User;
import java.sql.Connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserDaoTest {
    private Database db;
    private User testUser;
    private UserDao testUserDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        testUser = new User("testUsername", "testPassword", "testEmail", "First", "Last", "m", "123456");
        Connection connection = db.openConnection("database/FamilyMapDatabase.sqlite");
        testUserDao = new UserDao(connection);
        testUserDao.deleteAll();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

// Insert
    @Test
    public void createPass() throws DataAccessException {
        testUserDao.create(testUser);
        User checkCreate = testUserDao.findUserByPersonID("123456");

        // Check to make sure that the User was inserted successfully by inserting, then querying it and making sure the result isn't null
        assertNotNull(checkCreate);
    }

    @Test
    public void createFail() throws DataAccessException {
        testUserDao.create(testUser);

        // username, email, and personID are all marked as unique in the db, so attempting to insert the same User multiple times should throw an error
        assertThrows(DataAccessException.class, () -> testUserDao.create(testUser));
    }

// Query
    @Test
    public void findUserByPersonIDPass() throws DataAccessException {
        testUserDao.create(testUser);
        User newUser = new User("newUsername", "newPassword", "newEmail", "newFirst", "newLast", "m", "234567");
        testUserDao.create(newUser);

        User findTestUser = testUserDao.findUserByPersonID("123456");
        User findNewUser = testUserDao.findUserByPersonID("234567");

        // Insert 2 users into the db and make sure we can find each of them by their ID
        assertNotNull(findTestUser);
        assertNotNull(findNewUser);
    }

    @Test
    public void findUserByPersonIDFail() throws DataAccessException {
        testUserDao.create(testUser);

        User findTestUser = testUserDao.findUserByPersonID("234567");

        // Insert a user with ID "123456" and then search for a user with a different ID
        assertNull(findTestUser);
    }

    @Test
    public void findUserByUsernamePass() throws DataAccessException {
        testUserDao.create(testUser);
        User newUser = new User("newUsername", "newPassword", "newEmail", "newFirst", "newLast", "m", "234567");
        testUserDao.create(newUser);

        User findTestUser = testUserDao.findUserByUsername("newUsername");
        User findNewUser = testUserDao.findUserByUsername("newUsername");

        // Insert 2 users into the db and make sure we can find each of them by their ID
        assertNotNull(findTestUser);
        assertNotNull(findNewUser);
    }

    @Test
    public void findUserByUsernameFail() throws DataAccessException {
        testUserDao.create(testUser);

        User findTestUser = testUserDao.findUserByUsername("wrongUsername");

        // Insert a user with username "newUsername" and then search for a user with a different username
        assertNull(findTestUser);
    }


// Update

// Delete
    @Test
    public void deleteAllTest() throws DataAccessException {
        testUserDao.create(testUser);
        User findTestUser = testUserDao.findUserByPersonID("123456");

        // first, make sure insert works
        assertNotNull(findTestUser);

        testUserDao.deleteAll();

        findTestUser = testUserDao.findUserByPersonID("123456");

        // after the clear, make sure searching that worked previously no longer works
        assertNull(findTestUser);
    }

// Other functionality
    @Test
    public void validateUserPass() throws DataAccessException {
        testUserDao.create(testUser);

        boolean find = testUserDao.validateUser("testUsername", "testPassword");

        // insert a user with username "testUsername" and password "testPassword" and assert that it was inserted with the correct information
        assertTrue(find);
    }

    @Test
    public void validateUserFail() throws DataAccessException {
        testUserDao.create(testUser);

        boolean find = testUserDao.validateUser("wrongUsername", "wrongPassword");

        // insert a user with username "testUsername" and password "testPassword" and assert that searching for them with the wrong information does not return them
        assertFalse(find);
    }
}
