package clases;

public class ActividadTuristica {
    
    private Proveedor proveedor;
    private ActividadTuristica departamento;
    private String nombre;
    private String descripcion;
    private int duracionHoras;
    private double costoPorTurista;
    private String ciudad;
    //private DataFecha fechaAlta;
    public ActividadTuristica(String nombre,String descripcion,int duracionHoras,double costoPorTurista,String ciudad){
    this.ciudad=ciudad;
    this.nombre=nombre;
    this.costoPorTurista=costoPorTurista;
    this.duracionHoras=duracionHoras;
    this.descripcion=descripcion;
    }
}
