package validator;

import model.Client;

public class PhoneValidator implements Validator<Client> {
	/**Validates a phone number
	 * Must start with 07 and the length must be 10 (format in Romania)
	 */
	public void validate(Client t) {
		boolean isSuccess = true;
		char [] phone = t.getPhone().toCharArray();
		for(char c : phone) {
			if(!Character.isDigit(c)) {
				isSuccess = false;
			}
		}
		
		if(t.getPhone().length() != 10) {
			isSuccess = false;
		}
		
		if(phone[0] != '0' && phone[1] != '7') {
			isSuccess = false;
		}
		
		if(!isSuccess) {
			throw new IllegalArgumentException("The phone number is not correct!");
		}
	}

}
