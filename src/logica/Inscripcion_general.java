package logica;

import datatypes.DataInscripcionGeneral;
import datatypes.DataSalida;
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
import javax.persistence.ManyToOne;

@Entity
public class Inscripcion_general implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "inscripciongeneral")
    private Collection<Turista> turista;

    @ManyToOne
    @JoinColumn(name = "Inscripcion_Salida")
    private SalidasTuristicas salida;

    public Inscripcion_general() {
    }

    private LocalDate fechaCompra;
    private float costoGeneral;
    private int cantidad;

    public Inscripcion_general(int cantidad, float costoGeneral, SalidasTuristicas salida, Collection<Turista> turista) {
        this.cantidad = cantidad;
        this.costoGeneral = costoGeneral;
        this.salida = salida;
        this.turista = turista;
        salida.agregarInscripcionGral(this);
        for (Turista t : turista) {
            t.agregarInscripcionGral(this);  // Llamar a la función para cada turista en la colección
        }
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

        DataInscripcionGeneral dt = new DataInscripcionGeneral(id, turistas, salida.devolverData(), fechaCompra, costoGeneral, cantidad);

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
}
