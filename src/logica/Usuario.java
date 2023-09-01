/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package logica;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import logica.Usuario;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario implements Serializable {
     @Column(unique = true) // Aquí indicamos que el nick debe ser único
    private String nickname;
    private String nombre;
    private String apellido;
    
    
    //@GeneratedValue(strategy = GenerationType.TABLE)
    @Id
    private String correo;
    private LocalDate fechaNacimiento;
    
    public Usuario(){};
    
    public Usuario(String nick, String name, String apll, String correo, LocalDate fecNac){
        this.nickname = nick;
        this.nombre = name;
        this.apellido = apll;
        this.correo = correo;
        this.fechaNacimiento = fecNac;
        usuariosMail = new HashMap<String, Usuario>();
       
    }
    
    public String getNick(){
        return nickname;
    }
    public String getNombre(){
        return nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public String getCorreo(){
        return correo;
    }
    public LocalDate getFecnac(){
        return fechaNacimiento;
    }
    
    
    
    public void setNick(String nick){
        nickname=nick;
    }
    public void setNombre(String nom) {
        nombre = nom;
    }
    public void setApellido(String ap) {
        apellido = ap;
    }
    public void setCorreo(String corr){
        correo=corr;
    }
    public void setFecha(LocalDate fecha){
        fechaNacimiento=fecha;
    }

    //protected Map<String, Usuario> usuariosMail;
    protected Map<String, Usuario> usuariosMail = new HashMap<>();

    private static Usuario instancia = null;
 
    public static Usuario getinstance() {
        if (instancia == null)
            instancia = new Usuario();
        return instancia;
    }

    public void addUsuario(Usuario usu) {
        String mail = usu.getCorreo();
        usuariosMail.put(mail, usu);
    }

    public Usuario obtenerUsuario(String mail) {
        return ((Usuario) usuariosMail.get(mail));
    }
    
    public Usuario obtenerUsuarioPorNick(String nick) {  //polpo
        return ((Usuario) usuariosMail.get(nick));
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
