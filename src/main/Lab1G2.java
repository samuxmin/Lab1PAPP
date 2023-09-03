package main;

import controladores.Fabrica;
import controladores.ISistema;
import static controladores.Sistema.turistaMail;
import excepciones.UsuarioRepetidoException;
import java.time.LocalDate;
import java.util.Map;
import logica.Usuario;
import datatypes.DataUsuario;
import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Lab1G2 {
    //LISTA TODOS LOS USUARIOS  
    public static void imprimirUsuarios() {
        for (Map.Entry<String, Usuario> entry : Usuario.usuariosMail.entrySet()) {
            Usuario usuario = entry.getValue();
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Apellido: " + usuario.getApellido());
            System.out.println("Correo: " + usuario.getCorreo());
            System.out.println("Nickname: " + usuario.getNick());
            System.out.println("Fecha de Nacimiento: " + usuario.getFecnac());
            // Asegúrate de que la clase Usuario tenga un método getNacionalidad
            // System.out.println("Nacionalidad: " + usuario.getNacionalidad());
            System.out.println(); // Separador entre usuarios
        }
    }

    public static void main(String[] args) throws UsuarioRepetidoException {
        Fabrica f = new Fabrica();
        ISistema sys = f.getSistema();
        
       try {
        sys.registrarUsuario("nick_proveedor2", "p2", "Apellido2", "p2@mail.com", LocalDate.now(), "proveedor", "Argentina", "Descripción del proveedor2", "www.proveedor2.com");
        sys.registrarUsuario("nick_proveedor", "NombreProveedor", "ApellidoProveedor", "proveedor@mail.com", LocalDate.now(), "proveedor", "", "Descripción del proveedor", "www.proveedor.com");
        sys.registrarUsuario("nick_turista", "NombreTurista", "ApellidoTurista", "turista1@mail.com", LocalDate.now(), "turista", "Argentina", null, null);
        sys.registrarUsuario("t2", "t2", "t2", "turista2@mail.com", LocalDate.now(), "turista", "Argentina", "1", "1");
        imprimirUsuarios();
        } catch (Exception err) {
           // System.out.println(err);
        }
        System.out.println("\n");
        

        
        //LISTA TODOS LOS TURISTAS
        Map<String, DataUsuario> turistas = sys.getTurista();
         System.out.println("\n DATATYPE:");
        for (Map.Entry<String, DataUsuario> entry : turistas.entrySet()) {
            DataUsuario dataTurista = entry.getValue();
            System.out.println("Nombre: " + dataTurista.getNombre());
            System.out.println("Apellido: " + dataTurista.getApellido());
            System.out.println("Correo: " + dataTurista.getCorreo());
            System.out.println("Nickname: " + dataTurista.getNick());
            System.out.println("Fecha de Nacimiento: " + dataTurista.getFecNac());
            System.out.println("Nacionalidad: " + dataTurista.getNacionalidad());
            System.out.println(); // Separador entre turistas
        }
        //LISTA TODOS LOS PROVEEDORES
        Map<String, DataUsuario> proveedores = sys.getProveedor();
        for (Map.Entry<String, DataUsuario> entry : proveedores.entrySet()) {
            DataUsuario dataProveedor = entry.getValue();
            System.out.println("Nombre: " + dataProveedor.getNombre());
            System.out.println("Apellido: " + dataProveedor.getApellido());
            System.out.println("Correo: " + dataProveedor.getCorreo());
            System.out.println("Nickname: " + dataProveedor.getNick());
            System.out.println("Fecha de Nacimiento: " + dataProveedor.getFecNac());
            System.out.println("Web: " + dataProveedor.GetWeb());
            System.out.println("Descripción: " + dataProveedor.getDescripcion());
            System.out.println(); // Separador entre proveedores
        }
}
    
}


