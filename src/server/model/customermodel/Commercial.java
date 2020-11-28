package server.model.customermodel;

import java.io.Serializable;

public class Commercial extends Customer implements Serializable {

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String temp = "\nCustomer ID:\t"+ this.getCustomerID() + 
				"\nFirst Name:\t" + this.getFirstName() + 
				"\nLast Name:\t" + this.getLastName() +
				"\nPhone Number:\t" + this.getPhoneNumber() +
				"\nAddress:\t" + this.getAddress()+
				"\nPostal Code:\t" + this.getPostalCode() +
				"\nType:\t\tCommercial";
		return temp;
	}
	

}
