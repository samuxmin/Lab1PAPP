
package logica;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity 
public class Paquete implements Serializable{
    @Id
    private String nombre_paquete;
    private String descr;
    private LocalDate validez;
    private int descuento;
    
//@ManyToMany(mappedBy="paquete")
//  private Collection<Inscripcion_paquete> insc_paquete;     
    
@OneToMany
    @JoinTable(name = "Paquete_ACT",
            joinColumns=@JoinColumn(name="Paquete_nombre_paquete"),
            inverseJoinColumns=@JoinColumn(name="ActividadTuristica_nombre"))
    private Collection<ActividadTuristica> actTuristica;

    public Paquete() {
    }
    public Paquete(String nombre_paquete, String descri, int descuento, LocalDate validez){
    this.nombre_paquete=nombre_paquete;
    this.descr=descri;
    this.descuento=descuento;
    this.validez=validez;
    }
}
