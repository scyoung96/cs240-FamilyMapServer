package Service;

import DataAccess.*;
import Result.ClearResult;

import java.sql.Connection;

/**
 * Used to attempt to clear the all data in the Database
 */
public class ClearService {
    /**
     * Attempts to clear the Database
     *
     * @return The Result of the attempt to clear the Database
     */
    public ClearResult ClearService() throws DataAccessException {
        Database db = new Database();
        Connection connection = db.openConnection("database/FamilyMapDatabase.sqlite");
        UserDao userDao = new UserDao(connection);
        PersonDao personDao = new PersonDao(connection);
        EventDao eventDao = new EventDao(connection);
        AuthtokenDao authtokenDao = new AuthtokenDao(connection);

        ClearResult result = new ClearResult(null, false);

        try {
            userDao.deleteAll();
            personDao.deleteAll();
            eventDao.deleteAll();
            authtokenDao.deleteAll();

            result.setMessage("Clear succeeded.");
            result.setSuccess(true);
        }
        catch(Exception e) {
            result.setMessage("Error: failed to clear database");
            result.setSuccess(false);

            e.printStackTrace();
        }

        db.closeConnection(true);
        return result;
    }
}
