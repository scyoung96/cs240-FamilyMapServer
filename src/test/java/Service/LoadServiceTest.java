package Service;

import DataAccess.*;
import Request.*;
import Result.*;
import Model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoadServiceTest {
    private Database db;

    UserDao testUserDao;
    PersonDao testPersonDao;
    EventDao testEventDao;
    AuthtokenDao testAuthtokenDao;

    User testUser;
    Person testPerson;
    Event testEvent;
    Authtoken testAuthtoken;

    LoadService testService;
    LoadRequest testRequest;
    LoadResult testResult;


    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new Database();
        Connection connection = db.openConnection("database/FamilyMapDatabase.sqlite");

        testUserDao = new UserDao(connection);
        testUserDao.deleteAll();
        testPersonDao = new PersonDao(connection);
        testPersonDao.deleteAll();
        testEventDao = new EventDao(connection);
        testEventDao.deleteAll();
        testAuthtokenDao = new AuthtokenDao(connection);
        testAuthtokenDao.deleteAll();

        testUser = new User("testUsername", "testPassword", "testEmail", "First", "Last", "m", "123456");
        testPerson = new Person("testPerson", "testUsername", "testFirstName", "testLastName", "m", "testFatherID", "testMotherID", "testSpouseID");
        testEvent = new Event("testEventID", "testUsername", "testPersonID", (float)0.0, (float)0.0, "testCountry", "testCity", "testEventType", 0);
        testAuthtoken = new Authtoken("testAuthtoken", "testUsername");

        testService = new LoadService();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
//        db.closeConnection(false);
    }

    @Test
    public void LoadServicePass() throws DataAccessException, IOException {
        // insert the info into the database
        testUserDao.create(testUser);
        testPersonDao.create(testPerson);
        testEventDao.create(testEvent);
        testAuthtokenDao.create(testAuthtoken);

        // close the database so the service can use it
        db.closeConnection(true);

        String fileString = Files.readString(Path.of("passoffFiles/LoadData.json"));

        Map<String, List<Map<String, String>>> reqData = new Gson().fromJson(fileString, Map.class);
        List<Map<String, String>> userList = reqData.get("users");
        List<Map<String, String>> personList = reqData.get("persons");
        List<Map<String, String>> eventList = reqData.get("events");

        User[] userArr = new User[userList.size()];
        Person[] personArr = new Person[personList.size()];
        Event[] eventArr = new Event[eventList.size()];

        int arrCount = 0;

        for (Map<String, String> userObj : userList) {
            String userObjStr = userObj.toString().replaceAll("([a-zA-Z]+?) ([a-zA-Z]+?)", "$1_$2");
            String userObjStr2 = userObjStr.replaceAll("([a-zA-Z]+?) ([a-zA-Z]+?)", "$1_$2");
            User user = new Gson().fromJson(userObjStr2, User.class);

            userArr[arrCount] = user;

            arrCount++;
        }

        arrCount = 0;

        for (Map<String, String> personObj : personList) {
            String personObjStr = personObj.toString().replaceAll("([a-zA-Z]+?) ([a-zA-Z]+?)", "$1_$2");
            String personObjStr2 = personObjStr.replaceAll("([a-zA-Z]+?) ([a-zA-Z]+?)", "$1_$2");
            Person person = new Gson().fromJson(personObjStr2, Person.class);

            personArr[arrCount] = person;

            arrCount++;
        }

        arrCount = 0;

        for (Map<String, String> eventObj : eventList) {
            String eventObjStr = eventObj.toString().replaceAll("([a-zA-Z]+?) ([a-zA-Z]+?)", "$1_$2");
            String eventObjStr2 = eventObjStr.replaceAll("([a-zA-Z]+?) ([a-zA-Z]+?)", "$1_$2");
            Event event = new Gson().fromJson(eventObjStr2, Event.class);

            eventArr[arrCount] = event;

            arrCount++;
        }

        testRequest = new LoadRequest(userArr, personArr, eventArr);
        testResult = testService.LoadService(testRequest);

        // create a User, Person, Event, and Authtoken in the database, then perform the clear and attempt to find them
        assertTrue(testResult.isSuccess());
    }

    @Test
    public void LoadServiceEmptyPass() throws DataAccessException, IOException {
        // insert the info into the database
        testUserDao.create(testUser);
        testPersonDao.create(testPerson);
        testEventDao.create(testEvent);
        testAuthtokenDao.create(testAuthtoken);

        // close the database so the service can use it
        db.closeConnection(true);


        User[] userArr = new User[0];
        Person[] personArr = new Person[0];
        Event[] eventArr = new Event[0];

        testRequest = new LoadRequest(userArr, personArr, eventArr);
        testResult = testService.LoadService(testRequest);

        // create a User, Person, Event, and Authtoken in the database, then perform the clear and attempt to find them
        assertTrue(testResult.isSuccess());
    }
}
