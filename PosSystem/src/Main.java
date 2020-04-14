

import pos.domainLayer.ItemID;
import pos.domainLayer.Money;
import pos.domainLayer.Register;
import pos.domainLayer.Sale;
import pos.domainLayer.Store;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Store store = new Store();
		
		Register register = store.getRegister();
	
		Sale sale = register.makeNewSale(); // makeNewSale버튼 눌렸을때 해야 하는 처리.
		register.enterItem(new ItemID(100), 3);
		register.enterItem(new ItemID(200), 2);
		
		register.endSale();
		
	
		System.out.println("Total = " + sale.getTotal("curTotal"));
		
		register.makePayment(new Money(10000));
		
		System.out.println("Balance = "+sale.getBalance());
	}

}
