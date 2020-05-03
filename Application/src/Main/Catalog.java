package Main;

import java.awt.Font;
import javax.swing.*;

public class Catalog {
	
	public static void main(String[] args) {
		new Catalog();
	}
	
	Catalog() {
		JFrame catalog = new JFrame("Book Broker Catalog");
		// Create components
		JLabel header = addHeader();
		JTextField searchField = addSearchField();
		JComboBox<String> sortBox = addSortBox();
		JButton loginButton = addLoginButton();
		// Add components to frame
		catalog.add(header);
		catalog.add(searchField);
		catalog.add(sortBox);
		catalog.add(loginButton);
		
		
		catalog.setLayout(null);
		catalog.setSize(500,700);
		catalog.setVisible(true);
		catalog.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	private JLabel addHeader() {
		JLabel l = new JLabel("Book Broker");
		l.setBounds(10,10,150,20);
		l.setFont(new Font("Helvetica", Font.BOLD, 24));
		return l;
	}
	
	private JTextField addSearchField() {
		JTextField tf = new JTextField("Search...");
		tf.setBounds(10,50,150,40);
		return tf;
	}
	
	private JComboBox<String> addSortBox() {
		String sortOptions[]  = {"Title A-Z", "Title Z-A", "Date Published"};
		JComboBox<String> cb = new JComboBox<String>(sortOptions);
		cb.setBounds(170, 50, 100, 39);
		return cb;
	}

	
	private JButton addLoginButton() {
		JButton b = new JButton("Login");
		b.setBounds(380, 5, 100, 40);
		return b;
	}
}
