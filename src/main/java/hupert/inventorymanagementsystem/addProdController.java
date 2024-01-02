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
import java.util.Random;
import java.util.ResourceBundle;

public class addProdController implements Initializable {

    public TextField prodIDField;
    public TextField prodNameField;
    public TextField prodInvField;
    public TextField prodPriceField;
    public TextField prodMaxField;
    public TextField prodMachineID;
    public TextField prodMinField;
    public Button prodSave;
    public Button prodCancel;
    public TextField partSearchField;
    public TableView partAddTable;
    public TableColumn addPartIdCol;
    public TableColumn addPartNameCol;
    public TableColumn addPartInvCol;
    public TableColumn addPartPriceCol;
    public TableColumn apPartIdCol;
    public TableColumn apPartNameCol;
    public TableColumn apPartInvCol;
    public TableColumn apPartPriceCol;
    public Button addPartButton;
    public Button removePartButton;

    static ObservableList<Part> tempList = FXCollections.observableArrayList();

    public TableView associatedPartTable;

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

        partAddTable.setItems(parts);
        partAddTable.refresh();
    }

    @FXML
    protected void onAddPartButtonClick(){
        Part selectedPart = (Part) partAddTable.getSelectionModel().getSelectedItem();
        tempList.add(selectedPart);

        associatedPartTable.setItems(tempList);

        apPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        apPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        apPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        apPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    protected void onRemovePartButtonClick(){
        Part selectedPart = (Part) associatedPartTable.getSelectionModel().getSelectedItem();
        tempList.remove(selectedPart);
        refreshAPTable();
    }

    @FXML
    protected void onProdSaveClick() throws IOException {
        intErrorLabel.setVisible(false);
        priceErrorLabel.setVisible(false);
        inputErrorLabel.setVisible(false);

        Random idGen = new Random();
        int genId = idGen.nextInt(2000);
        // Check generated ID against current IDs
        for(int i = 0; i < (Inventory.prodIds.size()); i++){
            // If IDs match, generate new ID
            if(genId == (Integer) Inventory.prodIds.get(i)){
                genId = idGen.nextInt(3000);
            }
        }

        try {
            String name = prodNameField.getText();
            int inv = Integer.parseInt(prodInvField.getText());
            double price = Double.parseDouble(prodPriceField.getText());
            int max = Integer.parseInt(prodMaxField.getText());
            int min = Integer.parseInt(prodMinField.getText());
            if (inv > max || inv < min){
                inputErrorLabel.setVisible(true);
            }
            // Create new product object
            Product p = new Product(genId, name, price, inv, min, max, tempList);
            // Add new product to inventory
            Inventory.getAllProducts().add(p);
        }
        catch (Exception e){
            intErrorLabel.setVisible(true);
            priceErrorLabel.setVisible(true);
            return;
        }

        inputErrorLabel.setVisible(false);
        // Return to home page
        FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 779, 351);
        Stage stage = (Stage)prodSave.getScene().getWindow();
        stage.setTitle("IMS Home");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onCancelClick() throws IOException {
        //When add parts clicked, open add parts form
        FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 779, 351);
        Stage stage = (Stage)prodCancel.getScene().getWindow();
        stage.setTitle("IMS Home");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Clear temp list so items do not remain from last add
        tempList.clear();

        partAddTable.setItems(Inventory.getAllParts());
        // Set column specific attributes for each part
        addPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        addPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    protected void refreshAPTable(){
        associatedPartTable.setItems(tempList);

        apPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        apPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        apPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        apPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
