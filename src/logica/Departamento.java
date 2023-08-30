package logica;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;

@Entity
public class Departamento implements Serializable{
    @Id
    private String nombreDepartamento;

    public Departamento() {
    }
    
    public Departamento(String nombreDepartamento){this.nombreDepartamento=nombreDepartamento;}
    
    public String getNombreDepto(){
    return nombreDepartamento;
    }
    
}
