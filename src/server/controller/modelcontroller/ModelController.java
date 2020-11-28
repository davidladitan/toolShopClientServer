package server.controller.modelcontroller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;


import server.controller.databasecontroller.DatabaseController;
import server.controller.servercontroller.ServerController;
import universal.model.customermodel.Customer;
import universal.model.customermodel.CustomerList;
import universal.model.inventorymodel.Electrical;
import universal.model.inventorymodel.Inventory;
import universal.model.inventorymodel.NonElectrical;
import universal.model.inventorymodel.Order;
import universal.model.inventorymodel.ShopApp;
import universal.model.inventorymodel.SupplierList;
import universal.model.inventorymodel.Tool;
import universal.model.inventorymodel.ToolList;
import universal.model.serial.Message;


public class ModelController implements Runnable{
	private DatabaseController databaseController;
	private ServerController serverController;
	private Socket socket;

	
	
	
	public ModelController(Socket aSocket) {
		// TODO Auto-generated constructor stub
		setDatabaseController(new DatabaseController());
		setSocket(aSocket);	
	}

	/**
	 * Retrieves a list of Customers from SQL and returns it as a CustomerList
	 * @return a CustomerList of all customers in the SQL Database
	 */
	public CustomerList getCustomerListSQL() {
		ResultSet resultSet = databaseController.getCustomerList();
		CustomerList customerList = new CustomerList();
		customerList.parseCustomerList(resultSet);
		return customerList;
	}
	
	/**
	 * Retrieves Customer with a given customer ID and returns it as a CustomerList
	 * @param customerID the unique ID of the customer
	 * @return a CustomerList of a customer with given customerID in the SQL Datbase
	 */
	public CustomerList searchCustomerIDSQL(int customerID) {
		System.out.println("In searchCustomerIDSQL(int customerID) " + customerID);
		ResultSet resultSet = databaseController.searchCustomerID(customerID);
		System.out.println("In searchCustomerIDSQL(int customerID) got ResultSet");
		CustomerList customerList = new CustomerList();
		System.out.println("In searchCustomerIDSQL(int customerID) created CustomerList");
		customerList.parseCustomerList(resultSet);
		System.out.println("In searchCustomerIDSQL(int customerID) parsed");
		return customerList;
	}
	

	public CustomerList searchCustomerNameSQL(String last) {
		ResultSet resultSet = databaseController.searchCustomerName(last);
		CustomerList customerList = new CustomerList();
		customerList.parseCustomerList(resultSet);
		return customerList;
	}
	/**
	 * Retrieves a list of Customers who are a certain type as a CustomerList
	 * @param customerType the type of the customer, either R (Residential) or C (Commercial)
	 * @return CustomerList that contains the  list of customers of a given type
	 */
	public CustomerList searchCustomerTypeSQL(String customerType) {
		ResultSet resultSet = databaseController.searchCustomerType(customerType);
		CustomerList customerList = new CustomerList();
		customerList.parseCustomerList(resultSet);
		return customerList;
	}
	
	/**
	 * Deletes a given Customer from the SQL Database. Returns the Customer that was deleted.
	 * @param CustomerID the ID of the customer
	 * @return CustomerList that contains the customer that was deleted
	 */
	public CustomerList deleteCustomerSQL(int CustomerID) {
		ResultSet resultSet = databaseController.deleteCustomer(CustomerID);
		CustomerList customerList = new CustomerList();
		customerList.parseCustomerList(resultSet);
		return customerList;
	}
	
