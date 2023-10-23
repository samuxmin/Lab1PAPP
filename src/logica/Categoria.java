package logica;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/*
 * @author facu
 */
@Entity
public class Categoria implements Serializable{
    @Id
    public String nombreCat; 

    public Categoria() {
    }

    public Categoria(String nombreCat) {
        this.nombreCat = nombreCat;
    }

    public String getNombreCat() {
        return nombreCat;
    }

    public void setNombreCat(String nombreCat) {
        this.nombreCat = nombreCat;
    }

    public static Map<String, Categoria> categorias = new HashMap<>(); //mapa de categorias

    public static void agregarCategoria(Categoria categoria) {
        String nombreCategoria = categoria.getNombreCat();
        categorias.put(nombreCategoria, categoria);
    }

    public static Categoria obtenerCategoria(String nombreCategoria) {
        return categorias.get(nombreCategoria);
    }
    
    @ManyToMany(mappedBy = "categorias")
private Set<ActividadTuristica> actividades = new HashSet<>();

@ManyToMany(mappedBy = "categorias")
private Set<Paquete> paquetes = new HashSet<>();

  
}
