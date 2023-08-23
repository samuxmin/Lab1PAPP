/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.time.LocalDate;

public class Turista extends Usuario {
    private String nacionalidad;
    public Turista(String nick, String name, String apll, String mail, LocalDate fecNac,String nacionalidad){
        super( nick,  name,  apll,  mail, fecNac);
        this.nacionalidad = nacionalidad;
    }
}
