package server.model.inventorymodel;

import java.io.Serializable;
import java.sql.ResultSet;

/**
 * Provides data fields and methods to create a Java data-type, representing an Shop in a Java Application.
 * This class stores the databases for the supplier and inventory along with several class methods that
 * will be used to provide the front end with information and functionality to readjust stock values.
 * 
 * @author Patrick Kwan
 * @version 1.0
 * @since October 7, 2020
 */
public class ShopApp  implements Serializable{
	
	/**
	 * The database for the suppliers.
	 */
	private SupplierList supplierList;
	//private Order orderDB;
	
	/**
	 * The database for the inventory.
	 */
	private Inventory inventory;
	
	public ShopApp(Inventory inventoryDB2, SupplierList supplierDB2) {
		this.setInventory(inventoryDB2);
		this.setSupplierList(supplierDB2);
		//this.setOrderDB(orderDB2);
		
	}
	
	/**
	 * Adds a Tool to the inventory
	 * @param tool is the tool being added
	 */
	public void addTool(Tool tool) {
		
	 inventory.addTool(tool);
		
	}
	
	public Tool getTool(int toolID) {
		return inventory.getTool(toolID);
	}
	
	/**
	 * Gets the supplier database.
	 * @return the supplier database as a type of SupplierList
	 */
	public SupplierList getSupplierList() {
		return supplierList;
	}
	
	/**
	 * Sets the supplier database to a given supplierList.
	 * @param supplierList the database of suppliers that provide all items in the store.
	 */
	public void setSupplierList(SupplierList supplierList) {
		this.supplierList = supplierList;
	}
	
	/**
	 * Gets the inventory database.
	 * @return the inventory database as a type of Inventory.
	 */
	public Inventory getInventory() {
		return this.inventory;
	}
	
	/**
	 * Sets the inventory database to a given Inventory object.
	 * @param inventory the database of the inventory that stores all items in store.
	 */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	/**
	 * Gets the order database from the inventory database.
	 * @return the order database as a type of Order.
	 */
	public Order getOrder() {
		return this.inventory.getOrder();
	}
	
	
	
	/**
	 * Outputs to the console a list of tools stored in the inventory.
	 */
	public void listTools() {
		System.out.print("Listing Tools in Inventory:\n\n"+this.getInventory());
	}
	
	/**
	 * Outputs a string with the information of item being searched via provided description.
	 * @param description is the name of the item being searched.
	 * @return a String of the item with all its corresponding information or an error message.
	 */
	public String searchName(String description) {
		
		String retStr="";
		Tool tempItem = this.getInventory().searchName(description);
		if (tempItem == null) {
			retStr+="The following item: \"" + description + "\" was not found.";
		}else {
			retStr+="Here is the information for \"" + description + "\"\n" + tempItem;
		}
		
		return retStr;
	}
	
	/**
	 * Outputs a string with the information of item being searched via provided item id.
	 * @param id the id of the item being searched.
	 * @return a String of the item with all its corresponding information or an error message.
	 */
	public String searchID(int id) {
		
		String retStr="";
		Tool tempItem = this.getInventory().searchID(id);
		if (tempItem == null) {
			retStr+="The following item with ID: " + id + " was not found.";
		}else {
			retStr+="Here is the information for item with ID: " + id + "\n" + tempItem;
		}
		return retStr;
			
	}
	
	/**
	 * Outputs a string with the Information of item being searched via item id.
	 * @param id the id of the item being searched.
	 * @return a String of the item with all its corresponding information or an error message.
	 */
	public String checkQuantity(int id) {
		
		String retStr="";
		int quantity = this.getInventory().checkToolQuantity(id);
		if (quantity == -1) {
			retStr+="The following item with ID: " + id + " was not found.";
		}else {
			retStr+="Item with ID: " + id + " has " + quantity + " in stock.";
		}
		
		return retStr;
		
	}
	
	/**
	 * Decreases the quantity of an item with the given id.
	 * @param id the id of the item being reduced.
	 * @return a String prompting user that an item has been reduced along with information of the item or an error message.
	 */
	public String decreaseTool(int id) {
		String retStr="";
		Tool tempItem = this.getInventory().searchID(id);
		if (tempItem == null) {
			retStr+="The following item with ID: " + id + " was not found.";
		}else {
			this.getInventory().decreaseTool(id);
			retStr+="Item with ID: " + id + " has been decreased by 1\n" + tempItem;
		}
		return retStr;
		
	}
	
	/**
	 * Outputs the Order of the day or an error message if there is no order today.
	 */
	public void listOrder() {
		if(this.getOrder()!=null)
			System.out.print("Outputting Order:\n\n"+ this.getOrder());
		else
			System.out.print("No Order has been made today.");
	}
	
	/**
	 * Parses and adds suppliers from a result set. The result set will be a left join between Supplier and International with information from both. 
	 * @param resultSet the resultSet from the SQL Query a left join between Supplier and International
	 */
	public void parseSupplierList(ResultSet resultSet) {
		supplierList.parseSupplierList(resultSet);
	}
	
	public void parseInventory(ResultSet resultSet) {
		inventory.parseInventory(resultSet, supplierList);
	}
	
}
