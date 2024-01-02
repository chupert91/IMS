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

public class APDeleteController implements Initializable {
    public Button confirmPartDel;
    public Button cancelPartDel;

    private static Part dataDelForward = null;


    @FXML
    protected void onConfirmPartDel() throws IOException {

        try {
            if (dataDelForward != null) {

                modProdController.getModTempList().remove(dataDelForward);

                FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("mod-prod.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 847, 572);
                Stage stage = (Stage)confirmPartDel.getScene().getWindow();
                stage.setTitle("IMS Home");
                stage.setScene(scene);
                stage.show();
            }
            else{
                System.out.println("item null");
            }
        }
        catch (Exception e){
            return;
        }
    }

    @FXML
    protected void onCancelClick() throws IOException {
        //When add parts clicked, open add parts form
        FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("mod-prod.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 847, 572);
        Stage stage = (Stage)cancelPartDel.getScene().getWindow();
        stage.setTitle("IMS Home");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dataDelForward = modProdController.getDfPart();
    }

}
