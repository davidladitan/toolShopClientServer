package client.view;
import client.view.CustomerGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class InventoryGUI extends JPanel {

    private JRadioButton[] toolOptions;
    private JButton searchToolButton;
    private JButton checkQtyButton;
    private JButton viewToolsButton;
    private JButton viewOrdersButton;
    private JList toolResultList;
    private JTextField[] toolInfoFields;
    private JButton sellToolButton;
    private JRadioButton[] invOptions;
    private JButton modifyInventoryButton;
    private JTextField toolInput;

    public void setDisplayList(Vector<String> displayList) {
        this.displayList = displayList;
    }

    private Vector<String> displayList;



    public InventoryGUI(String title){
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inventory page


        // left panel

        // top left 1st column
        JPanel topLeft1 = new JPanel();
//        topLeft1.setLayout(new GridLayout(3, 1, 1, 2));
        topLeft1.setLayout(new BoxLayout(topLeft1, BoxLayout.Y_AXIS) );
//        topLeft1.setPreferredSize(new Dimension(10, ));

        JPanel topLeft11 = new JPanel();
        JLabel searchToolBy = new JLabel("Search for Tool By:");


        ButtonGroup toolRadioGroup = new ButtonGroup();

        String[] toolOptionLabels = {"Tool Name", "Tool Id"};

        JPanel toolRadioPanel = new JPanel();
        toolRadioPanel.setLayout(new BoxLayout(toolRadioPanel, BoxLayout.Y_AXIS));
        toolOptions = new JRadioButton[2];

        for (int i = 0; i < toolOptions.length; i++) {
            toolOptions[i] = new JRadioButton(toolOptionLabels[i]);
//            toolOptions[i].addItemListener();
            toolRadioPanel.add(toolOptions[i]);
            toolRadioGroup.add(toolOptions[i]);
        }

        toolInput = new JTextField(12);
        JPanel inputPanel = new JPanel();
        inputPanel.add(toolInput);



        searchToolButton = new JButton("Search Tool");
        searchToolButton.setAlignmentX(Component.LEFT_ALIGNMENT );
//        searchToolButton.addActionListener(this);
        checkQtyButton = new JButton("Check Quantity");
        checkQtyButton.setAlignmentX(Component.LEFT_ALIGNMENT );
//        checkQtyButton.addActionListener(this);

        topLeft11.add(searchToolBy);
        topLeft11.add(toolRadioPanel);
        topLeft11.add(inputPanel);

        topLeft11.add(searchToolButton);
        topLeft11.add(checkQtyButton);

        topLeft1.add(topLeft11);
        topLeft1.setAlignmentX(Component.LEFT_ALIGNMENT);


        // top left 2nd column
        JPanel topLeft2 = new JPanel();
//        topLeft2.setLayout(new GridLayout(2, 1, 1, 2));
        topLeft2.setLayout(new BoxLayout(topLeft2, BoxLayout.Y_AXIS));

        JPanel topLeft21 = new JPanel();
        JPanel topLeft22 = new JPanel();

        JLabel viewToolsLabel = new JLabel("View all tools:");
        JLabel viewOrdersLabel = new JLabel("View Orders:");

        viewToolsButton = new JButton("View Tools");
        viewOrdersButton = new JButton("View Orders");


        topLeft21.add(viewToolsLabel);
        topLeft21.add(viewToolsButton);
        topLeft22.add(viewOrdersLabel);
        topLeft22.add(viewOrdersButton);

        topLeft2.add(topLeft21);
        topLeft2.add(topLeft22);

        JPanel invTopLeftPanel = new JPanel();
        invTopLeftPanel.setLayout(new GridLayout(1, 2, 2, 2));
//        invTopLeftPanel.setLayout(new BoxLayout(invTopLeftPanel, BoxLayout.X_AXIS));
        invTopLeftPanel.add(topLeft1);
        invTopLeftPanel.add(topLeft2);
        invTopLeftPanel.setPreferredSize(new Dimension(20,10));

        // bottom left
        JPanel invBottomLeftPanel = new JPanel();
//        invBottomLeftPanel.setLayout(new GridLayout(2, 1, 1, 1));
        invBottomLeftPanel.setLayout(new BoxLayout(invBottomLeftPanel, BoxLayout.Y_AXIS));
        JLabel viewToolResultLabel = new JLabel("View Results:");


        toolResultList = new JList();
        toolResultList.setFixedCellWidth(50);

        displayList = new Vector<>();

        toolResultList.setListData(displayList);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(toolResultList);
        toolResultList.setLayoutOrientation(JList.VERTICAL);

        invBottomLeftPanel.add(viewToolResultLabel);
        invBottomLeftPanel.add(scrollPane);

        JPanel invLeftPanel = new JPanel();
        invLeftPanel.setLayout(new GridLayout(2, 1, 2, 2));
//        invLeftPanel.setLayout(new BoxLayout(invLeftPanel, BoxLayout.Y_AXIS));

        invLeftPanel.setBorder(BorderFactory.createTitledBorder("Search Tool"));
        invLeftPanel.add(invTopLeftPanel);
        invLeftPanel.add(invBottomLeftPanel);
        invLeftPanel.add(invBottomLeftPanel);

        // Right Panel

        JPanel invRightPanel = new JPanel();
        invRightPanel.setLayout(new BoxLayout(invRightPanel, BoxLayout.Y_AXIS));
        invRightPanel.setBorder(BorderFactory.createTitledBorder("Tool Info"));


        String[] ToolInfoTitles = {"Tool ID:", "Tool Name:", "Tool Type:", "Electrical Power Type:", "Price:","Quantity in stock", "Supplier Id:", "Supplier Name:", "Supplier contact"};
        String[] ToolInfoFieldNames = {"toolId", "toolName", "ToolType", "electricalPowerType","price","quantity", "supplierId", "supplierName", "supplierContact"};
        JPanel[] toolInfoPanels = new JPanel[9];
        JLabel[] toolInfoLabels = new JLabel[9];
        toolInfoFields = new JTextField[9];

        for (int i = 0; i < ToolInfoFieldNames.length; i++) {
            toolInfoPanels[i] = new JPanel();
            toolInfoLabels[i] = new JLabel(ToolInfoTitles[i]);
            toolInfoFields[i] = new JTextField(20);
            toolInfoFields[i].setName(ToolInfoFieldNames[i]);
//            infoFields[i].addActionListener();
            toolInfoPanels[i].add(toolInfoLabels[i]);
            toolInfoPanels[i].add(toolInfoFields[i]);
            invRightPanel.add(toolInfoPanels[i]);

        }

        // bottom right
        JPanel bottomRightPanel = new JPanel();
        bottomRightPanel.setLayout(new GridLayout(1,2,2,2));

        JPanel bottomRight1 = new JPanel();
//        bottomRight1.setLayout(new GridLayout(1,2,1,1));
        JLabel sellToolLabel = new JLabel("Sell Tool");
        sellToolButton = new JButton("Sell Tool");
//        sellToolButton.addActionListener(this); // test
        bottomRight1.add(sellToolLabel);
        bottomRight1.add(sellToolButton);

        JPanel bottomRight2 = new JPanel();
        bottomRight2.setLayout(new BoxLayout(bottomRight2, BoxLayout.Y_AXIS));
        JPanel invRadioPanel = new JPanel();
        invRadioPanel.setLayout(new GridLayout(2,1,1,1));
        invOptions = new JRadioButton[2];
        String [] checkOptions = {"Add tool to Inventory", "Remove Tool from Inventory"};
        ButtonGroup inventoryRadioGroup = new ButtonGroup();


        for (int i = 0; i < invOptions.length; i++) {
            invOptions[i] = new JRadioButton(checkOptions[i]);
//            toolOptions[i].addItemListener();
            invRadioPanel.add(invOptions[i]);
            inventoryRadioGroup.add(invOptions[i]);
        }

        modifyInventoryButton = new JButton("Save");
//        modifyInventoryButton.addActionListener(this); //test

        bottomRight2.add(invRadioPanel);
        bottomRight2.add(modifyInventoryButton);

        bottomRightPanel.add(bottomRight1);
        bottomRightPanel.add(bottomRight2);

        invRightPanel.add(bottomRightPanel);


        JPanel inventoryPage = new JPanel();
        inventoryPage.setLayout(new GridLayout(1, 2, 2, 2));
//        inventoryPage.setPreferredSize(new Dimension(1000, 400));
        inventoryPage.add(invLeftPanel);
        inventoryPage.add(invRightPanel);


        add(inventoryPage);

    }

    public void addListeners(ActionListener actionListener){
        searchToolButton.addActionListener(actionListener);
        checkQtyButton.addActionListener(actionListener);
        viewToolsButton.addActionListener(actionListener);
        viewOrdersButton.addActionListener(actionListener);
        modifyInventoryButton.addActionListener(actionListener);
        sellToolButton.addActionListener(actionListener);
        toolInput.addActionListener(actionListener);
    }

    public void addMouseListeners(MouseListener mouseListener){
        toolResultList.addMouseListener(mouseListener);
    }


    public String getToolSearchTypeRadio(){
        String searchCriteria = "";
        for (int i =0; i < toolOptions.length; i++){
            if (toolOptions[i].isSelected()){
                searchCriteria = toolOptions[i].getText();
            }
        }
        return searchCriteria;
    }

    public String getInventoryModRadio(){
        String invModification = "";
        for (int i =0; i < invOptions.length; i++){
            if (invOptions[i].isSelected()){
                invModification = invOptions[i].getText();
            }
        }
        return invModification;
    }

    public String getToolInput(){
        String toolInputText = "";
        toolInputText = toolInput.getText();
        return toolInputText;
    }

    public String getToolInfoTextFields(){

        String toolInfo = "";
        for (int i =0; i< toolInfoFields.length; i++){
            toolInfo += toolInfoFields[i].getText() + ";";
        }

        return toolInfo;
    }

    public void setResultListData(Vector<String> arr){
        displayList = arr;
        toolResultList.setListData(arr);
    }

    public JRadioButton[] getToolOptions() {
        return toolOptions;
    }

    public JButton getSearchToolButton() {
        return searchToolButton;
    }

    public JButton getCheckQtyButton() {
        return checkQtyButton;
    }

    public JButton getViewToolsButton() {
        return viewToolsButton;
    }

    public JButton getViewOrdersButton() {
        return viewOrdersButton;
    }

    public JList getToolResultList() {
        return toolResultList;
    }

    public JTextField[] getToolInfoFields() {
        return toolInfoFields;
    }

    public JButton getSellToolButton() {
        return sellToolButton;
    }

    public JRadioButton[] getInvOptions() {
        return invOptions;
    }

    public JButton getModifyInventoryButton() {
        return modifyInventoryButton;
    }

    public Vector<String> getDisplayList() {
        return displayList;
    }

    public JTextField toolInputgetter(){
        return toolInput;
    }

}
