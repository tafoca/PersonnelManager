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
class Personnel {
  public String id,name, salary, departement;
    
  public Personnel(String id,String name, String salary, String departement) {
       this(name, salary, departement);
       this.id = id;
    }
 
    public Personnel(String name, String salary, String departement) {
       
        this.name = name;
        this.salary = salary;
        this.departement = departement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    
    @Override
    public String toString() {
        return "Personnel{" + "id=" + id + ", name=" + name + ", salary=" + salary + ", departement=" + departement + '}';
    }
    
    
  
    
    
    
}
