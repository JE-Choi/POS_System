import pos.domainLayer.Register;
import pos.domainLayer.Store;
import pos.presentaionLayer.ProcessSaleJFrame;

public class mainGUI {

	public static void main(String[] args) {
		
		System.setProperty("dbFile.name", args[0]); // ����� ���ڷ� dbFilename ����
		
		Store store = new Store();
		
		Register register = store.getRegister();
		
		new ProcessSaleJFrame(register);
	}

}
