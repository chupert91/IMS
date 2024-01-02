/**
 * @author Chuck Hupert II
 * C482 PA
 */

package hupert.inventorymanagementsystem;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ImsApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ImsApplication.class.getResource("/hupert/inventorymanagementsystem/home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 779, 351);
        stage.setTitle("C482 IMS");
        stage.setScene(scene);
        stage.show();
    }

    /*
     * JavaDocs are located in the "JavaDocs" folder
     */

    public static void main(String[] args) {
        launch();
    }
}

