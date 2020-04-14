package pos.domainLayer;


// Adapter, Polymorphism 패턴 사용
public interface ITaxCalculatorAdapter {	
	abstract Money getTaxes(Sale sale);

}
