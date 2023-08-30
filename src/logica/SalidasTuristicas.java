package logica;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDate;

@Entity 
public class SalidasTuristicas implements Serializable{
    @ManyToMany
    private Collection<Inscripcion_general> inscripciongeneral;
    
    @ManyToMany
    private Collection<ActividadTuristica> actividadtur;    
    
    @Id
    private String nombreSalida;
    private int cantidadMax;
    private ActividadTuristica actividadAsociada;
    private int cantidadMaximaTuristas;
    private LocalDate fechaAlta;
    private LocalDate fechaSalida;
    public SalidasTuristicas(){};
    public SalidasTuristicas(int cantidadMax,int cantidadMaximaTuristas,LocalDate fechaAlta, LocalDate fechaSalida){
        this.cantidadMax=cantidadMax;
        this.cantidadMaximaTuristas=cantidadMaximaTuristas;
        this.fechaAlta=fechaAlta;
        this.fechaSalida=fechaSalida;
    }
}
