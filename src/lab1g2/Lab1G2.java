package lab1g2;


import clases.Usuario;
import controlador.Fabrica;
import controlador.ISistema;
import java.time.LocalDate;

public class Lab1G2 {

    public static void main(String[] args) {
        Usuario u = new Usuario("samuxmin","samuel","mindler","sam@mail.com",LocalDate.now());
        System.out.print(u.getNombre());
        
        Fabrica f = new Fabrica();
        ISistema sys = f.getSistema();
        sys.prueba();
        //sys.prueba2();
        
    }
    
}
