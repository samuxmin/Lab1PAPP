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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String[] getTuristas() {
        return turistas;
    }

    public void setTuristas(String[] turistas) {
        this.turistas = turistas;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getCostoGeneral() {
        return costoGeneral;
    }

    public void setCostoGeneral(double costoGeneral) {
        this.costoGeneral = costoGeneral;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
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
