package client.controller.modelcontroller;
//import universal.*;
import universal.model.customermodel.Commercial;
import universal.model.customermodel.Customer;
import universal.model.customermodel.CustomerList;
import universal.model.customermodel.Residential;
import universal.model.inventorymodel.*;
import universal.model.serial.Message;
import client.controller.viewcontroller.InventoryGUIController;
import client.controller.viewcontroller.CustomerGUIController;
import client.controller.clientcontroller.ClientController;

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * author: David Laditan
 */
public class ModelController {


    private InventoryGUIController inventoryController;
    private CustomerGUIController customerController;
    private Message message;
    private ClientController clientController;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Message response;
    private Vector<String> displayList;
    private ArrayList<Customer> listOfCustomers;
    private ArrayList<Tool> listOfTools;

    public ModelController(ClientController clientController){
        this.clientController = clientController;
        message = new Message(0);

      try {
    	outputStream = new ObjectOutputStream(clientController.getaSocket().getOutputStream());
		inputStream = new ObjectInputStream(clientController.getaSocket().getInputStream());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

        response = null;
      listOfCustomers = null;

    }

    /**
     *sends request to server to return customers with a given customer id
     * @param id customer id to search
     */
    public void searchCustomerById(int id) {
        message.setTaskNumber(2);
        message.setSearchID(id);
        displayList = new Vector<>();
        listOfCustomers = new ArrayList<>();
        try {
            ArrayList<Customer> customers = searchCustomer();
            if(customers.isEmpty()){
                displayList.add("Customer with Id:" + id + " does not exist");
            }else{
                buildCustomerDisplayList(customers);
            }

            for(String c : displayList){
                System.out.println(c);
            }

            customerController.setDisplayResult(displayList);

            outputStream.flush();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return array list containing Customer objects, which are the results of the search request
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private ArrayList <Customer> searchCustomer() throws IOException, ClassNotFoundException {
        outputStream.flush();
        outputStream.reset();
        outputStream.writeObject(message);  // sends message to server. message contains task order and id or name of customer


        response = (Message) inputStream.readObject();

        CustomerList customerList = response.getCustomerList(); // get the result of the search query from the resturn message
        ArrayList<Customer> customers =  customerList.getCustomerList();
        listOfCustomers = customers;
        return customers;
    }

    /**
     * sends request to server to return customers with a given name
     * @param name customer name to search
     */
    public void searchCustomerByName(String name) {
        message.setTaskNumber(3);
        System.out.println("message task number set to " + message.getTaskNumber());
        String searchName = "%" + name + "%";
        message.setSearchString(searchName);
        displayList = new Vector<>();
        listOfCustomers = new ArrayList<>();
        try {

            ArrayList<Customer> customers = searchCustomer();

            if(customers.isEmpty()){
                displayList.add("No customers with name " + name);
            }else{
                buildCustomerDisplayList(customers);
            }


            customerController.setDisplayResult(displayList);


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * private method to add search result to display list
     * @param customers
     */
    private void buildCustomerDisplayList(ArrayList<Customer> customers) {
        String header = String.format("%-5s %-15s %-15s %-5s %-15s", "Id", "First Name", "Last Name", "Type", "Phone number");
        displayList.add(header);
        for (Customer customer : customers) {
            String customerDisplay = String.format("%-5d %-20s %-15s %-8s %-15s",customer.getCustomerID(),customer.getFirstName(), customer.getLastName(), customer.getCustomerType(), customer.getPhoneNumber());
            displayList.add(customerDisplay);
        }
    }

    /**
     * sends request to server to return customers of a given customer type
     * @param type customer type to search
     */
    public void searchCustomerByType(String type) {
        message.setTaskNumber(4);
        message.setSearchString(type);
        displayList = new Vector<>();
        listOfCustomers = new ArrayList<>();
        try {

            System.out.println("\n\n task number set to " + message.getTaskNumber());
            System.out.println("message Id set to " + message.getSearchString());

            ArrayList<Customer> customers = searchCustomer();
            //
            if(customers.isEmpty()){
                displayList.add("No customers of type " + type);
            }else{
                buildCustomerDisplayList(customers);
            }
            customerController.setDisplayResult(displayList);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * sends request to the server to add a tool to the database
     * @param clientInfo customer information gotten from text fields
     */
    public void addCustomerToDB(String clientInfo, String clientType) {
        message.setTaskNumber(5);
        System.out.println("client to add to db " + clientInfo);

        Customer customer = null;
        if (clientType.equals("R")){
            customer = new Residential();
        }else if(clientType.equals("C")){
            customer = new Commercial();
        }
        String [] customerInfo = clientInfo.split(";");
        int id = Integer.parseInt(customerInfo[0]);
        customer.setCustomerID(id);
        customer.setFirstName(customerInfo[1]);
        customer.setLastName(customerInfo[2]);
        customer.setAddress(customerInfo[3]);
        customer.setPostalCode(customerInfo[4]);
        customer.setPhoneNumber(customerInfo[5]);
        customer.setCustomerType(clientType);

        message.setCustomer(customer);

        try {
            outputStream.flush();
            outputStream.reset();
            outputStream.writeObject(message);
            response = (Message) inputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * sends request to the server to delete a tool from the database
     * @param clientInfo customer information gotten from text fields
     */
    public void deleteCustomerFromDB(String clientInfo) {

        message.setTaskNumber(6);
        System.out.println("client to delete from db " + clientInfo);


        String [] customerInfo = clientInfo.split(";");
        int id = Integer.parseInt(customerInfo[0]);
        message.setSearchID(id);

        try {
            outputStream.flush();
            outputStream.reset();
            outputStream.writeObject(message);
            response = (Message) inputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     *sends request to server to return a tool with a given name
     * @param toolName tool name gotten from text field
     */
    public void searchToolByName(String toolName) {
        message.setTaskNumber(8);
        message.setSearchString(toolName);
        displayList = new Vector<>();
        listOfTools = new ArrayList<>();
        try {

            System.out.println("\n\nmessage tool name set to " + message.getSearchString());


            ArrayList<Tool> tools = searchTool();

            for (Tool t : tools) {
                System.out.println("received tool" + t.getToolID()+ t.getName() + t.getType());
            }

            if(tools.isEmpty()){
                displayList.add("Tool with name:" + toolName + " does not exist");
            }else{
                buildToolDisplayList(tools);
            }


            for (String c : displayList) {
                System.out.println(c);
            }

//            customerController.setModelController(this);
            inventoryController.setDisplayResult(displayList);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * helper method to add server response to display list
     * @param tools response from server, a list of Tool objects
     */
    private void buildToolDisplayList(ArrayList<Tool> tools) {
        String header = String.format("%-12s %-20s %-15s %-20s", "Tool Id", "Tool name", "Tool type", "Price");
        displayList.add(header);
        for (Tool t : tools) {
            String tool = String.format("%-12d %-20s %-15s %-20.2f",t.getToolID(),t.getName(), t.getType(),t.getPrice());
            displayList.add(tool);
        }
    }

    public void searchToolById(int toolId) {
        message.setTaskNumber(7);
        message.setSearchID(toolId);
        displayList = new Vector<>();
        listOfTools = new ArrayList<>();
        try {

            ArrayList<Tool> tools = searchTool(); // adds server response to tools
            if(tools.isEmpty()){
                displayList.add("Tool with Id:" + toolId + " does not exist");
            }else{
                buildToolDisplayList(tools);
            }

            // prints display list to terminal
            for (String c : displayList) {
                System.out.println(c);
            }

            inventoryController.setDisplayResult(displayList);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * helper method that sends request message to the server and puts response in a Tool array list
     * @return array list of tools
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private ArrayList<Tool> searchTool() throws IOException, ClassNotFoundException {
        outputStream.flush();
        outputStream.reset();
        outputStream.writeObject(message);

        System.out.println("after Outputstream");
        response = (Message) inputStream.readObject();

        System.out.println("after Inputstream");
        ToolList toolList = response.getToolList();
        ArrayList<Tool> tools = toolList.getToolList();
        listOfTools = tools;
        return tools;
    }


    /**
     * checks the quantity of a tool in stock
     * @param toolId tool id of tool
     */
    public void checkQuantityById(int toolId) {
        message.setTaskNumber(14);
        message.setSearchID(toolId);
        displayList = new Vector<>();
        listOfTools = new ArrayList<>();
        try {

            //outputStream = new ObjectOutputStream(clientController.getaSocket().getOutputStream());
            //inputStream = new ObjectInputStream(clientController.getaSocket().getInputStream());
            System.out.println("\n\nmessage tool id set to " + message.getSearchID());
            System.out.println("before outputStream");

            ArrayList<Tool> tools = searchTool();

            if(tools.isEmpty()){
                displayList.add("Tool with Id:" + toolId + " does not exist");
            }else{
                String header = String.format("%-12s %-20s %-15s %-20s", "Tool Id", "Tool name", "Tool type", "Quantity in stock");
                displayList.add(header);
                for (Tool t : tools) {
                    String tool = String.format("%-12d %-20s %-15s %-20d",t.getToolID(),t.getName(), t.getType(),t.getQuantityStocked());
                    displayList.add(tool);
                }
            }

            for (Tool t : tools) {
                System.out.println("received tool" + t.getToolID()+ t.getName() + t.getType());
            }



            for (String c : displayList) {
                System.out.println(c);
            }

//            customerController.setModelController(this);
            inventoryController.setDisplayResult(displayList);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * sends request to server asking for a list of all tools in the databse
     */
    public void getAllTools() {
        message.setTaskNumber(11);
        displayList = new Vector<>();
        listOfTools = new ArrayList<>();
        try {

            System.out.println("\n\nmessage tool id set to " + message.getSearchID());
            System.out.println("before outputStream");

            ArrayList<Tool> tools = searchTool();

            if(tools.isEmpty()){
                displayList.add("No tools in Inventory");
            }else{
                buildToolDisplayList(tools);
            }

            inventoryController.setDisplayResult(displayList);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * sends request to server asking for all orders generated. Displays the result on the GUI and also writes to orders.txt file
     */
    public void getAllOrders() {
        message.setTaskNumber(12);
        displayList = new Vector<>();
        try {

            //outputStream = new ObjectOutputStream(clientController.getaSocket().getOutputStream());
            //inputStream = new ObjectInputStream(clientController.getaSocket().getInputStream());
            System.out.println("\n\nmessage tool id set to " + message.getSearchID());
            System.out.println("before outputStream");

            outputStream.flush();
            outputStream.reset();
            outputStream.writeObject(message);

            System.out.println("after Outputstream");
            response = (Message) inputStream.readObject();

            System.out.println("after Inputstream");

            Order order = response.getOrder();


            if (order == null){
                displayList.add("No orders for today");
            }else{
                ArrayList <OrderLine> orderLines = order.getOrderDB();
                String orderString = "ORDER ID: " + order.getOrderID();
                String dateOrdered = "Date Ordered: " + order.getDate();
                String header = String.format("%-12s %-20s %-15s", "Item Name", "Quantity Ordered", "Supplier Id");
                displayList.add(orderString);
                displayList.add(dateOrdered);
                displayList.add(" ");
                displayList.add(header);
                for (OrderLine line : orderLines){
                    orderString += "\n\nItem description: "+ line.getName() +"\nAmount ordered: "+ line.getQuantityOrdered()
                            + "\nSupplierID: "+ line.getSupplier().getSupplierID();
                    displayList.add(String.format("%-19s %-25d %-15d", line.getName(), line.getQuantityOrdered(), line.getSupplier().getSupplierID()));
                }

                BufferedWriter output = null;
                try {
                    File file = new File("orders.txt");
                    output = new BufferedWriter(new FileWriter(file));
                    output.write(orderString);
                } catch ( IOException e ) {
                    e.printStackTrace();
                } finally {
                    if ( output != null ) {
                        output.close();
                    }
                }


            }

            inventoryController.setDisplayResult(displayList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * sends a request to the server to add a tool to the database
     * @param toolInfo tool information gotten from text fields
     */
        public void addToolToDB(String toolInfo) {
            message.setTaskNumber(10);
            System.out.println("tool to add to db " + toolInfo);

            Tool tool = null;

            String [] itemInfo = toolInfo.split(";");

            if (itemInfo[2].equals("Electrical")){
                tool = new Electrical();
            }else {
                tool = new NonElectrical();
            }

            int id = Integer.parseInt(itemInfo[0]);
            double price = Double.parseDouble(itemInfo[4]);
            int qty = Integer.parseInt(itemInfo[5]);
            int supplierId = Integer.parseInt(itemInfo[6]);

            tool.setToolD(id);
            tool.setName(itemInfo[1]);
            tool.setType(itemInfo[2]);
            tool.setPrice(price);
            tool.setQuantityStocked(qty);
            Supplier newSupplier = new International();
            newSupplier.setSupplierID(supplierId);
            tool.setSupplier(newSupplier);


            message.setTool(tool);

            try {
                outputStream.flush();
                outputStream.reset();
                outputStream.writeObject(message);
                response = (Message) inputStream.readObject();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

    /**
     * sends a request to the server to remove a tool from the database
     *  @param toolInfo tool information gotten from text fields
     */
    public void removeToolFromDB(String toolInfo) {
        message.setTaskNumber(9);
        System.out.println("tool to delete from db " + toolInfo);


        String [] itemInfo = toolInfo.split(";");
        int id = Integer.parseInt(itemInfo[0]);
        message.setSearchID(id);

        try {
            outputStream.flush();
            outputStream.reset();
            outputStream.writeObject(message);
            response = (Message) inputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * sends a request to server to reduce tool quantity by 1
     * @param toolInfo tool information gotten from text fields
     */
    public void decreaseToolQuantity(String toolInfo) {
        message.setTaskNumber(13);
        System.out.println("tool to sell" + toolInfo);


        String [] itemInfo = toolInfo.split(";");
        int id = Integer.parseInt(itemInfo[0]);
        message.setSearchID(id);

        try {
            outputStream.flush();
            outputStream.reset();
            outputStream.writeObject(message);
            response = (Message) inputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void setInventoryController(InventoryGUIController inventoryController) {
        this.inventoryController = inventoryController;
    }

    public void setCustomerController(CustomerGUIController customerController) {
        this.customerController = customerController;
    }

    public ArrayList<Customer> getListOfCustomers() {
        return listOfCustomers;
    }

    public ArrayList<Tool> getListOfTools() {
        return listOfTools;
    }


}




