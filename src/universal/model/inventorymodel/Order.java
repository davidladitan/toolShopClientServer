package universal.model.inventorymodel;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * Provides data fields and methods to create a Java data-type, representing an Order (a collection of OrderLines) in a Java Application.
 * This class stores information about the individual order including the randomize orderID, order date and a corresponding list of Orders.
 * 
 * @author Patrick Kwan
 * @version 1.0
 * @since October 7, 2020
 *
 */
public class Order implements Serializable{
	/**
	 * The list of Orders in the form of an Array List
	 */
	private ArrayList<OrderLine> orderDB;
	
	/**
	 * The randomize order ID corresponding to the order.
	 */
	private int orderID;
	
	/**
	 * The date that the order was made. 
	 */
	private String date;
	
	/**
	 * Constructs a Order object, generating specified values for the orderID and date using Math, Format, Date class methods.
	 * @see <a href="https://dzone.com/articles/random-number-generation-in-java">https://dzone.com/articles/random-number-generation-in-java</a>
	 * @see <a href="http://www.java2s.com/Code/Java/Data-Type/FulllengthofmonthnameSimpleDateFormatMMMM.htm">http://www.java2s.com/Code/Java/Data-Type/FulllengthofmonthnameSimpleDateFormatMMMM.htm</a>
	 */
	public Order() {
		orderDB = new ArrayList<OrderLine>();
		orderID = (int) (Math.random()*((99999-10000+1)) + 10000) ;
		Format formatter = new SimpleDateFormat("MMMM dd, yyyy"); 
	    String s = formatter.format(new Date());
	    
		date =s;
	}
	
	/**
	 * This adds an OrderLine of a given item and quantity into the Order object. If the item exists, it updates the value
	 * the the quantity being ordered for that item.
	 * @param itemOrdered the item being ordered.
	 * @param quantity the quantity of the item being ordered.
	 */
	public void addOrder(Tool itemOrdered, int quantity) {
		
		OrderLine temp;
		temp = this.searchDescription(itemOrdered.getName());
		
		if(temp==null)
			orderDB.add(new OrderLine(itemOrdered, quantity));
		else
			temp.setQuantityOrdered(temp.getQuantityOrdered()+1);
		
	}
	
	/**
	 * This returns an OrderLine corresponding to the item being searched using a given description
	 * @param name the name of the item.
	 * @return an OrderLine object of the item being ordered with the specified description.
	 */
	public OrderLine searchDescription(String name) {
		
		for (OrderLine i: orderDB) {
			if (i.getName().equals(name))
				return i;	
		}
		
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		String temp ="ORDER ID: " + this.orderID +  "\nDate Ordered: " + this.date;
		
		for (OrderLine i: orderDB) {
			temp += i;
		}
				
		return temp;
		
	}
	
	public int getOrderID() {
		return this.orderID;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public ArrayList<OrderLine> getOrderDB(){
		return this.orderDB;
	}
}
