package pos.domainLayer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Sale {
	private List<SalesLineItem> lineItems =  new ArrayList<SalesLineItem>(); 
	private Date date = new Date(); 
	private boolean isComplete = false; 
	private Payment payment; 
	private List <PropertyListener> propertyListeners = new ArrayList<PropertyListener>();

	private Money total;
	private ISalePricingStrategy ps = null;
	
	public Money getBalance()	{
	
		return payment.getAmount().minus(total);
	}
	
	public void becomeComplete(){ 
		isComplete = true; 
	}
	
	public boolean isComplete() {  
		return isComplete; 
	}
	

	public void makeLineItem(ProductDescription desc, int quantity)	{
		SalesLineItem sl = new SalesLineItem(desc, quantity);
		lineItems.add(sl);
		
	}
	
	public Money getTotal(String type) {
		if(type.equals("curTotal")) {
			total = new Money();
			Money subtotal = null;
			
			for(SalesLineItem lineItem : lineItems)
			{
				subtotal = lineItem.getSubtotal();

				total.add(subtotal);
			}
		
			return total;
		} else if(type.equals("other")){
			return total;
		}
		
		return new Money();
	}
	
	public void makePayment(Money cashTendered) {
		payment = new Payment(cashTendered);
	}
	
	public void setTotal(Money newTotal) {
		total = newTotal;
		// 관찰자들에게 상태 변화 알림.
		publishPropertyEvent( "sale.total", total);
	}	
	public void addPropertyListener(PropertyListener lis) {
		propertyListeners.add( lis );
	}
	
	public void publishPropertyEvent( String name, Money value ) {
		for(PropertyListener pl : propertyListeners) {
			pl.onPropertyEvent( this, name, value );
		}
	}
	public void calculateTax() {
		ServicesFactory taxFactory = ServicesFactory.getInstance();
		ITaxCalculatorAdapter taxCalc = taxFactory.getTaxCalculatorAdapter();
		// 세금 적용된 가격으로 total 갱신
		if(taxCalc != null) {
			total = taxCalc.getTaxes(this);
		}
		
	}
	
	public void applyDiscount() {
		
		if(ps != null) {
			// 할인 적용된 가격으로 total 갱신
			total = ps.getTotal(this);
		}
		
	}
	
	public void setDiscountStrategy() {
		String className= System.getProperty( "PricingStrategy.class.name" );
		try {
			ps = (ISalePricingStrategy) Class.forName(className).newInstance();
			
			ISalePricingStrategy Absolute_ps = new  AbsoluteDiscountOverThresholdPricingStrategy();
			ISalePricingStrategy Percent_ps = new PercentDiscountPricingStrategy();
			((CompositePricingStrategy)ps).add(Absolute_ps);
			((CompositePricingStrategy)ps).add(Percent_ps);		
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
		}
		
	}

}
