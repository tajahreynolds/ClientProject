package Objects;

public class PersonalBookShelf {

	private int userId, bookId, isRead;

	public PersonalBookShelf(int userId, int bookId, int isRead) {
		super();
		this.userId = userId;
		this.bookId = bookId;
		this.isRead = isRead;
	}
	
	public String prepInsertQuery() {
		return String.format("INSERT INTO PersonalBookShelf (userId, bookId, isRead) VALUES"
				+ "('%d', '%d', '%d')", userId, bookId, isRead);
	}

	@Override
	public String toString() {
		return "PersonalBookShelf [userId=" + userId + ", bookId=" + bookId + ", isRead=" + isRead + "]";
	}

	public int getUserId() {
		return userId;
	}

	public int getBookId() {
		return bookId;
	}

	public int getIsRead() {
		return isRead;
	}
	
}
