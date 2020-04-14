package pos.domainLayer;

import java.util.Iterator;
public class CompositeBestForStorePricingStrategy extends CompositePricingStrategy{

	@Override
	public Money getTotal(Sale sale) {
		Money highestTotal = new Money (Integer.MIN_VALUE);
		
		Iterator<ISalePricingStrategy> i = strategies.iterator();
		while(i.hasNext()) {
			ISalePricingStrategy strategy = i.next();
			
			Money total = strategy.getTotal(sale);
			if(total.getAmount() > highestTotal.getAmount()) {
				highestTotal = total;
			}
			
		}
		return highestTotal;
	}
	

}
