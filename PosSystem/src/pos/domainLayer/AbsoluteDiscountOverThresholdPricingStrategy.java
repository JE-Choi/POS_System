package pos.domainLayer;

public class AbsoluteDiscountOverThresholdPricingStrategy implements ISalePricingStrategy {
	Money threshold = new Money(20000);
	@Override
	public Money getTotal(Sale sale) {
		Money m = sale.getTotal("other");
		int amount = m.getAmount();
		if(m.getAmount() > threshold.getAmount()) {
			amount = (amount / 100) * 80;
		}
		return new Money(amount);
	}

}
