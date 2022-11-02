package DataAccess;

import Model.Authtoken;
import java.sql.Connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthtokenDaoTest {
    private Database db;
    private Authtoken testAuthtoken;
    private AuthtokenDao testAuthtokenDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        testAuthtoken = new Authtoken("testAuthtoken", "testUsername");
        Connection connection = db.openConnection("database/FamilyMapDatabase.sqlite");
        testAuthtokenDao = new AuthtokenDao(connection);
        testAuthtokenDao.deleteAll();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

// Insert
    @Test
    public void createPass() throws DataAccessException {
        testAuthtokenDao.create(testAuthtoken);
        String checkCreate = testAuthtokenDao.findAuthtokenByUsername("testUsername");

        // Check to make sure that the Authtoken was inserted successfully by inserting, then querying it and making sure the result isn't null
        assertNotNull(checkCreate);
    }

    @Test
    public void createFail() throws DataAccessException {
        Authtoken nullTestAuthtoken = testAuthtoken;
        nullTestAuthtoken.setAuthtoken(null);

        // the "authtoken" attribute of an authtoken cannot be null, so attempting to create one should fail
        assertThrows(DataAccessException.class, () -> testAuthtokenDao.create(nullTestAuthtoken));
    }

    // Query
    @Test
    public void findAuthtokenByUsernamePass() throws DataAccessException {
        testAuthtokenDao.create(testAuthtoken);

        String findAuthtoken = testAuthtokenDao.findAuthtokenByUsername("testUsername");

        // Insert authtoken into the db and make sure we can find it by its username
        assertNotNull(findAuthtoken);
    }

    @Test
    public void findAuthtokenByUsernameFail() throws DataAccessException {
        testAuthtokenDao.create(testAuthtoken);

        String findAuthtoken = testAuthtokenDao.findAuthtokenByUsername("wrongUsername");

        // Insert authtoken into the db and make sure we can't find it by the wrong username
        assertNull(findAuthtoken);
    }

    @Test
    public void findUsernameByAuthtokenPass() throws DataAccessException {
        testAuthtokenDao.create(testAuthtoken);

        String findUsername = testAuthtokenDao.findUsernameByAuthtoken("testAuthtoken");

        // Insert authtoken into the db and make sure we can find it by its authtoken
        assertNotNull(findUsername);
    }

    @Test
    public void findUsernameByAuthtokenFail() throws DataAccessException {
        testAuthtokenDao.create(testAuthtoken);

        String findAuthtoken = testAuthtokenDao.findAuthtokenByUsername("wrongAuthtoken");

        // Insert authtoken into the db and make sure we can't find it by the wrong authtoken
        assertNull(findAuthtoken);
    }


// Update

    // Delete
    @Test
    public void deleteAllTest() throws DataAccessException {
        testAuthtokenDao.create(testAuthtoken);
        String findAuthtoken = testAuthtokenDao.findAuthtokenByUsername("testUsername");

        // first, make sure insert works
        assertNotNull(findAuthtoken);

        testAuthtokenDao.deleteAll();

        findAuthtoken = testAuthtokenDao.findAuthtokenByUsername("testUsername");

        // after the clear, make sure searching that worked previously no longer works
        assertNull(findAuthtoken);
    }

// Other functionality

}
