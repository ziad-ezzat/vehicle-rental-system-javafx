package project;

import java.sql.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
       
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root2 = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        Scene scene1 = new Scene(root2); 
        stage.setScene(scene1);
        stage.show();
        
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        launch(args);
    }
    
}
