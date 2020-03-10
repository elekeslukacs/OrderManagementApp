package presentation;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;

/**
 * Class that represents the view of the Clients window
 * @author Elekes Lukacs
 *
 */

public class ViewClient extends JFrame {

	private JPanel contentPane;
	private JTextField textId;
	private JTextField textName;
	private JTextField textEmail;
	private JTextField textPhone;
	private JTable tableClient;
	private JTextField textEmailSearch;
	private JTextField textField;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnList;
	private JButton btnFindEmail;
	private JButton btnFindPhone;
	private JScrollPane scrollPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnFindId;
	private JButton btnFindName;
	

	/**
	 * Create the frame.
	 */
	public ViewClient() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 995, 656);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblName.setBounds(22, 142, 81, 45);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblEmail.setBounds(22, 212, 81, 36);
		contentPane.add(lblEmail);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblId.setBounds(22, 96, 56, 16);
		contentPane.add(lblId);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblPhone.setBounds(22, 273, 81, 36);
		contentPane.add(lblPhone);
		
		textId = new JTextField();
		textId.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textId.setBounds(87, 96, 88, 22);
		contentPane.add(textId);
		textId.setColumns(10);
		
		textName = new JTextField();
		textName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textName.setColumns(10);
		textName.setBounds(87, 156, 176, 22);
		contentPane.add(textName);
		
		textEmail = new JTextField();
		textEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textEmail.setColumns(10);
		textEmail.setBounds(87, 220, 176, 22);
		contentPane.add(textEmail);
		
		textPhone = new JTextField();
		textPhone.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textPhone.setColumns(10);
		textPhone.setBounds(87, 281, 176, 22);
		contentPane.add(textPhone);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(415, 85, 550, 342);
		contentPane.add(scrollPane);
		
		btnInsert = new JButton("Insert");
		btnInsert.setActionCommand("insertclient"); 
		btnInsert.setBounds(78, 526, 117, 45);
		contentPane.add(btnInsert);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setActionCommand("updateclient");
		btnUpdate.setBounds(255, 526, 111, 45);
		contentPane.add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.setActionCommand("deleteclient");
		btnDelete.setBounds(445, 526, 111, 45);
		contentPane.add(btnDelete);
		
		btnList = new JButton("List");
		btnList.setActionCommand("listclient");
		btnList.setBounds(649, 526, 117, 45);
		contentPane.add(btnList);
		
		JLabel lblFindBy = new JLabel("Find by:");
		lblFindBy.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblFindBy.setBounds(22, 344, 117, 27);
		contentPane.add(lblFindBy);
		
		JLabel lblEmail_1 = new JLabel("Email:");
		lblEmail_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblEmail_1.setBounds(57, 384, 64, 22);
		contentPane.add(lblEmail_1);
		
		JLabel lblPhonelast = new JLabel("Phone (last 3 digits):");
		lblPhonelast.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPhonelast.setBounds(57, 419, 156, 27);
		contentPane.add(lblPhonelast);
		
		textEmailSearch = new JTextField();
		textEmailSearch.setBounds(126, 384, 163, 22);
		contentPane.add(textEmailSearch);
		textEmailSearch.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(225, 422, 64, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnFindEmail = new JButton("Find Email");
		btnFindEmail.setActionCommand("findemail");
		btnFindEmail.setBounds(306, 385, 97, 25);
		contentPane.add(btnFindEmail);
		
		btnFindPhone = new JButton("Find Phone");
		btnFindPhone.setActionCommand("findphone");
		btnFindPhone.setBounds(306, 421, 97, 25);
		contentPane.add(btnFindPhone);
		
		JLabel lblFindById = new JLabel("ID:");
		lblFindById.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblFindById.setBounds(57, 459, 56, 16);
		contentPane.add(lblFindById);
		
		JLabel lblName_1 = new JLabel("Name:");
		lblName_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblName_1.setBounds(57, 488, 56, 16);
		contentPane.add(lblName_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(101, 458, 64, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(126, 487, 116, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		btnFindId = new JButton("Find ID");
		btnFindId.setActionCommand("findidclient");
		btnFindId.setBounds(306, 457, 97, 25);
		contentPane.add(btnFindId);
		
		btnFindName = new JButton("Find Name");
		btnFindName.setActionCommand("findnameclient");
		btnFindName.setBounds(306, 488, 97, 25);
		contentPane.add(btnFindName);
	}
	
	/**
	 * Adds actionlisteners to the buttons
	 * @param l - actionlistener
	 */
	public void addListeners(ActionListener l) {
		btnInsert.addActionListener(l);
		btnUpdate.addActionListener(l);
		btnDelete.addActionListener(l);
		btnList.addActionListener(l);
		btnFindEmail.addActionListener(l);
		btnFindPhone.addActionListener(l);
		btnFindId.addActionListener(l);
		btnFindName.addActionListener(l);
	}
	
	/**
	 * 
	 * @return Table of clients.
	 */
	public JTable getTable() {
		return this.tableClient;
	}
	
	/**
	 * Sets the table of clients
	 * @param newTable - table which will be set to table of clients
	 */
	public void setTable(JTable newTable) {
		this.tableClient = newTable;
		scrollPane.setViewportView(tableClient);
		revalidate();
		repaint();
	}
	
	/**
	 * Displays a message as a dialog 
	 * @param message - Message to be displayed
	 */
	public void printMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
	/**
	 * Gets the id introduced
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
	 * @return Name introduced
	 */
	public String getName() {
		return this.textName.getText();
	}
	
	/**
	 * 
	 * @return Email introduced
	 */
	public String getEmail() {
		return this.textEmail.getText();
	}
	/**
	 * 
	 * @return Phone number introduced
	 */
	public String getPhone() {
		return this.textPhone.getText();
	}
	/**
	 * 
	 * @return Searched email introduced
	 */
	public String getSearchedEmail() {
		return this.textEmailSearch.getText();
	}
	/**
	 * 
	 * @return Searched phone introduced
	 */
	public String getSearchedPhone() {
		return this.textField.getText();
	}
	
	public String getSearchedName() {
		return this.textField_2.getText();
	}
	
	public int getSearchedId() {
		try {
			int num = Integer.parseInt(this.textField_1.getText());
			return num;
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Wrong format");
			throw new NumberFormatException("not number");
		}
	}
}
