/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author samuel
 */
public class Fabrica {
    public Fabrica(){}
    public ISistema getSistema(){
        ISistema s = Sistema.getInstance();
        return s;
    }
}
