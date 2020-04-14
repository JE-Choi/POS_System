package pos.domainLayer;

import java.util.ArrayList;
import java.util.Iterator;

// Controller 패턴 사용
public class Register {
	private ProductCatalog catalog;
	private Sale currentSale;
	public Register(ProductCatalog catalog){
		this.catalog = catalog;
	}
	
	public void endSale()
	{
		currentSale.becomeComplete();
	}
	
	public void enterItem(ItemID id, int quantity)
	{
		ProductDescription desc = catalog.getProductDescription(id);
		currentSale.makeLineItem(desc, quantity);
	}
	
	public Sale makeNewSale()
	{
		currentSale = new Sale();
		return currentSale;
	}
	
	public void makePayment(Money cashTendered)
	{
		currentSale.makePayment(cashTendered);
	}


	public String showDesc(ItemID id) {
		ProductDescription desc = catalog.getProductDescription(id);
		return desc.getDescription();
	}

	public String showPrice(ItemID id) {
		ProductDescription desc = catalog.getProductDescription(id);
		return Integer.toString(desc.getPrice().getAmount());
	}
	
	public String[] loadItemIDs() {
		return catalog.ItemIDs();
	}
	
	public void calculateTax() {
		currentSale.calculateTax();
	}
	
	
	public void applyDiscount() {
		currentSale.applyDiscount();
	}


}
