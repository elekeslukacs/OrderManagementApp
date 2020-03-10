package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

/**Class that implements a view, from where the user can choose options
 * 
 * @author Elekes Lukacs
 *
 */

public class View extends JFrame {

	private JPanel contentPane;
	private JButton btnProducts;
	private JButton btnClients;
	private JButton btnPlaceAnOrder;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					View frame = new View();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public View() {
		setTitle("Order Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnProducts = new JButton("Products");
		btnProducts.setActionCommand("openproducts");
		btnProducts.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnProducts.setBounds(178, 132, 183, 76);
		contentPane.add(btnProducts);
		
		btnClients = new JButton("Clients");
		btnClients.setActionCommand("openclients");
		btnClients.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnClients.setBounds(178, 252, 183, 76);
		contentPane.add(btnClients);
		
		btnPlaceAnOrder = new JButton("Place an Order");
		btnPlaceAnOrder.setActionCommand("placeorder");
		btnPlaceAnOrder.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnPlaceAnOrder.setBounds(178, 377, 183, 81);
		contentPane.add(btnPlaceAnOrder);
		
		JLabel lblSelectYourOption = new JLabel("Select Your Option");
		lblSelectYourOption.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblSelectYourOption.setBounds(171, 27, 251, 59);
		contentPane.add(lblSelectYourOption);
		this.setVisible(true);
	}
	
	/**
	 * Adds listeners to the buttons
	 * @param l - ActionListener
	 */
	public void addListener(ActionListener l) {
		btnProducts.addActionListener(l);
		btnClients.addActionListener(l);
		btnPlaceAnOrder.addActionListener(l);
	}
}
