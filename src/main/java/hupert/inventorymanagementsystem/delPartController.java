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

public class delPartController implements Initializable {
    public Button confirmPartDel;
    public Button cancelPartDel;

    private static InHouse dataIHForward = null;
    private static Outsourced dataOutForward = null;

    public static void setDataIHForward(InHouse part){
        dataIHForward = part;
    }

    public static void setDataOutsourcedForward(Outsourced selectedPart) {dataOutForward = selectedPart;}

    @FXML
    protected void onConfirmPartDel() throws IOException {
        try {
            if (dataIHForward != null) {
                Inventory.getAllParts().remove(dataIHForward);
                FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("home-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 779, 351);
                Stage stage = (Stage)confirmPartDel.getScene().getWindow();
                stage.setTitle("IMS Home");
                stage.setScene(scene);
                stage.show();
            }
            else if (dataOutForward != null) {
                Inventory.getAllParts().remove(dataOutForward);
                FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("home-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 779, 351);
                Stage stage = (Stage)confirmPartDel.getScene().getWindow();
                stage.setTitle("IMS Home");
                stage.setScene(scene);
                stage.show();
            }
            else{
                return;
            }
        }
        catch (Exception e){
            return;
        }
    }

    @FXML
    protected void onCancelClick() throws IOException {
        //When add parts clicked, open add parts form
        FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 779, 351);
        Stage stage = (Stage)cancelPartDel.getScene().getWindow();
        stage.setTitle("IMS Home");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("Delete Initialized");

    }
}
