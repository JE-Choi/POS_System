package pos.domainLayer;


// Adapter, Polymorphism ���� ���
public interface ITaxCalculatorAdapter {	
	abstract Money getTaxes(Sale sale);

}
