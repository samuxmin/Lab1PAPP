package logica;
import datatypes.DataActividad;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity 
public class ActividadTuristica implements Serializable{
    private Collection<SalidasTuristicas> salidastur;
   
    private Proveedor proveedor;
    @Id
    private String nombre;
    private String descripcion;
    private int duracionHoras;
    private double costoPorTurista;
    private String ciudad;
    private LocalDate fechaAlta;

    public ActividadTuristica() {
    }
    
    public ActividadTuristica(String nombre,String descripcion,int duracionHoras,double costoPorTurista,String ciudad, LocalDate fechaAlta){
    this.ciudad=ciudad;
    this.nombre=nombre;
    this.costoPorTurista=costoPorTurista;
    this.duracionHoras=duracionHoras;
    this.descripcion=descripcion;
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
     public DataActividad devolverData(){
        Object [] salidasTur = salidastur.toArray();
        String[] datosSalida = new String[salidasTur.length];
        String nombreSalida;
        for(int i = 0; i< salidasTur.length ; i++){
            nombreSalida = ((SalidasTuristicas)  salidasTur[i]).getNombreSalida();
            datosSalida[i] = nombreSalida;
        }
        //DataActividad(String[] salidasTur, String departamento, String proveedor, String nombre, String descripcion, int duracionHoras, double costoPorTurista, String ciudad, LocalDate fechaAlta) {
       
        return new DataActividad(datosSalida,"",proveedor.getCorreo(), nombre, descripcion, duracionHoras, costoPorTurista, ciudad, fechaAlta);
    }
      public SalidasTuristicas[] devolverSalidas(){
                if (salidastur.isEmpty()) {
            return null;
        } else {
            Object[] s = salidastur.toArray();
           SalidasTuristicas[] salidasArr = new SalidasTuristicas[s.length];
            for (int i = 0; i < s.length; i++) {
                salidasArr[i] = (SalidasTuristicas) s[i];
            }
            return salidasArr;
        }
    }
      public SalidasTuristicas getSalidaByNombre(String nombre){
        SalidasTuristicas[] salidas = this.devolverSalidas();
        
        for(int i = 0; i < salidas.length;i++){
            if(salidas[i].getNombreSalida() == nombre){
                return salidas[i];
            }
        }
        return null;
    }

      
    public SalidasTuristicas[] devolverSalidasVigentes(){
        SalidasTuristicas[] salidasVigentes = null ;//new SalidasTuristicas[];
        //salidasVigentes.
        return salidasVigentes;
    
    }
    @Override
    public String toString() {
        return "ActividadTuristica{" + "nombre=" + nombre + ", descripcion=" + descripcion + ", duracionHoras=" + duracionHoras + ", costoPorTurista=" + costoPorTurista + ", ciudad=" + ciudad + ", fechaAlta=" + fechaAlta + '}';
    }
}
