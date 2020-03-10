package validator;

import model.Product;

public class ProductNameValidator implements Validator<Product>{
	/**Validates the name of a product, it cannot be empty
	 * 
	 */
	public void validate(Product t) {
		if(t.getName().equals("")) {
			throw new IllegalArgumentException("The name of the product is empty!");
		}
	}

}
