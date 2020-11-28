package server.controller.databasecontroller;

import java.sql.*;
import java.util.ArrayList;
//import universal.model.*;
import universal.model.customermodel.Customer;
import universal.model.inventorymodel.Electrical;
import universal.model.inventorymodel.NonElectrical;
import universal.model.inventorymodel.Order;
import universal.model.inventorymodel.OrderLine;
import universal.model.inventorymodel.Tool;

public class DatabaseController implements IDBCredentials {

	// Attributes
	/**
	 * Object of type Connection, the jdbc connection used to make PreparedStatements
	 */
	private Connection jdbc_connection;//Object of type connection from the JDBC class that deals with connecting to 

	/**
	 * Object of type PreparedStatement from JDBC class that enables the creation of Prepared Query Statements
	 */
	private PreparedStatement pStat;
	
	/**
	 * Object of type ResultSet from the JDBC class that stores the result of the query
	 */
	private ResultSet resultSet;//object of type ResultSet from the JDBC class that stores the result of the query
	
	/**
	 * Name of the database that stores all the tool shop information
	 */
	private String databaseName = "toolshop";
	//Unused Code
	//the database 
	//private Statement stmt; //object of type statement from JDBC class that enables the creation "Query 
	//statements"
	
	/**
	 * Initializes the connection between the databaseController and the SQL server toolshop (details in the IDBCredentials) and makes the first
	 * use toolshop SQL command to allow every successive SQL query to work.
	 */
	public DatabaseController() {
		initializeConnection();
		useToolShopCommand();
	}
	
	/**
	 * Initializes the connection between the databaseController and the SQL server toolshop (details in the IDBCredentials)
	 */
	public void initializeConnection() {
		try {
            //Register JDBC driver
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
            //Open a connection
			jdbc_connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			System.out.println("MySQLJDBC: Connection made with SQL Server");
		} catch (SQLException e) {
			System.out.println("Problem");
			e.printStackTrace();
		}

	}

