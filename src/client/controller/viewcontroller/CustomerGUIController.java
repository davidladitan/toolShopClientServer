package client.controller.viewcontroller;

import client.view.CustomerGUI;
import client.controller.modelcontroller.ModelController;
import universal.model.customermodel.Customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

public class CustomerGUIController {
    CustomerGUI customerView;

    public void setModelController(ModelController modelController) {
        this.modelController = modelController;
    }

    ModelController modelController;

    public CustomerGUIController(CustomerGUI customerView){

        this.customerView = customerView;
        customerView.addListeners(new CustomerListener());
        customerView.addMouseListeners(new CustomerListener());
    }

    public void setDisplayResult(Vector<String> arr){
        customerView.setResultListData(arr);
    }

    class CustomerListener implements ActionListener, MouseListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String searchCriteria = "";
            String customerTypeInput = "";

            // search for client based selection, will call method from main controller
            if (e.getSource() == customerView.getSearchButton() || e.getSource() == customerView.getClientTypeSearch()){
                searchCriteria = customerView.getSearchTypeRadio();
                
                customerTypeInput = customerView.getCustomerTypeInput();
                
                if(searchCriteria.equals("Client ID")){
                    int id = Integer.parseInt(customerTypeInput);
                    
                    System.out.println("This is the id that was parsed from the GUI" + id);
                    
                    modelController.searchCustomerById(id);
                }else if (searchCriteria.equals("Last Name")){
                    modelController.searchCustomerByName(customerTypeInput);
                }else if(searchCriteria.equals("Client Type")){
                    modelController.searchCustomerByType(customerTypeInput);
                }
                customerTypeInput = customerView.getCustomerTypeInput();
                System.out.println(searchCriteria);
                System.out.println(customerTypeInput);
            }


            // add customer to database
            if(e.getSource() == customerView.getAddCustomerButton()){
                String clientInfo = customerView.getClientInfoTextFields();
                System.out.println(clientInfo);
                String clientType = customerView.getCustomerTypeCombo();
                modelController.addCustomerToDB(clientInfo, clientType);
                System.out.println(clientType);
            }

            if (e.getSource() == customerView.getClearSearch()){
                customerView.setDisplayList(new Vector<>());
                customerView.clearCustomerTypeInput();
            }

            // delete customer from db
            if (e.getSource() == customerView.getDeleteCustomerButton()){
                String customerInfo = customerView.getClientInfoTextFields();
                modelController.deleteCustomerFromDB(customerInfo);
                System.out.println(customerInfo);
            }

            if (e.getSource() == customerView.getClearButton()){
                customerView.clearCustomerTextFields();
            }

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
                int index = customerView.getResultList().getSelectedIndex();
                System.out.println("Double clicked on Item " + index);
                ArrayList<Customer> customers = modelController.getListOfCustomers();
                Customer selectedCust = customers.get(index -1 );
//                String selected = customerView.getDisplayList().get(index);
                customerView.getInfoFields()[0].setText(String.valueOf(selectedCust.getCustomerID()));
                customerView.getInfoFields()[1].setText(selectedCust.getFirstName());
                customerView.getInfoFields()[2].setText(selectedCust.getLastName());
                customerView.getInfoFields()[3].setText(selectedCust.getAddress());
                customerView.getInfoFields()[4].setText(selectedCust.getPostalCode());
                customerView.getInfoFields()[5].setText(selectedCust.getPhoneNumber());
                customerView.setCustomerTypeCombo(selectedCust.getCustomerType());
            }
        }
    }





}
