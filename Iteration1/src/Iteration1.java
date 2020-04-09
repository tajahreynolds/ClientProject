import java.util.ArrayList;

public class Iteration1 {

	public static void main(String[] args) {
		ArrayList<Books> bookList = new ArrayList<Books>();
		
		Books book1 = new Books(1, 1, "Prey", "Michael Crichton", "November 25, 2002", 502);
		Books book2 = new Books(2, 2, "test", "Steve Crichton", "November 25, 2001", 501);
		Books book3 = new Books(3, 3, "test2", "Michael", "November 25, 20020", 500);
		
		bookList.add(book1);
		bookList.add(book2);
		bookList.add(book3);
		
		//Print all, no filters
		for (Books it : bookList) {
			System.out.println(it.toString());
		}
		
		//Print according to filters, will be static for this instance
		System.out.println();
		for (Books it : bookList) {
			if (it.getPageCount() > 500)
				System.out.println(it.toString());
		}
		
		System.out.println();
		for (Books it : bookList) {
			if (it.getTitle().equals("Prey"))
				System.out.println(it.toString());
			
		}
		
		System.out.println();
		for (Books it : bookList) {
			if (it.getAuthor().contains("Michael"))
				System.out.println(it.toString());
		}
	}
	
}
