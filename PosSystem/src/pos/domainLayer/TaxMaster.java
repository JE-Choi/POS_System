package pos.domainLayer;

public class TaxMaster {
	
	// 세율 10%를 부과한 합계 반환
	public Money calcTax(Sale s) {
		int total = s.getTotal("curTotal").getAmount();
		int amount = (total/100) * 110;
		Money m = new Money(amount);
		return m;
	}
}
