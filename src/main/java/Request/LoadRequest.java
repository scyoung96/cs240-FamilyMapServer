package Request;

import Model.Event;
import Model.Person;
import Model.User;

/**
 * Used to load the provided 'User', 'Person', and 'Event' data into the database
 */
public class LoadRequest {
    /**
     * The Users to load into the Database
     */
    private User[] users;
    /**
     * The Persons to load into the Database
     */
    private Person[] persons;
    /**
     * The Events to load into the Database
     */
    private Event[] events;

    /**
     * Creates a Request to load data into the Database
     *
     * @param users The Users to load into the Database
     * @param persons The Persons to load into the Database
     * @param events The Events to load into the Database
     */
    public LoadRequest(User[] users, Person[] persons, Event[] events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }
}
