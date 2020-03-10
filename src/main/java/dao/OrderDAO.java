package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.ConnectionFactory;
import model.OrderRequest;

/**Class that extends AbstractDAO class and implements specific queries for orderrequest table in the database
 * 
 * @author Elekes Lukacs
 *
 */

public class OrderDAO extends AbstractDAO<OrderRequest> {
	private final static String findAmount = "SELECT amount FROM order WHERE idcustomer=? AND idproduct=?";
	
	/**Selects the amount of an order
	 *  
	 * @param customerId - Id of the client
	 * @param productId - Id of the product
	 * @return
	 */
	public int getAmount (int customerId, int productId) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(findAmount);
			statement.setInt(1, customerId);
			statement.setInt(2, productId);
			result = statement.executeQuery();
			result.next();
			return result.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(result);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return -1;
	}
}
