package hupert.inventorymanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class modProdController implements Initializable {

    static Part dfPart = null;

    private static Product dataForward = null;

    public TextField modProdIDField;
    public TextField modProdNameField;
    public TextField modProdInvField;
    public TextField modProdPriceField;
    public TextField modProdMaxField;
    public TextField modProdMachineID;
    public TextField modProdMinField;
    public Button modProdSave;
    public Button modProdCancel;
    public Button removeAPButton;
    public TextField partSearchField;
    public TableView addPartTable;
    public TableColumn addPartIDCol;
    public TableColumn addPartNameCol;
    public TableColumn addPartInvCol;
    public TableColumn addPartPriceCol;
    public TableView associatedPartTable;
    public TableColumn apPartIdCol;
    public TableColumn apPartNameCol;
    public TableColumn apPartInvCol;
    public TableColumn apPartPriceCol;
    public Button addAPButton;


    static ObservableList<Part> modTempList = FXCollections.observableArrayList();
    public Label inputErrorLabel;
    public Label intErrorLabel;
    public Label priceErrorLabel;


    private ObservableList<Part> searchByPartName(String partialName){
        ObservableList<Part> partNames = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();

        for (Part p : allParts){
            if (p.getName().contains(partialName)){
                partNames.add(p);
            }
        }
        return partNames;
    }

    private Part partIdSearch(int pid){
        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partIds = FXCollections.observableArrayList();

        for (Part p : allParts){
            if (p.getId() == pid){
                return p;
            }
        }
        return null;
    }

    @FXML
    public void partSearchHandler(ActionEvent actionEvent) {
        String s = partSearchField.getText();
        ObservableList<Part> parts = searchByPartName(s);
        // If list size is zero, see if user searched by ID (integer)
        if (parts.size() == 0){
            try{
                int pid = Integer.parseInt(s);
                Part p = partIdSearch(pid);

                if (p != null){
                    parts.add(p);
                }
            }
            catch (NumberFormatException e){
                // Do nothing
            }
        }

        addPartTable.setItems(parts);
        addPartTable.refresh();
    }
    @FXML
    protected void onAddPartButtonClick(){
        Part selectedPart = (Part) addPartTable.getSelectionModel().getSelectedItem();
        if (modTempList.contains(selectedPart)){
            return;
        }
        // Add selected part to temp list
        modTempList.add(selectedPart);
        // refresh AP table with temp list
        associatedPartTable.setItems(modTempList);

        apPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        apPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        apPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        apPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    protected void onRemovePartButtonClick(){

        try {
            Part selectedPart = (Part) associatedPartTable.getSelectionModel().getSelectedItem();

            if (selectedPart == null){return;}

            dfPart = selectedPart;

            //When Modify parts clicked, open add parts form
            FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("apDel.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 350, 150);
            Stage stage = (Stage)removeAPButton.getScene().getWindow();
            stage.setTitle("Confirm Delete Part");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            return;
        }
    }

    @FXML
    protected void onSaveClick() throws IOException {
        intErrorLabel.setVisible(false);
        priceErrorLabel.setVisible(false);
        inputErrorLabel.setVisible(false);
        try {
            // Get information in fields
            int id = Integer.parseInt(modProdIDField.getText());
            String name = modProdNameField.getText();
            double price = Double.parseDouble(modProdPriceField.getText());
            int stock = Integer.parseInt(modProdInvField.getText());
            int min = Integer.parseInt(modProdMinField.getText());
            int max = Integer.parseInt(modProdMaxField.getText());
            ObservableList<Part> modList = modTempList;
            if (stock > max || stock < min) {
                inputErrorLabel.setVisible(true);
                return;
            }
            // Create new product object
            Product p = new Product(id, name, price, stock, min, max, modList);
            // Remove selected product from inventory
            Inventory.getAllProducts().remove(dataForward);
            // Replace with updated product
            Inventory.getAllProducts().add(p);
            // Clear data passing variable
            dataForward = null;//
            // Return home
            FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("home-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 779, 351);
            Stage stage = (Stage) modProdCancel.getScene().getWindow();
            stage.setTitle("IMS Home");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            intErrorLabel.setVisible(true);
            priceErrorLabel.setVisible(true);
            return;
        }

    }


    @FXML
    protected void onCancelClick() throws IOException {
        //When add parts clicked, open add parts form
        FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 779, 351);
        Stage stage = (Stage)modProdCancel.getScene().getWindow();
        stage.setTitle("IMS Home");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dataForward = ImsController.getProdDF();

        modTempList = dataForward.getAssociatedParts();

        addPartTable.setItems(Inventory.getAllParts());
        // Set column specific attributes for each part
        addPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        // Set associated parts table based on forwarded selected product
        associatedPartTable.setItems(modTempList);

        apPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        apPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        apPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        apPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        // Set forwarded attributes to variables

        int pId = dataForward.getId();
        String n = dataForward.getName();
        double price = dataForward.getPrice();
        int stock = dataForward.getStock();
        int min = dataForward.getMin();
        int max = dataForward.getMax();
        // Set attributes to proper fields
        modProdIDField.setText(String.valueOf(pId));
        modProdNameField.setText(n);
        modProdPriceField.setText(String.valueOf(price));
        modProdInvField.setText(String.valueOf(stock));
        modProdMinField.setText(String.valueOf(min));
        modProdMaxField.setText(String.valueOf(max));

    }

    public static Part getDfPart(){
        return dfPart;
    }

    public static ObservableList<Part> getModTempList(){
        return modTempList;
    }

}
