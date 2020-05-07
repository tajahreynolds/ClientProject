package Main;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Catalog {
	
	private String test = "This is a test";
	public static void main(String[] args) {
		new Catalog();
	}
	
	Catalog() {
		JFrame catalog = new JFrame("Book Broker Catalog");
		// Create components
		JLabel header = addHeader();
		JTextField searchField = addSearchField();
		JComboBox<String> sortBox = addSortBox();
		
		//Opens new login screen with username and password field, clicking enter closes the window
		JButton loginButton = addLoginButton();
		loginButton.addActionListener(new AbstractAction("open") {
			public void actionPerformed(ActionEvent e) {
				JFrame loginWindow = new JFrame("Login");
				JLabel loginHeader = addLoginHeader();
				JTextField user = addUserField();
				JTextField pass = addPassField();
				
				JButton loginEnter = addLoginEnterButton();
				loginEnter.addActionListener(new AbstractAction("close") {
					public void actionPerformed(ActionEvent e) {
						loginButton.setText("User");
						loginWindow.dispose();
					}
				});
				
				loginWindow.add(loginHeader);
				loginWindow.add(user);
				loginWindow.add(pass);
				loginWindow.add(loginEnter);
				
				loginWindow.setLayout(null);
				loginWindow.setSize(300, 300);
				loginWindow.setVisible(true);
				loginWindow.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
			}
		});
		
		JButton personalBookListButton = addPersonalListButton();
		personalBookListButton.addActionListener(new AbstractAction("open") {
			public void actionPerformed(ActionEvent e) {
				JFrame personalBookList = new JFrame("Your Book List");
				JButton close = new JButton("Close");
				JLabel personalListHeader = new JLabel("Your Book List");
				personalListHeader.setBounds(20, 20, 200, 20);
				personalListHeader.setFont(new Font("Helvetica", Font.BOLD, 24));
				
				close.setBounds(620, 10, 100, 40);
				close.addActionListener(new AbstractAction("close") {
					public void actionPerformed(ActionEvent e) {
						personalBookList.dispose();
					}
				});
				
				JButton savedBookOne = new JButton("Book");
				savedBookOne.setBounds(10, 100, 300, 180);
				savedBookOne.addActionListener(new AbstractAction("open") {
					public void actionPerformed(ActionEvent e) {
						JFrame savedBookOneDetails = new JFrame("Book Details");
						
						JLabel bookTitle = new JLabel("Title");
						bookTitle.setBounds(100,50,150,40);
						bookTitle.setFont(new Font("Helvetica", Font.BOLD, 24));
						
						JLabel bookDescription = new JLabel("Description...");
						bookDescription.setBounds(100, 100, 150, 350);
						bookDescription.setFont(new Font("Helvetica", Font.PLAIN, 16));
						
						JLabel addedMessage = new JLabel("Added to your list.");
						addedMessage.setBounds(310, 600, 200, 75);
						addedMessage.setFont(new Font("Helvetica", Font.PLAIN, 16));
						addedMessage.setVisible(false);
						
						JButton addToPersonalList = new JButton("Add to your personal list");
						addToPersonalList.setBounds(275, 550, 200, 75);
						addToPersonalList.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								addedMessage.setVisible(true);
							}
						});
						
						JButton close = new JButton("Close");
						close.setBounds(620, 10, 100, 40);
						close.addActionListener(new AbstractAction("close") {
							public void actionPerformed(ActionEvent e) {
								savedBookOneDetails.dispose();
							}
						});
						
						savedBookOneDetails.add(addedMessage);
						savedBookOneDetails.add(addToPersonalList);
						savedBookOneDetails.add(bookTitle);
						savedBookOneDetails.add(bookDescription);
						savedBookOneDetails.add(close);
						savedBookOneDetails.setLayout(null);
						savedBookOneDetails.setSize(750, 750);
						savedBookOneDetails.setVisible(true);
						savedBookOneDetails.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
					}
				});
				
				JButton savedBookTwo = new JButton("Book");
				savedBookTwo.setBounds(10, 300, 300, 180);
				savedBookTwo.addActionListener(new AbstractAction("open") {
					public void actionPerformed(ActionEvent e) {
						JFrame savedBookTwoDetails = new JFrame("Book Details");
						
						JLabel bookTitle = new JLabel("Title");
						bookTitle.setBounds(100,50,150,40);
						bookTitle.setFont(new Font("Helvetica", Font.BOLD, 24));
						
						JLabel bookDescription = new JLabel("Description...");
						bookDescription.setBounds(100, 100, 150, 350);
						bookDescription.setFont(new Font("Helvetica", Font.PLAIN, 16));
						
						JLabel addedMessage = new JLabel("Added to your list.");
						addedMessage.setBounds(310, 600, 200, 75);
						addedMessage.setFont(new Font("Helvetica", Font.PLAIN, 16));
						addedMessage.setVisible(false);
						
						JButton addToPersonalList = new JButton("Add to your personal list");
						addToPersonalList.setBounds(275, 550, 200, 75);
						addToPersonalList.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								addedMessage.setVisible(true);
							}
						});
						
						JButton close = new JButton("Close");
						close.setBounds(620, 10, 100, 40);
						close.addActionListener(new AbstractAction("close") {
							public void actionPerformed(ActionEvent e) {
								savedBookTwoDetails.dispose();
							}
						});
						
						savedBookTwoDetails.add(addedMessage);
						savedBookTwoDetails.add(addToPersonalList);
						savedBookTwoDetails.add(bookTitle);
						savedBookTwoDetails.add(bookDescription);
						savedBookTwoDetails.add(close);
						savedBookTwoDetails.setLayout(null);
						savedBookTwoDetails.setSize(750, 750);
						savedBookTwoDetails.setVisible(true);
						savedBookTwoDetails.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
					}
				});
				
				JButton savedBookThree = new JButton("Book");
				savedBookThree.setBounds(10, 500, 300, 180);
				savedBookThree.addActionListener(new AbstractAction("open") {
					public void actionPerformed(ActionEvent e) {
						JFrame savedBookThreeDetails = new JFrame("Book Details");
						
						JLabel bookTitle = new JLabel("Title");
						bookTitle.setBounds(100,50,150,40);
						bookTitle.setFont(new Font("Helvetica", Font.BOLD, 24));
						
						JLabel bookDescription = new JLabel("Description...");
						bookDescription.setBounds(100, 100, 150, 350);
						bookDescription.setFont(new Font("Helvetica", Font.PLAIN, 16));
						
						JLabel addedMessage = new JLabel("Added to your list.");
						addedMessage.setBounds(310, 600, 200, 75);
						addedMessage.setFont(new Font("Helvetica", Font.PLAIN, 16));
						addedMessage.setVisible(false);
						
						JButton addToPersonalList = new JButton("Add to your personal list");
						addToPersonalList.setBounds(275, 550, 200, 75);
						addToPersonalList.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								addedMessage.setVisible(true);
							}
						});
						
						JButton close = new JButton("Close");
						close.setBounds(620, 10, 100, 40);
						close.addActionListener(new AbstractAction("close") {
							public void actionPerformed(ActionEvent e) {
								savedBookThreeDetails.dispose();
							}
						});
						
						savedBookThreeDetails.add(addedMessage);
						savedBookThreeDetails.add(addToPersonalList);
						savedBookThreeDetails.add(bookTitle);
						savedBookThreeDetails.add(bookDescription);
						savedBookThreeDetails.add(close);
						savedBookThreeDetails.setLayout(null);
						savedBookThreeDetails.setSize(750, 750);
						savedBookThreeDetails.setVisible(true);
						savedBookThreeDetails.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
					}
				});
				
				personalBookList.add(savedBookOne);
				personalBookList.add(savedBookTwo);
				personalBookList.add(savedBookThree);
				personalBookList.add(personalListHeader);
				personalBookList.add(close);
				personalBookList.setLayout(null);
				personalBookList.setSize(750, 750);
				personalBookList.setVisible(true);
				personalBookList.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
			}
		});
		
		//Adding the books to the main page, each is responsive and opens a new window with details about the book
		JButton book1 = new JButton("Book");
		book1.setBounds(10, 100, 300, 180);
		book1.addActionListener(new AbstractAction("open") {
			public void actionPerformed(ActionEvent e) {
				JFrame bookOneDetails = new JFrame("Book Details");
				
				JLabel bookTitle = new JLabel("Title");
				bookTitle.setBounds(100,50,150,40);
				bookTitle.setFont(new Font("Helvetica", Font.BOLD, 24));
				
				JLabel bookDescription = new JLabel(test);
				bookDescription.setBounds(100, 100, 150, 350);
				bookDescription.setFont(new Font("Helvetica", Font.PLAIN, 16));
				
				JLabel addedMessage = new JLabel("Added to your list.");
				addedMessage.setBounds(310, 600, 200, 75);
				addedMessage.setFont(new Font("Helvetica", Font.PLAIN, 16));
				addedMessage.setVisible(false);
				
				JButton addToPersonalList = new JButton("Add to your personal list");
				addToPersonalList.setBounds(275, 550, 200, 75);
				addToPersonalList.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addedMessage.setVisible(true);
					}
				});
				
				JButton close = new JButton("Close");
				close.setBounds(620, 10, 100, 40);
				close.addActionListener(new AbstractAction("close") {
					public void actionPerformed(ActionEvent e) {
						bookOneDetails.dispose();
					}
				});
				
				bookOneDetails.add(addedMessage);
				bookOneDetails.add(addToPersonalList);
				bookOneDetails.add(bookTitle);
				bookOneDetails.add(bookDescription);
				bookOneDetails.add(close);
				bookOneDetails.setLayout(null);
				bookOneDetails.setSize(750, 750);
				bookOneDetails.setVisible(true);
				bookOneDetails.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
			}
		});
		
		JButton book2 = new JButton("Book");
		book2.setBounds(10, 300, 300, 180);
		book2.addActionListener(new AbstractAction("open") {
			public void actionPerformed(ActionEvent e) {
				JFrame bookTwoDetails = new JFrame("Book Details");
				
				JLabel bookTitle = new JLabel("Title");
				bookTitle.setBounds(100,50,150,40);
				bookTitle.setFont(new Font("Helvetica", Font.BOLD, 24));
				
				JLabel bookDescription = new JLabel("Description...");
				bookDescription.setBounds(100, 100, 150, 350);
				bookDescription.setFont(new Font("Helvetica", Font.PLAIN, 16));
				
				JLabel addedMessage = new JLabel("Added to your list.");
				addedMessage.setBounds(310, 600, 200, 75);
				addedMessage.setFont(new Font("Helvetica", Font.PLAIN, 16));
				addedMessage.setVisible(false);
				
				JButton addToPersonalList = new JButton("Add to your personal list");
				addToPersonalList.setBounds(275, 550, 200, 75);
				addToPersonalList.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addedMessage.setVisible(true);
					}
				});
				
				JButton close = new JButton("Close");
				close.setBounds(620, 10, 100, 40);
				close.addActionListener(new AbstractAction("close") {
					public void actionPerformed(ActionEvent e) {
						bookTwoDetails.dispose();
					}
				});
				
				bookTwoDetails.add(addedMessage);
				bookTwoDetails.add(addToPersonalList);
				bookTwoDetails.add(bookTitle);
				bookTwoDetails.add(bookDescription);
				bookTwoDetails.add(close);
				bookTwoDetails.setLayout(null);
				bookTwoDetails.setSize(750, 750);
				bookTwoDetails.setVisible(true);
				bookTwoDetails.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
			}
		});
		
		JButton book3 = new JButton("Book");
		book3.setBounds(10, 500, 300, 180);
		book3.addActionListener(new AbstractAction("open") {
			public void actionPerformed(ActionEvent e) {
				JFrame bookThreeDetails = new JFrame("Book Details");
				
				JLabel bookTitle = new JLabel("Title");
				bookTitle.setBounds(100,50,150,40);
				bookTitle.setFont(new Font("Helvetica", Font.BOLD, 24));
				
				JLabel bookDescription = new JLabel("Description...");
				bookDescription.setBounds(100, 100, 150, 350);
				bookDescription.setFont(new Font("Helvetica", Font.PLAIN, 16));
				
				JLabel addedMessage = new JLabel("Added to your list.");
				addedMessage.setBounds(310, 600, 200, 75);
				addedMessage.setFont(new Font("Helvetica", Font.PLAIN, 16));
				addedMessage.setVisible(false);
				
				JButton addToPersonalList = new JButton("Add to your personal list");
				addToPersonalList.setBounds(275, 550, 200, 75);
				addToPersonalList.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addedMessage.setVisible(true);
					}
				});
				
				JButton close = new JButton("Close");
				close.setBounds(620, 10, 100, 40);
				close.addActionListener(new AbstractAction("close") {
					public void actionPerformed(ActionEvent e) {
						bookThreeDetails.dispose();
					}
				});
				
				bookThreeDetails.add(addedMessage);
				bookThreeDetails.add(addToPersonalList);
				bookThreeDetails.add(bookTitle);
				bookThreeDetails.add(bookDescription);
				bookThreeDetails.add(close);
				bookThreeDetails.setLayout(null);
				bookThreeDetails.setSize(750, 750);
				bookThreeDetails.setVisible(true);
				bookThreeDetails.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
			}
		});
		
		JButton book4 = new JButton("Book");
		book4.setBounds(350, 100, 300, 180);
		book4.addActionListener(new AbstractAction("open") {
			public void actionPerformed(ActionEvent e) {
				JFrame bookFourDetails = new JFrame("Book Details");
				
				JLabel bookTitle = new JLabel("Title");
				bookTitle.setBounds(100,50,150,40);
				bookTitle.setFont(new Font("Helvetica", Font.BOLD, 24));
				
				JLabel bookDescription = new JLabel("Description...");
				bookDescription.setBounds(100, 100, 150, 350);
				bookDescription.setFont(new Font("Helvetica", Font.PLAIN, 16));
				
				JLabel addedMessage = new JLabel("Added to your list.");
				addedMessage.setBounds(310, 600, 200, 75);
				addedMessage.setFont(new Font("Helvetica", Font.PLAIN, 16));
				addedMessage.setVisible(false);
				
				JButton addToPersonalList = new JButton("Add to your personal list");
				addToPersonalList.setBounds(275, 550, 200, 75);
				addToPersonalList.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addedMessage.setVisible(true);
					}
				});
				
				JButton close = new JButton("Close");
				close.setBounds(620, 10, 100, 40);
				close.addActionListener(new AbstractAction("close") {
					public void actionPerformed(ActionEvent e) {
						bookFourDetails.dispose();
					}
				});
				
				bookFourDetails.add(addedMessage);
				bookFourDetails.add(addToPersonalList);
				bookFourDetails.add(bookTitle);
				bookFourDetails.add(bookDescription);
				bookFourDetails.add(close);
				bookFourDetails.setLayout(null);
				bookFourDetails.setSize(750, 750);
				bookFourDetails.setVisible(true);
				bookFourDetails.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
			}
		});
		
		JButton book5 = new JButton("Book");
		book5.setBounds(350, 300, 300, 180);
		book5.addActionListener(new AbstractAction("open") {
			public void actionPerformed(ActionEvent e) {
				JFrame bookFiveDetails = new JFrame("Book Details");
				
				JLabel bookTitle = new JLabel("Title");
				bookTitle.setBounds(100,50,150,40);
				bookTitle.setFont(new Font("Helvetica", Font.BOLD, 24));
				
				JLabel bookDescription = new JLabel("Description...");
				bookDescription.setBounds(100, 100, 150, 350);
				bookDescription.setFont(new Font("Helvetica", Font.PLAIN, 16));
				
				JLabel addedMessage = new JLabel("Added to your list.");
				addedMessage.setBounds(310, 600, 200, 75);
				addedMessage.setFont(new Font("Helvetica", Font.PLAIN, 16));
				addedMessage.setVisible(false);
				
				JButton addToPersonalList = new JButton("Add to your personal list");
				addToPersonalList.setBounds(275, 550, 200, 75);
				addToPersonalList.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addedMessage.setVisible(true);
					}
				});
				
				JButton close = new JButton("Close");
				close.setBounds(620, 10, 100, 40);
				close.addActionListener(new AbstractAction("close") {
					public void actionPerformed(ActionEvent e) {
						bookFiveDetails.dispose();
					}
				});
				
				bookFiveDetails.add(addedMessage);
				bookFiveDetails.add(addToPersonalList);
				bookFiveDetails.add(bookTitle);
				bookFiveDetails.add(bookDescription);
				bookFiveDetails.add(close);
				bookFiveDetails.setLayout(null);
				bookFiveDetails.setSize(750, 750);
				bookFiveDetails.setVisible(true);
				bookFiveDetails.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
			}
		});
		
		JButton book6 = new JButton("Book");
		book6.setBounds(350, 500, 300, 180);
		book6.addActionListener(new AbstractAction("open") {
			public void actionPerformed(ActionEvent e) {
				JFrame bookSixDetails = new JFrame("Book Details");
				
				JLabel bookTitle = new JLabel("Title");
				bookTitle.setBounds(100,50,150,40);
				bookTitle.setFont(new Font("Helvetica", Font.BOLD, 24));
				
				JLabel bookDescription = new JLabel("Description...");
				bookDescription.setBounds(100, 100, 150, 350);
				bookDescription.setFont(new Font("Helvetica", Font.PLAIN, 16));
				
				JLabel addedMessage = new JLabel("Added to your list.");
				addedMessage.setBounds(310, 600, 200, 75);
				addedMessage.setFont(new Font("Helvetica", Font.PLAIN, 16));
				addedMessage.setVisible(false);
				
				JButton addToPersonalList = new JButton("Add to your personal list");
				addToPersonalList.setBounds(275, 550, 200, 75);
				addToPersonalList.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addedMessage.setVisible(true);
					}
				});
				
				JButton close = new JButton("Close");
				close.setBounds(620, 10, 100, 40);
				close.addActionListener(new AbstractAction("close") {
					public void actionPerformed(ActionEvent e) {
						bookSixDetails.dispose();
					}
				});
				
				bookSixDetails.add(addedMessage);
				bookSixDetails.add(addToPersonalList);
				bookSixDetails.add(bookTitle);
				bookSixDetails.add(bookDescription);
				bookSixDetails.add(close);
				bookSixDetails.setLayout(null);
				bookSixDetails.setSize(750, 750);
				bookSixDetails.setVisible(true);
				bookSixDetails.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
			}
		});

		// Add components to frame
		catalog.add(header);
		catalog.add(searchField);
		catalog.add(sortBox);
		catalog.add(loginButton);
		catalog.add(personalBookListButton);
		catalog.add(book1);
		catalog.add(book2);
		catalog.add(book3);
		catalog.add(book4);
		catalog.add(book5);
		catalog.add(book6);
		
		catalog.setLayout(null);
		catalog.setSize(750, 750);
		catalog.setVisible(true);
		catalog.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	private JLabel addHeader() {
		JLabel l = new JLabel("Book Broker");
		l.setBounds(10,10,150,20);
		l.setFont(new Font("Helvetica", Font.BOLD, 24));
		return l;
	}
	private JLabel addLoginHeader() {
		JLabel l = new JLabel("User Login");
		l.setBounds(20,20,250,40);
		l.setFont(new Font("Helvetica", Font.BOLD, 24));
		return l;
	}
	
	private JTextField addSearchField() {
		JTextField tf = new JTextField("Search...");
		tf.setBounds(10,50,150,40);
		return tf;
	}
	private JTextField addUserField() {
		JTextField tf = new JTextField("Username");
		tf.setBounds(20,75,200,40);
		return tf;
	}
	private JTextField addPassField() {
		JTextField tf = new JTextField("Password");
		tf.setBounds(20,125,200,40);
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
		b.setBounds(620, 10, 100, 40);
		return b;
	}
	private JButton addPersonalListButton() {
		JButton b = new JButton("Personal List");
		b.setBounds(500, 10, 100, 40);
		return b;
	}
	private JButton addLoginEnterButton() {
		JButton b = new JButton("Enter");
		b.setBounds(100, 180, 80, 50);
		return b;
	}
	
	
//	private JButton addBook1() {
//		JButton b = new JButton("Book");
//		b.setBounds(10, 100, 300, 180);
//		return b;
//	}
//	private JButton addBook2() {
//		JButton b = new JButton("Book");
//		b.setBounds(10, 300, 300, 180);
//		return b;
//	}
//	private JButton addBook3() {
//		JButton b = new JButton("Book");
//		b.setBounds(10, 500, 300, 180);
//		return b;
//	}
//	private JButton addBook4() {
//		JButton b = new JButton("Book");
//		b.setBounds(350, 100, 300, 180);
//		return b;
//	}
//	private JButton addBook5() {
//		JButton b = new JButton("Book");
//		b.setBounds(350, 300, 300, 180);
//		return b;
//	}
//	private JButton addBook6() {
//		JButton b = new JButton("Book");
//		b.setBounds(350, 500, 300, 180);
//		return b;
//	}
}