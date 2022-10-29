package Service;

import DataAccess.*;
import Model.Event;
import Model.Person;
import Model.User;
import Request.LoadRequest;
import Result.LoadResult;

import java.sql.Connection;

/**
 * Used to clear all data from the Database and subsequently load the User, Person, and Event data from the Request into the Database
 */
public class LoadService {
    /**
     * Attempts to perform the Load Request
     *
     * @param r The Request to be performed
     * @return The Result of the attempt to perform the Request
     */
    public LoadResult LoadService(LoadRequest r) throws DataAccessException {
        Database db = new Database();
        Connection connection = db.openConnection("database/FamilyMapDatabase.sqlite");
        UserDao userDao = new UserDao(connection);
        PersonDao personDao = new PersonDao(connection);
        EventDao eventDao = new EventDao(connection);

        LoadResult result = new LoadResult(null, false);

        try {
            userDao.deleteAll();
            personDao.deleteAll();
            eventDao.deleteAll();

            for (User user : r.getUsers()) {
                userDao.create(user);
            }

            for (Person person : r.getPersons()) {
                personDao.create(person);
            }

            for (Event event : r.getEvents()) {
                eventDao.create(event);
            }

            result.setMessage("Successfully added " + r.getUsers().length + " users, " + r.getPersons().length + " persons, and " + r.getEvents().length + " events to the database.");
            result.setSuccess(true);
        }
        catch(Exception e) {
            result.setMessage("Error: failed to load data into database");
            result.setSuccess(false);

            e.printStackTrace();
        }

        db.closeConnection(true);
        return result;
    }
}
