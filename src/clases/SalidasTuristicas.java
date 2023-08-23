package clases;

public class SalidasTuristicas {
    private String nombreSalida;
    private int cantidadMax;
    private ActividadTuristica actividadAsociada;
    private int cantidadMaximaTuristas;
    //private LocalDate fechaAlta;
    //private LocalDate fechaSalida;
    //private String horaSalida;
    //private String lugarSalida;
    public SalidasTuristicas(int cantidadMax,int cantidadMaximaTuristas){
        this.cantidadMax=cantidadMax;
        this.cantidadMaximaTuristas=cantidadMaximaTuristas;
    }
}
