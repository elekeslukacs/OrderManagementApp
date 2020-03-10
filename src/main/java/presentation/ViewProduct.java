package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

/** Class that implements the Product window
 * 
 * @author Elekes Lukacs
 *
 */

public class ViewProduct extends JFrame {

	private JPanel contentPane;
	private JTable tableProduct;
	private JTextField textId;
	private JTextField textProduct;
	private JTextField textPrice;
	private JTextField textStock;
	private JTextField textPrice1;
	private JTextField textPrice2;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnList;
	private JButton btnFilter;
	private JScrollPane scrollPane;
	private JLabel lblFindById;
	private JTextField textField_id;
	private JLabel lblFindByName;
	private JTextField textField;
	private JButton btnFindId;
	private JButton btnFindName;

	/**
	 * Create the frame.
	 */
	public ViewProduct() {
		setTitle("Product");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1066, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//tableProduct = new JTable();
		//tableProduct.setBounds(387, 60, 575, 342);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(387, 60, 575, 342);
		contentPane.add(scrollPane);
		
		//contentPane.add(scrollPane);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblId.setBounds(34, 90, 56, 16);
		contentPane.add(lblId);
		
		JLabel lblProduct = new JLabel("Product:");
		lblProduct.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblProduct.setBounds(34, 131, 86, 27);
		contentPane.add(lblProduct);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblPrice.setBounds(34, 186, 75, 27);
		contentPane.add(lblPrice);
		
		JLabel lblStock = new JLabel("Stock:");
		lblStock.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblStock.setBounds(34, 238, 75, 32);
		contentPane.add(lblStock);
		
		textId = new JTextField();
		textId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textId.setColumns(10);
		textId.setBounds(113, 88, 88, 22);
		contentPane.add(textId);
		
		textProduct = new JTextField();
		textProduct.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textProduct.setColumns(10);
		textProduct.setBounds(113, 136, 163, 22);
		contentPane.add(textProduct);
		
		textPrice = new JTextField();
		textPrice.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textPrice.setColumns(10);
		textPrice.setBounds(113, 191, 88, 22);
		contentPane.add(textPrice);
		
		textStock = new JTextField();
		textStock.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textStock.setColumns(10);
		textStock.setBounds(113, 246, 88, 22);
		contentPane.add(textStock);
		
		btnInsert = new JButton("Insert");
		btnInsert.setActionCommand("insertproduct");
		btnInsert.setBounds(92, 532, 122, 44);
		contentPane.add(btnInsert);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setActionCommand("updateproduct");
		btnUpdate.setBounds(294, 532, 113, 44);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setActionCommand("deleteproduct");
		btnDelete.setBounds(509, 532, 113, 44);
		contentPane.add(btnDelete);
		
		btnList = new JButton("List");
		btnList.setActionCommand("listproduct");
		btnList.setBounds(720, 532, 113, 44);
		contentPane.add(btnList);
		
		JLabel lblFilterByPrice = new JLabel("Filter By Price:");
		lblFilterByPrice.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblFilterByPrice.setBounds(12, 346, 156, 32);
		contentPane.add(lblFilterByPrice);
		
		textPrice1 = new JTextField();
		textPrice1.setBounds(160, 354, 56, 22);
		contentPane.add(textPrice1);
		textPrice1.setColumns(10);
		
		textPrice2 = new JTextField();
		textPrice2.setBounds(243, 354, 56, 22);
		contentPane.add(textPrice2);
		textPrice2.setColumns(10);
		
		btnFilter = new JButton("Filter");
		btnFilter.setActionCommand("filter");
		btnFilter.setBounds(179, 391, 97, 25);
		contentPane.add(btnFilter);
		
		lblFindById = new JLabel("Find by ID:");
		lblFindById.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblFindById.setBounds(12, 445, 108, 21);
		contentPane.add(lblFindById);
		
		textField_id = new JTextField();
		textField_id.setBounds(132, 444, 56, 22);
		contentPane.add(textField_id);
		textField_id.setColumns(10);
		
		lblFindByName = new JLabel("Find by Name:");
		lblFindByName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblFindByName.setBounds(12, 488, 130, 22);
		contentPane.add(lblFindByName);
		
		textField = new JTextField();
		textField.setBounds(160, 490, 139, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnFindId = new JButton("Find ID");
		btnFindId.setActionCommand("findidproduct");
		btnFindId.setBounds(243, 445, 97, 25);
		contentPane.add(btnFindId);
		
		btnFindName = new JButton("Find Name");
		btnFindName.setActionCommand("findnameproduct");
		btnFindName.setBounds(326, 489, 97, 25);
		contentPane.add(btnFindName);
	}
	
	public void addListener(ActionListener l) {
		btnInsert.addActionListener(l);
		btnUpdate.addActionListener(l);
		btnDelete.addActionListener(l);
		btnList.addActionListener(l);
		btnFilter.addActionListener(l);
		btnFindId.addActionListener(l);
		btnFindName.addActionListener(l);
	}

	
	public JTable getTable() {
		return this.tableProduct;
	}
	
	public void setTable(JTable newTable) {
		this.tableProduct = newTable;
		scrollPane.setViewportView(tableProduct);
		repaint();
		revalidate();
	}
	
	public void printMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
	/**
	 * 
	 * @return Id introduced
	 */
	public int getId() {
		try {
			int num = Integer.parseInt(this.textId.getText());
			return num;
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Wrong format");
			throw new NumberFormatException("not number");
		}
	}
	
	/**
	 * 
	 * @return Name introduced
	 */
	public String getProductName() {
		return this.textProduct.getText();
	}
	/**
	 * 
	 * @return Price introduced
	 */
	public float getPrice() {
		try {
			float num = Float.parseFloat(textPrice.getText());
			return num;
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Wrong format");
			throw new NumberFormatException("not number");
		}
	}
	
	/**
	 * 
	 * @return Stock introduced
	 */
	public int getStock() {
		try {
			int num = Integer.parseInt(this.textStock.getText());
			return num;
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Wrong format");
			throw new NumberFormatException("not number");
		}
	}
	/**
	 * 
	 * @return Minimum price introduced for filtering
	 */
	public float getPriceMin() {
		try {
			float num = Float.parseFloat(textPrice1.getText());
			return num;
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Wrong format");
			throw new NumberFormatException("not number");
		}
	}
	
	/**
	 * 
	 * @return Maximum price introduced for filtering
	 */
	public float getPriceMax() {
		try {
			float num = Float.parseFloat(textPrice2.getText());
			return num;
		}catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Wrong format");
			throw new NumberFormatException("not number");
		}
	}
	
	public String getSearchedName() {
		return this.textField.getText();
	}
	
	public int getSearchedId() {
		try {
			int num = Integer.parseInt(this.textField_id.getText());
			return num;
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Wrong format");
			throw new NumberFormatException("not number");
		}
	}
}
