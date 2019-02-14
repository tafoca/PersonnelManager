/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personnelmanager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author tabueu
 */
public class ShowPersonnelController implements Initializable {

    @FXML
    private TextField departement;

    @FXML
    private TextField name;

    @FXML
    private TextField salary;
    
    private Personnel personnel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            //chargement de la liste du personnel
            Integer id = 1;
            location = new URL("http://localhost:8080/personnels/show/" + id);
            HttpURLConnection con = (HttpURLConnection) location.openConnection();
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestMethod("GET");
            con.getInputStream();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            JSONParser parser = new JSONParser();

            JSONObject jSONObject = (JSONObject) parser.parse(in.readLine());

            this.personnel = new Personnel(
                    jSONObject.get("id").toString(),
                    jSONObject.get("name").toString(),
                    jSONObject.get("salary").toString(),
                    jSONObject.get("departement").toString());
            
            //affichage
            departement.setText(personnel.getDepartement());
            name.setText(personnel.getName());
            salary.setText(personnel.getSalary());
            
            
        } catch (Exception ex) {
            System.out.println("------->>" +ex.getMessage());
        }

    }

}
