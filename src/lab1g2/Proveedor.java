package lab1g2;

import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class Proveedor extends Usuario {
    private String descripcion;
    private String email;
    public Proveedor(String nick, String name, String apll, String mail, LocalDate fecNac,String descripcion,String email){
        super( nick,  name,  apll,  mail, fecNac);
        this.descripcion = descripcion;
        this.email = email;
    }
    
}
