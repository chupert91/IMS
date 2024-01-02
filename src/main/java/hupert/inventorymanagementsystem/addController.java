package hupert.inventorymanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class addController implements Initializable {

    public RadioButton partIHRadial;
    public RadioButton partOutsourcedRadial;
    public TextField partIDField;
    public TextField partNameField;
    public TextField partInvField;
    public TextField partPriceField;
    public TextField partMaxField;
    public TextField partMachineID;
    public TextField partMinField;
    public Button partSave;
    public Button partCancel;
    public Label superLabel;
    public Label inputErrorLabel;
    public Label priceErrorLabel;
    public Label intErrorLabel;


    @FXML
    protected void onSaveClick() throws IOException {
        intErrorLabel.setVisible(false);
        priceErrorLabel.setVisible(false);
        inputErrorLabel.setVisible(false);
        /**
         * FUTURE ENHANCEMENT
         * As the part and product table grow into the thousands; this ID generator will need to be reworked to provide
         * larger ID numbers due to collisions.
         */
        Random idGen = new Random();
        int genId = idGen.nextInt(2000);
        // Check generated ID against current IDs
        for(int i = 0; i < (Inventory.partIds.size()); i++){
            // If IDs match, generate new ID
            if(genId == (Integer) Inventory.partIds.get(i)){
                genId = idGen.nextInt(3000);
            }
        }

        // Call part contstructor using input fields as arguments
        try {
            String name = partNameField.getText();
            int inv = Integer.parseInt(partInvField.getText());
            double price = Double.parseDouble(partPriceField.getText());
            int max = Integer.parseInt(partMaxField.getText());
            int min = Integer.parseInt(partMinField.getText());
            String mIDs = partMachineID.getText();
            if (inv > max || inv < min){
                inputErrorLabel.setVisible(true);
                return;
            }
            if (partIHRadial.isSelected()) {
                int mID = Integer.parseInt(mIDs);
                InHouse p = new InHouse(genId, name, price, inv, min, max, mID);
                Inventory.getAllParts().add(p);
            } else if (partOutsourcedRadial.isSelected()) {
                String cn = mIDs;
                Outsourced o = new Outsourced(genId, name, price, inv, min, max, cn);
                Inventory.getAllParts().add(o);
            }
        }
        catch (Exception e){
            intErrorLabel.setVisible(true);
            priceErrorLabel.setVisible(true);
            return;
        }

        inputErrorLabel.setVisible(false);
        FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 779, 351);
        Stage stage = (Stage)partSave.getScene().getWindow();
        stage.setTitle("IMS Home");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onCancelClick() throws IOException {
        //When add parts clicked, open add parts form
        FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 779, 351);
        Stage stage = (Stage)partCancel.getScene().getWindow();
        stage.setTitle("IMS Home");
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Part Add Initialized");
    }


    public void onOutsourced(ActionEvent actionEvent) {
        superLabel.setText("Company Name");
        superLabel.setLayoutX(30.0);



    }

    public void onInHouse(ActionEvent actionEvent) {
        superLabel.setText("Machine ID");
    }
}
