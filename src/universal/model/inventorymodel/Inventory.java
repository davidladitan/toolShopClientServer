package universal.model.inventorymodel;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.model.customermodel.Commercial;
import server.model.customermodel.Customer;
import server.model.customermodel.Residential;

/**
 * Provides data fields and methods to create a Java data-type, representing an Inventory in a Java Application.
 * This class stores information about the tools in the shop and specific order to be made that day and the default stock
 * of each item.
 * 
 * @author Patrick Kwan
 * @version 1.0
 * @since October 7, 2020
 *
 */
public class Inventory implements Serializable{
	
	/**
	 * The array list of Items that represents the inventory.
	 */
	private ArrayList<Tool> toolList;
	
	/**
	 * The order for the day.
	 */
	private Order order;
	
	/**
	 * The default number of items that should be in stock.
	 */
	private static int stockDefault =50;
	
	/**
	 * The threshold value determining when an item should be restocked.
	 */
	private static int restockThreshold = 40;
	
	/**
	 * This constructs the Inventory object.
	 */
	public Inventory() {
		this.toolList = new ArrayList<Tool>();
		this.order = null;
	}
	
	/**
	 * Creates a new order if one doesn't exists.
	 */
	public void addOrder() {
		this.order=new Order();
	}
	
	
	/**
	 * Gets the Order Database.
	 * @return the Order database (ie the orders for the day)
	 */
	public Order getOrder() {
		return this.order;
	}
	
	/**
	 * Sets the orderDB to a specific Order object.
	 * @param order the database Order that holds all orderlines for today.
	 */
	public void setOrder(Order order) {
		this.order= order;
	}
	
	/**
	 * This adds an given Item to the inventory database. 
	 * @param item the Item being added to the inventory.
	 */
	public void addTool(Tool item) {
		
		if(item.getQuantityStocked()<restockThreshold) {
			
			if(order == null)
				this.addOrder();
			
			order.addOrder(item, stockDefault-item.getQuantityStocked() );
			
		}
		toolList.add(item);
	}
	
	/**
	 * Searches for a given item using a specific ID and returning said item if it exists, otherwise return null.
	 * @param id the id of the item being searched.
	 * @return the Item being searched from the inventory
	 */
	public Tool searchID(int id) {
		
		for (Tool i: toolList) {
			if (i.getToolID() == id)
				return i;	
		}
		
		return null;
	}
	
	/**
	 * Searches for a given item using a specific ID and returning said item if it exists, otherwise return null. 
	 * @param name the name of the item being searched.
	 * @return the Item being searched from the inventory.
	 */
	public Tool searchName(String name) {
		
		for (Tool i: toolList) {
			if (i.getName().equals(name) )
				return i;
		}
		
		return null;
	}
	
	/**
	 * This changes the quantity of a given item (searched using id) in the inventory by subtracting 1, if the item is below the threshold
	 * create an order that is the difference of the default stock value and the current quantity. 
	 * @param toolID the item id of the item whose quantity is being changed.
	 */
	public void decreaseTool(int toolID) {
		Tool temp = this.searchID(toolID);
		
		if(temp!=null&&temp.getQuantityStocked()>0) {
			
			temp.decreaseTool();
			
			
			if(temp.getQuantityStocked()<restockThreshold) {
				
				if(order == null)
					this.addOrder();
				
				order.addOrder(temp, stockDefault-temp.getQuantityStocked() );
				
			}
			
		}
		
		
	}
	
	/**
	 * This returns the quantity of a given Item, if the item does not exist it returns -1.
	 * @param toolID the item that is being searched and whose quantity is being returned.
	 * @return the quantity of the item being searched.
	 */
	public int checkToolQuantity(int toolID) {
		Tool temp =  this.searchID(toolID);
		if(temp != null)
			return temp.getQuantityStocked();
		return -1;
	}
	
	
	/**
	 * This returns the quantity of a given Item, if the item does not exist it returns -1.
	 * @param toolID the item that is being searched and whose quantity is being returned.
	 * @return the quantity of the item being searched.
	 */
	public Tool getTool(int toolID) {
		Tool temp =  this.searchID(toolID);
		return temp;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		String temp="";
		for(Tool i : toolList) {
			temp+= i;
		}
		return temp;
	}
	
	/**
	 * Parses inventory from result set that is the Left Join of Supplier and International, meaning you will have information about suppliers who are
	 * both international and local. Local suppliers will have null as the import tax value. Note the supplierList must be parse before hand from the same ResultSet
	 * before inventory is parsed.
	 * @param resultSet
	 * @param supplierList
	 */
	public void parseInventory(ResultSet resultSet, SupplierList supplierList) {
		 
		try {
			while(resultSet.next()) {

				if(resultSet.getString("Type").equals("Electrical")) {
					Electrical tool = new Electrical(resultSet.getInt("ToolID"),
													 resultSet.getString("Name"),
													 resultSet.getString("Type"),
													 resultSet.getInt("Quantity"),
													 resultSet.getDouble("Price"),
													 supplierList.searchID(resultSet.getInt("SupplierID")),
													 resultSet.getString("PowerType"));

				}
				//else if(resultSet.getString("Type").equals("NonElectrical")){
				else {
					NonElectrical tool = new NonElectrical(resultSet.getInt("ToolID"),
							 								resultSet.getString("Name"),
							 								resultSet.getString("Type"),
							 								resultSet.getInt("Quantity"),
							 								resultSet.getDouble("Price"),
							 								supplierList.searchID(resultSet.getInt("SupplierID")));
				}
	
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
