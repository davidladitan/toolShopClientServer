package client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class CustomerGUI extends JPanel {

    private JTextField clientTypeSearch;
    private JButton searchButton;
    private JButton clearSearch;
    private ButtonGroup searchRadioGroup;
    private JRadioButton[] options;
    private JList resultList;
    private JButton addCustomerButton;
    private JButton deleteCustomerButton;
    private JButton clearButton;
    private JTextField[] infoFields;
    private JComboBox clientTypeCombo;
    private Vector<String> displayList;



    public CustomerGUI(String title) {

        // search panel
        JPanel searchPanel = new JPanel(); // left panel?
        JLabel searchLabel = new JLabel("Select type of search to be performed:");
//        searchLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JPanel test1 = new JPanel();


        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.add(searchLabel);


        searchRadioGroup = new ButtonGroup();
        String[] searchLabels = {"Client ID", "Last Name", "Client Type"};

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
//        radioPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        options = new JRadioButton[3];

        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton(searchLabels[i]);
//            options[i].addItemListener();
            radioPanel.add(options[i]);
            searchRadioGroup.add(options[i]);
        }
        searchPanel.add(radioPanel);
//        searchPanel.setAlignmentX(Component.LEFT_ALIGNMENT);


        // parameter panel
        clientTypeSearch = new JTextField(10);
        searchButton = new JButton("Search");
//        searchButton.addActionListener(this); //test
        clearSearch = new JButton("Clear Search");

        JPanel searchParameter = new JPanel();
//        searchParameter.setLayout(new BoxLayout(searchParameter,BoxLayout.X_AXIS));
        searchParameter.add(clientTypeSearch);
        searchParameter.add(searchButton);
        searchParameter.add(clearSearch);
        JPanel parameterPanel = new JPanel();
        JLabel parameterLabel = new JLabel("Enter the search parameter below:");
        parameterPanel.setLayout(new GridLayout(2, 1, 2, 2));
        parameterPanel.add(parameterLabel);
        parameterPanel.add(searchParameter);

        JPanel topLeftPanel = new JPanel();
        topLeftPanel.setLayout(new BoxLayout(topLeftPanel, BoxLayout.Y_AXIS));
        topLeftPanel.add(searchPanel);
        topLeftPanel.add(parameterPanel);



        // bottom left panel
        JPanel bottomLeftPanel = new JPanel();
//        bottomLeftPanel.setLayout(new GridLayout(2, 1, 2, 2));
        bottomLeftPanel.setLayout(new BoxLayout(bottomLeftPanel, BoxLayout.Y_AXIS));
        JLabel searchResultLabel = new JLabel("Search Results:");
        resultList = new JList();
        resultList.setFixedCellWidth(100);

        displayList = new Vector<>();
        resultList.setListData(displayList);
//        resultList.addMouseListener(this); // test

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(resultList);

        bottomLeftPanel.add(searchResultLabel);
        bottomLeftPanel.add(scrollPane);
//        bottomLeftPanel.setBorder(new BorderLayout());


        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(topLeftPanel, BorderLayout.SOUTH);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        leftPanel.add(bottomLeftPanel);
        leftPanel.setBorder(BorderFactory.createTitledBorder("Search Clients"));




        // right panel

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        String[] clientInfoTitles = {"Client ID:", "First Name:", "Last Name:", "Address:", "Postal Code:", "Phone Number:"};
        String[] clientInfoFieldNames = {"clientId", "firstName", "lastName", "address", "postalCode", "phoneNumber"};
        JPanel[] infoPanels = new JPanel[6];
        JLabel[] infoLabels = new JLabel[6];
        infoFields = new JTextField[6];

        for (int i = 0; i < clientInfoFieldNames.length; i++) {
            infoPanels[i] = new JPanel();
            infoLabels[i] = new JLabel(clientInfoTitles[i]);
            infoFields[i] = new JTextField(20);
            infoFields[i].setName(clientInfoFieldNames[i]);
//            infoFields[i].addActionListener();
            infoPanels[i].add(infoLabels[i]);
            infoPanels[i].add(infoFields[i]);
            rightPanel.add(infoPanels[i]);

        }

        JPanel clientTypePanel = new JPanel();
        JLabel clientTypeLabel = new JLabel();
        clientTypeCombo = new JComboBox();
        clientTypeCombo.addItem("R");
        clientTypeCombo.addItem("C");
        clientTypePanel.add(clientTypeLabel);
        clientTypePanel.add(clientTypeCombo);

        JPanel buttonsPanel = new JPanel();

        addCustomerButton = new JButton("Add/Modify Customer");
//        addCustomerButton.addActionListener(this); // test
        deleteCustomerButton = new JButton("Delete Customer");
//        deleteCustomerButton.addActionListener(this); // test
        clearButton = new JButton("Clear");

        buttonsPanel.add(addCustomerButton);
        buttonsPanel.add(deleteCustomerButton);
        buttonsPanel.add(clearButton);



        rightPanel.add(clientTypePanel);
        rightPanel.add(buttonsPanel);
        rightPanel.setBorder(BorderFactory.createTitledBorder("Client Information"));


        JPanel customerPage = new JPanel();
        customerPage.setLayout(new GridLayout(1, 2, 2, 2));
//        customerPage.setPreferredSize(new Dimension(1000, 400));
        customerPage.add(leftPanel);
        customerPage.add(rightPanel);


        add(customerPage);



    }



    public void addListeners(ActionListener actionListener){
        searchButton.addActionListener(actionListener);
        clearSearch.addActionListener(actionListener);
        addCustomerButton.addActionListener(actionListener);
        deleteCustomerButton.addActionListener(actionListener);
        clearButton.addActionListener(actionListener);
        clientTypeSearch.addActionListener(actionListener);
    }

    public void addMouseListeners(MouseListener mouseListener){
        resultList.addMouseListener(mouseListener);
    }

    public String getSearchTypeRadio(){
        String searchCriteria = "";
        for (int i =0; i < options.length; i++){
            if (options[i].isSelected()){
                searchCriteria = options[i].getText();
            }
        }
        return searchCriteria;
    }

    public String getCustomerTypeInput(){
        String searchCriteria = "";
        searchCriteria = clientTypeSearch.getText();
        return searchCriteria;
    }

    public String getClientInfoTextFields(){

        String clientInfo = "";
        for (int i =0; i< infoFields.length; i++){
            clientInfo += infoFields[i].getText() + ";";
        }

        return clientInfo;
    }

    public void clearCustomerTextFields(){
        for (int i =0; i< infoFields.length; i++){
            infoFields[i].setText("");
        }
    }

    public void clearCustomerTypeInput(){
       clientTypeSearch.setText("");
    }

    public String getCustomerTypeCombo(){
        String customerType = String.valueOf(clientTypeCombo.getSelectedItem());
        return customerType;
    }

    public void setCustomerTypeCombo(String type){
        if(type.equals("R")){
            clientTypeCombo.setSelectedIndex(0);
        }else if (type.equals("C")){
            clientTypeCombo.setSelectedIndex(1);
        }
    }

    public void setResultListData(Vector<String> arr){
        displayList = arr;
        resultList.setListData(arr);
    }





    public JTextField getClientTypeSearch() {
        return clientTypeSearch;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getClearSearch() {
        return clearSearch;
    }

    public JButton getAddCustomerButton() {
        return addCustomerButton;
    }

    public JButton getDeleteCustomerButton() {
        return deleteCustomerButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public Vector<String> getDisplayList() {
        return displayList;
    }

    public void setDisplayList(Vector<String> displayList) {
        this.displayList = displayList;
    }

    public JList getResultList() {
        return resultList;
    }

    public JTextField[] getInfoFields() {
        return infoFields;
    }

}

