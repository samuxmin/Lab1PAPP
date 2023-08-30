
package main;

import controladores.Fabrica;
import controladores.ISistema;
import excepciones.UsuarioRepetidoException;
import java.time.LocalDate;
import logica.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Lab1G2 {

    public static void main(String[] args) throws UsuarioRepetidoException {
        Fabrica f = new Fabrica();
        ISistema sys = f.getSistema();
        try{
        sys.registrarUsuario("nick", "name", "apl", "mail", LocalDate.now());
       
            System.out.println( sys.verInfoUsuario("mail") );
        }catch(Exception err){
            System.out.println(err);
        }
        
    }
}