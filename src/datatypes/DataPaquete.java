package datatypes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class DataPaquete {
     private String nombre_paquete;
    private String descri;
    private LocalDate validez;
    private int descuento;
    
    public DataPaquete(String nombre_paquete, String descri, int descuento, LocalDate validez){
    this.nombre_paquete=nombre_paquete;
    this.descri=descri;
    this.descuento=descuento;
    this.validez=validez;
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
    public void setDescr(String descri) {
        this.descri = descri;
    }
    public String getValidez() {
        return validez.toString();
    }
    public void setValidez(LocalDate validez) {
        this.validez = validez;
    }
    public int getDescuento() {
        return descuento;
    }
    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }
  public String toString() {
        return getValidez() ;
    }
}
