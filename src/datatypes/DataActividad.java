/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datatypes;

import java.time.LocalDate;
import logica.Departamento;

/**
 *
 * @author samuel
 */
public class DataActividad {
      private String[] salidasTur;    

    private String departamento;
    
    private String proveedor;
    private String imagen;
    private String nombre;
    private String descripcion;
    private int duracionHoras;
    private double costoPorTurista;
    private String ciudad;
    private LocalDate fechaAlta;

    public String[] getSalidasTur() {
        return salidasTur;
    }

    public DataActividad(String[] salidasTur, Departamento departamento, String proveedor, String nombre, String descripcion, int duracionHoras, double costoPorTurista, String ciudad, LocalDate fechaAlta, String imagen) {
        this.salidasTur = salidasTur;
        this.departamento = departamento.getNombreDepartamento();
        this.proveedor = proveedor;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionHoras = duracionHoras;
        this.costoPorTurista = costoPorTurista;
        this.ciudad = ciudad;
        this.fechaAlta = fechaAlta;
        this.imagen=imagen;
    }

    public void setSalidasTur(String[] salidasTur) {
        this.salidasTur = salidasTur;
    }
    
    public String getImagen() {
        return imagen;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
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
}
