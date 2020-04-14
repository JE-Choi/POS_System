package pos.domainLayer;

public class PercentDiscountPricingStrategy implements ISalePricingStrategy {

	@Override
	public Money getTotal(Sale sale) {
		Money m = sale.getTotal("other");
		int amount = (m.getAmount()/100) * 90;
		return new Money(amount);
	}

}
