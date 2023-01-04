
package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;



public class OwnerController implements Initializable{
    
     String sql = "insert into waitlist (veid,type,moudel,avalible,salary)" 
            + " values (?,?,?,?,?)";
    Connection con = null;
    
    @FXML
    TextField carid;
    @FXML
    TextField salary;
    @FXML
    TextField moudel;
    @FXML
    TextField type;
    @FXML
    TextField avalible;

    
    @FXML
    private void back(ActionEvent event) throws IOException
    {
            Parent loader = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
            Scene scene = new Scene(loader);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene);
            app_stage.show();
    }
    
    @FXML
    private void home(ActionEvent event) throws IOException
    {
            Parent loader = FXMLLoader.load(getClass().getResource("/FXML/Home.fxml"));
            Scene scene = new Scene(loader);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene);
            app_stage.show();
    }
    
    @FXML
    private void add(ActionEvent event) throws IOException, SQLException
    {
         try {
              Class.forName("com.mysql.cj.jdbc.Driver");
              con=DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject","root","root");
            } catch (ClassNotFoundException ex) {
            } catch (SQLException ex) {
            }
        PreparedStatement stat= con.prepareStatement(sql);
        stat.setString(1,carid.getText());
        stat.setString(2, type.getText());
        stat.setString(3, moudel.getText());
        stat.setString(4, avalible.getText());
        stat.setString(5, salary.getText());
        stat.execute();
        con.close();
        System.out.println("vehicle in waitlist sucsess");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            
        }              
}
    
    
    

   
