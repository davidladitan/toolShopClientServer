package server.model.inventorymodel;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Provides data fields and methods to create a Java data-type, representing an Item (or Tool) in a Java Application.
 * This class stores information about the individual Item including the item ID, description, quantity stocked,
 * price and supplier information (accessible through Supplier datafield).
 * 
 * @author Patrick Kwan
 * @version 1.0
 * @since October 7, 2020
 */
public abstract class Tool implements Serializable{
	/**
	 * The item specific ID as provided by the text file.
	 */
	private int toolID;
	
	/**
	 * The item specific description (name) as provided by the text file.
	 */
	private String name;
	
	/**
	 * The type of the Tool either Electrical or NonElectrical
	 */
	private String type;
	
	/**
	 * The number of said item in stock as provided by the text file.
	 */
	private int quantityStocked;
	
	/**
	 * The item's price as provided by the text file.
	 */
	private double price;
	
	/**
	 * The item specific supplier as provided by the text file.
	 */
	private Supplier supplier;
	

	/**
	 * Constructs an Item object with specified values for all data fields of Item. The values of
	 * the data fields are supplied by the given parameters. Typically used by File Manager when loading in Data.
	 * @param toolID the item specific ID as provided by the text file. 
	 * @param name the item specific description (name) as provided by the text file
	 * @param quantityStocked the number of said item in stock as provided by the text file.
	 * @param price the item's price as provided by the text file.
	 * @param supplier the item specific supplier as provided by the text file.
	 */
	public Tool(int toolID, String name,  String type, int quantityStocked, double price, Supplier supplier) {
		this.setToolD(toolID);
		this.setName(name);
		this.setQuantityStocked(quantityStocked);
		this.setPrice(price);
		this.setSupplier(supplier);
		this.setType(type);
	}
	
	public Tool() {
		this.setToolD(-1);
		this.setName(null);
		this.setQuantityStocked(-1);
		this.setPrice(-1);
		this.setSupplier(null);
		this.setType(null);
		
	}
	
	/**
	 * This is used to decrease the quantity of the item in stock by 1.
	 */
	public void decreaseTool() {
		this.setQuantityStocked(this.getQuantityStocked()-1);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract String toString();
	
	/**
	 * Gets the itemID number for this Item.
	 * @return itemID number for this Item.
	 */
	public int getToolID() {
		return toolID;
	}
	
	/**
	 * Sets the itemID for this Item using specified value,
	 * @param itemID the itemID of the Item.
	 */
	public void setToolD(int itemID) {
		this.toolID = itemID;
	}
	
	/**
	 * Gets the description for Item.
	 * @return description for this Item.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the description of the item to specified value.
	 * @param description the name of the item.
	 */
	public void setName(String description) {
		this.name = description;
	}
	
	/**
	 * Gets the quantity of the item being stocked.
	 * @return the quantity of the item being stocked.
	 */
	public int getQuantityStocked() {
		return quantityStocked;
	}
	
	/**
	 * Sets the quantity of the item being stocked to specified value.
	 * @param quantityStocked the number of said Item in stock.
	 */
	public void setQuantityStocked(int quantityStocked) {
		this.quantityStocked = quantityStocked;
	}
	
	/**
	 * Gets the price of the item.
	 * @return the price of the item.
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Sets the price of the item to specified value.
	 * @param price the price of the item.
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Gets the supplier Object.
	 * @return The supplier object. 
	 */
	public Supplier getSupplier() {
		return supplier;
	}
	
	/**
	 * Sets the supplier to a given Supplier object.
	 * @param supplier the supplier of a given item.
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	} 

	
}
