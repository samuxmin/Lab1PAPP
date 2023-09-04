package logica;

import static controladores.Sistema.usuariosMail;
import datatypes.DataUsuario;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import static logica.Usuario.usuariosMail;
import datatypes.DataPaquete;
import java.util.ArrayList;
import static logica.Proveedor.paqueteNombre;

@Entity
public class Paquete implements Serializable {




    @Id
    private String nombre_paquete;
    private String descri;
    private LocalDate alta;
    private int validez;
    private int descuento;
    public static Map<String, Paquete> paquetes = new HashMap(); 

    public Paquete(String nombre_paquete, String descri, int descuento,int validez, LocalDate alta) {
        this.nombre_paquete = nombre_paquete;
        this.descri = descri;
        this.descuento = descuento;
        this.validez = validez;
        this.alta = alta;
        this.actTuristica = new ArrayList<>();
    }

    public Paquete() {
    }

    @ManyToMany
    @JoinTable(name = "Paquete_ACT",
            joinColumns = @JoinColumn(name = "Paquete_nombre_paquete"),
            inverseJoinColumns = @JoinColumn(name = "ActividadTuristica_nombre"))
    private Collection<ActividadTuristica> actTuristica;
    

    public Collection<ActividadTuristica> getActTuristica() {
        return actTuristica;
    }

    public void setActTuristica(Collection<ActividadTuristica> actTuristica) {
        this.actTuristica = actTuristica;
    }


    public String getNombre_paquete() {
        return nombre_paquete;
    }


    public static Paquete getPaqueteByNombre(String nombre_paquete) {
       return paquetes.get(nombre_paquete);
    }
}
