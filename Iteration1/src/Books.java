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
		this.setPageCount(pageCount);
	}
	
	public String prepInsertQuery() {
		return String.format("INSERT INTO Books (catalogId, title, author, publishDate, pageCount) VALUES"
				+ "('%d' '%s' '%s' '%s' '%d')", catalogId, title, author, publishDate, getPageCount());
	}
	
	@Override
	public String toString() {
		return "Books [bookId=" + bookId + ", catalogId=" + catalogId + ", title=" + title + ", author=" + author
				+ ", publishDate=" + publishDate + ", pageCount=" + getPageCount() + "]";
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
	
}
