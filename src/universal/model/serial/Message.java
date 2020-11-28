package universal.model.serial;
import java.io.Serializable;

import universal.model.customermodel.Customer;
import universal.model.customermodel.CustomerList;
import universal.model.inventorymodel.Order;
import universal.model.inventorymodel.Tool;
import universal.model.inventorymodel.ToolList;


public class Message implements Serializable {
	private int taskNumber;
	private String errorMessage;
	private int searchID;
	private String searchString;
	private String searchString2;
	private CustomerList customerList;
	private Customer customer;
	private ToolList toolList;
	private Tool tool;
	private Order order;
	
	
	public Message(int taskNumber){
		this.setTaskNumber(taskNumber);
		
	}


	public int getTaskNumber() {
		return taskNumber;
	}


	public void setTaskNumber(int taskNumber) {
		this.taskNumber = taskNumber;
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public int getSearchID() {
		return searchID;
	}


	public void setSearchID(int searchID) {
		this.searchID = searchID;
	}


	public String getSearchString() {
		return searchString;
	}


	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}


	public String getSearchString2() {
		return searchString2;
	}


	public void setSearchString2(String searchString2) {
		this.searchString2 = searchString2;
	}


	public CustomerList getCustomerList() {
		return customerList;
	}


	public void setCustomerList(CustomerList customerList) {
		this.customerList = customerList;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public ToolList getToolList() {
		return toolList;
	}


	public void setToolList(ToolList toolList) {
		this.toolList = toolList;
	}


	public Tool getTool() {
		return tool;
	}


	public void setTool(Tool tool) {
		this.tool = tool;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}

}
	