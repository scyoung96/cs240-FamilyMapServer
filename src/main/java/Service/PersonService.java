package Service;

import DataAccess.*;
import Model.Person;
import Model.User;
import Request.PersonRequest;
import Result.PersonResult;

import java.sql.Connection;

/**
 * Used to attempt to find the Person specified in the request
 */
public class PersonService {
    /**
     * Attempts to perform the Person Request
     *
     * @param r The Request to be performed
     * @return The Result of the attempt to perform the Request
     */
    public PersonResult PersonService(PersonRequest r) throws DataAccessException {
        Database db = new Database();
        Connection connection = db.openConnection("database/FamilyMapDatabase.sqlite");
        PersonDao personDao = new PersonDao(connection);
        AuthtokenDao authtokenDao = new AuthtokenDao(connection);

        PersonResult result = new PersonResult(null, null, null, null, null, null, null, null, null, false);

        try {
            String username = authtokenDao.findUsernameByAuthtoken(r.getAuthtoken());
            if (username != null) {
                if (username.equals(personDao.findByPersonID(r.getPersonID()).getAssociatedUsername())) {
                    Person person = personDao.findByPersonID(r.getPersonID());

                    result.setAssociatedUsername(person.getAssociatedUsername());
                    result.setPersonID(person.getPersonID());
                    result.setFirstName(person.getFirstName());
                    result.setLastName(person.getLastName());
                    result.setGender(person.getGender());
                    result.setFatherID(person.getFatherID());
                    result.setMotherID(person.getMotherID());
                    result.setSpouseID(person.getSpouseID());
                    result.setSuccess(true);
                }
                else {
                    result.setMessage("Error: requested person not associated with current user");
                    result.setSuccess(false);
                }
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
