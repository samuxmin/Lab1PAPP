/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datatypes;

import java.time.LocalDate;
import java.util.Collection;


/**
 *
 * @author samuel
 */
public class DataInscripcionGeneral {
     private Long id;
    private String[] turistas;
    
    private String salida;
    private LocalDate fechaCompra;
    private double costoGeneral;
    private int cantidad;

    public DataInscripcionGeneral(Long id, String[] turistas, String salida, LocalDate fechaCompra, double costoGeneral, int cantidad) {
        this.id = id;
        this.turistas = turistas;
        this.salida = salida;
        this.fechaCompra = fechaCompra;
        this.costoGeneral = costoGeneral;
        this.cantidad = cantidad;
    }
}
