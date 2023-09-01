package logica;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity 
public class ActividadTuristica implements Serializable{
    
    @ManyToMany(mappedBy="actividadtur")
    private Collection<SalidasTuristicas> salidastur;    
    
    @ManyToOne
            @JoinColumn(name="Dept_nombredepto")
    private Departamento departamento;
    
    private Proveedor proveedor;
    @Id
    private String nombre;
    private String descripcion;
    private int duracionHoras;
    private double costoPorTurista;
    private String ciudad;
    private LocalDate fechaAlta;

    public ActividadTuristica() {
    }
    
    public ActividadTuristica(String nombre,String descripcion,int duracionHoras,double costoPorTurista,String ciudad, LocalDate fechaAlta){
    this.ciudad=ciudad;
    this.nombre=nombre;
    this.costoPorTurista=costoPorTurista;
    this.duracionHoras=duracionHoras;
    this.descripcion=descripcion;
    }
}
