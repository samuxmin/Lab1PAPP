
package controladores;
import datatypes.DataUsuario;
import logica.Usuario;
import java.time.LocalDate;
import excepciones.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
    
    public void registrarUsuario(String nick,String name, String ap, String mail, LocalDate fecNac) throws UsuarioRepetidoException {
        ManejadorUsuario mu = ManejadorUsuario.getinstance();
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
    public DataUsuario verInfoUsuario(String mail) throws UsuarioNoExisteException {
        ManejadorUsuario mu = ManejadorUsuario.getinstance();
        Usuario u = mu.obtenerUsuario(mail);
        if (u != null)
            return new DataUsuario(u.getNick() ,u.getNombre(), u.getApellido(), u.getCorreo(),  u.getFecnac());
        else
            throw new UsuarioNoExisteException("El usuario " + mail + " no existe");

    }

    @Override
    public DataUsuario[] getUsuarios() throws UsuarioNoExisteException {
        ManejadorUsuario mu = ManejadorUsuario.getinstance();
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
