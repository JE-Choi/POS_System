package pos.domainLayer;

public class TaxMaster {
	
	// ���� 10%�� �ΰ��� �հ� ��ȯ
	public Money calcTax(Sale s) {
		int total = s.getTotal("curTotal").getAmount();
		int amount = (total/100) * 110;
		Money m = new Money(amount);
		return m;
	}
}
