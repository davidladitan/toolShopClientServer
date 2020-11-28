package server.model.inventorymodel;

import java.io.Serializable;

public class NonElectrical extends Tool implements Serializable{

	public NonElectrical(int itemId, String name, String type, int quantityStocked, double price, Supplier supplier) {
		super(itemId, name, type, quantityStocked, price, supplier);
		// TODO Auto-generated constructor stub
	}

	public NonElectrical() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return("Tool ID:\t"+ this.getToolID() +"\nTool Name:\t"+ this.getName() +
				"\nQuantityStocked:\t"+ this.getQuantityStocked() +"\nPrice:\t"+ this.getPrice() +
				"\nSupplierID:\t"+ this.getSupplier().getSupplierID()+
				"\nTool Type:\t" + this.getType() + "\n\n");
	}



}
