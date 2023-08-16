/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab1g2;

import java.time.LocalDate;

public class Lab1G2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Usuario u = new Usuario("samuxmin","samuel","mindler","sam@mail.com",LocalDate.now());
        System.out.print(u.getNombre());
    }
    
}
