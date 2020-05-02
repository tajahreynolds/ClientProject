package Main;

public class Book {
	
	private int bookId;
	private String catalog;
	private String title;
	private String author;
	private String publishDate;
	private int pageCount;

	public Book(int bookId, String catalogId, String title, String author, String publishDate, int pageCount) {
		this.bookId = bookId;
		this.catalog = catalogId;
		this.title = title;
		this.author = author;
		this.publishDate = publishDate;
		this.pageCount = pageCount;
	}
	
	public String prepInsertQuery() {
		return String.format("INSERT INTO Book (catalog, title, author, publishDate, pageCount) VALUES"
				+ "('%s', '%s', '%s', '%s', '%d')", catalog, title, author, publishDate, pageCount);
	}
	
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", catalog=" + catalog + ", title=" + title + ", author=" + author
				+ ", publishDate=" + publishDate + ", pageCount=" + pageCount + "]";
	}

}