	/**
	 * Checks if the customer received from the client exists in the database. Used to determine if customer information needs to be inserted, updated or deleted
	 * @param customer is the Customer being updated or added to the database
	 * @return True if the customer exists (next operation should be update) or False if the client does not exist (next operation should be insert)
	 */
	public boolean checkCustomerExists(Customer customer) {
		ResultSet resultSet = databaseController.searchCustomerID(customer.getCustomerID());
		CustomerList customerList = new CustomerList();
		customerList.parseCustomerList(resultSet);
		if(!customerList.getCustomerList().isEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Updates or adds customer to the database depending on whether they exist
	 * @param customer the customer being added
	 * @return CustomerList with either the updated information or added customer
	 */
	public CustomerList updateOrAddCustomerSQL(Customer customer) {
		
		boolean isUpdate = checkCustomerExists(customer);
		ResultSet resultSet;
		
		if(isUpdate) {
			System.out.println("Updating Customer Information");
			resultSet = databaseController.updateCustomer(customer);
		}else {
			System.out.println("Adding New Customer Information");
			resultSet = databaseController.addCustomer(customer);
		}
		
		CustomerList customerList = new CustomerList();
		customerList.parseCustomerList(resultSet);
		return customerList;
	}
	

	
	/**
	 * 
	 * @param toolID
	 * @return
	 */
	public ToolList searchToolIDSQL(int toolID) {
		ResultSet resultSet =databaseController.searchToolID(toolID);
		ToolList toolList = new ToolList();
		toolList.parseToolList(resultSet);
		return toolList;
	}
	
	public ToolList searchToolNameSQL(String toolName) {
		ResultSet resultSet =databaseController.searchToolName(toolName);
		ToolList toolList = new ToolList();
		toolList.parseToolList(resultSet);
		return toolList;
	}
	
	public ToolList searchDeleteSQL(int toolID) {
		// TODO Auto-generated method stub
		ResultSet resultSet =databaseController.deleteTool(toolID);
		ToolList toolList = new ToolList();
		toolList.parseToolList(resultSet);
		return toolList;
	}
	
	public ToolList getToolListSQL() {
		// TODO Auto-generated method stub
		ResultSet resultSet =databaseController.getToolList();
		ToolList toolList = new ToolList();
		toolList.parseToolList(resultSet);
		return toolList;
	}
	
	public ToolList addToolElectricalSQL(Electrical tool) {
		ResultSet resultSet = databaseController.addElectricalTool((Electrical)tool);
		System.out.println("added electrical tool to db");
		ToolList toolList = new ToolList();
		toolList.parseToolList(resultSet);
		return toolList;
	}
	
	public ToolList addToolNonElectricalSQL(NonElectrical tool) {
		ResultSet resultSet = databaseController.addNonElectricalTool((NonElectrical)tool);
		ToolList toolList = new ToolList();
		toolList.parseToolList(resultSet);
		return toolList;
	}
	

	



	public void run() {
		

		CustomerList customerList = null;
		ToolList toolList = null;
		ShopApp shopapp = new ShopApp(new Inventory(), new SupplierList());
		int task = 0;
		Message message = null;
		
			try {
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				
				while(true) {
					
					out.flush();
					out.reset();
					message = (Message) in.readObject();
					task = message.getTaskNumber();
					System.out.println("this is the task " + message.getTaskNumber());

					
				switch (task){
				
				case 1://Get customer List
					customerList = getCustomerListSQL();
					System.out.println(customerList);
					message.setCustomerList(customerList);
					out.writeObject(message);
					break;
					
				case 2: //Search Customer by ID
					customerList= searchCustomerIDSQL(message.getSearchID());
					System.out.println(customerList);
					message.setCustomerList(customerList);
					out.writeObject(message);
					break;
								
				case 3: //"Search Customer by Name":
					customerList = searchCustomerNameSQL(message.getSearchString());
					System.out.println(customerList);
					message.setCustomerList(customerList);
					out.writeObject(message);
					break;
					
				case 4: // "Search Customer by Type":
					customerList= searchCustomerTypeSQL(message.getSearchString());
					System.out.println(customerList);
					message.setCustomerList(customerList);
					out.writeObject(message);
					break;
					
				case 5: //"Update or Add Customer information":
					customerList = updateOrAddCustomerSQL(message.getCustomer());
					System.out.println(customerList);
					message.setCustomerList(customerList);
					out.writeObject(message);
					break;
					
				case 6: // "Delete Customer":
					customerList = deleteCustomerSQL(message.getSearchID());
					System.out.println(customerList);
					message.setCustomerList(customerList);
					out.writeObject(message);
					break;
					
				case 7:// "Search Tool by ID"
					toolList = searchToolIDSQL(message.getSearchID());
					System.out.println(toolList);
					message.setToolList(toolList);
					out.writeObject(message);
					break;
				
				case 8://Search Tool by Name
					toolList = searchToolNameSQL(message.getSearchString());
					System.out.println(toolList);
					message.setToolList(toolList);
					out.writeObject(message);
					break;
				
				case 9://Delete Tool
					toolList = searchDeleteSQL(message.getSearchID());
					System.out.println(toolList);
					message.setToolList(toolList);
					out.writeObject(message);
					break;
					
				case 10://Add Tool
					String type = message.getTool().getType();
					if(type.equals("Electrical")) {
						System.out.println("currently in Electrical");
						toolList = addToolElectricalSQL((Electrical)message.getTool());
					}else {
						System.out.println("currently in NonElectrical");
						toolList = addToolNonElectricalSQL((NonElectrical)message.getTool());
					}
					message.setToolList(toolList);
					out.writeObject(message);
					System.out.println("response for add tool sent to client" + toolList);
					break;
					
				
				case 11://Get Tool List
					toolList = getToolListSQL();
					System.out.println(toolList);
					message.setToolList(toolList);
					out.writeObject(message);
					break;
					
				case 12://Get Order
					message.setOrder(shopapp.getOrder());
					System.out.println(shopapp.getOrder());
					out.writeObject(message);
					break;
				
				case 13://Sell Tool
					toolList = searchToolIDSQL(message.getSearchID());
					if(!toolList.getToolList().isEmpty()) {
						System.out.println("tool exists!!!");
						shopapp.addTool(toolList.getTool(0));
						shopapp.decreaseTool(message.getSearchID());
						message.setTool(shopapp.getTool(message.getSearchID()));
						databaseController.updateToolQuantity(message.getTool());
						Order order = shopapp.getOrder();
						if(order!=null) {
							databaseController.updateOrder(order);
						}
					}
					
					System.out.println(message.getTool());
					out.writeObject(message);
					break;
				
				case 14://Check Quantity
					toolList = searchToolIDSQL(message.getSearchID());
					message.setToolList(toolList);
					out.writeObject(message);
					break;
				
				}
				
			}
			
		       
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("hey");
				e.printStackTrace();
			} 

		
	}
	


	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public DatabaseController getDatabaseController() {
		return databaseController;
	}

	public void setDatabaseController(DatabaseController databaseController) {
		this.databaseController = databaseController;
	}

	
	
}
