/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personnelmanager.contact;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import personnelmanager.models.Contact;
import personnelmanager.models.Requester;

/**
 * FXML Controller class
 *
 * @author tabueu
 */
public class ContactController extends Requester implements Initializable {

    @FXML
    private TextField type;
    @FXML
    private TextField valeur;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void saveContact(ActionEvent event) throws IOException {
        Contact contact = new Contact(type.getText(), valeur.getText(), false);
        System.out.println("---"+contact.toString());
        String url_endpoint = "http://localhost:8080/add-contact";
        String path_return_file = "/personnelmanager/contact/contact.fxml";
        
        buildPost(contact, url_endpoint, path_return_file);
        
    }

   
    
}
