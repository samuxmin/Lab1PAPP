package logica;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ManyToOne;

@Entity
public class Proveedor extends Usuario {
    @OneToMany
    @JoinTable(name = "Proveedor_ACT",
            joinColumns=@JoinColumn(name="Proveedor_correo"),
            inverseJoinColumns=@JoinColumn(name="ActividadTuristica_nombre"))
    private Collection<ActividadTuristica> actTuristica;
    
    @OneToMany
    @JoinTable(name = "Proveedor_Paquete",
            joinColumns=@JoinColumn(name="Proveedor_correo"),
            inverseJoinColumns=@JoinColumn(name="Paquete_nombre_paquete"))
    private Collection<Paquete> paquete;
    
    @ManyToOne
            @JoinColumn(name="Dept_nombredepto")
    private Departamento departamento;
    
    private String web;
    private String descripcion;
    public Proveedor(){
        super();
    };
    public Proveedor(String nick, String name, String apll, String mail, LocalDate fecNac,String descripcion,String web){
        super( nick,  name,  apll,  mail, fecNac);
        this.descripcion = descripcion;
        this.web=web;
    }
    
}
