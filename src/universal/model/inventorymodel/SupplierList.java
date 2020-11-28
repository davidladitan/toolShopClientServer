package universal.model.inventorymodel;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Provides data fields and methods to create a Java data-type, representing a List of Supplier in a Java Application.
 * This class stores information about several individual supplier in form of an ArrayList and provides a method to search and add suppliers to
 * the supplier database.
 * 
 * @author Patrick Kwan
 * @version 1.0
 * @since October 7, 2020
 *
 */
public class SupplierList implements Serializable{
	
	/**
	 * The list of suppliers store in an ArrayList.
	 */
	private ArrayList<Supplier> supplierDB;
	
	public SupplierList(){
		supplierDB = new ArrayList<Supplier>();
	}
	
	/**
	 * This returns a given supplier being searched via a provided id and returns null if it doesn't exists.
	 * @param id the id of the supplier being searched.
	 * @return the supplier as a type supplier.
	 */
	public Supplier searchID(int id) {
		
		for (Supplier i: supplierDB) {
			if (i.getSupplierID() == id)
				return i;	
		}
		
		return null;
	}
	
	/**
	 * Adds a provided supplier entry to the supply database.
	 * @param supplier the supplier object being added to the database.
	 */
	public void addSupplier(Supplier supplier){
		
		supplierDB.add(supplier);
	}
	public void parseSupplierList(ResultSet resultSet) {
		
		try {
			while(resultSet.next())
			if(resultSet.getString("Type").equals("International")){
				International supplier = new International(resultSet.getInt("SupplierID"), 
						 									resultSet.getString("Name"),
						 									resultSet.getString("Type"),
						 									resultSet.getString("PhoneNum"),
						 									resultSet.getString("Address"),
						 									resultSet.getString("Cname"),
						 									resultSet.getDouble("ImportTax"));
				this.addSupplier(supplier);
				
			}else {
				Local supplier = new Local(resultSet.getInt("SupplierID"), 
						resultSet.getString("Name"),
						resultSet.getString("Type"),
						resultSet.getString("PhoneNum"),
						resultSet.getString("Address"),
						resultSet.getString("Cname"));
				this.addSupplier(supplier);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			

		
	}


}
