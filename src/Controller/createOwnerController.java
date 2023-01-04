/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class createOwnerController implements Initializable{
    
    
    String sql = "insert into owner ( username , password)" 
            + " values (?,?)";
    Connection con = null;
    
    @FXML
    TextField username;
    
    @FXML
    PasswordField password;
    
    @FXML
    private void back(ActionEvent event) throws IOException
    {
            Parent loader = FXMLLoader.load(getClass().getResource("/FXML/createUser.fxml"));
            Scene scene = new Scene(loader);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene);
            app_stage.show();
    }
    
    @FXML
    private  void addOwner(ActionEvent event) throws IOException, SQLException
    {
        try {
              Class.forName("com.mysql.cj.jdbc.Driver");
              con=DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject","root","root");
            } catch (ClassNotFoundException ex) {
            } catch (SQLException ex) {
            }
        PreparedStatement stat= con.prepareStatement(sql);
        stat.setString(1,username.getText());
        stat.setString(2, password.getText());
        stat.execute();
        con.close();
        System.out.println("owner add sucsess");
    }
   
    @Override
public void initialize(URL location, ResourceBundle resources) {
    
            
}
    
}
