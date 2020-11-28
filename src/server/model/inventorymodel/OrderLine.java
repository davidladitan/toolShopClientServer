package server.model.inventorymodel;

import java.io.Serializable;

/**
 * Provides data fields and methods to create a Java data-type, representing an orderline in a Java Application.
 * This class stores information about the individual order line including the item description and supplier (accessible
 * through Item orderItem) and the quantity ordered (int quantityOrdered).
 * 
 * @author Patrick Kwan
 * @version 1.0
 * @since October 7, 2020
 */

public class OrderLine implements Serializable {
	/**
	 *  The Item being ordered
	 */
	private Tool orderTool;
	
	/**
	 *  The number of a given Item being ordered.
	 */
	private int quantityOrdered;
	
	/**
	 * Constructs a OrderLine object with specified values for the orderItem and quantityOrdered. The values of
	 * the data fields are supplied by the given parameters.
	 * @param orderTool the Item being ordered
	 * @param quantityOrdered the number of said Item being ordered
	 */
	public OrderLine(Tool orderTool, int quantityOrdered) {
		this.setOrderTool(orderTool);
		this.setQuantityOrdered(quantityOrdered);
	}
	
	/**
	 * Gets the Item that is being ordered.
	 * @return the Item being ordered as an Item object.
	 */
	public Tool getOrderTool() {
		return orderTool;
	}
	
	/**
	 * Sets the orderItem to the specified Item.
	 * @param orderItem the Item that is being ordered
	 */
	public void setOrderTool(Tool orderItem) {
		this.orderTool = orderItem;
	}
	
	/**
	 * Gets the value of the number of said Item being ordered.
	 * @return the number of said Item being ordered.
	 */
	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	/**
	 * Sets the value of the number of said Item being ordered.
	 * @param quantityOrdered the quantity of the item being ordered.
	 */
	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}
	
	/**
	 * Gets the orderItem's description using the Item class getter method.
	 * @return the description of the item as a String.
	 */
	public String getName() {
		return this.getOrderTool().getName();
	}
	
	/**
	 * Gets the orderItem's supplier information using the Item and Supplier class getter method.
	 * @return the String of the Supplier company of the OrderLine
	 */
	public Supplier getSupplier() {
		return this.getOrderTool().getSupplier();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return("\n\nItem description: "+ this.getName() +"\nAmount ordered: "+ this.getQuantityOrdered()
				+ "\nSupplierID: "+ this.getSupplier().getSupplierID());
		
	}
	
	



}
