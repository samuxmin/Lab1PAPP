package logica;

import datatypes.DataUsuario;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import logica.Departamento;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ManyToOne;
import logica.Paquete;
import static logica.Usuario.usuariosMail;

@Entity
public class Proveedor extends Usuario {
@OneToMany(mappedBy = "proveedor")
    private Collection<ActividadTuristica> actTuristica;


    public Collection<ActividadTuristica> getActTuristica() {
        return actTuristica;
    }

    public void setActTuristica(Collection<ActividadTuristica> actTuristica) {
        this.actTuristica = actTuristica;
    }
    


   @ManyToOne
            @JoinColumn(name="Dept_nombredepto")
    private Departamento departamento; 

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    
    private String web;
    private String descripcion;
    public Proveedor(){
        super();
    };
    public Proveedor(String nick, String name, String apll, String mail, LocalDate fecNac,String descripcion,String web,String imagenP){
        super( nick,  name,  apll,  mail, fecNac, imagenP);
        this.descripcion = descripcion;
        this.web=web;
    }


    
    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /*
    public void addProveedor(Usuario usu) {//DESPUES DE PROBAR QUE FUNCIONA AGREGAR LOS GETTER
        String mail = usu.getCorreo();
        usuariosMail.put("proveedor1@mail.com", new Proveedor("nick1", "NombreProveedor1", "ApellidoProveedor1", "proveedor1@mail.com", LocalDate.of(1990, 5, 15), "CREO QUE MERECEN UNA RECOMPENSA, VELDA?", "www.proveedor1.com"));
    }
*/
  
    
    public Usuario obtenerProveedor(String mail) {
        return ((Usuario) usuariosMail.get(mail));
    }
    public void getProveedor(){
     for (Map.Entry<String, Usuario> entry : usuariosMail.entrySet()) {
            Usuario usuario = entry.getValue();
            if (usuario instanceof Proveedor) {
                Proveedor proveedor = (Proveedor) usuario;
                System.out.println("Nombre"+proveedor.getNombre()+" Apellido"+proveedor.getApellido()+" Correo"+proveedor.getCorreo()+" Nickname"+proveedor.getNick()+" Fecha de Nacimiento"+proveedor.getFecnac()+" Sitio Web"+proveedor.getWeb()+" Descripcion"+proveedor.getDescripcion());
            } 
          }
        }
    
    
      public static Map<String, Paquete> paqueteNombre = new HashMap<>();

  
    
    public ActividadTuristica selectActividad(String nombre){
        for (Map.Entry<String, Usuario> entry : Usuario.usuariosMail.entrySet()) {
        Usuario usuario = entry.getValue();
        if (usuario instanceof Proveedor) {
            Proveedor proveedor = (Proveedor) usuario;//Casteo de proveedor de usuario 
         for(ActividadTuristica act :proveedor.getActTuristica()){
             if(act.getNombre()==nombre){
             return  act;
             }
         }
           
        }
    }
       return null;
 }
       
    public Departamento AltaDepartamento(String nombreDepto,String descripcion, String web){
       Departamento departamentoExistente = Departamento.encontrarDepto(nombreDepto);
        if (departamentoExistente != null){
            return null;
        }else{
             for (Map.Entry<String, Usuario> entry : Usuario.usuariosMail.entrySet()) {
              Usuario usuario = entry.getValue();
              if (usuario instanceof Proveedor) {
                  Proveedor proveedor = (Proveedor) usuario;
                  if(proveedor.getWeb()==web && proveedor.getDescripcion()==descripcion){
                      if(proveedor.getDepartamento()==null){
                      Departamento nuevoDepartamento = new Departamento(nombreDepto,descripcion,web);
                      nuevoDepartamento.setNombreDepartamento(nombreDepto);
                      proveedor.setDepartamento(departamento);
                      Departamento.departamentos.put(nombreDepto, nuevoDepartamento);
                      return nuevoDepartamento;
                      }
                 }else{
                      proveedor.getDepartamento().setNombreDepartamento(nombreDepto);
                    }            
                  }
              }
        }
        return null;
   }
        
    public void addActTuristica(ActividadTuristica act) {                                                            

        actTuristica.add(act);
        
    }   
    public DataUsuario devolverData(){
        DataUsuario dt = new DataUsuario(nickname,nombre,apellido,correo,fechaNacimiento,null,web,descripcion);
        dt.setTipo("Proveedor");
        return dt;
    }
public static Proveedor[] getProveedores(){
    Proveedor[] proveedoresArr; // Arreglo de proveedores a devolver
    
    if (usuariosMail.isEmpty()) {
        return null;
    } else {
        Collection<Proveedor> proveedores = new ArrayList<>(); // Colección para ir agregando y transformar a arreglo

        Usuario[] usuariosArr = usuariosMail.values().toArray(new Usuario[0]); // Array con todos los usuarios
        for (int i = 0; i < usuariosArr.length; i++) {
            if (usuariosArr[i] instanceof Proveedor) {
                proveedores.add((Proveedor) usuariosArr[i]); // Va por cada usuario y si es proveedor lo agrega
            }
        }
        proveedoresArr = proveedores.toArray(new Proveedor[0]); // Convierte la colección a un arreglo
    }
    
    return proveedoresArr;
}


    private Collection<SalidasTuristicas> salidasAsociadas;

    
    public Collection<SalidasTuristicas> getSalidasAsociadas() {
        
        salidasAsociadas=new ArrayList();
        
        
        
        for(ActividadTuristica act : actTuristica){
            salidasAsociadas.addAll(act.getSalidastur());
        }
        return salidasAsociadas;
    }

    public void setSalidasAsociadas(Collection<SalidasTuristicas> salidasAsociadas) {
        this.salidasAsociadas = salidasAsociadas;
    }

    

}
