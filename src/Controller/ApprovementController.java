/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Mega Store
 */
public class ApprovementController implements Initializable {
    
    int idv ;
    String type;
    String moudel;
    String avalible;
    int salary;
    
    Connection con = null;
    PreparedStatement ps;
    ResultSet rs;
    
    @FXML
    TextField id;
    @FXML
    TextArea showtext;
    
    @FXML
    private void accept(ActionEvent event) throws SQLException
    {
        String sql = "insert into vehicles ( veID , type,moudel,avalible,salary)" 
            + " values (?,?,?,?,?)";
        String sqldel = "DELETE FROM `waitlist` WHERE `veid` = ?";
        try {
              Class.forName("com.mysql.cj.jdbc.Driver");
              con=DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject","root","root");
            } catch (ClassNotFoundException ex) {
            } catch (SQLException ex) {
            }
        PreparedStatement stat= con.prepareStatement(sql);
        stat.setString(1,""+idv);
        stat.setString(2, type);
        stat.setString(3, moudel);
        stat.setString(4, avalible);
        stat.setString(5,""+salary);
        stat.execute();
        PreparedStatement statedel = con.prepareStatement(sqldel);
        statedel.setString(1,""+idv);
        statedel.execute();
        con.close();
        System.out.println("vechile add sucsess");
        System.out.println(" "+idv+" "+type+" "+moudel+" "+avalible+" "+salary);
    }

    @FXML
    private void check(ActionEvent event) throws IOException, SQLException
    {
         String query = "SELECT * FROM `waitlist` WHERE `veid` =?";
         String idx = id.getText();
         try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject","root","root");
            } catch (ClassNotFoundException ex) {
            } catch (SQLException ex) {
            }
            ps=con.prepareStatement(query);
            ps.setString(1, idx);
            rs = ps.executeQuery();
            if(rs.next())
            {
                System.out.println("DOne");
                idv = rs.getInt("veid");
                type = rs.getString("type");
                moudel = rs.getString("moudel");
                avalible = rs.getString("avalible");
                salary = rs.getInt("salary");
                showtext.setText(" "+idv+" "+type+" "+moudel+" "+avalible+" "+salary);
            }
            else{
                    showtext.setText("no vehciles found !!! ");
                }
            System.out.println(con);
    }
    
    @FXML
    private void back(ActionEvent event) throws IOException
    {
        Parent loader = FXMLLoader.load(getClass().getResource("/FXML/Admin.fxml"));
        Scene scene = new Scene(loader);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}