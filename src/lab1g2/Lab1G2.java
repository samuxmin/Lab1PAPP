
package lab1g2;

import java.time.LocalDate;

public class Lab1G2 {

    public static void main(String[] args) {
        Usuario u = new Usuario("samuxmin","samuel","mindler","sam@mail.com",LocalDate.now());
        System.out.print(u.getNombre());
    }
    
}
