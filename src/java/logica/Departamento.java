package logica;
import javax.persistence.Entity;
import javax.persistence.Id;
import logica.Proveedor;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import logica.Paquete;

@Entity
public class Departamento implements Serializable{
    @Id
    private String nombreDepartamento;
    private String descripcion;
    private String url;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static Map<String, Departamento> getDepartamentos() {
        return departamentos;
    }

    public static void setDepartamentos(Map<String, Departamento> departamentos) {
        Departamento.departamentos = departamentos;
    }
/*
    public Collection<ActividadTuristica> getActTur() {
        return actTur;
    }*/

    public static Map<String, Departamento> departamentos= new HashMap<String, Departamento>();

     
    public static Departamento encontrarDepto(String nombreDpto) {
        return departamentos.get(nombreDpto);
    }
    
    public Departamento() {
        
        actTur = new ArrayList< ActividadTuristica>();
    }
    
    public Departamento(String nombreDepartamento,String descripcion, String url){
        this.nombreDepartamento=nombreDepartamento;
        this.descripcion=descripcion;
        this.url=url;
       
        actTur = new ArrayList< ActividadTuristica>();
     }
    

@OneToMany(mappedBy = "departamento")
    private Collection<ActividadTuristica> actTur;
  
   

/*
public void listarActividadesDepNoPaquete(String nombre_paquete){
    ActividadTuristica aux;
    for (Map.Entry<String, Usuario> entry : Usuario.usuariosMail.entrySet()) {
        Usuario usuario = entry.getValue();
        if (usuario instanceof Proveedor) {
            Proveedor proveedor = (Proveedor) usuario;//Casteo de proveedor de usuario 
        for(ActividadTuristica act :proveedor.getActTuristica()){
             if(act.getNombre()==nombre){
                 act.getDepartamento();
                  if(aux=selectDepartamento(nombre)){
                      for(ActividadTuristica ac:){
                      
                      }
                  }
                  
               }
             }
         }
        }
   }
*/

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public Collection<ActividadTuristica> getActTuristica() {
        return actTur;
    }




//FUNCIONES SAMUEL ACTIVIDAD EN DEPTO

   public int getCantActividades(){
       return actTur.size();
   }
   public ActividadTuristica[] getActividades (){
         // Devuelve el array de actividades de este depto
         // copiado del taque

            Object[] a = actTur.toArray()   ;
            ActividadTuristica[] actividadesArr = new ActividadTuristica[a.length];
            for (int i = 0; i < a.length; i++) {
                actividadesArr[i] = (ActividadTuristica) a[i];
            }
            return actividadesArr;
        
        
   }
   public ActividadTuristica getActividadByNombre(String actividad){
    for(ActividadTuristica a : actTur){
        if(a.getNombre() == actividad){
            return a;
        }
    }
    return null;
   }

public void addActTurisica(ActividadTuristica act){
    actTur.add(act);
}


}