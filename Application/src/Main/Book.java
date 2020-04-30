package Main;

public class Book {
	
	private int bookId;
	private int catalogId;
	private String title;
	private String author;
	private String publishDate;
	private int pageCount;
	private boolean read;

	public Book(int bookId, int catalogId, String title, String author, String publishDate, int pageCount) {
		this.bookId = bookId;
		this.catalogId = catalogId;
		this.title = title;
		this.author = author;
		this.publishDate = publishDate;
		this.setPageCount(pageCount);
		this.read = false;
	}
	
	public String prepInsertQuery() {
		return String.format("INSERT INTO Book (catalogId, title, author, publishDate, pageCount, read) VALUES"
				+ "('%d' '%s' '%s' '%s' '%d' '%s')", catalogId, title, author, publishDate, getPageCount(), read);
	}
	
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", catalogId=" + catalogId + ", title=" + title + ", author=" + author
				+ ", publishDate=" + publishDate + ", pageCount=" + getPageCount() + ", read=" + read + "]";
	}

	int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(int catalogId) {
		this.catalogId = catalogId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	
	public boolean getRead() {
		return read;
	}
	
	//for this I don't really see a point to actually asking for a parameter, the only time
	//you'd ever be calling it is if you want to set the bool to the opposite of what it
	//currently is
	public void setRead() {
		this.read = !this.read;
	}
	
}
