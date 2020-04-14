package pos.domainLayer;

public class TaxMasterAdapter implements ITaxCalculatorAdapter{

	@Override
	public Money getTaxes(Sale sale) {
		TaxMaster tax = new TaxMaster();
		return tax.calcTax(sale);
	}
}
