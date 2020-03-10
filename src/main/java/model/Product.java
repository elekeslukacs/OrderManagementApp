package model;

/**Class that represents a product and maps the table in the database.
 * 
 * @author Elekes Lukacs
 *
 */

public class Product {
	private int id;
	private String name;
	private float price;
	private int stock;
	
	/**Constructor of the class without parameters
	 * 
	 */
	public Product() {
		super();
		this.id = 0;
		this.name = "";
		this.price = 0;
		this.stock = 0;
	}
	
	/**Constructor of the class
	 * 
	 * @param id - id of the product	
	 * @param name - name of the product
	 * @param price - price of the product
	 * @param stock - number of products available on stock
	 */
	public Product (int id, String name, float price, int stock) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", stock=" + stock + "]";
	}
	
	

}
