package logica;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ManyToOne;
import logica.Paquete;

@Entity
public class Proveedor extends Usuario {
    @OneToMany
    @JoinTable(name = "Proveedor_ACT",
            joinColumns=@JoinColumn(name="Proveedor_correo"),
            inverseJoinColumns=@JoinColumn(name="ActividadTuristica_nombre"))
    private Collection<ActividadTuristica> actTuristica;

    public Collection<ActividadTuristica> getActTuristica() {
        return actTuristica;
    }

    public void setActTuristica(Collection<ActividadTuristica> actTuristica) {
        this.actTuristica = actTuristica;
    }
    
    @OneToMany
    @JoinTable(name = "Proveedor_Paquete",
            joinColumns=@JoinColumn(name="Proveedor_correo"),
            inverseJoinColumns=@JoinColumn(name="Paquete_nombre_paquete"))
    private Collection<Paquete> paquete;
    
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
    public Proveedor(String nick, String name, String apll, String mail, LocalDate fecNac,String descripcion,String web){
        super( nick,  name,  apll,  mail, fecNac);
        this.descripcion = descripcion;
        this.web=web;
    }

    public Collection<Paquete> getPaquete() {
        return paquete;
    }
  
            
    public void setPaquete(Collection<Paquete> paquete) {
        this.paquete = paquete;
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
    
    
    public void addProveedor(Usuario usu) {//DESPUES DE PROBAR QUE FUNCIONA AGREGAR LOS GETTER
        String mail = usu.getCorreo();
        usuariosMail.put("proveedor1@mail.com", new Proveedor("nick1", "NombreProveedor1", "ApellidoProveedor1", "proveedor1@mail.com", LocalDate.of(1990, 5, 15), "CREO QUE MERECEN UNA RECOMPENSA, VELDA?", "www.proveedor1.com"));
    }

  
    
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

  
    public Paquete selectPaquete(String nombre_paquete){
        for (Map.Entry<String, Usuario> entry : Usuario.usuariosMail.entrySet()) {
        Usuario usuario = entry.getValue();
        if (usuario instanceof Proveedor) {
            Proveedor proveedor = (Proveedor) usuario;//Casteo de proveedor de usuario 
         for(Paquete paq :proveedor.getPaquete()){
             if(paq.getNombre_paquete()==nombre_paquete){
             return  paq;
             }
         }
           
        }
    }
       return null;
 }
    
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
                      Departamento nuevoDepartamento = new Departamento(nombreDepto);
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
}
