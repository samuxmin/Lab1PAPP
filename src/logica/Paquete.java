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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static logica.Proveedor.paqueteNombre;

@Entity
public class Paquete implements Serializable {

    @Id
    private String nombre_paquete;
    private String descri;
    private LocalDate alta;
    private int validez;
    private int descuento;
    private String imagen;
    public static Map<String, Paquete> paquetes = new HashMap();

    
    public Paquete(String nombre_paquete, String descri, int descuento, int validez, LocalDate alta, String imagen) {
        this.nombre_paquete = nombre_paquete;
        this.descri = descri;
        this.descuento = descuento;
        this.validez = validez;
        this.alta = alta;
        this.imagen=imagen;
        this.actTuristica = new ArrayList<>();
        this.categorias = new ArrayList<>();
    }

    public Paquete() {
    }

    public void setAlta(LocalDate alta) {
        this.alta = alta;
    }

    public LocalDate getAlta() {
        return alta;
    }
    
     @ManyToMany
    @JoinTable(
        name = "Paquete_Categoria",
        joinColumns = @JoinColumn(name = "nombre_paquete"), // Nombre de la columna que hace referencia a Paquete
        inverseJoinColumns = @JoinColumn(name = "nombre_categoria") // Nombre de la columna que hace referencia a Categoria
    )
     private Collection<Categoria> categorias;

    @ManyToMany
    @JoinTable(name = "ActividadTuristica_Paquete",
            joinColumns = @JoinColumn(name = "Paquete_nombre"),
            inverseJoinColumns = @JoinColumn(name = "Act_nombre"))
    private Collection<ActividadTuristica> actTuristica;

    public DataPaquete devolverData() {
        List<String> actTuristicaNombres = actTuristica.stream()
                .map(ActividadTuristica::getNombre) // Suponiendo que la clase ActividadTuristica tiene un método getNombre
                .collect(Collectors.toList());

        return new DataPaquete(nombre_paquete, descri, alta, validez, descuento, actTuristicaNombres,imagen);
    }

    public Collection<ActividadTuristica> getActTuristica() {
        return actTuristica;
    }

    public void setActTuristica(Collection<ActividadTuristica> actTuristica) {
        this.actTuristica = actTuristica;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public int getValidez() {
        return validez;
    }

    public void setValidez(int validez) {
        this.validez = validez;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public String getNombre_paquete() {
        return nombre_paquete;
    }

    public static Paquete getPaqueteByNombre(String nombre_paquete) {
        return paquetes.get(nombre_paquete);
    }

    public void addActividad(ActividadTuristica act) {
        actTuristica.add(act);
    }

    public boolean tieneActividad(String nombreAct) {
        for (ActividadTuristica act : actTuristica) {
            if (act.getNombre() == nombreAct) {
                return true;
            }
        }
        return false;
    }

    public Collection<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Collection<Categoria> categorias) {
        this.categorias = categorias;
    }      
    
    public void addCategoria(Categoria cat) {
        categorias.add(cat);
    }
}