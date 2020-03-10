package validator;

import model.Product;

public class PriceValidator implements Validator<Product> {
	/**Validates a price to be positive
	 * 
	 */
	public void validate(Product t) {
		if(t.getPrice() < 0) {
			throw new IllegalArgumentException("The price of the product is a negative number!");
		}
	}
}
