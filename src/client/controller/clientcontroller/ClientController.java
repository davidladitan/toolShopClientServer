package client.controller.clientcontroller;

import client.controller.modelcontroller.ModelController;
import client.controller.viewcontroller.CustomerGUIController;
import client.controller.viewcontroller.InventoryGUIController;
import client.view.CustomerGUI;
import client.view.InventoryGUI;
import client.view.ToolShopGUI;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientController {

    private Socket aSocket;
    private BufferedReader stdIn;
    private ObjectInputStream objectInput;
    private ObjectOutputStream objectOutput;

    public ClientController (String serverName, int portNumber) {

        try {
            aSocket = new Socket (serverName, portNumber);

        }catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public ObjectInputStream getObjectInput() {
        return objectInput;
    }
    public ObjectOutputStream getObjectOutput() {
        return objectOutput;
    }
	public Socket getaSocket() {
		return aSocket;
	}

    public static void main(String [] args){
        ClientController controller = new ClientController("localHost", 9999);

        // set up gui
        CustomerGUI customerView = new CustomerGUI("Customer page");
        CustomerGUIController customerController = new CustomerGUIController(customerView);

        InventoryGUI inventoryView = new InventoryGUI("Inventory page");
        InventoryGUIController inventoryGUIController = new InventoryGUIController(inventoryView);

        JFrame frame = new ToolShopGUI(customerView, inventoryView, "Tool shop app");

        //set up controllers
        ModelController modelController = new ModelController(controller);
        modelController.setCustomerController(customerController); // put in constructor
        modelController.setInventoryController(inventoryGUIController);

        customerController.setModelController(modelController);
        inventoryGUIController.setModelController(modelController);

        // display GUI
        frame.setSize(1000, 700);
        frame.setVisible(true);

    }
    
     
}
