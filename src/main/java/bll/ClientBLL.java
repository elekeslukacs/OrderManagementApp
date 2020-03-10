package bll;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import dao.ClientDAO;
import model.Client;
import validator.ClientNameValidator;
import validator.EmailValidator;
import validator.PhoneValidator;
import validator.Validator;
/**
 * Class of business logic layer, where operations on the database are implemented using the DAO classes.
 * @author Elekes Lukacs
 *
 */
public class ClientBLL {
	
	private List<Validator<Client>> validators;
	private ClientDAO clientDao;
	/**Create the class
	 * 
	 */
	public ClientBLL() {
		validators = new ArrayList<Validator<Client>>();
		validators.add(new ClientNameValidator());
		validators.add(new EmailValidator());
		validators.add(new PhoneValidator());
		
		clientDao = new ClientDAO();
	}
	
	/**
	 * Validates and inserts a client.
	 * @param c - Client to be inserted
	 * @return Id of the inserted client.
	 * @throws SQLIntegrityConstraintViolationException
	 */
	public int insertClient(Client c) throws SQLIntegrityConstraintViolationException {
		for(Validator<Client> val : validators) {
			val.validate(c);
		}
		
		if(clientDao.hasRecord(c.getId())) {
			throw new SQLIntegrityConstraintViolationException("Client with id " + c.getId() + " already exists in the table!");
		}
		return clientDao.insert(c).getId();
	}
	
	/**
	 * Validates and updates a client.
	 * @param c - client to be updated
	 * @param id - id of the client to be updated
	 * @return id of the updated client
	 */
	public int updateClient(Client c, int id) {
		for(Validator<Client> val : validators) {
			val.validate(c);
		}
		return clientDao.update(c, id).getId();
	}
	
	/**
	 * Deletes a client.
	 * @param id - id of the client to be deleted
	 */
	public void deleteClient(int id) {
		clientDao.delete(id);
	}
	
	/**
	 * List all clients.
	 * @return List containing all clients.
	 */
	public List<Object> selectClient(){
		return clientDao.select();
	}
	
//	public List<Object> selectClientObjects() {
//		return clientDao.select();
//	}
	
	/**
	 * Finds a client which has its phone number's last three digits the same as the input parameter.
	 * @param lastThreeDigits - Last three digits of the phone number of the client to be found.
	 * @return Client having that phone number.
	 */
	public Client findClientByPhone(String lastThreeDigits) {
		boolean isSuccess = true;
		char [] phone = lastThreeDigits.toCharArray();
		for(char c : phone) {
			if(!Character.isDigit(c)) {
				isSuccess = false;
			}
		}
		
		if(!isSuccess) {
			throw new IllegalArgumentException("The last three digits are not digits!");
		}
		
		return clientDao.findByPhoneLastDigits(lastThreeDigits);
	}
	
	/**
	 * Finds clients based on email address.
	 * @param email - email address to be found
	 * @return List with clients having the email addresses similar.
	 */
	public List<Client> findByEmail(String email){
		return clientDao.findByEmail(email);
	}
	
	/**
	 * Finds clients by names
	 * @param name - name to be searched
	 * @return List containing clients having the name given.
	 */
	public List<Client> findClientByName (String name){
		return clientDao.findByName(name);
	}
	
	/**
	 * Finds client by id.
	 * @param id - id to be searched
	 * @return Client having that id.
	 */
	public Client findClientById(int id) {
		return clientDao.findById(id);
	}
	
}
