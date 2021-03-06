/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personnelmanager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import personnelmanager.models.Requester;

/**
 *
 * @author tabueu
 */
public class FXMLDocumentController extends Requester implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private TextField salary;

    @FXML
    private TextField departement;

    @FXML
    void savePersonnel(ActionEvent event) {
        System.out.println(" ecoute ....");

        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", name.getText());
        jSONObject.put("salary", salary.getText());
        jSONObject.put("departement", departement.getText());

        System.out.println("  " + jSONObject.toJSONString());

        try {
            URL url = new URL("http://localhost:8080/add-personnel");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(jSONObject.toString());
            wr.flush();
            wr.flush();
            wr.close();
            con.getInputStream();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            System.err.println("out lecture");

            //get state response
            if (in.readLine().equalsIgnoreCase("success")) {
                System.err.println("in lecture");

                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("AllPersonnels.fxml"));
                System.err.println("in all case");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setAlwaysOnTop(true);
                stage.setIconified(false);
                stage.centerOnScreen();
                stage.setResizable(false);
                stage.setMaximized(false);
                stage.show();

            }

        } catch (Exception e) {
            System.out.println("Eroor " + e.getMessage());
        }

    }

    @FXML
    void saveFunction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddFonction.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.setIconified(false);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.show();
    }

    //affichage personnel
    @FXML
    void showPersonnel(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ShowPersonnel.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.setIconified(false);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.show();
    }

    //show inteface Function detail
    @FXML
    void ShowFonction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ShowFonction.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.setIconified(false);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.show();
    }

    @FXML
    void saveRealisation(ActionEvent event) throws IOException {
        String mon_fichier = "/personnelmanager/Realisation/AddRealisation.fxml";
        buildFenetre(mon_fichier);
    }
    
    @FXML
    void addContact(ActionEvent event) throws IOException {
         String mon_fichier = "/personnelmanager/contact/contact.fxml";
        buildFenetre(mon_fichier);
    }

    @FXML
    void ShowRealisation(ActionEvent event) throws IOException {
        String mon_fichier = "/personnelmanager/Realisation/ShowRealisation.fxml";
        buildFenetre(mon_fichier);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
