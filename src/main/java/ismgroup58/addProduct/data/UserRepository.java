package ismgroup58.addProduct.data;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ismgroup58.addProduct.app.User;

@Repository
public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	/**
	 * Searches the database for all the
     * registered users.
	 * 
	 * @return a list of the users
	 */
	public List<User> getUsers() {
        return jdbcTemplate.query("SELECT * FROM user;", new UserMapper());
    }

    /**
	 * Searches a user in the DB
     * based on a given username.
	 * 
	 * @param username
	 * @return a User object or null if the user doesn't exist
	 * @throws Exception
	 */
	public User findUser(String username) {
		String query = "SELECT * FROM user WHERE username = ?;";
        return jdbcTemplate.queryForObject(query, new UserMapper(), username);
    }

    /**
	 * Is used to authenticate a user, to check if 
     * a user with the given credentials exists.
	 * 
	 * @param username the given username
	 * @param password the given password
	 * @return a User object that represents the user with these credentials
	 * @throws Exception if the credentials are not valid
	 */
	public User authenticate(String username, String password) throws Exception {
		String query = "SELECT * FROM user WHERE username = ? AND password = ?;";
        
        try {
            return jdbcTemplate.queryForObject(query, new UserMapper(), username, password);
        } catch (Exception e) {
            throw new Exception("Wrong username or password");
        }
    }

	/**
	 * Registers a new user in the DB given a User
     * object created by the fields from the Register Form.
	 * 
	 * @param user the User object created agter registering
	 * @throws Exception if a user with these fields already exists
	 */
	// public void register(User user) throws Exception {

	// 	// check if user already exists
	// 	String query = "SELECT * FROM user WHERE username = ? OR email = ? OR ssn = ?";
    //     User usr = jdbcTemplate.queryForObject(query, new UserMapper(),
    //                                 user.getUsername(), user.getEmail(), user.getSSN() );
    
    //     if (usr != null) { //user found -> Error Message!
           
    //         throw new Exception("Sorry, username, email or ssn already registered");
       
    //     } else { // user not found -> Insert user into DB
        
    //         // change the Query
    //         query = "INSERT INTO user (name, email, ssn, username, password,"
    //                 + " image, joined) VALUES (?, ?, ?, ?, ?, ?, ?);";
    //         jdbcTemplate.update(query, user.getName(), user.getEmail(), user.getSSN(),
    //                             user.getUsername(), user.getPassword(), user.getImage(),
    //                             user.getJoined());

    //     }
	// }
    
    public void register(User user) throws Exception {

        // check if user already exists
        String query = "SELECT * FROM user WHERE username = ? OR email = ? OR ssn = ?";
        User usr = null;

        try {
            usr = jdbcTemplate.queryForObject(query, new UserMapper(),
                    user.getUsername(), user.getEmail(), user.getSSN());
        } catch (EmptyResultDataAccessException e) {
            // No user found, this is expected behavior, so just set usr to null
            usr = null;
        }

        if (usr != null) { // user found -> Error Message!
            throw new Exception("Sorry, username, email or ssn already registered");
        } else { // user not found -> Insert user into DB
            user.setJoined(LocalDate.now().toString()); //get the current date
            query = "INSERT INTO user (name, email, ssn, username, password, image, joined) VALUES (?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(query, user.getName(), user.getEmail(), user.getSSN(),
                    user.getUsername(), user.getPassword(), user.getImage(), user.getJoined());
        }
    }

}
