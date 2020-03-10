package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connection.ConnectionFactory;
import model.Client;

/**Class that extends the generic AbstractDAO class and implements specific queries for the client table.
 * 
 * @author Elekes Lukacs
 *
 */

public class ClientDAO extends AbstractDAO<Client> {
	
	private final static String findPhoneLastDigits = "SELECT * FROM client WHERE phone LIKE ?";
	private final static String findEmail = "SELECT * FROM client WHERE email LIKE ?";
	
	public ClientDAO() {
		super();
	}
	
	/**Finds the client having the last three digits of the phone number similar to those searched.
	 * 
	 * @param lastThreeDigits - Last three digits of a number to be found
	 * @return Client having that phone number.
	 */
	public Client findByPhoneLastDigits(String lastThreeDigits) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String likeStatement = "";
		likeStatement ="" + "%" + lastThreeDigits + "";
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(findPhoneLastDigits);
			statement.setString(1, likeStatement);
			result = statement.executeQuery();
			Client c = createObjects(result).get(0);
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(result);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	/**Finds all clients that have email containing the string given as search parameter
	 * Uses LIKE clause
	 * @param email - Email to be searched
	 * @return List of clients having searched email.
	 */
	public List<Client> findByEmail(String email){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		String likeStatement = "";
		likeStatement = "" + "%" + email + "%";
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(findEmail);
			statement.setString(1, likeStatement);
			result = statement.executeQuery();
			return createObjects(result);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(result);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	
	
	

}
