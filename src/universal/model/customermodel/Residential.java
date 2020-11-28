package universal.model.customermodel;

import java.io.Serializable;

public class Residential extends Customer implements Serializable{

	@Override
	public String toString() {
		String temp = "\nCustomer ID:\t"+ this.getCustomerID() + 
				"\nFirst Name:\t" + this.getFirstName() + 
				"\nLast Name:\t" + this.getLastName() +
				"\nPhone Number:\t " + this.getPhoneNumber() +
				"\nAddress:\t" + this.getAddress()+
				"\nPostal Code:\t" + this.getPostalCode() +
				"\nType:\t\tResidential";
		// TODO Auto-generated method stub
		return temp;
	}
	

}
