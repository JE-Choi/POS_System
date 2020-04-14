package pos.domainLayer;

import java.util.ArrayList;
import java.util.List;

// Polymorphism, Composite ���� ���
public abstract class CompositePricingStrategy implements ISalePricingStrategy{
	protected List<ISalePricingStrategy> strategies = new ArrayList<ISalePricingStrategy> ();
	
	// strategy�� �߰�
	public void add(ISalePricingStrategy ps) {
		strategies.add(ps);
	}
}
