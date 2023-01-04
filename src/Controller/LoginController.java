package Controller;

import com.mysql.cj.xdevapi.PreparableStatement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class LoginController implements Initializable{
    
    @FXML
    private RadioButton customer;
    
    @FXML
    private RadioButton Owner;
    
    @FXML
    TextField username;
    
    @FXML
    TextField password;
    
    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, SQLException {
        
        if(customer.isSelected())
        {
            String query = "SELECT * FROM `customer` WHERE `username` =? AND `password` =?";
            String user = username.getText();
            String pass = password.getText();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject","root","root");
            } catch (ClassNotFoundException ex) {
            } catch (SQLException ex) {
            }
            ps=con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if(rs.next())
            {
                System.out.println("DOne");
                Parent loader = FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));
                Scene scene = new Scene(loader);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene);
                app_stage.show(); 
            }
            else{
                    JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);
                }
            System.out.println(con);
        }
        else if(Owner.isSelected())
        {
            String query = "SELECT * FROM `owner` WHERE `username` =? AND `password` =?";
            String user = username.getText();
            String pass = password.getText();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject","root","root");
            } catch (ClassNotFoundException ex) {
            } catch (SQLException ex) {
            }
            ps=con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if(rs.next())
            {
                System.out.println("DOne");
                Parent loader = FXMLLoader.load(getClass().getResource("/FXML/Owner.fxml"));
                Scene scene = new Scene(loader);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene);
                app_stage.show(); 
            }
            else{
                    JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);
                }
            System.out.println(con); 
        }
        else
        {
            String query = "SELECT * FROM `admin` WHERE `username` =? AND `password` =?";
            String user = username.getText();
            String pass = password.getText();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject","root","root");
            } catch (ClassNotFoundException ex) {
            } catch (SQLException ex) {
            }
            ps=con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if(rs.next())
            {
                System.out.println("DOne");
                Parent loader = FXMLLoader.load(getClass().getResource("/FXML/Admin.fxml"));
                Scene scene = new Scene(loader);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.setScene(scene);
                app_stage.show(); 
            }
            else{
                    JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);
                }
            System.out.println(con);
        }
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
}