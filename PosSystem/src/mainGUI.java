import pos.domainLayer.Register;
import pos.domainLayer.Store;
import pos.presentaionLayer.ProcessSaleJFrame;

public class mainGUI {

	public static void main(String[] args) {
		
		System.setProperty("dbFile.name", args[0]); // 명령행 인자로 dbFilename 전달
		
		Store store = new Store();
		
		Register register = store.getRegister();
		
		new ProcessSaleJFrame(register);
	}

}
