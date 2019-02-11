/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personnelmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * FXML Controller class
 *
 * @author tabueu
 */
public class AllPersonnelsController implements Initializable {
     
    
    @FXML
    private TableView<Personnel> personnel;

    @FXML
    private TableColumn<Personnel, String> id;

    @FXML
    private TableColumn<Personnel, String> name;

    @FXML
    private TableColumn<Personnel, String> salary;

    @FXML
    private TableColumn<Personnel, String> departement;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            URL url1 = new URL("http://localhost:8080/all-personnels"); 
            
            HttpURLConnection con = (HttpURLConnection)url1.openConnection();
            con.setUseCaches(false);
            con.setDoInput(true);
            //con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.getInputStream();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(in.readLine());
            
            System.out.println("  ---> "+ array.toJSONString());
            
            ObservableList<Personnel> listPersonnels = FXCollections.observableArrayList();
            
            for (Object object : array) {
                JSONObject jSONObject = (JSONObject) parser.parse(object.toString());
                 System.out.println("* -- *"+jSONObject.toJSONString());
               Personnel p = new Personnel(
                        jSONObject.get("name").toString(),
                        jSONObject.get("salary").toString(),
                        jSONObject.get("departement").toString());
                System.out.println("*** -- "+p.toString());
                
                listPersonnels.add(p);
               
                id.setCellValueFactory(new PropertyValueFactory<>("id"));
                name.setCellValueFactory(new PropertyValueFactory<>("name"));
                salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
                departement.setCellValueFactory(new PropertyValueFactory<>("departement"));
               
            }
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(AllPersonnelsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AllPersonnelsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AllPersonnelsController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }    
    
}
