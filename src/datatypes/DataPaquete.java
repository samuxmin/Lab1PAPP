package datatypes;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class DataPaquete {
    private String nombre_paquete;
    private String descri;
    private LocalDate alta;
    private int validez;
    private int descuento;
    private List<String> actTuristica;
    private String imagen;

    public DataPaquete() {
        // Constructor vacío necesario para la conversión
    }

    public DataPaquete(String nombre_paquete, String descri, LocalDate alta, int validez, int descuento, List<String> actTuristica,String imagen) {
        this.nombre_paquete = nombre_paquete;
        this.descri = descri;
        this.alta = alta;
        this.validez = validez;
        this.descuento = descuento;
        this.actTuristica = actTuristica;
        this.imagen=imagen;
    }

    public String getNombre_paquete() {
        return nombre_paquete;
    }

    public void setNombre_paquete(String nombre_paquete) {
        this.nombre_paquete = nombre_paquete;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public LocalDate getAlta() {
        return alta;
    }

    public void setAlta(LocalDate alta) {
        this.alta = alta;
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

    public List<String> getActTuristica() {
        return actTuristica;
    }

    public void setActTuristica(List<String> actTuristica) {
        this.actTuristica = actTuristica;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

}
