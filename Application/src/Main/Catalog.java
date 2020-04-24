package Main;

public class Catalog {
	
	private int catalogId;
	private String catalog;
	private BookList<Book> bookList;
	
	public Catalog(int catalogId, String catalog) {
		this.catalogId = catalogId;
		this.catalog = catalog;
		this.bookList = new BookList<Book>();
	}
	
	public String prepInsertQuery() {
		return String.format("INSERT INTO Catalog (catalog) VALUES"
				+ "('%s')", catalog);
	}

	@Override
	public String toString() {
		return "Catalog [catalogId=" + catalogId + ", catalog=" + catalog + "]";
	}
	
	public boolean addBook(Book newBook) {
		if (this.bookList.contains(newBook))
			return false;
		else
			this.bookList.add(newBook);
		return true;
	}
	
	public void view() {
		for (int index = 1; index <= bookList.getLength(); index++) {
			System.out.println(bookList.getEntry(index));
		}
	}
	
}
