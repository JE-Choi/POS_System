package pos.domainLayer;

public class GoodAsGoldTaxPro {
	// ���� 20%�� �ΰ��� �հ� ��ȯ
	public Money getTax(Sale s) {
		int total = s.getTotal("curTotal").getAmount();
		int amount = (total/100) * 120;
		Money m = new Money(amount);
		return m;
	}
}
