package Objects;

public class Book implements Comparable<Book> {
	
	private int bookId;
	private String catalog;
	private String title;
	private String author;
	private String publishDate;
	private int pageCount;

	public Book(int bookId, String catalog, String title, String author, String publishDate, int pageCount) {
		this.bookId = bookId;
		this.catalog = catalog;
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

	
	public int getBookId() {
		return bookId;
	}

	public String getCatalog() {
		return catalog;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public int getPageCount() {
		return pageCount;
	}

	@Override
	public int compareTo(Book o) {
		return this.title.compareTo(o.title);
	}

}
