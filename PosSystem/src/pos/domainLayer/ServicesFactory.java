package pos.domainLayer;

public class ServicesFactory {
	private static ITaxCalculatorAdapter taxCalculatorAdapter = null;
	private static ServicesFactory taxCalulatorFactory = null;
	

	public static synchronized ServicesFactory getInstance() {
		
		if ( taxCalculatorAdapter == null ){
			
			taxCalulatorFactory = new ServicesFactory();
			}
			
		return taxCalulatorFactory;
	}
	public ITaxCalculatorAdapter getTaxCalculatorAdapter()
	{		String className= System.getProperty( "taxcalculator.class.name" );
			try {
				if(!(className.equals(""))) {
					taxCalculatorAdapter= (ITaxCalculatorAdapter) Class.forName(className).newInstance();
				}
				
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			
		return taxCalculatorAdapter;
		}
}

