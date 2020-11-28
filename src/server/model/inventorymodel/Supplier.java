package server.model.inventorymodel;

import java.io.Serializable;

/**
 * Provides data fields and methods to create a Java data-type, representing a Supplier in a Java Application.
 * This class stores information about the individual supplier including the supplier ID, company name, address,
 * and sales contact.
 * 
 * @author Patrick Kwan
 * @version 1.0
 * @since October 7, 2020
 *
 */
public abstract class Supplier implements Serializable{
	
	/**
	 * The id of the supplier as provided by the text file.
	 */
	private int supplierID;
	
	/**
	 * The company name of the supplier  as provided by the text file.
	 */
	private String companyName;
	
	/**
	 * Type of the supplier either International or :ocal
	 */
	private String type;
	
	/**
	 * The address of the supplier as provided by the text file.
	 */
	private String address;
	
	/**
	 * The salesContact of the supplier as provided by the text file.
	 */
	private String salesContact;
	
	/**
	 *  The phone number of the supplier.
	 */
	private String phoneNumber;
	
	public Supplier() {
		this.setAddress(null);
		this.setCompanyName(null);
		this.setPhoneNumber(null);
		this.setSalesContact(null);
		this.setSupplierID(0);
		this.setType(null);
	}
	
	/**
	 * Constructs an Supplier object with specified values for all data fields of Supplier. The values of
	 * the data fields are supplied by the given parameters. Typically used by File Manager when loading in data. 
	 * @param supplierID the id of the supplier.
	 * @param companyName the company name of the supplier.
	 * @param address the address of the supplier.
	 * @param salesContact the name of the sales contact of the supplier.
	 */
	public Supplier(int supplierID, String companyName, String type, String address, String salesContact, String phoneNumber ){
		
		this.setSupplierID(supplierID);
		this.setCompanyName(companyName);
		this.setType(type);
		this.setAddress(address);
		this.setSalesContact(salesContact);
		this.setPhoneNumber(phoneNumber);
		
			
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract String toString();
	
	/**
	 * Gets the id of the supplier.
	 * @return the id of the supplier.
	 */
	public int getSupplierID() {
		return supplierID;
	}
	
	/**
	 * Sets the id of the supplier to the given value.
	 * @param supplierID id of the supplier.
	 */
	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}
	
	/**
	 * Gets the name of the supplier.
	 * @return the name of the supplier
	 */
	public String getCompanyName() {
		return companyName;
	}
	
	/**
	 * Sets the name of the supplier to the given value.
	 * @param companyName name of the supplier company.
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	/**
	 * Gets the address of the supplier.
	 * @return address of the supplier.
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets the address of the supplier to the given value.
	 * @param address the address of the supplier.
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Gets the sales contact for the supplier.
	 * @return sales contact for the supplier.
	 */
	public String getSalesContact() {
		return salesContact;
	}
	
	/**
	 * Sets the contact for the supplier to given value.
	 * @param salesContact the name of the supplier.
	 */
	public void setSalesContact(String salesContact) {
		this.salesContact = salesContact;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
}
