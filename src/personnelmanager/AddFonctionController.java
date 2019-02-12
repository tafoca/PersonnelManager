package personnelmanager;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tabueu
 */
public class AddFonctionController implements Initializable {
    @FXML
    private TextField functionName;

    @FXML
    private DatePicker dateDebut;

    @FXML
    private ComboBox<Personnel> personnel;
    
    ObservableList<Personnel> listPersonnels;

    @FXML
    void saveFonctioning(ActionEvent event) {
        
        System.out.println(" ecoute ....");
          
        JSONObject jSONObject = new JSONObject();
        //recuperation du personnel courant 
        Personnel p = this.listPersonnels.get(personnel.getSelectionModel().getSelectedIndex()) ;
       
        //construction du json du personnel
        JSONObject jSONObjectPerssonnel = new JSONObject();
        jSONObjectPerssonnel.put("id", p.getId());
       /* jSONObjectPerssonnel.put("name", p.getName());
        jSONObjectPerssonnel.put("salary", p.getSalary());
        jSONObjectPerssonnel.put("departement", p.getDepartement());*/
        
        System.out.println(" personnel :  -> <-" + jSONObjectPerssonnel.toJSONString());
        
        //construction final de l'objet
        jSONObject.put("functionName", functionName.getText());
        jSONObject.put("dateDebut", dateDebut.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        jSONObject.put("personnel", p.getId());
        
        System.out.println(" Function : -> " + jSONObject.toJSONString());
        
        try{
            URL url = new URL("http://localhost:8080/add-fonction");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            OutputStreamWriter wr =new OutputStreamWriter(con.getOutputStream());
            wr.write(jSONObject.toString());
            wr.flush();
            wr.flush();
            wr.close();
            con.getInputStream();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            System.err.println("out lecture");
            
            //get state response
            if(in.readLine().equalsIgnoreCase("success")){
                 System.err.println("in lecture");
           
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("AllPersonnels.fxml"));
                System.err.println("in all case");
                Scene  scene = new  Scene(root);
                stage.setScene(scene);
                stage.setAlwaysOnTop(true);
                stage.setIconified(false);
                stage.centerOnScreen();
                stage.setResizable(false);
                stage.setMaximized(false);
                stage.show();
                
                
            }
            
        }
        catch(Exception e){
            System.out.println("Eroor "+ e.getMessage());
        }


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            //chargement de la liste du personnel
            location = new URL("http://localhost:8080/all-personnels");
            HttpURLConnection con = (HttpURLConnection)location.openConnection();
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
            
           // System.out.println("  ---> "+ array.toJSONString());
            
            this.listPersonnels = FXCollections.observableArrayList();
            
            for (Object object : array) {
                JSONObject jSONObject = (JSONObject) parser.parse(object.toString());
                 System.out.println("* -- *"+jSONObject.toJSONString());
               Personnel p = new Personnel(
                       jSONObject.get("id").toString(),
                        jSONObject.get("name").toString(),
                        jSONObject.get("salary").toString(),
                        jSONObject.get("departement").toString());
               // System.out.println("*** -- "+p.toString());
                
                listPersonnels.add(p);
               
            }
            personnel.setItems(listPersonnels);
        } catch (Exception ex) {
            Logger.getLogger(AddFonctionController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
    }
    
}
