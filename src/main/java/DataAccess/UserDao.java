package DataAccess;

import Model.User;

import java.sql.Connection;

/**
 * Inserts, queries, updates, and deletes Users
 */
public class UserDao {
    /**
     * The connection this DAO will utilize to access the Database
     */
    private final Connection connection;

    /**
     * Establishes the connection to the Database
     *
     * @param connection The connection this DAO will utilize to access the Database
     */
    public UserDao(Connection connection) {
        this.connection = connection;
    }

// Insert
    /**
     * Creates a new User and inserts it into the Database
     *
     * @param user The user to insert into the Database
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public void create(User user) throws DataAccessException {
        // SQL code that inserts a user into the database
    }

// Query
    /**
     * Gets a User from the Database by personID
     *
     * @param personID The personID associated with the User to get
     * @return The User associated with the personID if one exists, else null
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public User findUserByPersonID(String personID) throws DataAccessException {
        return null;
    }

    /**
     * Gets a User from the Database by username
     *
     * @param username The username associated with the User to get
     * @return The User associated with the userID if one exists, else null
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public User findUserByUsername(String username) throws DataAccessException {

        return null;
    }

// Update
    /**
     * Replaces a User in the Database with another
     *
     * @param oldUser The User to be replaced
     * @param newUser The User to replace the old User
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public void update(User oldUser, User newUser) throws DataAccessException {

    }

// Delete
    /**
     * Removes a User from the Database
     *
     * @param user The User to remove from the Database
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public void delete(User user) throws DataAccessException {

    }

    /**
     * Removes all Users from the Database
     *
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public void deleteAll() throws DataAccessException {

    }

// Other functionality
    /**
     * Checks whether a username matches its corresponding password
     *
     * @param username The username of the account to validate
     * @param password The password to check against the actual password
     * @return True if password matches the password in the Database, else false
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public boolean validateUser(String username, String password) throws DataAccessException {
        return false;
    }
}
