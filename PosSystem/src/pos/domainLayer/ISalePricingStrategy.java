package pos.domainLayer;

// Polymorphism, Strategy ���� ���
public interface ISalePricingStrategy {
	public Money getTotal(Sale sale);
}
