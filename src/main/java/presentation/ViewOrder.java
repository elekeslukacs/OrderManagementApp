package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import model.Client;
import model.Product;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

/**Class that represents the Order window
 * 
 * @author Elekes Lukacs
 *
 */

public class ViewOrder extends JFrame {

	private JPanel contentPane;
	private JTable tableProduct;
	private JTable tableClient;
	private JTextField textAmount;
	private JButton btnConfirmOrder;
	private JScrollPane scrollClient;
	private JScrollPane scrollProduct;

	/**
	 * Create the frame.
	 */
	public ViewOrder() {
		setTitle("Order");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 997, 612);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		tableProduct = new JTable();
//		tableProduct.setBounds(39, 82, 380, 161);
		scrollProduct = new JScrollPane();
		scrollProduct.setBounds(39, 82, 380, 161);
		contentPane.add(scrollProduct);
		
//		tableClient = new JTable();
//		tableClient.setBounds(515, 82, 380, 161);
		scrollClient = new JScrollPane();
		scrollClient.setBounds(476, 82, 466, 161);
		contentPane.add(scrollClient);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblAmount.setBounds(301, 316, 87, 27);
		contentPane.add(lblAmount);
		
		textAmount = new JTextField();
		textAmount.setBounds(400, 321, 99, 22);
		contentPane.add(textAmount);
		textAmount.setColumns(10);
		
		btnConfirmOrder = new JButton("Confirm Order");
		btnConfirmOrder.setActionCommand("confirmorder");
		btnConfirmOrder.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnConfirmOrder.setBounds(358, 407, 219, 60);
		contentPane.add(btnConfirmOrder);
	}
	
	public void addListener(ActionListener l) {
		btnConfirmOrder.addActionListener(l);
	}
	
	public JTable getProductTable() {
		return this.tableProduct;
	}
	
	public JTable getClientTable() {
		return this.tableClient;
	}
	
	/**Sets the table with products
	 * 
	 * @param newTable
	 */
	public void setTableProduct(JTable newTable) {
		this.tableProduct = newTable;
		scrollProduct.setViewportView(tableProduct);
		repaint();
		revalidate();
	}
	
	/**Sets the table with clients
	 * 
	 * @param newTable
	 */
	public void setTableClient(JTable newTable) {
		this.tableClient = newTable;
		scrollClient.setViewportView(tableClient);
		repaint();
		revalidate();
	}
	
	public void printMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
	/**Creates and returns a Client which is selected from the table
	 * 
	 * @return Client selected
	 */
	public Client getClientSelected() {
		int row = tableClient.getSelectedRow();
		String idValue = tableClient.getValueAt(row, 0).toString();
		int id = Integer.parseInt(idValue);
		String name = tableClient.getValueAt(row, 1).toString();	
		String email = tableClient.getValueAt(row, 2).toString();
		String phone = tableClient.getValueAt(row, 3).toString();
		Client client = new Client(id, name, email, phone);
		return client;
	}
	/**Creates and returns a Product which is selected from the table
	 * 
	 * @return Product selected
	 */
	public Product getProductSelected() {
		int row = tableProduct.getSelectedRow();
		String idValue = tableProduct.getValueAt(row, 0).toString();
		int id = Integer.parseInt(idValue);
		String name = tableProduct.getValueAt(row, 1).toString();
		float price = Float.parseFloat(tableProduct.getValueAt(row, 2).toString());
		int stock = Integer.parseInt(tableProduct.getValueAt(row, 3).toString());
		
		Product product = new Product(id, name, price, stock);
		return product;
	}
	
	/**Returns the amount introduced as an int
	 * 
	 * @return Amount introduced.
	 */
	public int getAmount() {
		try {
			int num = Integer.parseInt(this.textAmount.getText());
			return num;
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Wrong format");
			throw new NumberFormatException("not number");
		}
	}
}
