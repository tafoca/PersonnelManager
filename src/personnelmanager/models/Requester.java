package personnelmanager.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author tabueu
 */
public class Requester {

    public Requester() {
    }

    public void buildFenetre(String mon_fichier) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(mon_fichier));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.setIconified(false);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.show();

    }

    /**
     * traitement des methode post
     *
     * @param jSONObject objet json a construire
     * @param url_endpoint url de notre enpoint pour le post
     * @param path_return_file chemin acces en <b>chemin.fxml </b> de la vue de
     * sortir
     */
    public void buildPost(JSONObject jSONObject, String url_endpoint, String path_return_file) throws IOException {

        System.out.println("--- ---> --->  " + jSONObject.toJSONString());

        URL url = new URL(url_endpoint);
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

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        //get state response
        if (in.readLine().equalsIgnoreCase("success")) {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(path_return_file));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setAlwaysOnTop(true);
            stage.setIconified(false);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.setMaximized(false);
            stage.show();

        }

    }

    public void buildPost(Contact o, String url_endpoint, String path_return_file) throws IOException {

        System.out.println("--- ---> --->  " + o.toString());

        URL url = new URL(url_endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setUseCaches(false);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        con.setRequestMethod("POST");
        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        wr.write(o.toString());
        wr.flush();
        wr.flush();
        wr.close();
        con.getInputStream();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        //get state response
        if (in.readLine().equalsIgnoreCase("success")) {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(path_return_file));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setAlwaysOnTop(true);
            stage.setIconified(false);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.setMaximized(false);
            stage.show();

        }

    }

    protected void buildPost_Gson(String jsonRealisation, String url_endpoint, String path_return_file) throws MalformedURLException, IOException {

        System.out.println("--- ---> gson object< --->  " + jsonRealisation);

        URL url = new URL(url_endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setUseCaches(false);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        con.setRequestMethod("POST");

        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        wr.write(jsonRealisation);
        wr.flush();
        wr.flush();
        wr.close();
        con.getInputStream();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        //get state response
        if (in.readLine().equalsIgnoreCase("success")) {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(path_return_file));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setAlwaysOnTop(true);
            stage.setIconified(false);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.setMaximized(false);
            stage.show();

        }

    }

    public JSONObject buildGet(URL location, String url_show) throws MalformedURLException, IOException, ParseException {

        JSONObject jSONObject = (JSONObject) this.buildURlGet(location, url_show);
        System.out.println("------>> " + jSONObject.toJSONString());

        return jSONObject;

    }

    public JSONArray getAllObjets(URL location, String url_show) throws MalformedURLException, IOException, ParseException {
        //chargement de la liste du personnel
        JSONArray array = (JSONArray) this.buildURlGet(location, url_show);
        System.out.println("  ---> " + array.toJSONString());
        return array;
    }

    private Object buildURlGet(URL location, String url_show) throws MalformedURLException, IOException, ParseException {
        location = new URL(url_show);
        HttpURLConnection con = (HttpURLConnection) location.openConnection();
        con.setUseCaches(false);
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setRequestMethod("GET");
        con.getInputStream();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        JSONParser parser = new JSONParser();
        return parser.parse(in.readLine());
    }

}
