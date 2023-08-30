/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package logica;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario implements Serializable {
     @Column(unique = true) // Aquí indicamos que el nick debe ser único
    private String nickname;
    private String nombre;
    private String apellido;
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private String correo;
    private LocalDate fechaNacimiento;
    
    public Usuario(){};
    
    public Usuario(String nick, String name, String apll, String mail, LocalDate fecNac){
        this.nickname = nick;
        this.nombre = name;
        this.apellido = apll;
        this.correo = mail;
        this.fechaNacimiento = fecNac;
    }
    
    public String getNick(){
        return nickname;
    }
    public String getNombre(){
        return nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public String getCorreo(){
        return correo;
    }
    public LocalDate getFecnac(){
        return fechaNacimiento;
    }
    
    
    
    public void setNick(String nick){
        nickname=nick;
    }
    public void setNombre(String nom) {
        nombre = nom;
    }
    public void setApellido(String ap) {
        apellido = ap;
    }
    public void setCorreo(String corr){
        correo=corr;
    }
    public void setFecha(LocalDate fecha){
        fechaNacimiento=fecha;
    }
}
