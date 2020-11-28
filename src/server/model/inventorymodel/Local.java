package server.model.inventorymodel;

import java.io.Serializable;

public class Local extends Supplier implements Serializable{
	
	public Local() {
		
	}
	public Local(int supplierID, String companyName, String type, String address, String salesContact,
			String phoneNumber) {
		super(supplierID, companyName, type, address, salesContact, phoneNumber);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return("Supplier ID:\t"+ this.getSupplierID() +
				"\nCompany Name:\t"+ this.getCompanyName() +
				"\nType:\t"+ this.getType() +
				"\nAddress:\t"+ this.getAddress() +
				"\nContact Name:\t"+ this.getSalesContact() +
				"\nPhone Number:\t"+ this.getPhoneNumber() +"\n\n");
		
	}

}
