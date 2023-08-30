
package logica;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Inscripcion_general implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy="inscripciongeneral")
    private Collection<Turista> turista;
    
    @ManyToMany(mappedBy="inscripciongeneral")
    private Collection<SalidasTuristicas> salidastur;    
    
    public Inscripcion_general() {
    }
    
    private LocalDate fechaCompra;
    private float costoGeneral;
    private int cantidad;
    
    public Inscripcion_general(int cantidad,float costoGeneral){
        this.cantidad=cantidad;
        this.costoGeneral=costoGeneral;
    }
}
