package pos.domainLayer;

public class GoodAsGoldTaxProAdapter implements ITaxCalculatorAdapter{
	@Override
	public Money getTaxes(Sale sale) {
		GoodAsGoldTaxPro tax = new GoodAsGoldTaxPro();
		return tax.getTax(sale);
	}


}
