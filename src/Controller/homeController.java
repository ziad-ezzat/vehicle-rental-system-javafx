/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Vehicles;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
/**
 *
 * @author AMIT
 */
public class homeController implements Initializable {
    
    
    
    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    
    @FXML private TableView<Vehicles> tableview;
    @FXML private TableColumn<Vehicles, String> CarID;
    @FXML private TableColumn<Vehicles, String> Type;
    @FXML private TableColumn<Vehicles, String> Moudel;
    @FXML private TableColumn<Vehicles, String> Avalible;
    @FXML private TableColumn<Vehicles, String> salary;
    @FXML private TableColumn<Vehicles, String> photo;
    @FXML private TextField filterField;
   
    @FXML
    private void back(ActionEvent event) throws IOException
    {
            Parent loader = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
            Scene scene = new Scene(loader);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(scene);
            app_stage.show();
    }
    
    
                   
    
    private final ObservableList<Vehicles> data = FXCollections.observableArrayList();
    
    
    @FXML
    private void show(ActionEvent event) throws IOException, SQLException
    {
        String query = "SELECT * FROM vehicles";
        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject","root","root");
            } catch (ClassNotFoundException ex) {
            } catch (SQLException ex) {
            }
            ps=con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next())
                {
                int idv = rs.getInt("veid");
                String type = rs.getString("type");
                String moudel = rs.getString("moudel");
                String avalible = rs.getString("avalible");
                int salaryc = rs.getInt("salary");
                ImageView photolo = new ImageView(new Image(this.getClass().getResourceAsStream("image0.png")));
                Vehicles emp = new Vehicles(photolo,idv, type, moudel, avalible, salaryc);
                data.add(emp);
                tableview.setItems(data);
                }
                ps.close();
    } 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println("Button Clicked and a new Row Added");
        
         
        ImageView emp1photo = new ImageView(new Image(this.getClass().getResourceAsStream("car.png")));
        ImageView emp2photo = new ImageView(new Image(this.getClass().getResourceAsStream("car.png")));
        ImageView emp3photo = new ImageView(new Image(this.getClass().getResourceAsStream("car.png")));
                
        
        Vehicles emp2 = new Vehicles(emp1photo, 115, "car", "bmw", "2/2/2003", 40000);
        Vehicles emp3 = new Vehicles(emp2photo, 116, "car", "bmw", "2/2/2003", 80000);
        Vehicles emp4 = new Vehicles(emp3photo,117, "car", "honday", "2/2/2003", 80000);
        
        data.add(emp2);
        data.add(emp3);
        data.add(emp4);
        
        
        tableview.setItems(data);
        
         photo.setPrefWidth(80); 
                    photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
        CarID.setCellValueFactory(
            new PropertyValueFactory<>("EmpID"));
        
        
        Type.setCellValueFactory(
            new PropertyValueFactory<>("firstName"));
        
        Moudel.setCellValueFactory(
            new PropertyValueFactory<>("email"));
        
        Avalible.setCellValueFactory(
            new PropertyValueFactory<>("department"));
        
        salary.setCellValueFactory(
            new PropertyValueFactory<>("salary"));        
        
        ImageView emp0photo = new ImageView(new Image(this.getClass().getResourceAsStream("My.png")));
        Vehicles emp1 = new Vehicles(emp0photo, 11200, "motorcycle", "marceds", "2/2/2003", 30000);
        data.add(emp1);
       
        
        
        tableview.setItems(data);
        
        FilteredList<Vehicles> filterData =new FilteredList<>(data, b -> true);
        
        filterField.textProperty().addListener((observable,oldValue,newValue) -> {
            filterData.setPredicate(vehicles -> {
                
                if(newValue == null || newValue.isEmpty())
                    {
                        return true;
                    }
                
                String lowCaseFilter = newValue.toLowerCase();
                if(vehicles.getFirstName().toLowerCase().indexOf(lowCaseFilter) != -1 )
                {
                    return true;
                }
                
                else if (vehicles.getDepartment().toLowerCase().indexOf(lowCaseFilter) != -1)
                    {
                        return true;
                    }
                
                else if (vehicles.getEmail().toLowerCase().indexOf(lowCaseFilter) != -1)
                    {
                        return true;
                    }
                else if (String.valueOf(vehicles.getSalary()).indexOf(lowCaseFilter) != -1)
                    {
                        return true;
                    }
                else
                {
                    return false;
                }
                      
            });
        });
        
        SortedList<Vehicles> sortedData = new  SortedList<>(filterData);
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(sortedData);    
        
    }
    
    
    @FXML
    private void details(ActionEvent event) throws IOException, SQLException
    {
        Parent loader = FXMLLoader.load(getClass().getResource("/FXML/Details.fxml"));
        Scene scene = new Scene(loader);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
        
    }
    

      
    
}
