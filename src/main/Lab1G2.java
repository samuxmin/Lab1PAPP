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
        
        try {
            
            sys.registrarUsuario("nick_turista", "NombreTurista", "ApellidoTurista", "turista@mail.com", LocalDate.now(), "turista", "Argentina", "", "");

           
            sys.registrarUsuario("nick_proveedor", "NombreProveedor", "ApellidoProveedor", "proveedor@mail.com", LocalDate.now(), "proveedor", "", "Descripción del proveedor", "www.proveedor.com");

            //sys.verInfoUsuario("mail"); // Puedes agregar esto para ver la información de un usuario registrado
        } catch (Exception err) {
            System.out.println(err);
        }
    }
}


