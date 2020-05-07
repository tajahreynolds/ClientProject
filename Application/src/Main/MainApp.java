package Main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import javax.swing.*;

import Database.FetchData;
import Objects.Book;
import Objects.Login;
import Objects.PersonalBookShelf;

public class MainApp {

	public static void main(String[] args) {
		new MainApp();
	}

	boolean isLogin;
	int userId;
	String userType;
	FetchData fetchData;
	JButton loginButton, logoutButton, personalBookListButton, newBookRequestButton;
	JLabel loginFail;
	JFrame mainWindow;
	List<Book> originList;
	boolean loadedList = false, filtered = false;

	public MainApp() {
		loadMainApp();
	}

	private void loadMainApp() {
		mainWindow = new JFrame("Book Broker");
		if(fetchData == null)
			fetchData = new FetchData();
		// Create components
		JLabel header = addHeader();
		JTextField searchField = addSearchField();
		JComboBox<String> sortBox = addSortBox();
		JComboBox<String> filterBox = addFilterBox();

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
				newBookRequestButton.setVisible(false);
			}
		});
		loginButton.addActionListener(popNewLoginWindow());

		loginFail = addLoginFailed();
		personalBookListButton = addPersonalListButton();
		personalBookListButton.addActionListener(popNewPersonalBookWindow());

		newBookRequestButton = addRequestNewBookButton();
		newBookRequestButton.addActionListener(popNewRequestWindow());

		//Adding the books to the main page, each is responsive and opens a new window with details about the book
		if (!loadedList) {
			originList = fetchData.FetchAllWithoutFilter();
			loadedList = true;
		}
		if (filtered) {
			//TODO
		}
		if(originList != null)
			for (int i = 0; i < originList.size() && i < 5; i++) {
				JButton b = new JButton(originList.get(i).getTitle());
				b.setBounds(10, 100 + i*80, 510, 60);
				b.addActionListener(popNewBookWindow(originList.get(i)));
				mainWindow.add(b);
			}

		// Add components to frame
		mainWindow.add(header);
		mainWindow.add(searchField);
		mainWindow.add(sortBox);
		mainWindow.add(filterBox);
		mainWindow.add(loginButton);
		mainWindow.add(logoutButton);
		mainWindow.add(personalBookListButton);
		mainWindow.add(newBookRequestButton);
		mainWindow.add(loginFail);
		loginFail.setVisible(false);

		personalBookListButton.setVisible(false);
		newBookRequestButton.setVisible(false);
		if(userType != null) {
			isLogin = true;
			logoutButton.setVisible(true);
			loginButton.setVisible(false);
			personalBookListButton.setVisible(true);
			newBookRequestButton.setVisible(true);
			loginFail.setVisible(false);
		}
		mainWindow.setLayout(null);
		mainWindow.setSize(550, 600);
		mainWindow.setVisible(true);
		mainWindow.setResizable(false);
		mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	private void reloadList() {
		originList = fetchData.FetchAllWithoutFilter();
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

				JLabel genre = new JLabel("Genre: " + b.getCatalog());
				genre.setBounds(100,200,400,40);
				genre.setFont(new Font("Helvetica", Font.BOLD, 24));

				String websiteLink = "https://en.wikipedia.org/wiki/" + b.getAuthor().replace(' ', '_');
				JLabel link = new JLabel("Link: " + websiteLink);
				link.setBounds(100,250,400,40);
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
						if(fetchData.insertData(pbs, "PersonalBookShelf")) {
							addedMessage.setVisible(true);
						}
					}
				});

				JButton removeBookFromList = new JButton("remove");
				removeBookFromList.setBounds(310, 300, 150, 75);
				removeBookFromList.setVisible(false);
				if(isLogin == true && userType != null && userType.equals("admin")) {
					removeBookFromList.setVisible(true);
				}
				removeBookFromList.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						fetchData.RemoveBook(b.getBookId());
						mainWindow.dispose();
						bookDetail.dispose();
						reloadList();
						loadMainApp();
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
				bookDetail.add(genre);
				bookDetail.add(link);
				bookDetail.add(author);
				bookDetail.add(close);
				bookDetail.add(removeBookFromList);
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
						userType = fetchData.getUserType(user.getText(), pass.getText());
						loginWindow.dispose();
						if(userId != -1) {
							isLogin = true;
							logoutButton.setVisible(true);
							loginButton.setVisible(false);
							personalBookListButton.setVisible(true);
							newBookRequestButton.setVisible(true);
							loginFail.setVisible(false);
						} else {
							logoutButton.setVisible(false);
							loginButton.setVisible(true);
							personalBookListButton.setVisible(false);
							newBookRequestButton.setVisible(false);
							loginFail.setVisible(true);
						}

					}
				});
				loginRegister.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {		
						Login l = new Login(-1, user.getText(), "", pass.getText(), "user");
						if(fetchData.insertData(l, "Login")) {
							userId = fetchData.verifyPassword(user.getText(), pass.getText());
							isLogin = true;
							logoutButton.setVisible(true);
							loginButton.setVisible(false);
							newBookRequestButton.setVisible(true);
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

				close.setBounds(420, 10, 100, 40);
				close.addActionListener(new AbstractAction() {
					public void actionPerformed(ActionEvent e) {
						personalBookList.dispose();
					}
				});
				List<Book> userBookList = fetchData.FetchByUserId(userId);
				if(userBookList != null) {
					for (int i = 0; i < userBookList.size() && i < 5; i++) {
						JButton b = new JButton(userBookList.get(i).getTitle());
						b.setBounds(20, 100 + i*60, 270, 40);
						b.addActionListener(popNewBookWindow(userBookList.get(i)));
						int bookId = userBookList.get(i).getBookId();
						JButton delete = new JButton("Delete");
						delete.setBounds(330, 100 + i*60, 100, 40);
						delete.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								fetchData.RemoveFromPersonalBookshelf(userId, bookId);
								personalBookList.dispose();
							}
						});

						JLabel readed = new JLabel("Book Read");
						readed.setBounds(440, 100 + i*60, 80, 40);
						JButton read = new JButton("Read");
						read.setBounds(440, 100 + i*60, 80, 40);
						if(fetchData.IfBookReaded(userId, bookId)) {
							read.setVisible(false);
						} else {
							readed.setVisible(false);
						}
						read.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								fetchData.MarkBookAsRead(userId, bookId);
								readed.setVisible(true);
								read.setVisible(false);
							}
						});


						personalBookList.add(b);
						personalBookList.add(delete);
						personalBookList.add(read);
						personalBookList.add(readed);
					}
				}

				personalBookList.add(personalListHeader);
				personalBookList.add(close);
				personalBookList.setLayout(null);
				personalBookList.setSize(550, 600);
				personalBookList.setVisible(true);
				personalBookList.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
			}
		};
	}

	private Action popNewRequestWindow() {
		return new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame form = new JFrame("New Book Request");
				JButton close = new JButton("Close");
				JLabel formHeader = new JLabel("New Book Request");
				formHeader.setBounds(20, 20, 300, 30);
				formHeader.setFont(new Font("Helvetica", Font.BOLD, 24));

				close.setBounds(420, 10, 100, 40);
				close.addActionListener(new AbstractAction() {
					public void actionPerformed(ActionEvent e) {
						form.dispose();
					}
				});

				JTextField ja1 = new JTextField("Title:");
				ja1.setBounds(20, 80, 300, 40);
				JTextField ja2 = new JTextField("Author:");
				ja2.setBounds(20, 130, 300, 40);
				JTextField ja3 = new JTextField("Publish Date:");
				ja3.setBounds(20, 180, 300, 40);
				JTextField ja4 = new JTextField("Genre:");
				ja4.setBounds(20, 230, 300, 40);

				JButton b = new JButton("Push");
				b.setBounds(20, 350, 150, 40);
				b.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Book b = new Book(-1, ja4.getText(), ja1.getText(), ja2.getText(), ja3.getText(), -1);
						if(userType.equals("user")) {
							addToWaitlist(b);
						} else if(userType.equals("admin")) {
							fetchData.insertData(b, "Book");
							mainWindow.dispose();
							reloadList();
							loadMainApp();
						}
						form.dispose();
					}
				});

				form.add(close);
				form.add(formHeader);
				form.add(ja1);
				form.add(ja2);
				form.add(ja3);
				form.add(ja4);
				form.add(b);
				form.setLayout(null);
				form.setSize(550, 600);
				form.setVisible(true);
				form.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
			}
		};
	}

	private void addToWaitlist(Book b) {
		try {
			PrintWriter pw = new PrintWriter(new File("waitlist.txt"));
			pw.append(b.prepInsertQuery() + "\n");
			System.out.println("Added to waitlist");
			pw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
		tf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reloadList();
				for (int i = 0; i < originList.size(); i++) {
					if (!originList.get(i).toString().contains(tf.getText())) {
						originList.remove(i);
						i--;
					}
				}
				mainWindow.dispose();
				loadMainApp();
			}
		});
		tf.setBounds(10,60,125,30);
		return tf;
	}
	private JTextField addUserField() {
		JTextField tf = new JTextField("Username");
		tf.setBounds(20,75,200,40);
		return tf;
	}
	private JPasswordField addPassField() {
		JPasswordField pf = new JPasswordField("Password");
		pf.setBounds(20,125,200,40);
		return pf;
	}

	private JComboBox<String> addSortBox() {
		String sortOptions[]  = {"Sort By", "Title A-Z", "Title Z-A"};
		JComboBox<String> cb = new JComboBox<String>(sortOptions);
		cb.setBounds(137, 60, 70, 30);
		cb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(cb.getSelectedIndex()) {
				case 1:
					reloadList();
					Collections.sort(originList);
					mainWindow.dispose();
					loadMainApp();
					break;
				case 2:
					reloadList();
					Collections.sort(originList, Collections.reverseOrder());
					mainWindow.dispose();
					loadMainApp();
					break;
				default:
				}
			}
		});
		return cb;
	}

	private JComboBox<String> addFilterBox() {
		String filterOptions[]  = {"Filter Genre", "Fiction", "NonFiction", "Autobiography", "Biography", "Crime", "Drama",
				"Fantasy", "History", "Horror", "Mystery", "Romance", "SciFi", "Thriller"};
		JComboBox<String> cb = new JComboBox<String>(filterOptions);
		cb.setBounds(210, 60, 103, 30);
		cb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(cb.getSelectedIndex()) {
				case 0:
					reloadList();
					originList = fetchData.FetchAllWithoutFilter();
					mainWindow.dispose();
					loadMainApp();
					break;
				default:
					reloadList();
					originList = fetchData.FetchAllWithFilter(cb.getItemAt(cb.getSelectedIndex()));
					mainWindow.dispose();
					loadMainApp();
					break;
				}
			}
		});
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
		JButton b = new JButton("Bookshelf");
		b.setBounds(315, 10, 100, 40);
		return b;
	}
	private JButton addRequestNewBookButton() {
		JButton b = new JButton("Request A Book");
		b.setBounds(315, 60, 205, 30);
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

	private JLabel addLoginFailed() {
		JLabel l = new JLabel("Invalid Credentials");
		l.setBounds(420,50,125,20);
		l.setFont(new Font("Helvetica", Font.ITALIC, 12));
		return l;
	}	
}
