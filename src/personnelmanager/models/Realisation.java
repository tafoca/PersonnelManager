/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personnelmanager.models;

import personnelmanager.Fonction;



/**
 *
 * @author tabueu
 */
public class Realisation {
    
    Integer id;
    String realisationName;
    String description;
    String beginRealisation;
    private String endRealisation;
    private Fonction function;

    public Realisation() {
    }
     public Realisation(String realisationName, String description, String beginRealisation, String endRealisation) {
        this.realisationName = realisationName;
        this.description = description;
        this.beginRealisation = beginRealisation;
        this.endRealisation = endRealisation;
    }
    public Realisation(Integer id, String realisationName, String description, String beginRealisation, String endRealisation) {
        this(realisationName, description, beginRealisation, endRealisation);
        this.id = id;
    }
    
    /**
     * 
     * @param id
     * @param realisationName
     * @param description
     * @param beginRealisation
     * @param endRealisation
     * @param f 
     */
    public Realisation(Integer id, String realisationName, String description, String beginRealisation, String endRealisation,Fonction f) {
        this(id,realisationName, description, beginRealisation, endRealisation);
        this.function = f;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealisationName() {
        return realisationName;
    }

    public void setRealisationName(String realisationName) {
        this.realisationName = realisationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBeginRealisation() {
        return beginRealisation;
    }

    public void setBeginRealisation(String beginRealisation) {
        this.beginRealisation = beginRealisation;
    }

    public String getEndRealisation() {
        return endRealisation;
    }

    public void setEndRealisation(String endRealisation) {
        this.endRealisation = endRealisation;
    }

    public Fonction getFunction() {
        return function;
    }

    public void setFunction(Fonction function) {
        this.function = function;
    }

    @Override
    public String toString() {
        return "Realisation{" + "id=" + id + ", realisationName=" + realisationName + ", description=" + description + ", beginRealisation=" + beginRealisation + ", endRealisation=" + endRealisation + ", function=" + function + '}';
    }
    
    
    

    
}
