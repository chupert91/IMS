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
import java.util.ResourceBundle;

public class modPartController implements Initializable {

    private static InHouse dataIHForward = null;
    private static Outsourced dataOutForward = null;


    public RadioButton modPartIHRadial;
    public RadioButton modPartOutsourcedRadial;
    public TextField modPartIDField;
    public TextField modPartNameField;
    public TextField modPartInvField;
    public TextField modPartPriceField;
    public TextField modPartMaxField;
    public TextField modPartMinField;
    public TextField modPartMachineID;
    public Button modPartSave;
    public Button modPartCancel;
    public Label modSuperLabel;
    public Label inputErrorLabel;
    public Label intErrorLabel;
    public Label priceErrorLabel;

    /*
    public static void setDataIHForward(InHouse part){
        dataIHForward = part;
    }

    public static void setDataOutsourcedForward(Outsourced selectedPart) {
        dataOutForward = selectedPart;
    }
     */

    @FXML
    protected void onSaveClick() throws IOException {
        intErrorLabel.setVisible(false);
        priceErrorLabel.setVisible(false);
        inputErrorLabel.setVisible(false);

        try {
            // Call part contstructor using input fields as arguments
            int id = Integer.parseInt(modPartIDField.getText());
            String name = modPartNameField.getText();
            int inv = Integer.parseInt(modPartInvField.getText());
            double price = Double.parseDouble(modPartPriceField.getText());
            int max = Integer.parseInt(modPartMaxField.getText());
            int min = Integer.parseInt(modPartMinField.getText());
            String mIDs = modPartMachineID.getText();
            if (inv > max || inv < min){
                inputErrorLabel.setVisible(true);
                return;
            }

            if (modPartIHRadial.isSelected()) {
                int mID = Integer.parseInt(mIDs);
                InHouse p = new InHouse(id, name, price, inv, min, max, mID);
                Inventory.getAllParts().remove(dataIHForward);
                Inventory.getAllParts().add(p);
                dataIHForward = null;//
            } else if (modPartOutsourcedRadial.isSelected()) {
                String cn = mIDs;
                Outsourced o = new Outsourced(id, name, price, inv, min, max, cn);
                Inventory.getAllParts().remove(dataOutForward);
                Inventory.getAllParts().add(o);
                dataOutForward = null;//
            }
        }
        catch (Exception e){
            intErrorLabel.setVisible(true);
            priceErrorLabel.setVisible(true);
            return;
        }
        // Clear error message
        inputErrorLabel.setVisible(false);
        //When Save clicked, set part information and return to main screen
        FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 779, 351);
        Stage stage = (Stage)modPartSave.getScene().getWindow();
        stage.setTitle("IMS Home");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onCancelClick() throws IOException {

        //When cancel clicked, return to main screen
        dataOutForward = null;
        dataIHForward = null;
        FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 779, 351);
        Stage stage = (Stage)modPartCancel.getScene().getWindow();
        stage.setTitle("IMS Home");
        stage.setScene(scene);
        stage.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String radial = ImsController.getRadial();
        if (radial.contains("Inhouse")){
            dataIHForward = ImsController.getIhDF();

            if (dataIHForward == null){
                modPartOutsourcedRadial.setSelected(true);
                return;
            }
            int pId = dataIHForward.getId();
            String n = dataIHForward.getName();
            double price = dataIHForward.getPrice();
            int stock = dataIHForward.getStock();
            int min = dataIHForward.getMin();
            int max = dataIHForward.getMax();
            int mId = dataIHForward.getMachineID();

            modPartIDField.setText(String.valueOf(pId));
            modPartNameField.setText(n);
            modPartPriceField.setText(String.valueOf(price));
            modPartInvField.setText(String.valueOf(stock));
            modPartMinField.setText(String.valueOf(min));
            modPartMaxField.setText(String.valueOf(max));
            modPartMachineID.setText(String.valueOf(mId));

            modSuperLabel.setText("Machine ID");

            modPartIHRadial.setSelected(true);
        }
        else{
            dataOutForward = ImsController.getOsDF();

            if (dataOutForward == null){
                modPartIHRadial.setSelected(true);
                return;
            }
            int pId = dataOutForward.getId();
            String n = dataOutForward.getName();
            double price = dataOutForward.getPrice();
            int stock = dataOutForward.getStock();
            int min = dataOutForward.getMin();
            int max = dataOutForward.getMax();
            String cn = dataOutForward.getCompanyName();

            modPartIDField.setText(String.valueOf(pId));
            modPartNameField.setText(n);
            modPartPriceField.setText(String.valueOf(price));
            modPartInvField.setText(String.valueOf(stock));
            modPartMinField.setText(String.valueOf(min));
            modPartMaxField.setText(String.valueOf(max));
            modPartMachineID.setText(cn);
            modSuperLabel.setText("Company Name");
            modSuperLabel.setLayoutX(30.0);

            modPartOutsourcedRadial.setSelected(true);
        }
    }

    public void onInHouseSelected(ActionEvent actionEvent) {
        if (dataIHForward == null){
            modPartOutsourcedRadial.setSelected(true);
            return;
        }
        int pId = dataIHForward.getId();
        String n = dataIHForward.getName();
        double price = dataIHForward.getPrice();
        int stock = dataIHForward.getStock();
        int min = dataIHForward.getMin();
        int max = dataIHForward.getMax();
        int mId = dataIHForward.getMachineID();

        modPartIDField.setText(String.valueOf(pId));
        modPartNameField.setText(n);
        modPartPriceField.setText(String.valueOf(price));
        modPartInvField.setText(String.valueOf(stock));
        modPartMinField.setText(String.valueOf(min));
        modPartMaxField.setText(String.valueOf(max));
        modPartMachineID.setText(String.valueOf(mId));

        modSuperLabel.setText("Machine ID");
    }

    public void onOutsourcedSelected(ActionEvent actionEvent) {
        if (dataOutForward == null){
            modPartIHRadial.setSelected(true);
            return;
        }
        int pId = dataOutForward.getId();
        String n = dataOutForward.getName();
        double price = dataOutForward.getPrice();
        int stock = dataOutForward.getStock();
        int min = dataOutForward.getMin();
        int max = dataOutForward.getMax();
        String cn = dataOutForward.getCompanyName();

        modPartIDField.setText(String.valueOf(pId));
        modPartNameField.setText(n);
        modPartPriceField.setText(String.valueOf(price));
        modPartInvField.setText(String.valueOf(stock));
        modPartMinField.setText(String.valueOf(min));
        modPartMaxField.setText(String.valueOf(max));
        modPartMachineID.setText(cn);
        modSuperLabel.setText("Company Name");
        modSuperLabel.setLayoutX(30.0);
    }
}
