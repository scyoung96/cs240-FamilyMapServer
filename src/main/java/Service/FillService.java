package Service;

import DataAccess.*;
import Model.Event;
import Model.Person;
import Model.User;
import Request.FillRequest;
import Result.FillResult;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.*;

import com.google.gson.Gson;

/**
 * Used to attempt to log in the User specified in the request
 */
public class FillService {
    /**
     * Attempts to perform the Login Request
     *
     * @param r The Request to be performed
     * @return The Result of the attempt to perform the Request
     */
    public FillResult FillService(FillRequest r) throws DataAccessException {
        Database db = new Database();
        Connection connection = db.openConnection("database/FamilyMapDatabase.sqlite");
        UserDao userDao = new UserDao(connection);
        PersonDao personDao = new PersonDao(connection);
        EventDao eventDao = new EventDao(connection);
        AuthtokenDao authtokenDao = new AuthtokenDao(connection);

        FillResult result = new FillResult(null, false);

        try {
            User user = userDao.findUserByUsername(r.getUsername());
            if (user != null) {
                if (r.getGenerations() > -1) {
                    String f_in = Files.readString(Path.of("passoffFiles/LoadData.json"));
                    Map<String, List<Map<String, String>>> loadData = new Gson().fromJson(f_in, Map.class);

                    personDao.deleteAllForUsername(r.getUsername());
                    eventDao.deleteAllForUsername(r.getUsername());

                    Stack<String> fatherIDs =  new Stack<>();
                    Stack<String> fatherIDsNextGen =  new Stack<>();
                    Stack<String> motherIDs =  new Stack<>();
                    Stack<String> motherIDsNextGen =  new Stack<>();

                    int birthYear = 1900;
                    int marriageYear = 1925;
                    int deathYear = 1950;
                    Random rand = new Random();

                    for (int i = r.getGenerations(); i > 0; i--) {
                        for (int numToGen = (int)Math.pow(2, i-1); numToGen > 0; numToGen--) {

                            String motherPersonID = UUID.randomUUID().toString();
                            float marriageLatitude = -90 + rand.nextFloat() * (180);
                            float marriageLongitude = -180 + rand.nextFloat() * (360);

                            // GENERATE RANDOM FATHER
                            String fatherPersonID = UUID.randomUUID().toString();
                            fatherIDsNextGen.push(fatherPersonID);

                            String fatherFatherID = null;
                            String fatherMotherID = null;
                            // if this is the first loop (and thus the 'highest/oldest' level), generate no parent data
                            if (i == r.getGenerations()) {
                                fatherFatherID = null;
                                fatherMotherID = null;
                            }
                            // in subsequent loops, pull from the previous generation's IDs
                            else {
                                fatherFatherID = fatherIDs.pop();
                                fatherMotherID = motherIDs.pop();
                            }

                            Map<String, String> randomFather = null;
                            do {
                                randomFather = loadData.get("persons").get(rand.nextInt(loadData.get("persons").size()));
                            }
                            while (!randomFather.get("gender").equals("m"));

                            Person father = new Person(fatherPersonID, r.getUsername(), randomFather.get("firstName"), randomFather.get("lastName"), "m", fatherFatherID, fatherMotherID, motherPersonID);

                            // GENERATE FATHER EVENTS
                            String fatherBirthId = UUID.randomUUID().toString();
                            float fatherBirthLatitude = -90 + rand.nextFloat() * (180);
                            float fatherBirthLongitude = -180 + rand.nextFloat() * (360);

                            String fatherMarriageId = UUID.randomUUID().toString();

                            String fatherDeathId = UUID.randomUUID().toString();
                            float fatherDeathLatitude = -90 + rand.nextFloat() * (180);
                            float fatherDeathLongitude = -180 + rand.nextFloat() * (360);

                            Event fatherBirth = new Event(fatherBirthId, r.getUsername(), fatherPersonID, fatherBirthLatitude, fatherBirthLongitude, "USA", "Salt Lake City", "Birth", birthYear);
                            Event fatherMarriage = new Event(fatherMarriageId, r.getUsername(), fatherPersonID, marriageLatitude, marriageLongitude, "USA", "Salt Lake City", "Marriage", marriageYear);
                            Event fatherDeath = new Event(fatherDeathId, r.getUsername(), fatherPersonID, fatherDeathLatitude, fatherDeathLongitude, "USA", "Salt Lake City", "Death", deathYear);



                            // GENERATE RANDOM MOTHER
                            motherIDsNextGen.push(motherPersonID);

                            String motherFatherID = null;
                            String motherMotherID = null;
                            // if this is the first loop (and thus the 'highest/oldest' level), generate no parent data
                            if (i == r.getGenerations()) {
                                motherFatherID = null;
                                motherMotherID = null;
                            }
                            // in subsequent loops, pull from the previous generation's IDs
                            else {
                                motherFatherID = fatherIDs.pop();
                                motherMotherID = motherIDs.pop();
                            }

                            Map<String, String> randomMother = null;
                            do {
                                randomMother = loadData.get("persons").get(rand.nextInt(loadData.get("persons").size()));
                            }
                            while (!randomMother.get("gender").equals("f"));

                            Person mother = new Person(motherPersonID, r.getUsername(), randomMother.get("firstName"), randomMother.get("lastName"), "f", motherFatherID, motherMotherID, fatherPersonID);

                            // GENERATE MOTHER EVENTS
                            String motherBirthId = UUID.randomUUID().toString();
                            float motherBirthLatitude = -90 + rand.nextFloat() * (180);
                            float motherBirthLongitude = -180 + rand.nextFloat() * (360);

                            String motherMarriageId = UUID.randomUUID().toString();

                            String motherDeathId = UUID.randomUUID().toString();
                            float motherDeathLatitude = -90 + rand.nextFloat() * (180);
                            float motherDeathLongitude = -180 + rand.nextFloat() * (360);

                            Event motherBirth = new Event(motherBirthId, r.getUsername(), motherPersonID, motherBirthLatitude, motherBirthLongitude, "USA", "Salt Lake City", "Birth", birthYear);
                            Event motherMarriage = new Event(motherMarriageId, r.getUsername(), motherPersonID, marriageLatitude, marriageLongitude, "USA", "Salt Lake City", "Marriage", marriageYear);
                            Event motherDeath = new Event(motherDeathId, r.getUsername(), motherPersonID, motherDeathLatitude, motherDeathLongitude, "USA", "Salt Lake City", "Death", deathYear);



                            // UPDATE DATABASE
                            personDao.create(father);
                            eventDao.create(fatherBirth);
                            eventDao.create(fatherMarriage);
                            eventDao.create(fatherDeath);

                            personDao.create(mother);
                            eventDao.create(motherBirth);
                            eventDao.create(motherMarriage);
                            eventDao.create(motherDeath);
                        }

                        fatherIDs = (Stack<String>)fatherIDsNextGen.clone();
                        motherIDs = (Stack<String>)motherIDsNextGen.clone();

                        fatherIDsNextGen.clear();
                        motherIDsNextGen.clear();

                        birthYear += 25;
                        marriageYear += 25;
                        deathYear += 25;
                    }

                    // ADD PERSON TO DATABASE FOR USER
                    Person person = new Person(user.getPersonID(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getGender(), fatherIDs.pop(), motherIDs.pop(), null);
                    personDao.create(person);


                    // ADD EVENTS TO DATABASE FOR USER
                    String userBirthId = UUID.randomUUID().toString();
                    float userBirthLatitude = -90 + rand.nextFloat() * (180);
                    float userBirthLongitude = -180 + rand.nextFloat() * (360);

                    Event userBirth = new Event(userBirthId, r.getUsername(), user.getPersonID(), userBirthLatitude, userBirthLongitude, "USA", "Salt Lake City", "Birth", birthYear);
                    eventDao.create(userBirth);


                    // SET RESPONSE STRING
                    int personsAdded = 1;
                    int eventsAdded = 1;
                    for (int i = r.getGenerations(); i > 0; i--) {
                         personsAdded += (int)Math.pow(2, i);
                         eventsAdded += (int)Math.pow(2, i) * 3;
                    }

                    result.setMessage("Successfully added " + personsAdded + " persons and " + eventsAdded + " events to the database.");
                    result.setSuccess(true);
                }
                else {
                    result.setMessage("Error: invalid generations");
                    result.setSuccess(false);
                }
            }
            else {
                result.setMessage("Error: invalid user");
                result.setSuccess(false);
            }
        }
        catch(Exception e) {
            result.setMessage("Error: failed to fill user data");
            result.setSuccess(false);

            e.printStackTrace();
        }

        db.closeConnection(true);
        return result;
    }
}
