package Main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import Database.FetchData;
import Database.InsertData;

public class Catalog {
	
	public static void main(String[] args) {
		new Catalog();
	}
	
	private boolean isLogin;
	private int userId;
	JButton loginButton, logoutButton, personalBookListButton;
	FetchData fetchData;
	InsertData insertData;
	
	public Catalog() {
		JFrame catalog = new JFrame("Book Broker Catalog");
		fetchData = new FetchData();
		insertData = new InsertData();
		// Create components
		JLabel header = addHeader();
		JTextField searchField = addSearchField();
		JComboBox<String> sortBox = addSortBox();
		
		//Opens new login screen with username and password field, clicking enter closes the window
		loginButton = addLoginButton();
		logoutButton = addLogoutButton();
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userId = -1;
				isLogin = false;
				logoutButton.setVisible(false);
				loginButton.setVisible(true);
				personalBookListButton.setVisible(false);
			}
		});
		loginButton.addActionListener(popNewLoginWindow());
		
		personalBookListButton = addPersonalListButton();
		personalBookListButton.addActionListener(popNewPersonalBookWindow());
		
		//Adding the books to the main page, each is responsive and opens a new window with details about the book
		List<Book> originList = fetchData.FetchAllWithoutFilter();
		for (int i = 0; i < originList.size() && i < 5; i++) {
			JButton b = new JButton(originList.get(i).getTitle());
			b.setBounds(20, 100 + i*80, 500, 60);
			b.addActionListener(popNewBookWindow(originList.get(i)));
			catalog.add(b);
		}
		
		// Add components to frame
		catalog.add(header);
		catalog.add(searchField);
		catalog.add(sortBox);
		catalog.add(loginButton);
		catalog.add(logoutButton);
		catalog.add(personalBookListButton);
		personalBookListButton.setVisible(false);
		
		catalog.setLayout(null);
		catalog.setSize(550, 600);
		catalog.setVisible(true);
		catalog.setResizable(false);
		catalog.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	private Action popNewBookWindow(Book b) {
		return new AbstractAction("open") {
			public void actionPerformed(ActionEvent e) {
				JFrame bookDetail = new JFrame("Book Details");
				
				JLabel bookTitle = new JLabel("Title: " + b.getTitle());
				bookTitle.setBounds(100,50,400,40);
				bookTitle.setFont(new Font("Helvetica", Font.BOLD, 24));
				
				JLabel author = new JLabel("Author: " + b.getAuthor());
				author.setBounds(100,100,400,40);
				author.setFont(new Font("Helvetica", Font.BOLD, 24));
				
				JLabel date = new JLabel("Publish Date: " + b.getPublishDate());
				date.setBounds(100,150,400,40);
				date.setFont(new Font("Helvetica", Font.BOLD, 24));
				
				String websiteLink = "https://en.wikipedia.org/wiki/" + b.getAuthor().replace(' ', '_');
				JLabel link = new JLabel("Link: " + websiteLink);
				link.setBounds(100,200,400,40);
				link.setFont(new Font("Helvetica", Font.BOLD, 15));
				
				JLabel addedMessage = new JLabel("Added to your list.");
				addedMessage.setBounds(100, 380, 200, 75);
				addedMessage.setFont(new Font("Helvetica", Font.PLAIN, 16));
				addedMessage.setVisible(false);
				
				JButton addToPersonalList = new JButton("Add to your personal list");
				addToPersonalList.setBounds(100, 300, 200, 75);
				addToPersonalList.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						PersonalBookShelf pbs = new PersonalBookShelf(userId, b.getBookId(), 0);
						System.out.println(pbs);
						if(insertData.insertData(pbs, "PersonalBookShelf")) {
							System.out.println("Success");
							addedMessage.setVisible(true);
						}
					}
				});
				
				JButton close = new JButton("Close");
				close.setBounds(420, 10, 100, 40);
				close.addActionListener(new AbstractAction("close") {
					public void actionPerformed(ActionEvent e) {
						bookDetail.dispose();
					}
				});
				if(!isLogin) {
					addToPersonalList.setVisible(false);
				}
				
				bookDetail.add(addedMessage);
				bookDetail.add(addToPersonalList);
				bookDetail.add(bookTitle);
				bookDetail.add(date);
				bookDetail.add(link);
				bookDetail.add(author);
				bookDetail.add(close);
				bookDetail.setLayout(null);
				bookDetail.setSize(550, 600);
				bookDetail.setVisible(true);
				bookDetail.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
			}
		};
	}
	
	private Action popNewLoginWindow() {
		return new AbstractAction("open") {
			public void actionPerformed(ActionEvent e) {
				JFrame loginWindow = new JFrame("Login");
				JLabel loginHeader = addLoginHeader();
				JTextField user = addUserField();
				JTextField pass = addPassField();
				
				JButton loginEnter = addLoginEnterButton();
				JButton loginRegister = addLoginRegisterButton();
				loginEnter.addActionListener(new AbstractAction("close") {
					public void actionPerformed(ActionEvent e) {
						userId = fetchData.verifyPassword(user.getText(), pass.getText());
						loginWindow.dispose();
						if(userId != -1) {
							isLogin = true;
							logoutButton.setVisible(true);
							loginButton.setVisible(false);
							personalBookListButton.setVisible(true);
						} else {
							logoutButton.setVisible(false);
							loginButton.setVisible(true);
							personalBookListButton.setVisible(false);
						}
							
					}
				});
				loginRegister.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {		
						Login l = new Login(-1, user.getText(), "", pass.getText(), "user");
						if(insertData.insertData(l, "Login")) {
							userId = fetchData.verifyPassword(user.getText(), pass.getText());
							isLogin = true;
							logoutButton.setVisible(true);
							loginButton.setVisible(false);
							personalBookListButton.setVisible(true);
							loginWindow.dispose();
						}
					}
				});
				
				
				loginWindow.add(loginHeader);
				loginWindow.add(user);
				loginWindow.add(pass);
				loginWindow.add(loginEnter);
				loginWindow.add(loginRegister);
				
				loginWindow.setLayout(null);
				loginWindow.setSize(300, 300);
				loginWindow.setVisible(true);
				loginWindow.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
			}
		};
	}
	
	private Action popNewPersonalBookWindow() {
		return new AbstractAction("open") {
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
		};
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
		b.setBounds(420, 10, 100, 40);
		return b;
	}
	private JButton addLogoutButton() {
		JButton b = new JButton("Logout");
		b.setBounds(420, 10, 100, 40);
		b.setVisible(false);
		return b;
	}
	private JButton addPersonalListButton() {
		JButton b = new JButton("Personal");
		b.setBounds(300, 10, 100, 40);
		return b;
	}
	private JButton addLoginEnterButton() {
		JButton b = new JButton("Enter");
		b.setBounds(20, 180, 80, 50);
		return b;
	}
	private JButton addLoginRegisterButton() {
		JButton b = new JButton("Register");
		b.setBounds(120, 180, 100, 50);
		return b;
	}
	
}
