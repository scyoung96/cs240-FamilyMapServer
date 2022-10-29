package DataAccess;

import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        String sql = "INSERT INTO user (username, password, email, firstName, lastName, gender, personID) VALUES(?,?,?,?,?,?,?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFirstName());
            stmt.setString(5, user.getLastName());
            stmt.setString(6, user.getGender());
            stmt.setString(7, user.getPersonID());

            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting user into the database");
        }
    }

// Query
    /**
     * Gets a User from the Database by personID
     *
     * @param personID The personID associated with the User to get
     * @return The User associated with the personID if one exists, else null
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public User findByPersonID(String personID) throws DataAccessException {
        User user;
        ResultSet rs;
        String sql = "SELECT * FROM user WHERE personID = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, personID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"), rs.getString("personID"));
                return user;
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
     * Gets a User from the Database by username
     *
     * @param username The username associated with the User to get
     * @return The User associated with the userID if one exists, else null
     * @throws DataAccessException Exception thrown when an error occurs accessing the Database
     */
    public User findUserByUsername(String username) throws DataAccessException {
        User user;
        ResultSet rs;
        String sql = "SELECT * FROM user WHERE username = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"), rs.getString("personID"));
                return user;
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
        String sql = "DELETE FROM user";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the user table");
        }
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
        User user;
        ResultSet rs;
        String sql = "SELECT * FROM user WHERE username = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"), rs.getString("personID"));

                if (user.getPassword().equals(password)) {
                    return true;
                }
            }
            else {
                return false;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding user in the database");
        }
        return false;
    }
}
