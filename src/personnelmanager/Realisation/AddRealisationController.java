package personnelmanager.Realisation;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import personnelmanager.Fonction;
import personnelmanager.models.Realisation;
import personnelmanager.models.Requester;

/**
 * FXML Controller class
 *
 * @author tabueu
 */
public class AddRealisationController extends Requester implements Initializable {

    @FXML
    private TextField realisationName;
    @FXML
    private DatePicker beginRealisation;
    @FXML
    private ComboBox<Fonction> function;
    @FXML
    private DatePicker endRealisation;
    @FXML
    private TextArea description;
     
    ObservableList<Fonction> fonctions;
    
    

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final String URL_ALLFunction = "http://localhost:8080/all-fonctions";
        try {
            JSONArray array = getAllObjets(url, URL_ALLFunction);
            this.fonctions = FXCollections.observableArrayList();
            
            JSONParser parser = new JSONParser();
            for (Object object : array) {
                JSONObject jSONObject = (JSONObject) parser.parse(object.toString());
                System.out.println("* -- *" + jSONObject.toJSONString());
                Fonction f = new Fonction(
                        jSONObject.get("dateDebut").toString(),
                        jSONObject.get("functionName").toString()
                );
                f.setId(jSONObject.get("id").toString());

                fonctions.add(f);

            }
            function.setItems(fonctions);
        } catch (Exception e) {

        }
    }

    @FXML
    private void saveRealisation(ActionEvent event) throws IOException {
        JSONObject jSONObject = new JSONObject();
        String url_endpoint = "http://localhost:8080/add-realisation";
        String path_return_file = "/personnelmanager/Realisation/AddRealisation.fxml";
        Gson gson =new Gson();
        String jsonFct = gson.toJson(this.fonctions.get(function.getSelectionModel().getSelectedIndex()));
        Realisation r =new Realisation(realisationName.getText(), 
                description.getText(),
                beginRealisation.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                endRealisation.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        r.setFunction(this.fonctions.get(function.getSelectionModel().getSelectedIndex()));
        String jsonRealisation = gson.toJson(r);
        
        //Realisation realisation1 = gson.fromJson(realisation.toJSONString().toString(), Realisation.class);
        jSONObject.put("function", jsonFct);
        //  jSONObject.put("function", (this.fonctions.get(function.getSelectionModel().getSelectedIndex())).getId());
        jSONObject.put("realisationName", realisationName.getText());
        jSONObject.put("description", description.getText());
        jSONObject.put("beginRealisation", beginRealisation.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        jSONObject.put("endRealisation", endRealisation.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.println(" Realisation : -> " + jSONObject.toJSONString());
        
        //buildPost(jSONObject, url_endpoint, path_return_file);
        
        buildPost_Gson(jsonRealisation, url_endpoint, path_return_file);
    }

   

}
