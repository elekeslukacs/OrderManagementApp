package validator;
/**Interface having one method to validate an object
 * 
 * @author Elekes Lukacs
 *
 * @param <T> Can be Client, Product, OrderRequest
 */
public interface Validator<T> {
	
	public void validate (T t);
	
}
