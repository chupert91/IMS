package hupert.inventorymanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class delProdController implements Initializable {
    public Button confirmProdDel;
    public Button cancelProdDel;

    static Product productTBD = null;

    @FXML
    protected void onConfirmDeleteClick() throws IOException{
        // Check if Product has associated parts
        if (productTBD.getAssociatedParts().size() != 0){
            return;
        }
        else{
            // Delete product from inventory
            Inventory.getAllProducts().remove(productTBD);
            // Send back to home view
            FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("home-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 779, 351);
            Stage stage = (Stage)cancelProdDel.getScene().getWindow();
            stage.setTitle("IMS Home");
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    protected void onCancelClick() throws IOException {
        //When add parts clicked, open add parts form
        FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 779, 351);
        Stage stage = (Stage)cancelProdDel.getScene().getWindow();
        stage.setTitle("IMS Home");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productTBD = ImsController.getProdDF();
    }
}

