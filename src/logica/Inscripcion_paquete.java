
package logica;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Inscripcion_paquete implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy="inscripcionpaquete")
    private Collection<Turista> turista;    
    
    @ManyToOne
    @JoinColumn(name="p_nombre_paquete")
    private Paquete paquete;
    
    public Inscripcion_paquete() {
    }

    
    private float costoPaquete;
    private LocalDate fechaCompra;
    private int cantidad;
    private LocalDate vencimiento;
    private String email;
    public Inscripcion_paquete(float costoPaquete,int cantidad,LocalDate vencimiento,LocalDate fechaCompra){
        this.costoPaquete=costoPaquete;
        this.cantidad=cantidad;
        this.vencimiento=vencimiento;
        this.fechaCompra=fechaCompra;
    }
}
