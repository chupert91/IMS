module hupert.inventorymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens hupert.inventorymanagementsystem to javafx.fxml;
    exports hupert.inventorymanagementsystem;
}