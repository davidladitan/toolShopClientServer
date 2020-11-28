package universal.model.customermodel;

import java.io.Serializable;

public abstract class Customer implements Serializable{
	private int customerID;
	private String firstName;
	private String lastName;
	private String address;
	private String postalCode;
	private String phoneNumber;
	private String customerType; 
	
	public Customer() {
		customerID=-1;
		firstName=null;
		lastName=null;
		address=null;
		postalCode=null;
		phoneNumber=null;
		customerType=null;
	}
	
	public boolean checkLength(String information, String informationType) {
		
		boolean valid = true;
		switch(informationType) {
		case "name":
			if (information.length()>20) {
				valid = false;
			}
			break;
			
		case "address":
			if (information.length()>50) {
				valid = false;
			}
			break;
			
		case "postal code":
			if (information.length()>7) {
				valid = false;
			}
			break;
			
		case "phone number":
			if (information.length()>12) {
				valid = false;
			}
			break;
		}
		return valid;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
	public abstract String toString();

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	

}
