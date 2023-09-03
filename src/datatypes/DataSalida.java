/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datatypes;

import java.time.LocalDate;

/**
 *
 * @author samuel
 */
public class DataSalida {
     private DataInscripcionGeneral [] inscripciongeneral;

    private DataActividad actividadtur;    

    private String nombreSalida;
    private int cantidadMaximaTuristas;

    public DataInscripcionGeneral[] getInscripciongeneral() {
        return inscripciongeneral;
    }

    public void setInscripciongeneral(DataInscripcionGeneral[] inscripciongeneral) {
        this.inscripciongeneral = inscripciongeneral;
    }

    public DataActividad getActividadtur() {
        return actividadtur;
    }

    public void setActividadtur(DataActividad actividadtur) {
        this.actividadtur = actividadtur;
    }

    public String getNombreSalida() {
        return nombreSalida;
    }

    public void setNombreSalida(String nombreSalida) {
        this.nombreSalida = nombreSalida;
    }



    public int getCantidadMaximaTuristas() {
        return cantidadMaximaTuristas;
    }

    public void setCantidadMaximaTuristas(int cantidadMaximaTuristas) {
        this.cantidadMaximaTuristas = cantidadMaximaTuristas;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public DataSalida(DataInscripcionGeneral[] inscripciongeneral, DataActividad actividadtur, String nombreSalida,  int cantidadMaximaTuristas, LocalDate fechaAlta, LocalDate fechaSalida) {
        this.inscripciongeneral = inscripciongeneral;
        this.actividadtur = actividadtur;
        this.nombreSalida = nombreSalida;
        this.cantidadMaximaTuristas = cantidadMaximaTuristas;
        this.fechaAlta = fechaAlta;
        this.fechaSalida = fechaSalida;
    }
    private LocalDate fechaAlta;
    private LocalDate fechaSalida;
}
