 
package controladores;

import logica.Usuario;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ManejadorUsuario {
    private Map<String, Usuario> usuariosMail;
    private static ManejadorUsuario instancia = null;

    private ManejadorUsuario() {
        usuariosMail = new HashMap<String, Usuario>();
    }

    public static ManejadorUsuario getinstance() {
        if (instancia == null)
            instancia = new ManejadorUsuario();
        return instancia;
    }

    public void addUsuario(Usuario usu) {
        String mail = usu.getCorreo();
        usuariosMail.put(mail, usu);
    }

    public Usuario obtenerUsuario(String mail) {
        return ((Usuario) usuariosMail.get(mail));
    }

    public Usuario[] getUsuarios() {
        if (usuariosMail.isEmpty())
            return null;
        else {
            Collection<Usuario> usrs = usuariosMail.values();
            Object[] o = usrs.toArray();
            Usuario[] usuarios = new Usuario[o.length];
            for (int i = 0; i < o.length; i++) {
                usuarios[i] = (Usuario) o[i];
            }

            return usuarios;
        }
    }

}

