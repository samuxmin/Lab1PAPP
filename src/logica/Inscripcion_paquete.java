package logica;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Inscripcion_paquete implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "inscripcionpaquete")
    private Collection<Turista> turista;

    @ManyToOne
    @JoinColumn(name = "p_nombre_paquete")
    private Paquete paquete;
    
    
    /*
    Por cada inscripcion que se use en una actividad contenida en el paquete, se guardara cuantas van, asi 
    llevamos la cuenta de la cantidad usada real del paquete segun cada actividad
    ejemplo, si el pac contiene act1 y act2 y se compra con ua cantidad de 12,
    
    debe contabilizarse que no se usen mas  de 12 inscripciones en cada una de ellas
    ej 12 act1 y 10 act2 se podran inscribir 2 a act2 pero 0 a act1
    jijija
    */
@ElementCollection()
    Map<String, Integer> mapaInscr;

    public Inscripcion_paquete() {
        this.mapaInscr = new HashMap<String, Integer>();
    }

    private float costoPaquete;
    private LocalDate fechaCompra;
    private int cantidad;
    private LocalDate vencimiento;
    private String email;
    private int cantidadInscrUsadas = 0;

    public int getCantidadInscrUsadas() {
        return cantidadInscrUsadas;
    }

    public void setCantidadInscrUsadas(int cantidadInscrUsadas) {
        this.cantidadInscrUsadas = cantidadInscrUsadas;
    }

    public void aumentarCantInscrUsadas(int aumento) {
        setCantidadInscrUsadas(cantidadInscrUsadas + aumento);
    }

    public Inscripcion_paquete(String email, float costoPaquete, int cantidad, LocalDate vencimiento, LocalDate fechaCompra) {
        this.mapaInscr = new HashMap<String, Integer>();
        this.costoPaquete = costoPaquete;
        this.cantidad = cantidad;
        this.vencimiento = vencimiento;
        this.fechaCompra = fechaCompra;
        this.email = email;
        //iniciarMapaInscr();
    }

    public Inscripcion_paquete(Long id, Collection<Turista> turista, Paquete paquete, float costoPaquete, LocalDate fechaCompra, int cantidad, LocalDate vencimiento, String email) {
        this.mapaInscr = new HashMap<String, Integer>();
        this.id = id;
        this.turista = turista;
        this.paquete = paquete;
        this.costoPaquete = costoPaquete;
        this.fechaCompra = fechaCompra;
        this.cantidad = cantidad;
        this.vencimiento = vencimiento;
        this.email = email;
       // iniciarMapaInscr();
    }

    public Map<String, Integer> getMapaInscr() {
        return mapaInscr;
    }

    public void setMapaInscr(Map<String, Integer> mapaInscr) {
        this.mapaInscr = mapaInscr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<Turista> getTurista() {
        return turista;
    }

    public void setTurista(Collection<Turista> turista) {
        this.turista = turista;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public float getCostoPaquete() {
        return costoPaquete;
    }

    public void setCostoPaquete(float costoPaquete) {
        this.costoPaquete = costoPaquete;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(LocalDate vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void iniciarMapaInscr() {
        for (ActividadTuristica act : paquete.getActTuristica()) {
            mapaInscr.put(act.getNombre(), 0);
        }
    }

    public void aumentarCantInscrUsadas(int cantidadSumar, String nombreAct) {
        int cantActual = mapaInscr.get(nombreAct);
        mapaInscr.put(nombreAct, (cantActual+cantidadSumar));
    }
    
    public boolean quedanXPuestos(int cantidad, String actividad){
        System.out.println(actividad);
        int cantActual = mapaInscr.get(actividad);
        return (cantidad <= cantActual + cantidad);
    }

}
