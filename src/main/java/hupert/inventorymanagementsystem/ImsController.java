/*
 * CONTROLLER FOR HOME PAGE
 */

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

public class ImsController implements Initializable {
    static Product prodDF = null;

    static String radial = null;

    static InHouse ihDF = null;

    static Outsourced osDF = null;
    // Button declarations
    public Button PartsAddButton;
    public Button PartsModButton;
    public Button PartsDelButton;
    public Button ProdAddButton;
    public Button ProdModButton;
    public Button ProdDeleteButton;
    public Button delPartsButton;
    public Button Exit01;
    public Button delProdButton;
    public TableView PartsTable;
    public TableColumn PartIDColumn;
    public TableColumn PartNameColumn;
    public TableColumn PartInvColumn;
    public TableColumn PartPriceColumn;
    public TableColumn ProdIDColumn;
    public TableColumn ProdNameColumn;
    public TableColumn ProdInvColumn;
    public TableColumn ProdPriceColumn;
    public TableView ProdTable;
    public TextField partSearchField;
    public TextField productSearchField;
    public Label deleteErrorLabel;
    // On Action method definitions and related methods
    private ObservableList<Product> searchByProdName(String partialName){
        ObservableList<Product> prodNames = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = Inventory.getAllProducts();

        for (Product p : allProducts){
            if (p.getName().contains(partialName)){
                prodNames.add(p);
            }
        }
        return prodNames;
    }

    private Product prodIdSearch(int pid){
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        ObservableList<Part> prodIds = FXCollections.observableArrayList();

        for (Product p : allProducts){
            if (p.getId() == pid){
                return p;
            }
        }
        return null;
    }

    @FXML
    public void prodSearchHandler(ActionEvent actionEvent) {
        String s = productSearchField.getText();
        ObservableList<Product> products = searchByProdName(s);
        // If list size is zero, see if user searched by ID (integer)
        if (products.size() == 0){
            try{
                int pid = Integer.parseInt(s);
                Product p = prodIdSearch(pid);

                if (p != null){
                    products.add(p);
                }
            }
            catch (NumberFormatException e){
                // Do nothing
            }
        }

        ProdTable.setItems(products);
        ProdTable.refresh();
    }

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

