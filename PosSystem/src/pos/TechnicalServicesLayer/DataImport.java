package pos.TechnicalServicesLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;

// Pure Fabrication 
// singleton ���� ���
public class DataImport {

	  //*/ DB������ ���� ���� ������
	   private Connection myConnection;
	   private Statement myStatement;
	   private ResultSet myResultSet;
	   private static DataImport data = new DataImport();

	   public static DataImport getInstance() {
		   return data;
	   }
	   
	   private DataImport() {
		   
		   try {
			   String filename = System.getProperty("dbFile.name");
			   myConnection = DriverManager.getConnection("jdbc:ucanaccess://"+filename);
			  
			   myStatement = myConnection.createStatement();

		   }catch(SQLException e){
			   e.printStackTrace();
		   }
	  }

	public ResultSet loadProductAllDesc()
	   {

		   try {
		
			   myResultSet = myStatement.executeQuery(
					   "SELECT * From ProductDescriptions");
			
		   }catch(SQLException e) {
			   
		   }
		   return myResultSet;
	   } 
}

