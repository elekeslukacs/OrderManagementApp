package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import connection.ConnectionFactory;
import model.Product;

/**Class that extends AbstractDAO and implements specific queries for product table
 * 
 * @author Elekes Lukacs
 *
 */

public class ProductDAO extends AbstractDAO<Product>{
	private final static String findPriceBetween = "SELECT * FROM product WHERE price BETWEEN ? AND ?";

	/**Selects products with the price between the two limits
	 * 
	 * @param limit1 - The lower bound of the price
	 * @param limit2 - The upper bound of the price
	 * @return
	 */
	public List<Product> filterByPrice (float limit1, float limit2){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		
		//List<Product> products = new ArrayList<Product>();
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(findPriceBetween);
			statement.setFloat(1, limit1);
			statement.setFloat(2, limit2);
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
