package DataAccess;

import Model.Authtoken;

import java.sql.Connection;

/**
 * Inserts, queries, updates, and deletes Authtokens
 */
public class AuthtokenDao {
    /**
     * The connection this DAO will utilize to access the Database
     */
    private final Connection connection;

    /**
     * Establishes the connection to the Database
     *
     * @param connection The connection this DAO will utilize to access the Database
     */
    public AuthtokenDao(Connection connection) {
        this.connection = connection;
    }

// Insert
    /**
     * Inserts an Authtoken into the Database
     * @param authtoken The Authtoken to insert
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public void create(Authtoken authtoken) throws DataAccessException {

    }

// Query
    /**
     * Gets an Authtoken from the Database by username
     *
     * @param username The username associated with the Authtoken to get
     * @return The Authtoken associated with the username if one exists, else null
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public String findAuthtokenByUsername(String username) throws DataAccessException {

        return null;
    }

    /**
     * Gets an Authtoken from the Database by username
     *
     * @param authtoken The Authtoken associated with the username to get
     * @return The username associated with the Authtoken if one exists, else null
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public String findUsernameByAuthtoken(String authtoken) throws DataAccessException {

        return null;
    }

// Update
    /**
     * Replaces an Authtoken in the Database with another
     *
     * @param oldAuthtoken The Authtoken to be replaced
     * @param newAuthtoken The Authtoken to replace the old Authtoken
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public void update(Authtoken oldAuthtoken, Authtoken newAuthtoken) throws DataAccessException{

    }

// Delete
    /**
     * Removes an Authtoken from the Database
     *
     * @param authtoken The authtoken to remove from the Database
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public void delete(Authtoken authtoken) throws DataAccessException {

    }

    /**
     * Removes all Authtokens from the Database
     *
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public void deleteAll() throws DataAccessException {

    }

    /**
     * Removes all Authtokens for a User by username
     *
     * @param username The username associated with the User whose Authtokens to delete
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public void deleteAllForUsername(String username) throws DataAccessException {

    }

// Other functionality

}
