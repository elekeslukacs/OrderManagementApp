package model;

/**
 * Class that represents a client and maps a table in the relational database in MySQL.
 * */

public class Client {
	/**Id of the client.*/
	private int id;
	/**Name of the client.*/
	private String name;
	/**Email address of the client.*/
	private String email;
	/**Phone number of the client.*/
	private String phone;
	
	/**Default constructor without parameters.*/
	public Client() {
		super();
		this.id = 0;
		this.name = "";
		this.email = "";
		this.phone = "";
	}
	
	/**Constructor with parameters.
	 * 
	 * @param id - Id of the client to be created
	 * @param name - Name of the client to be created
	 * @param email - Email address of the client to be created
	 * @param phone - Phone number of the client to be created
	 */
	public Client(int id, String name, String email, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	/** Getter for the id
	 * 
	 * @return id of the client.
	 */
	public int getId() {
		return id;
	}

	/**Setter for the id
	 * 
	 * @param id - Id of the client
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** Getter for the name
	 * 
	 * @return name of the client.
	 */
	public String getName() {
		return name;
	}

	/**Setter for the name
	 * 
	 * @param name - Name of the client
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** Getter for the email
	 * 
	 * @return email of the client.
	 */
	public String getEmail() {
		return email;
	}

	/**Setter for the email
	 * 
	 * @param email - Email of the client
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/** Getter for the phone
	 * 
	 * @return phone of the client.
	 */
	public String getPhone() {
		return phone;
	}

	/**Setter for the phone
	 * 
	 * @param phone - Name of the client
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**Method for representing a client as a string.
	 * 
	 * @return Client as a string representation.
	 */
	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + "]";
	}
	
	

}
