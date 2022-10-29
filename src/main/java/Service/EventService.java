package Service;

import DataAccess.*;
import Model.Event;
import Request.EventRequest;
import Result.EventResult;

import java.sql.Connection;

/**
 * Used to attempt to find the Event specified in the request
 */
public class EventService {
    /**
     * Attempts to perform the Person Request
     *
     * @param r The Request to be performed
     * @return The Result of the attempt to perform the Request
     */
    public EventResult EventService(EventRequest r) throws DataAccessException {
        Database db = new Database();
        Connection connection = db.openConnection("database/FamilyMapDatabase.sqlite");
        EventDao eventDao = new EventDao(connection);
        AuthtokenDao authtokenDao = new AuthtokenDao(connection);

        EventResult result = new EventResult(null, null, null, 0, 0, null, null, null, 0, null, false);

        try {
            String username = authtokenDao.findUsernameByAuthtoken(r.getAuthtoken());
            if (username != null) {
                if (username.equals(eventDao.findByEventID(r.getEventID()).getAssociatedUsername())) {
                    Event event = eventDao.findByEventID(r.getEventID());

                    result.setAssociatedUsername(event.getAssociatedUsername());
                    result.setEventID(event.getEventID());
                    result.setPersonID(event.getPersonID());
                    result.setLatitude(event.getLatitude());
                    result.setLongitude(event.getLongitude());
                    result.setCountry(event.getCountry());
                    result.setCity(event.getCity());
                    result.setEventType(event.getEventType());
                    result.setYear(event.getYear());
                    result.setSuccess(true);
                }
                else {
                    result.setMessage("Error: requested event not associated with current user");
                    result.setSuccess(false);
                }
            }
            else {
                result.setMessage("Error: invalid authtoken");
                result.setSuccess(false);
            }
        }
        catch(Exception e) {
            result.setMessage("Error: failed to get event");
            result.setSuccess(false);

            e.printStackTrace();
        }

        db.closeConnection(true);
        return result;
    }
}
