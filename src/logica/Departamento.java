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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import logica.Paquete;

@Entity
public class Departamento implements Serializable{
    @Id
    private String nombreDepartamento;
    public static Map<String, Departamento> departamentos = new HashMap<String, Departamento>();
     private Map<String, ActividadTuristica> actividades ;
     
    public static Departamento encontrarDepto(String nombreDpto) {
        return departamentos.get(nombreDpto);
    }

    public Departamento() {
   
        actividades = new HashMap<String, ActividadTuristica>();

    }
    
    public Departamento(String nombreDepartamento){
        this.nombreDepartamento=nombreDepartamento;
        
        actividades = new HashMap<String, ActividadTuristica>();
    }
    
    public String getNombreDepto(){
    return nombreDepartamento;
    }
    @OneToMany
    @JoinTable(name = "Departamento_Act",
            joinColumns=@JoinColumn(name="Departamento_nombre"),
            inverseJoinColumns=@JoinColumn(name="ActividadTuristica_nombre"))
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

    public void setActTuristica(Collection<ActividadTuristica> actTuristica) {
        this.actTur = actTuristica;
    }


//FUNCIONES SAMUEL ACTIVIDAD EN DEPTO

   public int getCantActividades(){
       return actividades.size();
   }
   public ActividadTuristica[] getActividades (){
         // Devuelve el array de actividades de este depto
         // copiado del taque
        if (actividades.isEmpty()) {
            return null;
        } else {
            Collection<ActividadTuristica> activs = actividades.values();
            Object[] a = activs.toArray();
            ActividadTuristica[] actividadesArr = new ActividadTuristica[a.length];
            for (int i = 0; i < a.length; i++) {
                actividadesArr[i] = (ActividadTuristica) a[i];
            }
            return actividadesArr;
        }
        
   }
   public ActividadTuristica getActividadByNombre(String actividad){
    return actividades.get(actividad);
   }











}


