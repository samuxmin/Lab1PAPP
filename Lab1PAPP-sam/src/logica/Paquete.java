package logica;
import static controladores.Sistema.usuariosMail;
import datatypes.DataUsuario;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import static logica.Usuario.usuariosMail;
import datatypes.DataPaquete;
import static logica.Proveedor.paqueteNombre;


@Entity 
public class Paquete implements Serializable{
    @Id
    private String nombre_paquete;
    private String descri;
    private LocalDate validez;
    private int descuento;

    public Paquete(String nombre_paquete, String descri, int descuento, LocalDate validez){
    this.nombre_paquete=nombre_paquete;
    this.descri=descri;
    this.descuento=descuento;
    this.validez=validez;
    }
    public Paquete(){}
         
    
@ManyToMany
    @JoinTable(name = "Paquete_ACT",
            joinColumns=@JoinColumn(name="Paquete_nombre_paquete"),
            inverseJoinColumns=@JoinColumn(name="ActividadTuristica_nombre"))
    private Collection<ActividadTuristica> actTuristica;

    public Collection<ActividadTuristica> getActTuristica() {
        return actTuristica;
    }

    public void setActTuristica(Collection<ActividadTuristica> actTuristica) {
        this.actTuristica = actTuristica;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public LocalDate getValidez() {
        return validez;
    }

    public void setValidez(LocalDate validez) {
        this.validez = validez;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }
    

   public static void setInstancia(Paquete instancia) {
        Paquete.instancia = instancia;
    }
    public String getNombre_paquete() {
        return nombre_paquete;
    }
     
    private static Paquete instancia = new Paquete();
    
    public static Paquete getinstance() {
        return instancia;
    }
    
    
    Paquete paquete = Paquete.getinstance(); 
     
    
     
  
}
