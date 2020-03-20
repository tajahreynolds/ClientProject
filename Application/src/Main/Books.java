package Main;

public class Books {
	
	private int bookId;
	private int catalogId;
	private String title;
	private String author;
	private String publishDate;
	private int pageCount;

	public Books(int bookId, int catalogId, String title, String author, String publishDate, int pageCount) {
		this.bookId = bookId;
		this.catalogId = catalogId;
		this.title = title;
		this.author = author;
		this.publishDate = publishDate;
		this.pageCount = pageCount;
	}
	
	public String prepInsertQuery() {
		return String.format("INSERT INTO Books (catalogId, title, author, publishDate, pageCount) VALUES"
				+ "('%d' '%s' '%s' '%s' '%d')", catalogId, title, author, publishDate, pageCount);
	}
	
	@Override
	public String toString() {
		return "Books [bookId=" + bookId + ", catalogId=" + catalogId + ", title=" + title + ", author=" + author
				+ ", publishDate=" + publishDate + ", pageCount=" + pageCount + "]";
	}
	
}
