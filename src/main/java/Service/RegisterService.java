package Service;

import DataAccess.AuthtokenDao;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.UserDao;
import Model.Authtoken;
import Model.User;
import Request.RegisterRequest;
import Result.RegisterResult;

import java.sql.Connection;
import java.util.UUID;

/**
 * Used to attempt to create a new User specified in the request
 */
public class RegisterService {
    /**
     * Attempts to perform the Register Request
     *
     * @param r The Request to be performed
     * @return The Result of the attempt to perform the Request
     */
    public RegisterResult RegisterService(RegisterRequest r) throws DataAccessException {
        Database db = new Database();
        Connection connection = db.openConnection("database/FamilyMapDatabase.sqlite");
        UserDao userDao = new UserDao(connection);
        AuthtokenDao authtokenDao = new AuthtokenDao(connection);

        RegisterResult result = new RegisterResult(null, null, null, null, false);
        UUID uuid = UUID.randomUUID();
        User user = new User(r.getUsername(), r.getPassword(), r.getEmail(), r.getFirstName(), r.getLastName(), r.getGender(), uuid.toString());

        try {
            userDao.create(user);

            String authtokenStr = UUID.randomUUID().toString();
            Authtoken authtoken = new Authtoken(authtokenStr, r.getUsername());
            authtokenDao.create(authtoken);

            result.setAuthtoken(authtokenStr);
            result.setUsername(r.getUsername());
            result.setPersonID(uuid.toString());
            result.setSuccess(true);
        }
        catch(Exception e) {
            result.setMessage("Error: failed to create user");
            result.setSuccess(false);

            e.printStackTrace();
        }

        db.closeConnection(true);
        return result;
    }
}
