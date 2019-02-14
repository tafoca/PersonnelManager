/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personnelmanager.Realisation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import personnelmanager.Fonction;
import personnelmanager.Personnel;
import personnelmanager.models.Realisation;
import personnelmanager.models.Requester;

/**
 * FXML Controller class
 *
 * @author tabueu
 */
public class ShowRealisationController extends Requester implements Initializable {
    
    //info sur la fonction
    
    @FXML
    private TextField DatedebFonction;
    @FXML
    private TextField nameFonction;
    
    //info sur la realisation
    @FXML
    private TextField name;
    @FXML
    private Label nom_fct;
    @FXML
    private TextField dateFin;
    
    @FXML
    private TextField dateDebut;
    
    //info sur une personne 
    @FXML
    private Label nom;
    @FXML
    private Label dept;
    @FXML
    private Label salaire;
    
    private Personnel personnel;
    private Fonction fonction;
    private Realisation realisation;
    
    
    
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //parametre nom
        nom.setText("");
        dept.setText("");
        salaire.setText("");
    
        try {
             Integer id = 1;
         String url_show = "http://localhost:8080/realisations/show/" + id;
         
         JSONObject buildGet = this.buildGet(url, url_show);
         
          JSONObject fct =  (JSONObject) buildGet.get("function");
          JSONObject pers = (JSONObject) fct.get("personnel");
          
          personnel = new Personnel(pers.get("id").toString(),
                                    pers.get("name").toString(), 
                                    pers.get("salary").toString(),
                                    pers.get("departement").toString());
         
          fonction = new Fonction(fct.get("id").toString(), 
                                  fct.get("dateDebut").toString(),
                                  fct.get("functionName").toString(),
                                  personnel);
          
          realisation = new Realisation(Integer.parseInt(buildGet.get("id").toString()),
                                        buildGet.get("realisationName").toString(),
                                         buildGet.get("description").toString(),
                                           buildGet.get("beginRealisation").toString(),
                                            buildGet.get("endRealisation").toString(), 
                                            fonction);
          
          builMyView();
          
        } catch (IOException ex) {
            Logger.getLogger(ShowRealisationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ShowRealisationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } 
    
    public void builMyView(){
          //info sur la fonction
    DatedebFonction.setText(fonction.getDateDebut());
    nameFonction.setText(fonction.getFunctionName());
    
    //info sur la realisation
     name.setText(realisation.getRealisationName());
     nom_fct.setText(realisation.getFunction().getFunctionName());
     dateFin.setText(realisation.getEndRealisation());
     dateDebut.setText(realisation.getBeginRealisation());
    
    //info sur une personne 
     nom.setText(personnel.getName());
     dept.setText(personnel.getDepartement());
     salaire.setText(personnel.getSalary());
    
    }
        
    
}
