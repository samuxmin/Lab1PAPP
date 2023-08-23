/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package clases;
import java.time.LocalDate;

public class Usuario {
    private String nickname;
    private String nombre;
    private String apellido;
    private String correo;
    private LocalDate fechaNacimiento;
    
    public Usuario(String nick, String name, String apll, String mail, LocalDate fecNac){
        this.nickname = nick;
        nombre = name;
        apellido = apll;
        correo = mail;
        fechaNacimiento = fecNac;
    }
    
    public String getNombre(){
        return nombre;
    }
}
