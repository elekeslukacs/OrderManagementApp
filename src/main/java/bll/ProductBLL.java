package bll;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import dao.ProductDAO;
import model.Product;
import validator.PriceValidator;
import validator.ProductNameValidator;
import validator.StockValidator;
import validator.Validator;

public class ProductBLL {
	
	private List<Validator<Product>> validators;
	private ProductDAO productDao;
	
	public ProductBLL() {
		validators = new ArrayList<Validator<Product>>();
		validators.add(new ProductNameValidator());
		validators.add(new PriceValidator());
		validators.add(new StockValidator());
		
		productDao = new ProductDAO();
	}
	
		public int insertProduct(Product p) throws SQLIntegrityConstraintViolationException {
			for(Validator<Product> val : validators) {
				val.validate(p);
			}
			if(productDao.hasRecord(p.getId())) {
				throw new SQLIntegrityConstraintViolationException("Product with id " + p.getId() + " already exists in the table!");
			}
			return productDao.insert(p).getId();
		}
		
		public int updateProduct(Product p, int id) {
			for(Validator<Product> val : validators) {
				val.validate(p);
			}
			return productDao.update(p, id).getId();
		}
		
		public void deleteProduct(int id) {
			productDao.delete(id);
		}
		
		public List<Object> selectProducts() {
			return productDao.select();
		}
		
		public List<Product> filterByPrice(float limit1, float limit2){
			if(limit1 < 0 || limit1 > limit2) {
				throw new IllegalArgumentException("The price interval is invalid!");		
			}
			return productDao.filterByPrice(limit1, limit2);
		}
		
		public List<Product> findProductByName(String name){
			return productDao.findByName(name);
		}
		
		public Product findProductById(int id) {
			return productDao.findById(id);
		}
}
