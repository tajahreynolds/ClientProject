
public class Catalogs {
	
	private int catalogId;
	private String catalog;
	
	public Catalogs(int catalogId, String catalog) {
		this.catalogId = catalogId;
		this.catalog = catalog;
	}
	
	public String prepInsertQuery() {
		return String.format("INSERT INTO Catalogs (catalog) VALUES"
				+ "('%s')", catalog);
	}

	@Override
	public String toString() {
		return "Catalogs [catalogId=" + catalogId + ", catalog=" + catalog + "]";
	}
}