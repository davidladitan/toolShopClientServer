package server.model.customermodel;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerList implements Serializable{
	private ArrayList<Customer> customerList;
	
	public CustomerList(){
		customerList = new ArrayList<Customer>();
	}
	
	/**
	 * Parses customers stored in an SQL Result Set into an ArrayList
	 * @param sqlSet is the result set from an SQL Query
	 */
	public void parseCustomerList(ResultSet resultSet) {
		Customer customer = null; 
		try {
			while(resultSet.next()) {
				
				if(resultSet.getString("Type").equals("R")) {
					customer = new Residential();

				}
				else if(resultSet.getString("Type").equals("C")){
					customer = new Commercial();
				}
	

				customer.setCustomerID(resultSet.getInt("ClientID"));
				customer.setFirstName(resultSet.getString("fName"));
				customer.setLastName(resultSet.getString("lName"));
				customer.setAddress(resultSet.getString("Address"));
				customer.setPostalCode(resultSet.getString("PostalCode"));
				customer.setPhoneNumber(resultSet.getString("PhoneNum"));
				customer.setCustomerType( resultSet.getString("Type"));
				customerList.add(customer);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Customer getCustomer(int index) {
		return customerList.get(index);
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}
	

	public String toString() {
		
		String temp = "";
		for(Customer c : getCustomerList()) {
			if( c == null) {
				break;
			}
			temp += c + "\n";
		}
		return temp;
		
	}
	
	
}
