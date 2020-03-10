package validator;

import model.OrderRequest;

public class AmountValidator implements Validator<OrderRequest> {
	/**
	 * Validates amount
	 */
	public void validate(OrderRequest t) {
		if(t.getAmount() < 0) {
			throw new IllegalArgumentException("Amount is a negative number!");
		}
	}

}
