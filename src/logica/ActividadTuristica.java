package logica;

import datatypes.DataActividad;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ActividadTuristica implements Serializable {

     @OneToMany(mappedBy = "actividadAsociada")
    private Collection<SalidasTuristicas> salidastur;
    @ManyToMany
    @JoinTable(name = "ActividadTuristica_Paquete",
            joinColumns = @JoinColumn(name = "Act_nombre"),
            inverseJoinColumns = @JoinColumn(name = "Paquete_nombre"))
    private Collection<Paquete> paquete;
    @ManyToOne
@JoinColumn(name = "correoProveedor", referencedColumnName = "CORREO")
    private Proveedor proveedor;
    @Id
    private String nombre;
    
@ManyToOne
@JoinColumn(name = "departamento_nombre", referencedColumnName = "nombreDepartamento")
private Departamento departamento;
    private String descripcion;
    private int duracionHoras;
    private double costoPorTurista;
    private String ciudad;
    private LocalDate fechaAlta;

    public ActividadTuristica() {
    }

    public Collection<SalidasTuristicas> getSalidastur() {
        return salidastur;
    }

    public ActividadTuristica(String nombre, String descripcion, int duracionHoras, double costoPorTurista, String ciudad, LocalDate fechaAlta) {
        this.ciudad = ciudad;
        this.nombre = nombre;
        this.costoPorTurista = costoPorTurista;
        this.duracionHoras = duracionHoras;
        this.descripcion = descripcion;
        this.salidastur = new ArrayList<SalidasTuristicas>();
        this.fechaAlta=fechaAlta;
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

        return new DataActividad(datosSalida, "", proveedor.getCorreo(), nombre, descripcion, duracionHoras, costoPorTurista, ciudad, fechaAlta);
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
            if (salidas[i].getNombreSalida() == nombre) {
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

    public SalidasTuristicas crearSalida(String nombre, String descripcion, int cantidad, LocalDate fechaA, LocalDate fechaS) {
        SalidasTuristicas nuevaS = new SalidasTuristicas(nombre, descripcion, cantidad, fechaS, this,fechaA);
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
