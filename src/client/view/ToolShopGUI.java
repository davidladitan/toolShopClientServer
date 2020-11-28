package client.view;

import javax.swing.*;
import client.controller.viewcontroller.InventoryGUIController;
import client.controller.viewcontroller.CustomerGUIController;

import java.awt.*;

public class ToolShopGUI extends JFrame {
    JComponent customerView;
    JComponent inventoryView;
    JTabbedPane tabbedPane;

    public ToolShopGUI(JComponent customerView, JComponent inventoryView, String title) {
        super(title);
        this.customerView = customerView;
        this.inventoryView = inventoryView;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Customer Management System", customerView);
        tabbedPane.addTab("Inventory Management System", inventoryView);
        getContentPane().add(tabbedPane);

    }

    public static void main(String [] args){

        // add socket connection to main controller
        CustomerGUI customerView = new CustomerGUI("customer");
        CustomerGUIController guiController = new CustomerGUIController(customerView);

        InventoryGUI inventoryView = new InventoryGUI("inventory");
        InventoryGUIController inventoryGUIController = new InventoryGUIController(inventoryView);

        JFrame frame = new ToolShopGUI(customerView, inventoryView, "Tool shop app");
        frame.pack();
//        frame.setLayout(new GridLayout());
//        frame.setSize(1200, 700);
        frame.setVisible(true);
    }
}
