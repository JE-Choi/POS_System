package pos.domainLayer;

public class ItemID {

	String id;


	public ItemID(String id) {	
		this.id = id;
	}

	public ItemID(int id) {	
		this.id = String.valueOf(id);
	}

	public String getId() {
	
		return id;
	}

	@Override
	public String toString() {
		return id;
	}
	


	
	
	
}
