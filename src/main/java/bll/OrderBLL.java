package bll;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.OrderDAO;
import dao.ProductDAO;
import model.Client;
import model.OrderRequest;
import model.Product;
import validator.AmountValidator;

/**
 * Class of the business logic layer, implements operations with orders using DAO class.
 * @author Elekes Lukacs
 *
 */

public class OrderBLL {
	private AmountValidator val;
	private OrderDAO orderDao;
	private ProductDAO productDao;
	
	/**Create the object
	 * 
	 */
	public OrderBLL() {
		val = new AmountValidator();
		orderDao = new OrderDAO();
		productDao = new ProductDAO();
	}
	
	/**Validates an order, updates the brought product with new value on stock.
	 * 
	 * @param o - Order to confirm
	 * @param p - Product that is bought
	 */
	public void confirmOrder(OrderRequest o, Product p) {
		if(o.getAmount() <= p.getStock()) {
			val.validate(o);
			orderDao.insert(o);
			p.setStock(p.getStock()-o.getAmount());
			productDao.update(p, p.getId());
		}
		else {
			throw new IllegalArgumentException("Under-Stock!");
		}
	}
	
	/**Creates a bill using some information from the order, client and product
	 * 
	 * @param product - product bought
	 * @param client - client who made the purchase
	 * @param order - the order
	 * @return String with the bill content
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
	public String createBill(Product product, Client client, OrderRequest order) throws FileNotFoundException, UnsupportedEncodingException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		StringBuilder sb = new StringBuilder();
		sb.append("Order details:\r\n\r\n");
		sb.append("Name: " + client.getName());
		sb.append("\r\nPhone Number: " + client.getPhone());
		sb.append("\r\nProduct: " + product.getName());
		sb.append("\r\nPrice: " + product.getPrice() + " RON");
		sb.append("\r\nAmount: " + order.getAmount());
		float totalPrice = order.getAmount()*product.getPrice();
		sb.append("\r\nTotal price: " + totalPrice + "RON");
		sb.append("\r\nDate: " + dateFormat.format(date));
		return sb.toString();
	}

}
