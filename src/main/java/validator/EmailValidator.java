package validator;

import model.Client;

public class EmailValidator implements Validator<Client> {
	
	/**
	 * Counts how many times a char appears in a string
	 * @param str - string in which char will be counted
	 * @param c - char to count
	 * @return number of apparition
	 */
	 private static boolean countChar(String str, char c) {
		 char[] charArr = str.toCharArray();
	      int count = 0;
	      for(int i = 1; i<charArr.length-2; i++){
	          if (charArr[i] == c){
	              count++;
	          }
	      }
	      if (count>1){
	          return false;

	      }
	      else return true;
	 }
	 
	/**
	 * Validates email address
	 * Must be of minimum length 3, contain '@' but not at the beginning and not at the end
	 */
	public void validate(Client t) {
		boolean isSuccess = true;
		if(t.getEmail().length()<3 || t.getEmail().charAt(0) == '@' || t.getEmail().charAt(t.getEmail().length()-1) == '@'){
            isSuccess = false;
		}
		else {
			if (!(t.getEmail().contains("@"))){
                isSuccess = false;
            }
			
			if(!countChar(t.getEmail(), '@')){
                isSuccess = false;  
            }
		}
		
		if(!isSuccess) {
			throw new IllegalArgumentException("The email format is not correct!");
		}
	}

}
