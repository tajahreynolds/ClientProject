
public class BookList implements ListInterface<Books> {

	public void add(Books newEntry);
	
	public void add(int newPosition, Books newEntry);
	
	public Books remove(int givenPosition);
	
	public void clear();
	
	public Books replace(int givenPosition, Books newEntry);
	
	public Books getEntry(int givenPosition);
	
	public Books[] toArray();
	
	public boolean contains(Books anEntry);
	
	public int getLength();
	
	public boolean isEmpty();
}
