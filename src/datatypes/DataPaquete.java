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

    public DataPaquete() {
        // Constructor vacío necesario para la conversión
    }

    public DataPaquete(String nombre_paquete, String descri, LocalDate alta, int validez, int descuento, List<String> actTuristica) {
        this.nombre_paquete = nombre_paquete;
        this.descri = descri;
        this.alta = alta;
        this.validez = validez;
        this.descuento = descuento;
        this.actTuristica = actTuristica;
    }

    // Getters y setters
    // ...
}
