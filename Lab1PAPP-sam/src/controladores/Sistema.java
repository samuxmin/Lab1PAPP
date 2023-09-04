package controladores;

import datatypes.DataActividad;
import datatypes.DataPaquete;
import datatypes.DataSalida;
import datatypes.DataUsuario;
import logica.Usuario;
import java.time.LocalDate;
import excepciones.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Proveedor;
import logica.Turista;
import java.util.HashMap;
import java.util.Map;
import logica.ActividadTuristica;
import logica.Departamento;
import logica.Paquete;
import static logica.Proveedor.paqueteNombre;
import logica.SalidasTuristicas;

public class Sistema implements ISistema {

    private final EntityManager em;
    private DataActividad[] dataActividades;
    private Departamento deptoSeleccionado;

    public Sistema() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab1G2PU");
        em = emf.createEntityManager();
    }

    static Sistema instancia = null;

    static Sistema getInstance() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    ;
    
    public void prueba2() {
        System.out.println("este no");
    }

    public static Map<String, DataUsuario> turistaMail = new HashMap<>();
    public static Map<String, DataUsuario> usuariosMail = new HashMap<>();

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

        Usuario nuevoUsuario = new Usuario(nick, name, ap, mail, fecNac);

        try {
            em.getTransaction().begin();
            em.persist(nuevoUsuario);
            em.getTransaction().commit();
        } catch (Exception e) {
        }

        if (tipoUsuario.equals("turista")) {
            Turista turista = new Turista(nick, name, ap, mail, fecNac, nacionalidad);
            mu.addUsuario(turista);

            try {
                em.getTransaction().begin();
                em.persist(turista);
                em.getTransaction().commit();
            } catch (Exception e) {
            }

        } else if (tipoUsuario.equals("proveedor")) {
            Proveedor proveedor = new Proveedor(nick, name, ap, mail, fecNac, descripcion, web);
            mu.addUsuario(proveedor);

            try {
                em.getTransaction().begin();
                em.persist(proveedor);
                em.getTransaction().commit();
            } catch (Exception e) {
            }
        }
    }

    public DataUsuario verInfoUsuario(String mail) throws UsuarioNoExisteException {
        Usuario mu = Usuario.getinstance();
        Usuario u = mu.obtenerUsuario(mail);
        if (u != null) {
            return new DataUsuario(u.getNick(), u.getNombre(), u.getApellido(), u.getCorreo(), u.getFecnac(), null, null, null);
        } else {
            throw new UsuarioNoExisteException("El usuario " + mail + " no existe");
        }

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
                du[i] = new DataUsuario(usuario.getNick(), usuario.getNombre(), usuario.getApellido(), usuario.getCorreo(), usuario.getFecnac(), null, null, null);
            }

            return du;
        } else {
            throw new UsuarioNoExisteException("No existen usuarios registrados");
        }

    }


    /*
public void listarTurista(){
        
        DataUsuario datatur=null;
        //Turista tur=null;
        Map<String, DataUsuario> auxTurista = new HashMap<>();

        //tur = (Turista) auxTurista;
        
        datatur = getTurista();
        
        for (Map.Entry<String, DataUsuario> entry : auxTurista.entrySet()){
            System.out.println("Nombre"+datatur.getNombre()+" Apellido"+datatur.getApellido()+" Correo"+datatur.getCorreo()+" Nickname"+datatur.getNick()+" Fecha de Nacimiento"+datatur.getFecNac()+" Nacionalidad"+datatur.getNacionalidad());  
        } 

}
     */
    public Map<String, DataUsuario> getTurista() {
        turistaMail.clear(); // Limpia el mapa turistaMail antes de agregar usuarios

        for (Map.Entry<String, Usuario> entry : Usuario.usuariosMail.entrySet()) {
            Usuario usuario = entry.getValue();

            if (usuario instanceof Turista) {
                Turista turista = (Turista) usuario;

                DataUsuario dataTurista = new DataUsuario(turista.getNick(), turista.getNombre(), turista.getApellido(), turista.getCorreo(), turista.getFecnac(), turista.getNacionalidad(), null, null);

                turistaMail.put(turista.getCorreo(), dataTurista);
            }
        }

        return turistaMail;
    }

    public Map<String, DataUsuario> getProveedor() {
        usuariosMail.clear(); // Limpia el mapa turistaMail antes de agregar usuarios

        for (Map.Entry<String, Usuario> entry : Usuario.usuariosMail.entrySet()) {
            Usuario usuario = entry.getValue();

            if (usuario instanceof Proveedor) {
                Proveedor proveedor = (Proveedor) usuario;

                DataUsuario dataProveedor = new DataUsuario(proveedor.getNick(), proveedor.getNombre(), proveedor.getApellido(), proveedor.getCorreo(), proveedor.getFecnac(), null, proveedor.getWeb(), proveedor.getDescripcion());

                usuariosMail.put(usuario.getCorreo(), dataProveedor);
            }
        }

        return usuariosMail;
    }

    public void AgregarActividadTuristicaPaquete(String nombre_paquete,String nombreDepartamento,String nombre){
    listarPaquete();    
    Paquete paq= selectPaquete(nombre_paquete);
    Departamento dep=selectDepartamento(nombreDepartamento);
    listarActividadesDepNoPaquete(nombre_paquete);
    ActividadTuristica act=selectActividad(nombre);
    }
    
   public void ConsultaPaqueteActTuistica(String nombrePaquete){
        listarPaquete();
        Paquete paq=  selectPaquete(nombrePaquete);
        paq.getNombre_paquete();
        paq.getDescri();
        paq.getValidez();
        paq.getDescuento();
        paq.getActTuristica();
        //button
    }
    /*
   public void AltaDepartamento(String nombreDepto,String descripcion, String web){
       Departamento departamentoExistente = Departamento.encontrarDepto(nombreDepto);
        if (departamentoExistente != null) {
            return null;
        } else {
            Departamento nuevoDepartamento = new Departamento(nombreDepto);
            nuevoDepartamento.setDescripcion(descripcion);
            nuevoDepartamento.setWeb(web);
             Departamento.departamentos.put(nombreDepto, nuevoDepartamento);
             return nuevoDepartamento;
        }
   }
   */

   public void listarActividadesDepNoPaquete(String nombre_paquete) {
        for (Map.Entry<String, Usuario> entry : Usuario.usuariosMail.entrySet()) {
            Usuario usuario = entry.getValue();
            if (usuario instanceof Proveedor) {
                Proveedor proveedor = (Proveedor) usuario;
                Departamento departamento = selectDepartamento(proveedor.getDepartamento().getNombreDepto()); // Obtiene el departamento del proveedor
                Paquete paqueteSeleccionado = proveedor.selectPaquete(nombre_paquete); // Obtiene el paquete seleccionado

                if (departamento != null && paqueteSeleccionado != null) {
                    for (ActividadTuristica actividad : departamento.getActTuristica()) {
                        if (!paqueteSeleccionado.getActTuristica().contains(actividad)) {
                            // La actividad no está en el paquete seleccionado, puedes hacer algo con ella
                            System.out.println("Nombre de la actividad: " + actividad.getNombre());
                        }
                    }
                }
            }
        }
    }

    public Departamento selectDepartamento(String nombreDepartamento) {
        for (Map.Entry<String, Usuario> entry : Usuario.usuariosMail.entrySet()) {
            Usuario usuario = entry.getValue();
            if (usuario instanceof Proveedor) {
                Proveedor proveedor = (Proveedor) usuario;//Casteo de proveedor de usuario 
                return proveedor.getDepartamento();
            }
        }
        return null;
    }

    public void listarPaquete() {
        for (Map.Entry<String, Paquete> entry : paqueteNombre.entrySet()) {
            Paquete paquete = entry.getValue();
            System.out.println("Nombre: " + paquete.getNombre_paquete());
        }
    }

    public Paquete selectPaquete(String nombre_paquete) {
        for (Map.Entry<String, Usuario> entry : Usuario.usuariosMail.entrySet()) {
            Usuario usuario = entry.getValue();
            if (usuario instanceof Proveedor) {
                Proveedor proveedor = (Proveedor) usuario;//Casteo de proveedor de usuario 
                for (Paquete paq : proveedor.getPaquete()) {
                    if (paq.getNombre_paquete() == nombre_paquete) {
                        return paq;
                    }
                }

            }
        }
        return null;
    }

    public ActividadTuristica selectActividad(String nombre) {
        for (Map.Entry<String, Usuario> entry : Usuario.usuariosMail.entrySet()) {
            Usuario usuario = entry.getValue();
            if (usuario instanceof Proveedor) {
                Proveedor proveedor = (Proveedor) usuario;//Casteo de proveedor de usuario 
                for (ActividadTuristica act : proveedor.getActTuristica()) {
                    if (act.getNombre() == nombre) {
                        return act;
                    }
                }
            }
        }
        return null;
    }

    // UNION FUNCS
    public DataActividad[] listarActividadesDepto(String nombreDpto) {

        deptoSeleccionado = Departamento.encontrarDepto(nombreDpto);
        ActividadTuristica[] actividades = deptoSeleccionado.getActividades();
        dataActividades = new DataActividad[actividades.length];
        for (int i = 0; i < actividades.length; i++) {
            dataActividades[i] = actividades[i].devolverData();
        }
        return dataActividades;
    }

    public String[] listarSalidasActividad(String actividad) {
        if (dataActividades != null) {
            DataActividad actividadData;
            for (int i = 0; i < dataActividades.length; i++) {
                if (dataActividades[i].getNombre() == actividad) {
                    return dataActividades[i].getSalidasTur();
                }
            }
        }
        return null;
    }

    public DataSalida datosSalida(String salida, String actividad, String nombreDepto) {
        // ManejadorDepto md = ManejadorDepto.getinstance();
        Departamento d = Departamento.encontrarDepto(nombreDepto);
        ActividadTuristica act = d.getActividadByNombre(actividad);
        SalidasTuristicas s = act.getSalidaByNombre(salida);
        return s.devolverData();

    }

    public DataSalida[] listarSalidasVigentes(String actividad) {
        DataSalida[] salidasVigentes = null;

        ActividadTuristica act = deptoSeleccionado.getActividadByNombre(actividad);
        SalidasTuristicas st[] = act.devolverSalidasVigentes();

        for (int i = 0; i < st.length; i++) {
            salidasVigentes[i] = st[i].devolverData();
        }
        return salidasVigentes;
    }
}
