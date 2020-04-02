import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {
		ArrayList<Books> bookList = new ArrayList<Books>();
		
		Books book1 = new Books(1, 1, "Prey", "Michael Crichton", "November 25, 2002", 502);
		Books book2 = new Books(2, 2, "Prey", "Geoff Crichton", "November 25, 2001", 501);
		Books book3 = new Books(3, 3, "Prey", "Michael", "November 25, 2000", 500);
		
		///Testing the print all logic
		System.out.println("The first call should return nothing, the second call should return the three books added.");
		System.out.println("Call One:");
		for (Books it : bookList) {
			System.out.print(it.toString());
		}
		System.out.println();
		System.out.println("Call Two:");
		bookList.add(book1);
		bookList.add(book2);
		bookList.add(book3);
		for (Books it : bookList) {
			System.out.print(it.toString());
		}
		System.out.println();
		System.out.println();

		///Testing printing with filters, there will be test for each different filtering "condition
		System.out.println("This call should print only books with the Book Id 1");
		for (Books it : bookList) {
			if (it.getBookId() == 1)
				System.out.println(it.toString());
		}
		System.out.println();
		System.out.println("This call should print only books with the Catalogue Id of 1");
		for (Books it : bookList) {
			if (it.getCatalogId() == 1)
				System.out.println(it.toString());
		}
		System.out.println();
		System.out.println("This call should print only books with the title 'Prey'");
		for (Books it : bookList) {
			if (it.getTitle().equals("Prey"))
				System.out.println(it.toString());
		}
		System.out.println();
		System.out.println();
		System.out.println("This call should print only books by the Author 'Michael Crichton'");
		for (Books it : bookList) {
			if (it.getAuthor().equals("Michael Crichton"))
				System.out.println(it.toString());
		}
		System.out.println();
		System.out.println();
		System.out.println("This call should print only books with the publication date November 25, 2002");
		for (Books it : bookList) {
			if (it.getPublishDate().equals("November 25, 2002"))
				System.out.println(it.toString());
		}
		System.out.println();
		System.out.println();
		System.out.println("This call should print only books 501 pages or longer");
		for (Books it : bookList) {
			if (it.getPageCount() >= 501)
				System.out.println(it.toString());
		}
		System.out.println();
	}

}
