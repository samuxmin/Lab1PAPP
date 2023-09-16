package logica;

import controladores.Sistema;
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
import javax.persistence.OneToMany;

@Entity
public class SalidasTuristicas implements Serializable {

    @OneToMany(mappedBy =  "salida")
    private Collection<Inscripcion_general> inscripciongeneral;


    @Id
    private String nombreSalida;

    int cantInscritos = 0;
    @ManyToOne
    @JoinColumn(name = "actividadAsociada",referencedColumnName = "nombre")
    private ActividadTuristica actividadAsociada;

    private int cantidadMaximaTuristas;
    private String lugar;

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    private LocalDate fechaAlta;
    private LocalDate fechaSalida;
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


    public SalidasTuristicas() {
    }

    

    public SalidasTuristicas(String nombre, String lugar, int cantidad, LocalDate fechaS, ActividadTuristica act) {
        this.actividadAsociada = act;
        this.nombreSalida = nombre;
        this.cantidadMaximaTuristas = cantidad;
        this.lugar = lugar;
        this.fechaSalida = fechaS;
        this.fechaAlta = Sistema.getInstance().fechaSistema;
    }
  public SalidasTuristicas(String nombre, String lugar, int cantidad, LocalDate fechaS, ActividadTuristica act, LocalDate fechaA) {
        this.actividadAsociada = act;
        this.nombreSalida = nombre;
        this.cantidadMaximaTuristas = cantidad;
        this.fechaSalida = fechaS;
        this.fechaAlta = fechaA;
        this.lugar=lugar;
    }
     public DataSalida devolverData() {

        // Arreglo de string de inscripciones para el datatype, sacado del toString de cada inscripcion de la coleccion
        DataInscripcionGeneral inscripciones[];
        Object[] inscrArr = inscripciongeneral.toArray();

        //para cada inscripcion en la coleccion ejecuta el metodo toString y lo guarda al array que se guarda en el dataSalida
        inscripciones = new DataInscripcionGeneral[inscrArr.length];
        for (int i = 0; i < inscrArr.length; i++) {
            inscripciones[i] = ((Inscripcion_general)inscrArr[i]).devolverData();
        }
        //crea el data con los arrays de String y los atributos pertinentes

        return new DataSalida(inscripciones, actividadAsociada.devolverData(), nombreSalida, cantidadMaximaTuristas, fechaAlta, fechaSalida,cantInscritos);
    }

    public boolean estaInscritoUsuario(String mailTurista) {
        for (Inscripcion_general inscripcion : inscripciongeneral) {
            if (inscripcion.tieneTurista(mailTurista)) {
                return true;
            }
        }

        return false;
    }

    public void agregarInscripcionGral(Inscripcion_general nuevaInscripcion) {
        inscripciongeneral.add(nuevaInscripcion);
    }

    public boolean estaVigente() {
        LocalDate fechaSis = Sistema.getInstance().fechaSistema;
        return fechaSalida.isAfter(fechaSis);
    }
    
    public boolean estaVigenteAct(SalidasTuristicas sal) {
        LocalDate fechaSis = LocalDate.now();
        return sal.fechaSalida.isAfter(fechaSis);
    }
        public void aumentarCantInscritos(int cantTurista) {
        cantInscritos += cantTurista;
    }

    public int getCantInscritos() {
        return cantInscritos;
    }

    public void setCantInscritos(int cantInscritos) {
        this.cantInscritos = cantInscritos;
    }
}
