package Service;

import DataAccess.AuthtokenDao;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.UserDao;
import Model.User;
import Request.LoginRequest;
import Result.LoginResult;

import java.sql.Connection;

/**
 * Used to attempt to log in the User specified in the request
 */
public class LoginService {
    /**
     * Attempts to perform the Login Request
     *
     * @param r The Request to be performed
     * @return The Result of the attempt to perform the Request
     */
    public LoginResult LoginService(LoginRequest r) throws DataAccessException {
        Database db = new Database();
        Connection connection = db.openConnection("database/FamilyMapDatabase.sqlite");
        UserDao userDao = new UserDao(connection);
        AuthtokenDao authtokenDao = new AuthtokenDao(connection);

        LoginResult result = new LoginResult(null, null, null, null, false);

        try {
            if (userDao.validateUser(r.getUsername(), r.getPassword())) {
                String authtokenStr = authtokenDao.findAuthtokenByUsername(r.getUsername());
                User user = userDao.findUserByUsername(r.getUsername());
                String personID = user.getPersonID();

                result.setAuthtoken(authtokenStr);
                result.setUsername(r.getUsername());
                result.setPersonID(personID);
                result.setSuccess(true);
            }
            else {
                result.setMessage("Error: invalid credentials");
                result.setSuccess(false);
            }
        }
        catch(Exception e) {
            result.setMessage("Error: failed to log user in");
            result.setSuccess(false);

            e.printStackTrace();
        }

        db.closeConnection(true);
        return result;
    }
}
