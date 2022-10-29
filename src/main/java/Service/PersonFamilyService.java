package Service;

import DataAccess.*;
import Model.Person;
import Request.PersonFamilyRequest;
import Result.PersonFamilyResult;

import java.sql.Connection;
import java.util.List;

/**
 * Used to attempt to find the Person specified in the request
 */
public class PersonFamilyService {
    /**
     * Attempts to perform the Person Request
     *
     * @param r The Request to be performed
     * @return The Result of the attempt to perform the Request
     */
    public PersonFamilyResult PersonFamilyService(PersonFamilyRequest r) throws DataAccessException {
        Database db = new Database();
        Connection connection = db.openConnection("database/FamilyMapDatabase.sqlite");
        PersonDao personDao = new PersonDao(connection);
        AuthtokenDao authtokenDao = new AuthtokenDao(connection);

        PersonFamilyResult result = new PersonFamilyResult(null, null, false);

        try {
            String username = authtokenDao.findUsernameByAuthtoken(r.getAuthtoken());
            if (username != null) {
                List<Person> data = personDao.findByAssociatedUsername(username);

                result.setData(data);
                result.setSuccess(true);
            }
            else {
                result.setMessage("Error: invalid authtoken");
                result.setSuccess(false);
            }
        }
        catch(Exception e) {
            result.setMessage("Error: failed to get person");
            result.setSuccess(false);

            e.printStackTrace();
        }

        db.closeConnection(true);
        return result;
    }
}
