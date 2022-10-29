package DataAccess;

import Model.Authtoken;
import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        String sql = "INSERT INTO authtoken (authtoken, username) VALUES(?,?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, authtoken.getAuthtoken());
            stmt.setString(2, authtoken.getUsername());

            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting authtoken into the database");
        }
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
        ResultSet rs;
        String sql = "SELECT * FROM authtoken WHERE username = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String authtoken = rs.getString("authtoken");
                return authtoken;
            }
            else {
                return null;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding user in the database");
        }
    }

    /**
     * Gets an Authtoken from the Database by username
     *
     * @param authtoken The Authtoken associated with the username to get
     * @return The username associated with the Authtoken if one exists, else null
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public String findUsernameByAuthtoken(String authtoken) throws DataAccessException {
        ResultSet rs;
        String sql = "SELECT * FROM authtoken WHERE authtoken = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, authtoken);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                return username;
            }
            else {
                return null;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding user in the database");
        }
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
        String sql = "DELETE FROM authtoken";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the authtoken table");
        }
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
