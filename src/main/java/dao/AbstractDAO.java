package dao;

import java.lang.reflect.ParameterizedType;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

/**Generic class for creating CRUD queries and creating access to the database
 * 
 * @author Elekes Lukacs
 *
 * @param <T> - Can be Client, Product and OrderRequest
 */

public class AbstractDAO<T> {
	
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	private final Class<T> type;

	/**Constructor of the class
	 * 
	 */
	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	}
	
	/**Builds the query for selecting elements with a where clause
	 * 
	 * @param field - Field for the WHERE clause
	 * @return A string containing the query
	 */
	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " =?");
		return sb.toString();
	}
	
	/**Build a query for selecting all the rows from a table
	 * 
	 * @return A string containing the query
	 */
	private String createListQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(type.getSimpleName());
		return sb.toString();
	}
	
	/**Builds a query for inserting new rows
	 * 
	 * @return A string containing the query
	 */
	private String createInsertQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(type.getSimpleName());
		sb.append(" (");
		for(Field field : type.getDeclaredFields()) {
			sb.append(field.getName() + ",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(") VALUES (");
		
		for(int i = 0; i < type.getDeclaredFields().length; i++) {
			sb.append("?,");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(")");
		return sb.toString();
	}
	
	/**Builds a query for updating rows
	 * 
	 * @param f - Field for the WHERE clause
	 * @return A string containing the query
	 */
	private String createUpdateQuery(String f) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append(type.getSimpleName());
		sb.append(" SET ");
		for(Field field : type.getDeclaredFields()) {
			sb.append(field.getName() + "=?,");
		}
		
		sb.deleteCharAt(sb.length()-1);
		sb.append(" WHERE " + f + "=?");
		return sb.toString();
		
	}
	
	/**Builds a query for deleting a row
	 * 
	 * @param field - Field for the WHERE clause
	 * @return String containing the query
	 */
	private String createDeleteQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + "=?");
		return sb.toString();
	}
	
	/**Builds a query for selecting data with a LIKE clause
	 * 
	 * @return String containing the query
	 */
	private String createLikeQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE name LIKE ?");
		return sb.toString();
	}
	
	/**Selects the row with the searched id
	 * 
	 * @param id - Id to find
	 * @return The object with the corresponding. If not found, null.
	 */
	public T findById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	/**Finds the object containing the searched name, uses LIKE clause
	 * 
	 * @param name - Any part of a name to be searched
	 * @return All the rows containing that name.
	 */
	public List<T> findByName(String name) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createLikeQuery();
		String likeStatement = "";
		likeStatement = "" + "%" + name + "%";
		
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, likeStatement);
			resultSet = statement.executeQuery();
			
			return createObjects(resultSet);
		}catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
				
	}
	
	/**Creates and returns a list containing the objects found by a query and stored in a ResultSet
	 * 
	 * @param resultSet - The result of the query
	 * @return List of objects extracted from the resultset
	 */
	protected List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();

		try {
			while (resultSet.next()) {
				T instance = type.newInstance();
				for (Field field : type.getDeclaredFields()) {
					Object value = resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**Inserts a new row
	 * 
	 * @param t - Object to be inserted
	 * @return Object inserted
	 */
	public T insert(T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		//ResultSet resultSet = null;
		String query = createInsertQuery();
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			int pos = 1;
			for(Field field : type.getDeclaredFields()) {
				field.setAccessible(true);
				Object value = field.get(t);
				statement.setString(pos, value.toString());
				pos++;
			}
			statement.executeUpdate();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		
		return t;
	}
	
	/**Updates an object
	 * 
	 * @param t - Object to be updated
	 * @param id - Id of the object
	 * @return Object updated
	 */
	public T update(T t, int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createUpdateQuery("id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			int pos = 1;
			for(Field field : type.getDeclaredFields()) {
				field.setAccessible(true);
				Object value = field.get(t);
				statement.setString(pos, value.toString());
				pos++;
			}
			statement.setInt(pos, id);
			statement.executeUpdate();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		
		return t;
		
	}

	/**Deletes a row
	 * 
	 * @param id - Id of the object to be deleted
	 */
	public void delete(int id) {
		//T instance = type.newInstance();
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createDeleteQuery("id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		
	}
	
	/**List all the objects of the class
	 * 
	 * @return List of objects.
	 */
	public List<Object> select() {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createListQuery();
		ResultSet resultSet = null;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			return (List<Object>) createObjects(resultSet);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
	
	/**
	 * Checks if an id already exists in the table/
	 * @param id - id to be checked
	 * @return True if exists, false if not.
	 */
	public boolean hasRecord(int id) {
	    String sql = "Select 1 from ";
	    sql = sql + type.getSimpleName() + " where id=?";
	    Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			 return resultSet.next();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
	    return false;
	   
	}

}
