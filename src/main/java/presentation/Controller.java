package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Client;
import model.OrderRequest;
import model.Product;

/**Class representing the Controller from MVC pattern, connects model and view.
 * 
 * @author Elekes Lukacs
 *
 */
public class Controller {
	private ClientBLL clientBll;
	private ProductBLL productBll;
	private OrderBLL orderBll;
	private View view;
	private ViewClient viewClient;
	private ViewProduct viewProduct;
	private ViewOrder viewOrder;
	private ButtonListener button;
	private int billCounter = 0;
	
	/**Create the controller
	 * 
	 */
	public Controller() {
		this.clientBll = new ClientBLL();
		this.productBll = new ProductBLL();
		this.orderBll = new OrderBLL();
		this.button = new ButtonListener();
		this.view = new View();
		this.view.addListener(button);
		this.viewClient = new ViewClient();
		this.viewClient.addListeners(button);
		this.viewProduct = new ViewProduct();
		this.viewProduct.addListener(button);
		this.viewOrder = new ViewOrder();
		this.viewOrder.addListener(button);
		
	}
	
	/**
	 * Creates a JTable from a list of objects
	 * @param objects - List of objects to be represented in the JTable
	 * @return JTable with the content from objects.
	 */
	private JTable createTable(List<Object> objects) {
		if(objects.size() == 0) {
			return null;
		}
		
		int columnNumber = objects.get(0).getClass().getDeclaredFields().length;
		String[] columnNames = new String[columnNumber];
		int i = 0;
		for (Field field : objects.get(0).getClass().getDeclaredFields()) {
			columnNames[i] = field.getName();
			i++;
		}
		
		String content[][] = new String[objects.size()][columnNumber];
		
		i=0;
		for(Object object : objects) {
			int j = 0;
			for(Field field : object.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object value;
				try {
					value = field.get(object);
					content[i][j] = value.toString();
					
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				j++;
			}
			i++;
		}
		
		JTable result = new JTable(content, columnNames);
		return result;
	}
	
	/**
	 * Creates a unique name for the bill of an order
	 * @param o - order which needs a bill
	 * @return String with the name of the bill (filename)
	 */
	private String fileNameCreator(OrderRequest o) {
		String name ="";
		name = "" + o.getIdcustomer() + o.getIdproduct() + o.getAmount() + "order_" + this.billCounter + ".txt";
		billCounter++;
		return name;
	}
	
	/**Class that implements the button listener
	 * 
	 * @author Elekes Lukacs
	 *
	 */
	class ButtonListener implements ActionListener {
		
	@Override
	public void actionPerformed(ActionEvent event) {
		
		int id;
		int row;
		String name;
		String email;
		String phone;
		Float price;
		int stock;
		List<Object> result = new ArrayList<Object>();
		Client newClient;
		Product newProduct;
		switch(event.getActionCommand()) {
		case "openproducts":
			viewProduct.setTable(createTable(productBll.selectProducts()));
			viewProduct.setVisible(true);
			break;
		case "openclients" :
			viewClient.setTable(createTable(clientBll.selectClient()));
			viewClient.setVisible(true);
			break;
		case "placeorder" :
			viewOrder.setTableClient(createTable(clientBll.selectClient()));
			viewOrder.setTableProduct(createTable(productBll.selectProducts()));
			viewOrder.setVisible(true);
			break;
		case "insertclient" :
			id = viewClient.getId();
			name = viewClient.getName();
			email = viewClient.getEmail();
			phone= viewClient.getPhone();
			newClient = new Client(id, name, email, phone);
			try {
				clientBll.insertClient(newClient);
				viewClient.printMessage("Client inserted successfully!");
				viewClient.setTable(createTable(clientBll.selectClient()));
				viewOrder.setTableClient(createTable(clientBll.selectClient()));
				viewOrder.setTableProduct(createTable(productBll.selectProducts()));
			} catch (IllegalArgumentException e){
				viewClient.printMessage(e.getMessage());
			} catch (SQLIntegrityConstraintViolationException e) {
				viewClient.printMessage(e.getMessage());
			}
			break;
		case "updateclient" :
			id = viewClient.getId();
			name = viewClient.getName();
			email = viewClient.getEmail();
			phone= viewClient.getPhone();
			newClient = new Client(id, name, email, phone);
			try {
				clientBll.updateClient(newClient, id);
				viewClient.printMessage("Client updated successfully!");
				viewClient.setTable(createTable(clientBll.selectClient()));
				viewOrder.setTableClient(createTable(clientBll.selectClient()));
				viewOrder.setTableProduct(createTable(productBll.selectProducts()));
			} catch (IllegalArgumentException e) {
				viewClient.printMessage(e.getMessage());
			}
			break;
		case "deleteclient" :
			row = viewClient.getTable().getSelectedRow();
			id = Integer.parseInt(viewClient.getTable().getValueAt(row, 0).toString());
			try {
				clientBll.deleteClient(id);
				viewClient.printMessage("Client deleted successfully!");
				viewClient.setTable(createTable(clientBll.selectClient()));
				viewOrder.setTableClient(createTable(clientBll.selectClient()));
				viewOrder.setTableProduct(createTable(productBll.selectProducts()));
			} catch (IllegalArgumentException e) {
				viewClient.printMessage(e.getMessage());
			}
			break;
		case "listclient" :
			viewClient.setTable(createTable(clientBll.selectClient()));
			break;
		case "findemail" :
			email = viewClient.getSearchedEmail();
			result.clear();
			for(Client c : clientBll.findByEmail(email)) {
				result.add(c);
			}
			viewClient.setTable(createTable(result));
			break;
		case "findphone" :
			result.clear();
			phone = viewClient.getSearchedPhone();
			newClient = clientBll.findClientByPhone(phone);
			//System.out.println(newClient.toString());
			result.add(newClient);
			viewClient.setTable(createTable(result));
			break;
		case "findidclient" :
			result.clear();
			id = viewClient.getSearchedId();
			newClient = clientBll.findClientById(id);
			result.add(newClient);
			viewClient.setTable(createTable(result));
			break;
		case "findnameclient" :
			result.clear();
			name = viewClient.getSearchedName();
			for(Client c : clientBll.findClientByName(name)) {
				result.add(c);
			}
			viewClient.setTable(createTable(result));
			break;
		case "insertproduct" :
			id = viewProduct.getId();
			name = viewProduct.getProductName();
			System.out.println("name is " + name);
			price = viewProduct.getPrice();
			stock = viewProduct.getStock();
			newProduct = new Product(id, name, price, stock);
			try {
				productBll.insertProduct(newProduct);
				viewProduct.printMessage("Product inserted successfully!");
				viewProduct.setTable(createTable(productBll.selectProducts()));
				viewOrder.setTableClient(createTable(clientBll.selectClient()));
				viewOrder.setTableProduct(createTable(productBll.selectProducts()));
			} catch (IllegalArgumentException e){
				viewProduct.printMessage(e.getMessage());
			} catch (SQLIntegrityConstraintViolationException e) {
				// TODO Auto-generated catch block
				viewProduct.printMessage(e.getMessage());
			}
			break;
		case "updateproduct" :
			id = viewProduct.getId();
			name = viewProduct.getProductName();
			price = viewProduct.getPrice();
			stock = viewProduct.getStock();
			newProduct = new Product(id, name, price, stock);
			try {
				productBll.updateProduct(newProduct, id);
				viewProduct.printMessage("Product updated successfully!");
				viewProduct.setTable(createTable(productBll.selectProducts()));
				viewOrder.setTableClient(createTable(clientBll.selectClient()));
				viewOrder.setTableProduct(createTable(productBll.selectProducts()));
			} catch (IllegalArgumentException e){
				viewProduct.printMessage(e.getMessage());
			}
			break;
		case "deleteproduct" :
			row = viewProduct.getTable().getSelectedRow();
			id = Integer.parseInt(viewProduct.getTable().getValueAt(row, 0).toString());
			try {
				productBll.deleteProduct(id);
				viewProduct.printMessage("Product deleted successfully!");
				viewProduct.setTable(createTable(productBll.selectProducts()));
				viewOrder.setTableClient(createTable(clientBll.selectClient()));
				viewOrder.setTableProduct(createTable(productBll.selectProducts()));
			} catch (IllegalArgumentException e) {
				viewProduct.printMessage(e.getMessage());
			}
			break;
		case "listproduct" :
			viewProduct.setTable(createTable(productBll.selectProducts()));
			break;
		case "filter" :
			float priceMin = viewProduct.getPriceMin();
			float priceMax = viewProduct.getPriceMax();
			result.clear();
			for(Product p : productBll.filterByPrice(priceMin, priceMax)) {
				result.add(p);
			}
			viewProduct.setTable(createTable(result));
			break;
		case "findidproduct" :
			result.clear();
			id = viewProduct.getSearchedId();
			newProduct = productBll.findProductById(id);
			result.add(newProduct);
			viewProduct.setTable(createTable(result));
			break;
		case "findnameproduct" :
			result.clear();
			name = viewProduct.getSearchedName();
			for(Product p : productBll.findProductByName(name)) {
				result.add(p);
			}
			viewProduct.setTable(createTable(result));
			break;
		case "confirmorder" :
			newClient = viewOrder.getClientSelected();
			newProduct = viewOrder.getProductSelected();
			stock = viewOrder.getAmount();
			OrderRequest o = new OrderRequest(newClient.getId(), newProduct.getId(), stock);
			try {
				orderBll.confirmOrder(o, newProduct);
				viewOrder.printMessage("Order was successfull!");
				PrintWriter writer = new PrintWriter(fileNameCreator(o), "UTF-8");
				writer.print(orderBll.createBill(newProduct, newClient, o));
				writer.close();
				
				//orderBll.createBill(newProduct, newClient, o);
				System.out.println(orderBll.createBill(newProduct, newClient, o));
				viewOrder.setTableClient(createTable(clientBll.selectClient()));
				viewOrder.setTableProduct(createTable(productBll.selectProducts()));
			} catch (IllegalArgumentException e) {
				viewOrder.printMessage(e.getMessage());
			} catch (FileNotFoundException e) {
				viewOrder.printMessage("Problem with creating the bill!");
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				viewOrder.printMessage("Problem with creating the bill!");
				e.printStackTrace();
			}
			break;
		}
		
	}

	}
}