	/**
	 * First SQL Query that directs all successive SQL queries to work on the toolshop database.
	 */
	public void useToolShopCommand() {
		
		try {
			String query = "USE "+databaseName;
			pStat = jdbc_connection.prepareStatement(query);
			pStat.executeUpdate();
			System.out.println("Using " + databaseName + " SQL database");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes the PreparedStatement, Connection and ResultSet objects
	 */
	public void close() {
		try {
			//stmt.close();
			//conn.close();
			//rs.close();
			pStat.close();
			jdbc_connection.close();
			resultSet.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieves a list of Customers from the SQL toolshop database. 
	 * @return ResultSet that is a list of Customers or null if there is no list of Customer
	 */
	public ResultSet getCustomerList() {
		
		try {
			String tableName  = "CLIENT";
			//String query = "SELECT * FROM " + tableName + " WHERE ClientID IS NOT NULL";
			String query = "SELECT * FROM " + tableName + " WHERE ClientID IS NOT NULL";
			pStat = jdbc_connection.prepareStatement(query);
			resultSet = pStat.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Search and retrieves customer with a given CustomerID from the SQL toolshop database.
	 * @param CustomerID
	 * @return ResultSet that contains the Customer with a given CustomerID
	 */
	public ResultSet searchCustomerID(int CustomerID) {
		String tableName  = "CLIENT";
		String query = "SELECT * FROM " + tableName + " WHERE ClientID = ?";
		try {
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setInt(1, CustomerID);
			resultSet = pStat.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * DEFUNCT
	 * Search and retrieves the Customer from the SQL Database with the corresponding last name
	 * @param lastName the last name of the customer being searched
	 * @return Result Set that contains the Customer that matches the last name search query.
	 */
	public ResultSet searchCustomerLastName(String lastName) {
		String tableName  = "CLIENT";
		String nameType = "LName";
		
		String query = "SELECT * FROM " + tableName + " WHERE " + nameType + " = ?";
		try {
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setString(1, lastName);
			resultSet = pStat.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * Search and Retrieves a customer who has the last and first name as contained by customer input parameter
	 * @param customer the customer who is being searched depending on implementation, everything but the firstname and lastname of customer can be null.
	 * @return ResultSet that contains a Customer that matches the full name search query.
	 *//*
	public ResultSet searchCustomerName(Customer customer) {

		String query = "SELECT * FROM CLIENT WHERE FName = ? AND LName = ?";
		try {
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setString(1, customer.getFirstName());
			pStat.setString(2, customer.getLastName());
			resultSet = pStat.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	*/
	public ResultSet searchCustomerName(String last) {

		String query = "SELECT * FROM CLIENT WHERE LName LIKE ?";
		try {
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setString(1, last);
//			pStat.setString(2, last);
			resultSet = pStat.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	/**
	 * Search and retrieves a list of Customers that matches a given type (R or C)
	 * @param type the type of the customer denoted as either R for Residential or C for Commercial
	 * @return a ResultSet that contains all Customers with the given Type.
	 */
	public ResultSet searchCustomerType(String type) {
		String tableName  = "CLIENT";
		String nameType = "type"; //You may have to change this! In the sql server, client table, this column is type, but i shouldve changed it to Type
		
		String query = "SELECT * FROM " + tableName + " WHERE " + nameType + " = ?";
		try {
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setString(1, type);
			resultSet = pStat.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * Updates customer tuple contained in the database with new information found in given customer 
	 * @param customer is the Customer object that contains all the changed data
	 * @return ResultSet containing the customer after being changed ??? can it be void??
	 */
	public ResultSet updateCustomer(Customer customer) {
		//String tableName  = "CLIENT";
		//String nameType = "ClientID"; //You may have to change this! In the sql server, client table, this column is type, but i shouldve changed it to Type
		
		String query = "UPDATE CLIENT SET FName = ?, LName = ?, type = ?, Address = ?, PhoneNum = ?, PostalCode = ? WHERE ClientID = ?"; 
		try {
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setString(1, customer.getFirstName());
			pStat.setString(2, customer.getLastName());
			pStat.setString(3, customer.getCustomerType());
			pStat.setString(4, customer.getAddress());
			pStat.setString(5, customer.getPhoneNumber());
			pStat.setString(6, customer.getPostalCode());
			pStat.setInt(7, customer.getCustomerID());
			pStat.executeUpdate();
			resultSet = searchCustomerID(customer.getCustomerID());
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * Adds a customer tuple contained in the database with the new information found in a given customer
	 * @param customer is the Customer object that contains all the new data
	 * @return ResultSet containing the customer after being added
	 */
	public ResultSet addCustomer(Customer customer) {
		//String tableName  = "CLIENT";
		//String nameType = "ClientID"; //You may have to change this! In the sql server, client table, this column is type, but i shouldve changed it to Type
		
		String query = "INSERT INTO CLIENT (ClientID, FName, LName, type, Address, PhoneNum, PostalCode) VALUES (?,?,?,?,?,?,?)  "; 
		try {
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setInt(1, customer.getCustomerID());
			pStat.setString(2, customer.getFirstName());
			pStat.setString(3, customer.getLastName());
			pStat.setString(4, customer.getCustomerType());
			pStat.setString(5, customer.getAddress());
			pStat.setString(6, customer.getPhoneNumber());
			pStat.setString(7, customer.getPostalCode());
			pStat.executeUpdate();
			resultSet = searchCustomerID(customer.getCustomerID());
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * Deletes a customer tuple contained in the database that has the given Customer ID
	 * @param CustomerID the ID of the customer that needs to be deleted
	 * @return ResultSet containing the customer that was deleted
	 */
	public ResultSet deleteCustomer(int CustomerID) {
		resultSet = searchCustomerID(CustomerID);
		
		try {
			String query = "DELETE FROM CLIENT WHERE ClientID = ? "; 
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setInt(1, CustomerID);
			pStat.executeUpdate();
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Retrieves a list of TOOLS from the SQL toolshop database. 
	 * @return ResultSet that is a list of Customers or null if there is no list of Customer
	 */
	public ResultSet getToolList() {
		
		try {
			//String tableName  = "TOOL";
			//String query = "SELECT * FROM " + tableName + " WHERE ToolID IS NOT NULL";
			String query = "SELECT T.ToolID, T.Name AS 'ToolName', T.Type AS 'ToolType', T.Quantity, T.Price, T.SupplierID, "
					+ "S.Name as 'SupplierName', S.Type AS 'SupplierType', S.Address, S.Cname, S.PhoneNum, "
					+ "I.ImportTax, E.PowerType "
					+ "FROM SUPPLIER as S, TOOL as T "
					+ "LEFT JOIN INTERNATIONAL as I USING (SupplierID) "
					+ "LEFT JOIN ELECTRICAL as E USING (ToolID) "
					+ "WHERE T.SupplierID = S.SupplierID "
					+ "ORDER BY T.ToolID;";
			
			
			
			pStat = jdbc_connection.prepareStatement(query);
			resultSet = pStat.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
		
	/**
	 * Search and retrieves Tool with a given ToolID from the SQL toolshop database.
	 * @param ToolID the ID of the tool
	 * @return ResultSet that contains the Tool with a given ToolID
	 */
	public ResultSet searchToolID(int toolID) {

		String query = "SELECT T.ToolID, T.Name AS 'ToolName', T.Type AS 'ToolType', T.Quantity, T.Price, T.SupplierID, "
				+ "S.Name as 'SupplierName', S.Type AS 'SupplierType', S.Address, S.Cname, S.PhoneNum, "
				+ "I.ImportTax, E.PowerType "
				+ "FROM SUPPLIER as S, TOOL as T "
				+ "LEFT JOIN INTERNATIONAL as I USING (SupplierID) "
				+ "LEFT JOIN ELECTRICAL as E USING (ToolID) "
				+ "WHERE T.ToolID = ? and T.SupplierID = S.SupplierID;";
		
		
		try {
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setInt(1, toolID);
			resultSet = pStat.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

	
	/**
	 * Search and Retrieves a tool who has the name as provide by input parameter
	 * @param toolName the name of the tool being searched
	 * @return ResultSet that contains a Tool that matches the name search query.
	 */
	public ResultSet searchToolName(String toolName) {
		
		String query = "SELECT T.ToolID, T.Name AS 'ToolName', T.Type AS 'ToolType', T.Quantity, T.Price, T.SupplierID, "
				+ "S.Name as 'SupplierName', S.Type AS 'SupplierType', S.Address, S.Cname, S.PhoneNum, "
				+ "I.ImportTax, E.PowerType "
				+ "FROM SUPPLIER as S, TOOL as T "
				+ "LEFT JOIN INTERNATIONAL as I USING (SupplierID) "
				+ "LEFT JOIN ELECTRICAL as E USING (ToolID) "
				+ "WHERE T.Name = ? and T.SupplierID = S.SupplierID;";

		try {
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setString(1, toolName);
			resultSet = pStat.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * Use to retrieve the corresponding Supplier for a given Tool
	 * @param tool
	 * @return
	 */
	public ResultSet searchSupplierID(Tool tool) {
		try {
			String query = "SELECT * FROM SUPPLIER WHERE SupplierID = ? "; 
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setInt(1, tool.getSupplier().getSupplierID());
			resultSet = pStat.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Use to check if the SupplierID exists, if it does not exist then nothing should be added 
	 * @param tool is the tool being added
	 * @return True wit the SupplierID exists, false otherwise.
	 */
	public boolean supplierIDExists(Tool tool) {
		resultSet = searchSupplierID(tool);	
		try {

			if(resultSet.next())
				return true;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Use to check if the tool exists, if it exists nothing should be added.
	 * @param tool is the tool being added
	 * @return True if the tool exists, false otherwise.
	 */
	public boolean toolExists(Tool tool) {
		resultSet = searchToolID(tool.getToolID());	
		try {
			//if( resultSet.getInt("ToolID") == tool.getToolID())
			if(resultSet.next())
				return true;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Adds a Electrical Tool tuple in the database with all the information supplied by the Electrical Tool 
	 * @param tool is an Electrical object that contains all the information to be added into the database
	 * @return ResultSet containing the Tool after being added, null if the Tool was not added 
	 */
	public ResultSet addElectricalTool(Electrical tool) {
		//String tableName  = "CLIENT";
		//String nameType = "ClientID"; //You may have to change this! In the sql server, client table, this column is type, but i shouldve changed it to Type
		
		if(supplierIDExists(tool) == false || toolExists(tool)==true)
			return null;
		
		try {
			String query = "INSERT INTO TOOL (ToolID, Name, Type, Quantity, Price, SupplierID) VALUES (?,?,?,?,?,?)  "; 
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setInt(1, tool.getToolID());
			pStat.setString(2, tool.getName());
			pStat.setString(3, tool.getType());
			pStat.setInt(4, tool.getQuantityStocked());
			pStat.setDouble(5, tool.getPrice());
			pStat.setInt(6, tool.getSupplier().getSupplierID());
			pStat.executeUpdate();
			query = "INSERT INTO ELECTRICAL (ToolID, PowerType) VALUES (?,?)";
			pStat.setInt(1,  tool.getToolID());
			pStat.setString(2, tool.getPowerInformation());
			
			resultSet = searchToolID(tool.getToolID());
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * Adds a NonElectrical Tool tuple in the database with all the information supplied by the NonElectrical Tool 
	 * @param tool is an NonElectrical object that contains all the information to be added into the database
	 * @return ResultSet containing the Tool after being added, null if the Tool was not added 
	 */
	public ResultSet addNonElectricalTool(NonElectrical tool) {
		//String tableName  = "CLIENT";
		//String nameType = "ClientID"; //You may have to change this! In the sql server, client table, this column is type, but i shouldve changed it to Type
		
		if( supplierIDExists(tool) == false ||toolExists(tool)==true)
			return null;
		
		try {
			String query = "INSERT INTO TOOL (ToolID, Name, Type, Quantity, Price, SupplierID) VALUES (?,?,?,?,?,?)  ";
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setInt(1, tool.getToolID());
			pStat.setString(2, tool.getName());
			pStat.setString(3, tool.getType());
			pStat.setInt(4, tool.getQuantityStocked());
			pStat.setDouble(5, tool.getPrice());
			pStat.setInt(6, tool.getSupplier().getSupplierID());
			pStat.executeUpdate();
			resultSet = searchToolID(tool.getToolID());
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	/**
	 * Deletes a customer tuple contained in the database that has the given Customer ID
	 * @param CustomerID the ID of the customer that needs to be deleted
	 * @return ResultSet containing the customer that was deleted
	 */
	public ResultSet deleteTool(int toolID) {
		resultSet = searchToolID(toolID);
		
		try {
			String query = "DELETE FROM TOOL WHERE ToolID = ? "; 
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setInt(1, toolID);
			pStat.executeUpdate();
			resultSet = this.searchToolID(toolID);
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public ResultSet updateToolQuantity(Tool tool) {
		
		try {
			String query = "UPDATE TOOL SET Quantity = ? WHERE ToolID = ?;";
			pStat = jdbc_connection.prepareStatement(query);
			pStat.setInt(1, tool.getQuantityStocked());
			pStat.setInt(2, tool.getToolID());
			pStat.executeUpdate();
			resultSet = this.searchToolID(tool.getToolID());
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ResultSet updateOrder(Order order) {
		
		
				try {
					String query = "REPLACE INTO  ORDERS (OrderID, Date) VALUES (?,?)";
					pStat = jdbc_connection.prepareStatement(query);
					System.out.println("Getting orderID");
					pStat.setInt(1, order.getOrderID());
					pStat.setString(2, order.getDate());
					pStat.executeUpdate();
					ArrayList<OrderLine>orderLineList = order.getOrderDB();
					//for (OrderLine o : orderLineList){
					System.out.println("OrderLineList is this big " + orderLineList.size());
					for(int i = 0; i < orderLineList.size(); i++) {
						OrderLine orderLine = orderLineList.get(i);
						query = "REPLACE INTO  ORDERLINE (OrderID, ToolID, SupplierID, Quantity) VALUES (?,?,?,?)";
						pStat = jdbc_connection.prepareStatement(query);
						pStat.setInt(1, order.getOrderID());
						pStat.setInt(2, orderLine.getOrderTool().getToolID());
						pStat.setInt(3, orderLine.getSupplier().getSupplierID());
						pStat.setInt(4, orderLine.getQuantityOrdered());
						pStat.executeUpdate();
					}
					return resultSet;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		
		return resultSet;
		
	}
	
	public static void main(String[] args0) {
		DatabaseController myApp = new DatabaseController();
		myApp.initializeConnection();
		//myApp.createTable();
		//myApp.insertUser();
		//myApp.insertUserPreparedStatment(1, "sam", "Smith");
		myApp.close();
	}
	
	public ResultSet getResultSet() {
		return resultSet;
	}
	


	

	
	
	
	
	
	
	
	
	//Unused Code
	
	
	/*
	public void insertUser() {
		try {
			stmt = conn.createStatement();
			String insert = "INSERT INTO STUDENT (ID, first, last) values (11, 'Joe','Jones')";
			int rowCount = stmt.executeUpdate(insert);
			System.out.println("row Count = " + rowCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createTable() {
		String sql = "CREATE TABLE STUDENT " + "(id INTEGER not NULL, " + " first VARCHAR(255), "
				+ " last VARCHAR(255), " + " PRIMARY KEY ( id ))";

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		System.out.println("Created table in given database...");
	}
	private void insertUserPreparedStatment(int i, String fName, String lName) {
		// TODO Auto-generated method stub
		try {
			String query = "INSERT INTO STUDENT (ID, first, last) values (?,?,?)";
			PreparedStatement pStat = conn.prepareStatement(query);
			pStat.setInt(1, i);
			pStat.setString(2, fName);
			pStat.setString(3, lName);
			int rowCount = pStat.executeUpdate();
			System.out.println("row Count = " + rowCount);
			pStat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
*/
}
