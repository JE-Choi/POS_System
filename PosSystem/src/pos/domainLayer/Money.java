package pos.domainLayer;

public class Money {
	
	int amount; 

	public Money(int amount) {
		this.amount = amount;
	}

	public Money() {
		this(0);
	}

	public int getAmount() {
		return amount;
	}
	public Money times (int quantity) {
		return new Money(amount * quantity);
	}
	

	public void add(Money m) {
		
		this.amount += m.getAmount();
	}
	

	public Money minus(Money m) {
		return new Money(amount - m.getAmount());
	}

	@Override
	public String toString() {
		return String.valueOf(amount);
	}
	
}
