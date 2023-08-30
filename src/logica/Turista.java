/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.time.LocalDate;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.DiscriminatorValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class Turista extends Usuario{
    
    @ManyToMany
    private Collection<Inscripcion_general> inscripciongeneral;
    
    @ManyToMany
    private Collection<Inscripcion_paquete> inscripcionpaquete;
    
    private String nacionalidad;
    public Turista(){
        super();
    };
    public Turista(String nick, String name, String apll, String mail, LocalDate fecNac,String nacionalidad){
        super( nick,  name,  apll,  mail, fecNac);
        this.nacionalidad = nacionalidad;
    }
}
