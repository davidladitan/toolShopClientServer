package client.controller.viewcontroller;

import client.controller.modelcontroller.ModelController;
import client.view.InventoryGUI;
import universal.model.customermodel.Customer;
import universal.model.inventorymodel.Electrical;
import universal.model.inventorymodel.Tool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

public class InventoryGUIController {
    InventoryGUI inventoryView;

    public void setModelController(ModelController modelController) {
        this.modelController = modelController;
    }

    ModelController modelController;

    public InventoryGUIController(InventoryGUI inventoryView){
        this.inventoryView = inventoryView;
        inventoryView.addListeners(new InventoryListener());
        inventoryView.addMouseListeners(new InventoryListener());

    }

    public void setDisplayResult(Vector<String> arr) {
        inventoryView.setResultListData(arr);
    }


    class InventoryListener implements ActionListener, MouseListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchCriteria = "";
            String toolIdentifier = "";
            if (e.getSource() == inventoryView.getSearchToolButton() || e.getSource() == inventoryView.toolInputgetter()){
                searchCriteria = inventoryView.getToolSearchTypeRadio();
                toolIdentifier = inventoryView.getToolInput();

                if(searchCriteria.equals("Tool Name")){
                    modelController.searchToolByName(toolIdentifier);
                }else if(searchCriteria.equals("Tool Id")){
                    int toolId = Integer.parseInt(toolIdentifier);
                    modelController.searchToolById(toolId);
                }
                System.out.println(searchCriteria);
                System.out.println(toolIdentifier);
            }
            if(e.getSource() == inventoryView.getCheckQtyButton()){
                String tool = inventoryView.getToolInput();
                String nameOrId = inventoryView.getToolSearchTypeRadio();

                if(nameOrId.equals("Tool Name")){
//                    modelController.checkQuantityByName(nameOrId);
                }else if(nameOrId.equals("Tool Id")){
                    int toolId = Integer.parseInt(tool);
                    modelController.checkQuantityById(toolId);
                }

                System.out.println(tool);
            }

            if(e.getSource() == inventoryView.getViewToolsButton()){
                // set result list with query result
                modelController.getAllTools();
                System.out.println("view all tool");
            }

            if(e.getSource() == inventoryView.getViewOrdersButton()){
                // set result list with query result
                modelController.getAllOrders();
                System.out.println("view all orders");
            }

            if (e.getSource() == inventoryView.getModifyInventoryButton()){
                String queryType = inventoryView.getInventoryModRadio() ;
                String toolInfo = inventoryView.getToolInfoTextFields();

                if (queryType.equals("Add tool to Inventory")){
                    modelController.addToolToDB(toolInfo);
                }else if(queryType.equals("Remove Tool from Inventory")){
                    modelController.removeToolFromDB(toolInfo);
                }
                System.out.println(queryType);
                System.out.println(toolInfo);
            }


            if(e.getSource() == inventoryView.getSellToolButton()){
                String toolInfo = inventoryView.getToolInfoTextFields();
                modelController.decreaseToolQuantity(toolInfo);
                System.out.println(toolInfo);

            }

//        if (e.getSource() == resultList){
//            int index = resultList.getSelectedIndex();
//            System.out.println("Double clicked on Item " + index);
//            String selected = demo.get(index);
//            infoFields[0].setText(selected);
//        }
        }

        public void setDisplayResult(Vector<String> arr){
            inventoryView.setResultListData(arr);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            return;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            return;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            return;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            return;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
//            int index = list.locationToIndex(e.getPoint());
                int index = inventoryView.getToolResultList().getSelectedIndex();
                System.out.println("Double clicked on Item " + index);
//                String selected = inventoryView.getDisplayList().get(index);
                ArrayList<Tool> tools = modelController.getListOfTools();
                Tool selectedTool = tools.get(index - 1);
                inventoryView.getToolInfoFields()[0].setText(String.valueOf(selectedTool.getToolID()));
                inventoryView.getToolInfoFields()[1].setText(selectedTool.getName());
                inventoryView.getToolInfoFields()[2].setText(selectedTool.getType());
                inventoryView.getToolInfoFields()[4].setText(String.valueOf(selectedTool.getPrice()));
                inventoryView.getToolInfoFields()[5].setText(String.valueOf(selectedTool.getQuantityStocked()));
                inventoryView.getToolInfoFields()[6].setText(String.valueOf(selectedTool.getSupplier().getSupplierID()));
                inventoryView.getToolInfoFields()[7].setText(selectedTool.getSupplier().getCompanyName());
                inventoryView.getToolInfoFields()[8].setText(selectedTool.getSupplier().getSalesContact());
                if (selectedTool.getType().equals("Electrical")){
                    Electrical tool = (Electrical) selectedTool;
                    inventoryView.getToolInfoFields()[3].setText(tool.getPowerInformation());
                }else{
                    inventoryView.getToolInfoFields()[3].setText("");
                }
            }
        }
    }

}
