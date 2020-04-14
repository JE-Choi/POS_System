package pos.domainLayer;

public class GoodAsGoldTaxPro {
	// 세율 20%를 부과한 합계 반환
	public Money getTax(Sale s) {
		int total = s.getTotal("curTotal").getAmount();
		int amount = (total/100) * 120;
		Money m = new Money(amount);
		return m;
	}
}
