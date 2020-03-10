package validator;

import model.Product;
/**Implements Validator interface
 * 
 * @author Elekes Lukacs
 *
 */
public class StockValidator implements Validator<Product> {
	/**Validates a product's stock
	 * 
	 */
	public void validate (Product t) {
		if(t.getStock() < 0) {
			throw new IllegalArgumentException("Stock is a negative number!");
		}
	}
}
