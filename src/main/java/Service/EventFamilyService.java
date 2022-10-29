package Service;

import DataAccess.*;
import Model.Event;
import Request.EventFamilyRequest;
import Result.EventFamilyResult;

import java.sql.Connection;
import java.util.List;

/**
 * Used to attempt to find the Events of the entire tree of the current User
 */
public class EventFamilyService {
    /**
     * Attempts to perform the FamilyEvent Request
     *
     * @param r The Request to be performed
     * @return The Result of the attempt to perform the Request
     */
    public EventFamilyResult EventFamilyService(EventFamilyRequest r) throws DataAccessException {
        Database db = new Database();
        Connection connection = db.openConnection("database/FamilyMapDatabase.sqlite");
        EventDao eventDao = new EventDao(connection);
        AuthtokenDao authtokenDao = new AuthtokenDao(connection);

        EventFamilyResult result = new EventFamilyResult(null, null, false);

        try {
            String username = authtokenDao.findUsernameByAuthtoken(r.getAuthtoken());
            if (username != null) {
                List<Event> data = eventDao.findByAssociatedUsername(username);

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
