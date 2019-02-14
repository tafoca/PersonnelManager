package personnelmanager;

/**
 *
 * @author tabueu
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ShowFonctionController implements Initializable {

    @FXML
    private TextField departement;

    @FXML
    private TextField dateDebut;

    @FXML
    private TextField functionName;

    @FXML
    private TextField name;

    @FXML
    private TextField salary;

    @FXML
    private Label nom_fct;

    private Fonction fonction;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
        try {
            //chargement de la liste du personnel
            Integer id = 1;
            location = new URL("http://localhost:8080/fonctions/show/" + id);
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

            System.out.println("-----------><><>>>>> " + jSONObject.toJSONString());
            this.fonction = new Fonction();
            this.fonction.setFunctionName(jSONObject.get("functionName").toString());
            this.fonction.setDateDebut(jSONObject.get("dateDebut").toString());
            System.out.println("----------->>> " + jSONObject.get("personnel"));
            JSONObject jsonpersone = (JSONObject) jSONObject.get("personnel");
            Personnel p = new Personnel(jsonpersone.get("id").toString(),
                    jsonpersone.get("name").toString(),
                    jsonpersone.get("salary").toString(),
                    jsonpersone.get("departement").toString());

            this.fonction.setPersonnel(p);

            //affichage final apres construction
            departement.setText(this.fonction.getPersonnel().getDepartement());

            dateDebut.setText(this.fonction.getDateDebut());

            functionName.setText(this.fonction.getFunctionName());
            name.setText(this.fonction.getPersonnel().getName());

            salary.setText(this.fonction.getPersonnel().getSalary());
            nom_fct.setText(this.fonction.getFunctionName());

        } catch (IOException | ParseException ex) {
            System.out.println("------->>" + ex.getMessage());
        }

    }

}
