
package controladores;
import datatypes.DataUsuario;
import logica.Usuario;
import java.time.LocalDate;
import excepciones.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Proveedor;
import logica.Turista;

public class Sistema implements  ISistema{
         private final EntityManager em;
         public Sistema(){
             EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab1G2PU");
             em = emf.createEntityManager();
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
    
    /*
    public void registrarUsuario(String nick,String name, String ap, String mail, LocalDate fecNac) throws UsuarioRepetidoException {
        Usuario mu = Usuario.getinstance();
        Usuario u = mu.obtenerUsuario(mail);
        if (u != null)
            throw new UsuarioRepetidoException("El usuario " + mail + " ya esta registrado");

        u = new Usuario(nick,name, ap, mail,fecNac);
        mu.addUsuario(u);
        try{
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            
        }
        catch(Exception e){}
          
    }
    */
    
   
    public void registrarUsuario(String nick, String name, String ap, String mail, LocalDate fecNac, String tipoUsuario, String nacionalidad, String descripcion, String web) throws UsuarioRepetidoException {
    Usuario mu = Usuario.getinstance();

    Usuario u = mu.obtenerUsuario(mail);
    if (u != null) {
        throw new UsuarioRepetidoException("El usuario con el correo electrónico " + mail + " ya está registrado");
    }

    u = mu.obtenerUsuarioPorNick(nick);
    if (u != null) {
        throw new UsuarioRepetidoException("El nickname " + nick + " ya está en uso");
    }


    if (tipoUsuario.equals("turista")) {
        Turista turista = new Turista(nick, name, ap, mail, fecNac, nacionalidad);
        mu.addUsuario(turista);
        
    
        try {
            em.getTransaction().begin();
            em.persist(turista);
            em.getTransaction().commit();
        } catch (Exception e) {}
        
    } else if (tipoUsuario.equals("proveedor")) {
        Proveedor proveedor = new Proveedor(nick, name, ap, mail, fecNac, descripcion, web);
        mu.addUsuario(proveedor);

        try {
            em.getTransaction().begin();
            em.persist(proveedor);
            em.getTransaction().commit();
        } catch (Exception e) {}
    }
}

    
    public DataUsuario verInfoUsuario(String mail) throws UsuarioNoExisteException {
        Usuario mu = Usuario.getinstance();
        Usuario u = mu.obtenerUsuario(mail);
        if (u != null)
            return new DataUsuario(u.getNick() ,u.getNombre(), u.getApellido(), u.getCorreo(),  u.getFecnac());
        else
            throw new UsuarioNoExisteException("El usuario " + mail + " no existe");

    }

    @Override
    public DataUsuario[] getUsuarios() throws UsuarioNoExisteException {
        Usuario mu = Usuario.getinstance();
        Usuario[] usrs = mu.getUsuarios();

        if (usrs != null) {
            DataUsuario[] du = new DataUsuario[usrs.length];
            Usuario usuario;

            // Para separar lógica de presentación, no se deben devolver los Usuario,
            // sino los DataUsuario
            for (int i = 0; i < usrs.length; i++) {
                usuario = usrs[i];
                du[i] = new DataUsuario(usuario.getNick() ,usuario.getNombre(), usuario.getApellido(), usuario.getCorreo(),  usuario.getFecnac());
            }

            return du;
        } else
            throw new UsuarioNoExisteException("No existen usuarios registrados");

    }
    
}
