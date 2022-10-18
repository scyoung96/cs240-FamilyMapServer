package DataAccess;

import Model.Person;
import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Inserts, queries, updates, and deletes Persons
 */
public class PersonDao {
    /**
     * The connection this DAO will utilize to access the Database
     */
    private final Connection connection;

    /**
     * Establishes the connection to the Database
     *
     * @param connection The connection this DAO will utilize to access the Database
     */
    public PersonDao(Connection connection) {
        this.connection = connection;
    }

// Insert
    /**
     * Inserts a Person into the Database
     *
     * @param person The Person to insert into the Database
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public void create(Person person) throws DataAccessException {
        String sql = "INSERT INTO person (personID, associatedUsername, firstName, lastName, gender, fatherID, motherID, spouseID) VALUES(?,?,?,?,?,?,?,?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getAssociatedUsername());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFatherID());
            stmt.setString(7, person.getMotherID());
            stmt.setString(8, person.getSpouseID());

            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting person into the database");
        }
    }

// Query
    /**
     * Gets a Person from the Database by personID
     *
     * @param personID The personID associated with the Person to get
     * @return The Person associated with the personID if one exists, else null
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public Person findByPersonID(String personID) throws DataAccessException {
        Person person;
        ResultSet rs;
        String sql = "SELECT * FROM person WHERE personID = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, personID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                person = new Person(rs.getString("personID"), rs.getString("associatedUsername"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"), rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID"));
                return person;
            }
            else {
                return null;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding person in the database");
        }
    }

    /**
     * Gets a Person from the Database by associatedUsername
     *
     * @param associatedUsername The associatedUsername associated with the Person to get
     * @return The Person associated with the associatedUsernam if one exists, else null
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public Person findByAssociatedUsername(String associatedUsername) throws DataAccessException {

        return null;
    }

    /**
     * Gets a Person from the Database by fatherID
     *
     * @param fatherID The fatherID associated with the Person to get
     * @return The Person associated with the fatherID if one exists, else null
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public Person findByFatherID(String fatherID) throws DataAccessException {
        // although irl a father can have multiple children, for the purposes of our app there can only be one...?

        return null;
    }

    /**
     * Gets a Person from the Database by motherID
     *
     * @param motherID The motherID associated with the Person to get
     * @return The Person associated with the motherID if one exists, else null
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public Person findByMotherID(String motherID) throws DataAccessException {
        // although irl a mother can have multiple children, for the purposes of our app there can only be one...?

        return null;
    }

    /**
     * Gets a Person from the Database by spouseID
     *
     * @param spouseID The spouseID associated with the Person to get
     * @return The Person associated with the spouseID if one exists, else null
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public Person findBySpouseID(String spouseID) throws DataAccessException {

        return null;
    }

// Update
    /**
     * Replaces a Person in the Database with another
     *
     * @param oldPerson The Person to be replaced
     * @param newPerson The Person to replace the old Person
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public void update(Person oldPerson, Person newPerson) throws DataAccessException {

    }

// Delete
    /**
     * Removes a Person from the Database
     *
     * @param person The Person to remove from the Database
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public void delete(Person person) throws DataAccessException {

    }

    /**
     * Removes all Persons from the Database
     *
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public void deleteAll() throws DataAccessException {
        String sql = "DELETE FROM person";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the person table");
        }
    }

// Other functionality

}
