package pos.domainLayer;

// Polymorphism, Strategy 패턴 사용
public interface ISalePricingStrategy {
	public Money getTotal(Sale sale);
}
