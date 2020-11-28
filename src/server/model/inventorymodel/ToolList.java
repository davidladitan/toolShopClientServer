package server.model.inventorymodel;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import server.model.customermodel.Commercial;
import server.model.customermodel.Customer;
import server.model.customermodel.Residential;

public class ToolList implements Serializable{

	private ArrayList <Tool> toolList; 
	
	public ToolList(){
		toolList = new ArrayList<Tool>();
	}
	
	public Supplier parseSupplier(ResultSet resultSet) {
		
			try {
				if(resultSet.getString("SupplierType").equals("International")){
					International supplier;
					supplier = new International(resultSet.getInt("SupplierID"), 
						 									resultSet.getString("SupplierName"),
						 									resultSet.getString("SupplierType"),
						 									resultSet.getString("PhoneNum"),
						 									resultSet.getString("Address"),
						 									resultSet.getString("Cname"),
						 									resultSet.getDouble("ImportTax"));
					return supplier;
					
				}else {
					Local supplier = new Local(resultSet.getInt("SupplierID"), 
							resultSet.getString("SupplierName"),
							resultSet.getString("SupplierType"),
							resultSet.getString("PhoneNum"),
							resultSet.getString("Address"),
							resultSet.getString("Cname"));
				 return supplier;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		 
	
	}
	
	
	/**
	 * Parses customers stored in an SQL Result Set into an ArrayList
	 * @param sqlSet is the result set from an SQL Query
	 */
	public void parseToolList(ResultSet resultSet) {
		try {
			
			while(resultSet.next()) {
				
				if(resultSet.getString("ToolType").equals("Electrical")) {
					Electrical tool = new Electrical(resultSet.getInt("ToolID"),
													resultSet.getString("ToolName"),
													resultSet.getString("ToolType"),
													resultSet.getInt("Quantity"),
													resultSet.getDouble("Price"),
													parseSupplier(resultSet),
													resultSet.getString("PowerType"));
					toolList.add(tool);

				}
				else if(resultSet.getString("ToolType").equals("NonElectrical")){
					NonElectrical tool = new NonElectrical(resultSet.getInt("ToolID"),
								resultSet.getString("ToolName"),
								resultSet.getString("ToolType"),
								resultSet.getInt("Quantity"),
								resultSet.getDouble("Price"),
								parseSupplier(resultSet));
					toolList.add(tool);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			return;
		}
		
	}
	
	public Tool getTool(int index) {
		return toolList.get(index);
	}

	public ArrayList<Tool> getToolList() {
		return toolList;
	}

	public void setToolList(ArrayList<Tool> toolList) {
		this.toolList = toolList;
	}
	

	public String toString() {
		
		String temp = "";
		for(Tool t : getToolList()) {
			if( t == null) {
				break;
			}
			temp += t + "\n";
		}
		return temp;
		
	}
	
	
}


