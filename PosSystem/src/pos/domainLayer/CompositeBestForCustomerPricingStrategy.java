package pos.domainLayer;

import java.util.Iterator;

public class CompositeBestForCustomerPricingStrategy extends CompositePricingStrategy{

	@Override
	public Money getTotal(Sale sale) {
	Money lowestTotal = new Money (Integer.MAX_VALUE);
		
		Iterator<ISalePricingStrategy> i = strategies.iterator();
		while(i.hasNext()) {
			ISalePricingStrategy strategy = i.next();
			
			Money total = strategy.getTotal(sale);
			if(total.getAmount() < lowestTotal.getAmount()) {
				lowestTotal = total;
			}
			
		}
		return lowestTotal;
	}

}
