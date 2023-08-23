/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author samuel
 */
public class Sistema implements  ISistema{
    public Sistema(){}
    public void prueba(){
        System.out.println("Prueba");
    }
    static Sistema instancia = null;
    static Sistema getInstance(){
        if(instancia == null){
            instancia = new Sistema();
        }
        return instancia;
    };
    
    public void prueba2(){
        System.out.println("este no");
    }
}
