package logica;
import datatypes.DataActividad;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
public class ActividadTuristica implements Serializable {
/*
    public void setSalidastur(Collection<SalidasTuristicas> salidastur) {
        this.salidastur = salidastur;
    }*/

    public void setCategorias(Collection<Categoria> categorias) {
        this.categorias = categorias;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

     @OneToMany(mappedBy = "actividadAsociada", cascade = CascadeType.PERSIST)
    private Collection<SalidasTuristicas> salidastur;
    @ManyToMany
    @JoinTable(name = "ActividadTuristica_Paquete",
            joinColumns = @JoinColumn(name = "Act_nombre"),
            inverseJoinColumns = @JoinColumn(name = "Paquete_nombre"))
    private Collection<Paquete> paquete;
    
    //private Collection<Categoria> categoria;
 @ManyToMany(cascade = CascadeType.PERSIST)
@JoinTable(
    name = "Actividad_Categoria",
    joinColumns = @JoinColumn(name = "nombre_actividad"), // Nombre de la columna que hace referencia a Paquete
    inverseJoinColumns = @JoinColumn(name = "nombre_categoria") // Nombre de la columna que hace referencia a Categoria
)
 private Collection<Categoria> categorias;

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }
    
    private String urlVideo;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
@JoinColumn(name = "correoProveedor", referencedColumnName = "CORREO")
    private Proveedor proveedor;
    @Id
    private String nombre;
    
@ManyToOne(cascade = CascadeType.PERSIST)
@JoinColumn(name = "departamento_nombre", referencedColumnName = "nombreDepartamento")
private Departamento departamento;
    private String descripcion;
    private String imagen;
    private int duracionHoras;
    private double costoPorTurista;

    public long getCantVisitas() {
        return cantVisitas = 0;
    }

    public void setCantVisitas(long cantVisitas) {
        this.cantVisitas = cantVisitas;
    }
    long cantVisitas = 0 ;
    private String ciudad;
    private LocalDate fechaAlta;
    private EstadoActividad estado;
    public ActividadTuristica() {
    }
/*
    public Collection<SalidasTuristicas> getSalidastur() {
        return salidastur;
    }
*/
   
    
    public void addCategoria(Categoria cat) {
        categorias.add(cat);
    }

    public Collection<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }       
            
    
    public EstadoActividad getEstado() {
        return estado;
    }

    public void setEstado(EstadoActividad estado) {
        this.estado = estado;
    }

    public ActividadTuristica(String nombre, String descripcion, int duracionHoras, double costoPorTurista, String ciudad, LocalDate fechaAlta, String imagen, String urlVideo) {
        this.ciudad = ciudad;
        this.nombre = nombre;
        this.costoPorTurista = costoPorTurista;
        this.duracionHoras = duracionHoras;
        this.descripcion = descripcion;
        this.salidastur = new ArrayList<SalidasTuristicas>();
        this.fechaAlta=fechaAlta;
        this.imagen=imagen;
        estado = EstadoActividad.AGREGADA;
        this.categorias = new ArrayList<>();
        this.urlVideo= urlVideo;
        
    }
    
    public String getImagen() {
        return imagen;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(int duracionHoras) {
        this.duracionHoras = duracionHoras;
    }

    public double getCostoPorTurista() {
        return costoPorTurista;
    }

    public void setCostoPorTurista(double costoPorTurista) {
        this.costoPorTurista = costoPorTurista;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public DataActividad devolverData() {
        Object[] salidasTur = salidastur.toArray();
        String[] datosSalida = new String[salidasTur.length];
        String nombreSalida;
        for (int i = 0; i < salidasTur.length; i++) {
            nombreSalida = ((SalidasTuristicas) salidasTur[i]).getNombreSalida();
            datosSalida[i] = nombreSalida;
        }
        //DataActividad(String[] salidasTur, String departamento, String proveedor, String nombre, String descripcion, int duracionHoras, double costoPorTurista, String ciudad, LocalDate fechaAlta) {

        return new DataActividad(datosSalida, departamento, proveedor.getCorreo(), nombre, descripcion, duracionHoras, costoPorTurista, ciudad, fechaAlta, imagen, urlVideo);
    }
   /* public SalidasTuristicas crearSalida(String nombre, String lugar, int cantidad,LocalDate fecSal, LocalDate alta) {
        //(String nombre, String descripcion, int cantidad, LocalDate fechaS, ActividadTuristica act, String lugar)
        SalidasTuristicas nuevaS = new SalidasTuristicas(nombre, lugar, cantidad, alta, this, fecSal);
        salidastur.add(nuevaS);
        nuevaS.setActividadAsociada(this);
        return nuevaS;
    }*/
    public SalidasTuristicas[] devolverSalidas() {

            Object[] s = salidastur.toArray();
            SalidasTuristicas[] salidasArr = new SalidasTuristicas[s.length];
            for (int i = 0; i < s.length; i++) {
                salidasArr[i] = (SalidasTuristicas) s[i];
            }
            return salidasArr;

    }

    public SalidasTuristicas getSalidaByNombre(String nombre) {
        SalidasTuristicas[] salidas = this.devolverSalidas();
        
        for (int i = 0; i < salidas.length; i++) {
            if (salidas[i].getNombreSalida() == null ? nombre == null : salidas[i].getNombreSalida().equals(nombre)) {
                return salidas[i];
            }
        }
        return null;
    }

    public SalidasTuristicas[] devolverSalidasVigentes() {
        if (salidastur.isEmpty()) {
            return null;
        }

        Collection<SalidasTuristicas> salidasVigentes = new ArrayList();
        for (SalidasTuristicas s : salidastur) {
            if (s.estaVigente()) {
                salidasVigentes.add(s);
            }
        }
        return (SalidasTuristicas[]) salidasVigentes.toArray();
    }

    @Override
    public String toString() {
        return "ActividadTuristica{" + "nombre=" + nombre + ", descripcion=" + descripcion + ", duracionHoras=" + duracionHoras + ", costoPorTurista=" + costoPorTurista + ", ciudad=" + ciudad + ", fechaAlta=" + fechaAlta + '}';
    }

    public SalidasTuristicas crearSalida(String nombre, String descripcion, int cantidad, LocalDate fechaA, LocalDate fechaS, String imagen,LocalTime hora) {
        SalidasTuristicas nuevaS = new SalidasTuristicas(nombre, descripcion, cantidad, fechaS, this,fechaA, imagen, hora);
        salidastur.add(nuevaS);
        nuevaS.setActividadAsociada(this);
        return nuevaS;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    
    
}