        PartsTable.setItems(parts);
        PartsTable.refresh();
    }

    @FXML
    protected void onAddButtonClick() throws IOException {
        //When add parts clicked, open add parts form
        FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("add-parts.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 779, 400);
        Stage stage = (Stage)PartsAddButton.getScene().getWindow();
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    public static String getRadial(){return radial;}

    public static InHouse getIhDF(){return ihDF;}

    public static Outsourced getOsDF(){return osDF;}
    // Create new scene when Modify clicked
    @FXML
    protected void onModButtonClick() throws IOException {
        try {
            InHouse selectedPart = (InHouse) PartsTable.getSelectionModel().getSelectedItem();

            if (selectedPart == null){return;}

            //modPartController.setDataIHForward(selectedPart);
            ihDF = selectedPart;
            radial = "Inhouse";
            //When Modify parts clicked, open add parts form
            FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("mod-part.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 779, 400);
            Stage stage = (Stage)PartsModButton.getScene().getWindow();
            stage.setTitle("Modify Part");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            Outsourced selectedPart = (Outsourced) PartsTable.getSelectionModel().getSelectedItem();

            if (selectedPart == null){return;}

            //modPartController.setDataOutsourcedForward(selectedPart);
            osDF = selectedPart;
            radial = "Outsourced";

            //When Modify parts clicked, open add parts form
            FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("mod-part.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 779, 351);
            Stage stage = (Stage)PartsModButton.getScene().getWindow();
            stage.setTitle("Modify Part");
            stage.setScene(scene);
            stage.show();
        }

    }

    // Create new scene when Delete clicked
    @FXML
    protected void onDelButtonClick() throws IOException {

        try {
            InHouse selectedPart = (InHouse) PartsTable.getSelectionModel().getSelectedItem();

            if (selectedPart == null){return;}

            delPartController.setDataIHForward(selectedPart);

            FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("del-part.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 350, 150);
            Stage stage = (Stage)PartsModButton.getScene().getWindow();
            stage.setTitle("Confirm Delete Part");
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            Outsourced selectedPart = (Outsourced) PartsTable.getSelectionModel().getSelectedItem();

            if (selectedPart == null){return;}

            delPartController.setDataOutsourcedForward(selectedPart);

            //When Modify parts clicked, open add parts form
            FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("del-part.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 350, 150);
            Stage stage = (Stage)PartsModButton.getScene().getWindow();
            stage.setTitle("Confirm Delete Part");
            stage.setScene(scene);
            stage.show();
        }

    }

    // Following 3 methods are for Products
    // Create new scene when Add clicked
    @FXML
    protected void onAddProdClick() throws IOException {
        //When add product clicked, open add product form
        FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("add-products.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 863, 557);
        Stage stage = (Stage)ProdAddButton.getScene().getWindow();
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }


    public static Product getProdDF(){
        return prodDF;
    }
    // Create new scene when Modify clicked
    @FXML
    protected void onModProdClick() throws IOException {
        /**
         * LOGICAL ERROR
         * Originally I attempted to utilize a set data method in the "modProdController" which controls the next screen
         * This was not handled properly and was resulting in a null variable on the next page which compounded into the
         * associated parts table remaining blank. The method call was left commented out below.
         * This was corrected by introducing the prodDF static variable that was set then accessed from the ModProdController
         * on initialization.
         */

        Product selectedProduct = (Product) ProdTable.getSelectionModel().getSelectedItem();

        if (selectedProduct == null){return;}

        prodDF = selectedProduct;

        //modProdController.setDataForward(selectedProduct);
        //When add product clicked, open add product form
        FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("mod-prod.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 847, 572);
        Stage stage = (Stage)ProdModButton.getScene().getWindow();
        stage.setTitle("Modify Product");
        stage.setScene(scene);
        stage.show();
    }


    // Create new scene when Delete clicked
    @FXML
    protected void onDelProdClick() throws IOException {

        Product selectedProd = (Product)ProdTable.getSelectionModel().getSelectedItem();
        if (selectedProd == null){return;}
        if (selectedProd.getAssociatedParts().size() != 0){
            deleteErrorLabel.setVisible(true);
            return;
        }
        // Clear error message
        deleteErrorLabel.setVisible(false);
        // Set forward data variable
        prodDF = selectedProd;
        // Show delete confirmation pop-up
        FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("del-prod.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 350, 150);
        Stage stage = (Stage)PartsModButton.getScene().getWindow();
        stage.setTitle("Confirm Delete Part");
        stage.setScene(scene);
        stage.show();

        //Inventory.getTheProdInventory().remove(selectedProd);
    }

    @FXML
    protected void onExitClick() throws IOException {
        System.exit(0);
    }

    private static boolean start = true;

    private void testData(){
        if (!start){
            return;
        }
        start = false;

        InHouse generic = new InHouse(101, "Gen Motherboard", 199.99, 2, 1, 10, 5055);
        Inventory.addPart(generic);
        Inventory.partIds.add(generic.getId());
        Outsourced amd = new Outsourced(201, "Ryzen 3700", 499.95, 3, 1, 10, "AMD");
        Inventory.addPart(amd);
        Inventory.partIds.add(amd.getId());
        Outsourced rtx2060 = new Outsourced(301, "GeForce RTX2060", 399.99, 2, 1, 10, "Nvidia");
        Inventory.addPart(rtx2060);
        Inventory.partIds.add(rtx2060.getId());
        Outsourced intel = new Outsourced(401, "Intel i9", 899.99, 3, 1, 10, "Intel");
        Inventory.addPart(intel);
        Inventory.partIds.add(intel.getId());


        ObservableList<Part> gen = FXCollections.observableArrayList(generic, amd);
        ObservableList<Part> amdD = FXCollections.observableArrayList(amd, rtx2060);
        ObservableList<Part> nvidiaD = FXCollections.observableArrayList(rtx2060, intel);

        Product genDesktop = new Product(102, "Generic Desktop", 699.00, 5, 1, 10, gen);
        Inventory.addProduct(genDesktop);
        Product amdDesktop = new Product(202, "AMD Desktop", 899.00, 3, 1, 10, amdD);
        Inventory.addProduct(amdDesktop);
        Product nvdDesktop = new Product(302, "Nvidia Desktop", 1400.00, 2, 1, 10, nvidiaD);
        Inventory.addProduct(nvdDesktop);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Add test data to main tables
        testData();
        // Set observable list to parts table
        PartsTable.setItems(Inventory.getAllParts());
        // Set column specific attributes for each part
        PartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        // Set observable list to product table
        ProdTable.setItems(Inventory.getAllProducts());
        // Set column specific attributes for each product
        ProdIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProdNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProdPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        ProdInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));


    }


}