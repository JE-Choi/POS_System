package pos.domainLayer;

import java.util.ArrayList;
import java.util.List;

// Polymorphism, Composite 패턴 사용
public abstract class CompositePricingStrategy implements ISalePricingStrategy{
	protected List<ISalePricingStrategy> strategies = new ArrayList<ISalePricingStrategy> ();
	
	// strategy를 추가
	public void add(ISalePricingStrategy ps) {
		strategies.add(ps);
	}
}
