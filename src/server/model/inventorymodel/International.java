package server.model.inventorymodel;

import java.io.Serializable;

public class International extends Supplier implements Serializable {
	
	private double importTax;
	
	public International(int supplierID, String companyName, String type, String address, String salesContact,
			String phoneNumber, double importTax) {
		super(supplierID, companyName, type, address, salesContact, phoneNumber);
		this.setImportTax(importTax);
		// TODO Auto-generated constructor stub
	}

	public double getImportTax() {
		return importTax;
	}

	public void setImportTax(double importTax) {
		this.importTax = importTax;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return
		"Supplier ID:\t"+ this.getSupplierID() +
		"\nCompany Name:\t"+ this.getCompanyName() +
		"\nType:\t"+ this.getType() +
		"\nImport Tax:\t" + this.getImportTax() +
		"\nAddress:\t"+ this.getAddress() +
		"\nContact Name:\t"+ this.getSalesContact() +
		"\nPhone Number:\t"+ this.getPhoneNumber() +"\n\n";
		
	}

}
