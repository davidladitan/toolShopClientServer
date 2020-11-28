package server.model.inventorymodel;

import java.io.Serializable;

public class Electrical extends Tool implements Serializable{
	
	private String powerInformation;

	public Electrical(int toolId, String type, String name, int quantityStocked, double price, Supplier supplier,  String powerInformation) {
		super(toolId, type, name, quantityStocked, price, supplier);
		this.setPowerInformation(powerInformation);
		// TODO Auto-generated constructor stub
	}

	public Electrical() {
		// TODO Auto-generated constructor stub
	}

	public String getPowerInformation() {
		return powerInformation;
	}

	public void setPowerInformation(String powerInformation) {
		this.powerInformation = powerInformation;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return("Tool ID:\t"+ this.getToolID() +"\nTool Name:\t"+ this.getName() +
				"\nQuantityStocked:\t"+ this.getQuantityStocked() +"\nPrice:\t"+ this.getPrice() +
				"\nSupplierID:\t"+ this.getSupplier().getSupplierID() + "\nTool Type:\t" + this.getType() +
				"\nPower Information:\t" + this.getPowerInformation()+"\n\n");
	}

}
