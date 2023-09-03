package logica;

import datatypes.DataActividad;
import datatypes.DataInscripcionGeneral;
import datatypes.DataSalida;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SalidasTuristicas implements Serializable {

    @ManyToMany
    private Collection<Inscripcion_general> inscripciongeneral;

    @Id
    private String nombreSalida;
    private int cantidadMax;

    @ManyToOne
    @JoinColumn(name = "ActividadT_Salida")
    private ActividadTuristica actividadAsociada;

    public Collection<Inscripcion_general> getInscripciongeneral() {
        return inscripciongeneral;
    }

    public void setInscripciongeneral(Collection<Inscripcion_general> inscripciongeneral) {
        this.inscripciongeneral = inscripciongeneral;
    }

    public String getNombreSalida() {
        return nombreSalida;
    }

    public void setNombreSalida(String nombreSalida) {
        this.nombreSalida = nombreSalida;
    }

    public int getCantidadMax() {
        return cantidadMax;
    }

    public void setCantidadMax(int cantidadMax) {
        this.cantidadMax = cantidadMax;
    }

    public ActividadTuristica getActividadAsociada() {
        return actividadAsociada;
    }

    public void setActividadAsociada(ActividadTuristica actividadAsociada) {
        this.actividadAsociada = actividadAsociada;
    }

    public int getCantidadMaximaTuristas() {
        return cantidadMaximaTuristas;
    }

    public void setCantidadMaximaTuristas(int cantidadMaximaTuristas) {
        this.cantidadMaximaTuristas = cantidadMaximaTuristas;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    private int cantidadMaximaTuristas;
    private LocalDate fechaAlta;
    private LocalDate fechaSalida;

    public SalidasTuristicas() {
    }

    ;
    public SalidasTuristicas(int cantidadMax, int cantidadMaximaTuristas, LocalDate fechaAlta, LocalDate fechaSalida) {
        this.cantidadMax = cantidadMax;
        this.cantidadMaximaTuristas = cantidadMaximaTuristas;
        this.fechaAlta = fechaAlta;
        this.fechaSalida = fechaSalida;
    }

    public DataSalida devolverData() {

        // Arreglo de string de inscripciones para el datatype, sacado del toString de cada inscripcion de la coleccion
        DataInscripcionGeneral inscripciones[] = null;
        Inscripcion_general[] inscrArr = (Inscripcion_general[]) inscripciongeneral.toArray();

        //para cada inscripcion en la coleccion ejecuta el metodo toString y lo guarda al array que se guarda en el dataSalida
        inscripciones = new DataInscripcionGeneral[inscrArr.length];
        for (int i = 0; i < inscrArr.length; i++) {
            inscripciones[i] = inscrArr[i].devolverData();
        }

        //crea el data con los arrays de String y los atributos pertinentes
        DataSalida data = new DataSalida(inscripciones, actividadAsociada.devolverData(), nombreSalida, cantidadMaximaTuristas, fechaAlta, fechaSalida);
        return data;
    }

    public boolean estaInscritoUsuario(String mailTurista) {
        for (Inscripcion_general inscripcion : inscripciongeneral) {
            if(inscripcion.tieneTurista(mailTurista)){
                return true;
            }
        }

        return false;
    }
}
