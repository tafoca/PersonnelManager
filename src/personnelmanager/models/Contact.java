/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personnelmanager.models;

import com.fasterxml.jackson.annotation.JsonView;
import java.io.Serializable;


/**
 *
 * @author tabueu
 */
public class Contact{
        
    @JsonView(Scope.FirstLevel.class)
    private Integer id;
    @JsonView(Scope.FirstLevel.class)
    private String type;
    @JsonView(Scope.FirstLevel.class)
    private String value;
    @JsonView(Scope.FirstLevel.class)
    private Boolean isNotificationContact;
    
     public Contact(String type, String value, Boolean isNotificationContact) {
        this.type = type;
        this.value = value;
        this.isNotificationContact = isNotificationContact;
    }
    

    public Integer getId_() {
        return id;
    }

    public void setId_(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getIsNotificationContact() {
        return isNotificationContact;
    }

    public void setIsNotificationContact(Boolean isNotificationContact) {
        this.isNotificationContact = isNotificationContact;
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + id + ", type=" + type + ", value=" + value + ", isNotificationContact=" + isNotificationContact + '}';
    }
    
    
 
}
