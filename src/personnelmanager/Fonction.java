/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personnelmanager;

/**
 *
 * @author tabueu
 */
public class Fonction {
    
    String id,dateDebut,functionName;
    Personnel personnel;
    
     public Fonction(String dateDebut, String functionName) {
        this.dateDebut = dateDebut;
        this.functionName = functionName;
    }

    public Fonction(String dateDebut, String functionName, Personnel personnel) {
        this(dateDebut, functionName);
        this.personnel = personnel;
    }
    /**
     * 
     * @param id
     * @param dateDebut
     * @param functionName
     * @param personnel 
     */
     public Fonction(String id,String dateDebut, String functionName, Personnel personnel) {
        this(dateDebut, functionName,personnel);
        this.id = id;
    }

    public Fonction() {
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    @Override
    public String toString() {
        return "Fonction{" + "id=" + id + ", dateDebut=" + dateDebut + ", functionName=" + functionName + ", personnel=" + personnel + '}';
    }
    
    
    
}
