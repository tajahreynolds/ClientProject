import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class Tester {

	@Test
	void testCatalogs() {
		ArrayList<Books> bookList = new ArrayList<Books>();
		
		Books book1 = new Books(1, 1, "Prey", "Michael Crichton", "November 25, 2002", 502);
		Books book2 = new Books(2, 2, "test", "Steve Crichton", "November 25, 2001", 501);
		Books book3 = new Books(3, 3, "test2", "Michael", "November 25, 20020", 500);
		
		bookList.add(book1);
		bookList.add(book2);
		bookList.add(book3);
		
		String output = "";
		for (Books it : bookList) {
			if (it.getPageCount() > 500)
				output = output + it.toString();
		}
		assertEquals("Books [bookId=1, catalogId=1, title=Prey, author=Michael Crichton, publishDate=November 25, 2002, pageCount=502]" + 
					"Books [bookId=2, catalogId=2, title=test, author=Steve Crichton, publishDate=November 25, 2001, pageCount=501]", output);
		
		System.out.println();
		for (Books it : bookList) {
			if (it.getTitle().equals("Prey"))
				assertEquals("Books [bookId=1, catalogId=1, title=Prey, author=Michael Crichton, publishDate=November 25, 2002, pageCount=502]", it.toString());
			
		}
		
		System.out.println();
		String output2 = "";
		for (Books it : bookList) {
			if (it.getAuthor().contains("Michael"))
				output2 = output2 + it.toString();
		}
		assertEquals("Books [bookId=1, catalogId=1, title=Prey, author=Michael Crichton, publishDate=November 25, 2002, pageCount=502]" + 
					"Books [bookId=3, catalogId=3, title=test2, author=Michael, publishDate=November 25, 20020, pageCount=500]", output2);
		
	}

}
