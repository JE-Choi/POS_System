package pos.domainLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import pos.TechnicalServicesLayer.DataImport;

public class ProductCatalog {

	private Map<String, ProductDescription> descriptions = new HashMap<String, ProductDescription>();
	private String[] itemList;
	public ProductCatalog(){
		
		DataImport data = DataImport.getInstance();
		ResultSet mResultSet =  data.loadProductAllDesc();
		
	
		  try {
			while(mResultSet.next()) {
				
				  String m_id = mResultSet.getString("itemId");
				  int m_price = mResultSet.getInt("price");
				  String m_description = mResultSet.getString("description");
				  
				  ItemID id = new ItemID(m_id);
				  Money price = new Money(m_price);
				  ProductDescription desc;
				  desc =  new ProductDescription( id, price, m_description);
				  descriptions.put(id.getId(), desc);
			   }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ProductDescription getProductDescription(ItemID id){
		return descriptions.get(id.toString());
		}

	// DB의 itemID 값들을 추출하여 List로 저장. (JFrame에서 index으로 접근하기 위해서)
	public String[] ItemIDs() {
		itemList = new String [descriptions.size()];
		Iterator iterator = descriptions.entrySet().iterator();
		int i = 0;
		while(iterator.hasNext()) {
			Entry entry = (Entry) iterator.next();
			ProductDescription desc= (ProductDescription) entry.getValue();
			itemList[i] = desc.getItemID().toString();
		    i++;
		}
		return itemList;
	}
}
