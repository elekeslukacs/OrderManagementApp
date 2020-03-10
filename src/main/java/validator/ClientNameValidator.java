package validator;

import model.Client;

public class ClientNameValidator implements Validator<Client> {
	/**Validates the name of a client
	 * Must contain only letters, '-' symbols or spaces
	 */
	public void validate(Client t) {
		boolean isSuccess = true;
		char [] name = t.getName().toCharArray();
		for(char c : name) {
			if(!Character.isLetter(c) && !(c==' ') && !(c=='-')) {
				isSuccess = false;
			}
		}
		if(!isSuccess) {
			throw new IllegalArgumentException("The name contains other characters than letters!");
		}
		
	}

}
