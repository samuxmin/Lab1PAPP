package logica;

import datatypes.DataInscripcionGeneral;
import datatypes.DataSalida;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Inscripcion_general implements Serializable {
    private static long cantInscr = 1;
    @Id
    private Long id;
    private String tipo;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "turista_inscripcion_general", // Nombre de la nueva tabla
        joinColumns = @JoinColumn(name = "nscripcion_general_id"), // Columna que hace referencia a Turista
        inverseJoinColumns = @JoinColumn(name = "iturista_correo") // Columna que hace referencia a Inscripcion_general
    )
    private Collection<Turista> turista;

    
    
    @ManyToOne
    @JoinColumn(name = "salidaInscripcion", referencedColumnName = "nombreSalida")
    private SalidasTuristicas salida;

    public Inscripcion_general() {
    }

    private LocalDate fechaCompra;
    private double costoGeneral;
    private int cantidad;
    

    public static long getCantInscr() {
        return cantInscr;
    }

    public static void setCantInscr(long cantInscr) {
        Inscripcion_general.cantInscr = cantInscr;
    }


        public Inscripcion_general(Long id, int cantidad, double costoGeneral, SalidasTuristicas salida, Collection<Turista> turista) {
          this.id = id;
        this.cantidad = cantidad;
        this.costoGeneral = (cantidad * salida.getActividadAsociada().getCostoPorTurista());
        this.salida = salida;
        this.turista = turista;
        this.tipo= tipo;

    }
    @Override
    public String toString() {
        return "Inscripcion_general{" + "id=" + id + ", turista=" + turista + ", fechaCompra=" + fechaCompra + ", costoGeneral=" + costoGeneral + ", cantidad=" + cantidad + '}';
    }

 public DataInscripcionGeneral devolverData() {

        String[] turistas;
        turistas = new String[turista.size()];
        int index = 0;
        for (Turista t : turista) {
            turistas[index] = t.getCorreo();
            index++;
        }

        DataInscripcionGeneral dt = new DataInscripcionGeneral(id, turistas, salida.getNombreSalida(), fechaCompra, costoGeneral, cantidad);

        return null;
    }
        public boolean tieneTurista(String mailTurista) {
        for (Turista t : turista) {
            if (t.getCorreo() == mailTurista) {
                return true;
            }
        }
        return false;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTurista(Collection<Turista> turista) {
        this.turista = turista;
    }

    public void setSalida(SalidasTuristicas salida) {
        this.salida = salida;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public void setCostoGeneral(double costoGeneral) {
        this.costoGeneral = costoGeneral;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public SalidasTuristicas getSalida() {
    return salida;
}
}
