package controladores;

import datatypes.DataActividad;
import datatypes.DataDepto;
import datatypes.DataPaquete;
import datatypes.DataSalida;
import datatypes.DataUsuario;
import logica.Usuario;
import java.time.LocalDate;
import excepciones.*;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Proveedor;
import logica.Turista;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.TypedQuery;
import logica.ActividadTuristica;
import logica.Departamento;
import logica.Inscripcion_general;
import logica.Paquete;
import logica.SalidasTuristicas;

public class Sistema implements ISistema {

    private final EntityManager em;
    private DataActividad[] dataActividades;
    private Departamento deptoSeleccionado;
    private ActividadTuristica actividadSeleccionada;
    private Turista turistaSeleccionado;
    private SalidasTuristicas salidaSeleccionada;
    private String nombre;
    private String descripcion;
    private int validez;
    private int descuento;
    private LocalDate alta;
    private LocalDate fechaSalida;
    public LocalDate fechaSistema = LocalDate.now();
    int cantidad;

    public Sistema() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab1G2PU");
        em = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab1G2PU");
        return emf.createEntityManager();
    }

    static Sistema instancia = null;

    public static Sistema getInstance() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    public static Map<String, DataUsuario> turistaMail = new HashMap<>();
    public static Map<String, DataUsuario> usuariosMail = new HashMap<>();

    public DataUsuario verInfoUsuario(String mail) throws UsuarioNoExisteException {

        Usuario u = Usuario.obtenerUsuario(mail);
        if (u != null) {
            return u.devolverData();
        } else {
            throw new UsuarioNoExisteException("El usuario " + mail + " no existe");
        }

    }

    @Override
    public DataUsuario[] getUsuarios() throws UsuarioNoExisteException {

        Usuario[] usrs = Usuario.getUsuarios();

        if (usrs != null) {
            DataUsuario[] du = new DataUsuario[usrs.length];
            Usuario usuario;

            // Para separar lógica de presentación, no se deben devolver los Usuario,
            // sino los DataUsuario
            for (int i = 0; i < usrs.length; i++) {
                usuario = usrs[i];
                du[i] = usuario.devolverData();
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

                DataUsuario dataTurista = turista.devolverData();
                turistaMail.put(turista.getCorreo(), dataTurista);
            }
        }

        return turistaMail;
    }

    public Map<String, DataUsuario> getProveedor() {
        usuariosMail.clear();// Limpia el mapa turistaMail antes de agregar usuarios
        Map<String, DataUsuario> proveedores = new HashMap<String, DataUsuario>();
        for (Usuario u : Usuario.usuariosMail.values()) {
            if (u instanceof Proveedor) {
                Proveedor proveedor = (Proveedor) u;

                DataUsuario dataProveedor = proveedor.devolverData();
                proveedores.put(u.getCorreo(), dataProveedor);
            }
        }

        return proveedores;
    }

    public void AgregarActividadTuristicaPaquete(String nombre_paquete, String nombreDepartamento, String nombre) {

        Departamento dep = selectDepartamento(nombreDepartamento);
        Paquete paq = selectPaquete(nombre_paquete);
        // listarActividadesDepNoPaquete(nombre_paquete);
        ActividadTuristica act = selectActividad(nombre);
        paq.addActividad(act);
        em.getTransaction().begin();
        em.merge(paq);
        em.getTransaction().commit();
    }

    public DataPaquete ConsultaPaqueteActTuistica(String nombrePaquete) {
        Paquete paq = selectPaquete(nombrePaquete);
        return paq.devolverData();

    }

    public void AltaDepartamento(String nombreDepto, String descripcion, String web) {
        Departamento departamentoExistente = Departamento.encontrarDepto(nombreDepto);
        if (departamentoExistente == null) {
            Departamento nuevoDepartamento = new Departamento(nombreDepto, descripcion, web);
            // nuevoDepartamento.setDescripcion(descripcion);
            // nuevoDepartamento.setWeb(web);
            Departamento.departamentos.put(nombreDepto, nuevoDepartamento);
            try {
                em.getTransaction().begin();
                em.persist(nuevoDepartamento);
                em.getTransaction().commit();
            } catch (Exception e) {
            }
        }
    }

    public ActividadTuristica[] listarActividadesDepNoPaquete(String nombre_paquete, String nombreDepto) {

        Departamento dept = selectDepartamento(nombreDepto);
        Paquete paqueteSeleccionado = selectPaquete(nombre_paquete); // Obtiene el paquete seleccionado

        List<ActividadTuristica> actividadesNoEnPaquete = new ArrayList<>();

        for (ActividadTuristica a : dept.getActTuristica()) {
            if (!paqueteSeleccionado.getActTuristica().contains(a)) {
                actividadesNoEnPaquete.add(a);
            }
        }
        return actividadesNoEnPaquete.toArray(new ActividadTuristica[0]);
    }

    public Departamento selectDepartamento(String nombreDepartamento) {
        deptoSeleccionado = Departamento.encontrarDepto(nombreDepartamento);
        return deptoSeleccionado;
    }

    public DataPaquete[] listarPaquetes() {
        Collection<Paquete> paquetesjovani = Paquete.paquetes.values();
        DataPaquete[] pqs = new DataPaquete[paquetesjovani.size()];
        int i = 0;

        for (Paquete p : paquetesjovani) {
            pqs[i] = p.devolverData();
            i++;
        }
        return pqs;
    }

    public Paquete selectPaquete(String nombre_paquete) {
        return Paquete.getPaqueteByNombre(nombre_paquete);
    }

    public ActividadTuristica selectActividad(String nombre) {
        for (Map.Entry<String, Usuario> entry : Usuario.usuariosMail.entrySet()) {
            Usuario usuario = entry.getValue();
            if (usuario instanceof Proveedor) {
                Proveedor proveedor = (Proveedor) usuario;//Casteo de proveedor de usuario 
                for (ActividadTuristica act : proveedor.getActTuristica()) {
                    if (act.getNombre() == nombre) {
                        actividadSeleccionada = act;
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

    public String[] listarActividadesDeptoArreglo(String nombredepto) {

        Departamento deptoSelect = Departamento.encontrarDepto(nombredepto);

        ActividadTuristica[] actividades = deptoSelect.getActividades();
        String[] nombresDepto = new String[actividades.length];
        for (int i = 0; i < actividades.length; i++) {
            nombresDepto[i] = actividades[i].getNombre();
        }
        return nombresDepto;
    }

    /*    public String[] listarSalidasActividad(String actividad) {

        for (int i = 0; i < dataActividades.length; i++) {
            if (dataActividades[i].getNombre() == actividad) {
                return dataActividades[i].getSalidasTur();
            }
        }
        return null;
    } */
    public String[] listarSalidasActividad(String actividad) {
        ActividadTuristica act = selectActividad(actividad);
        SalidasTuristicas[] salidastur = act.devolverSalidas();
        String[] arreglosalidasAct = new String[salidastur.length];
        for (int i = 0; i < salidastur.length; i++) {
            if ((salidastur[i].getFechaSalida()).isAfter(fechaSistema)) {

            }
            arreglosalidasAct[i] = salidastur[i].getNombreSalida();

        }
        return arreglosalidasAct;
    }

    public DataSalida datosSalida(String salida, String actividad, String nombreDepto) {
        Departamento d = Departamento.encontrarDepto(nombreDepto);
        ActividadTuristica act = d.getActividadByNombre(actividad);
        SalidasTuristicas s = act.getSalidaByNombre(salida);
        if (s == null) {
            return null;
        }
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

    public DataUsuario[] listarTuristas() {
        Turista[] turistasArr = Turista.getTuristas();
        DataUsuario[] dataTurs = new DataUsuario[turistasArr.length];
        for (int i = 0; i < turistasArr.length; i++) {
            dataTurs[i] = turistasArr[i].devolverData();
        }
        return dataTurs;
    }

    public boolean estaInscritoTuristaSalida(String mailTurista, String nombreSalida) {
        salidaSeleccionada = actividadSeleccionada.getSalidaByNombre(nombreSalida);
        return salidaSeleccionada.estaInscritoUsuario(mailTurista);
    }

    public boolean excedeLimiteInscripcion(String nombreSalida, int cantTurista) {

        salidaSeleccionada = actividadSeleccionada.getSalidaByNombre(nombreSalida);
        return (salidaSeleccionada.getCantInscritos() + cantTurista > salidaSeleccionada.getCantidadMaximaTuristas());
    }

    public SalidasTuristicas getSalidaByNombre(String nombreSalida) {
        for (Departamento d : Departamento.departamentos.values()) {
            for (ActividadTuristica actT : d.getActTuristica()) {

                SalidasTuristicas salida = actT.getSalidaByNombre(nombreSalida);
                if (salida != null) {
                    return salida;
                }
            }
        }
        return null;

    }

    public boolean inscripcionSalida(String mailTurista, String nombreSalida, int cantTurista, LocalDate fechaInscr) {
        boolean valido = true;
        turistaSeleccionado = (Turista) Usuario.obtenerUsuario(mailTurista);
        salidaSeleccionada = getSalidaByNombre(nombreSalida);
        this.alta = fechaInscr;
        if (salidaSeleccionada.estaInscritoUsuario(mailTurista) || salidaSeleccionada.getCantInscritos() + cantTurista > salidaSeleccionada.getCantidadMaximaTuristas()) {
            valido = false;
        }
        return valido;
    }

    public void confirmarInscripcion(int cantTurista, int costogral) {
        Collection<Turista> turistas = new ArrayList<>();
        turistas.add(turistaSeleccionado);

        Inscripcion_general nuevaInscripcion = new Inscripcion_general(cantTurista, costogral, salidaSeleccionada, turistas);
        nuevaInscripcion.setFechaCompra(alta);
        salidaSeleccionada.agregarInscripcionGral(nuevaInscripcion);
        salidaSeleccionada.aumentarCantInscritos(cantTurista);

        for (Turista t : turistas) {
            t.agregarInscripcionGral(nuevaInscripcion);  // Llamar a la función para cada turista en la colección

        }

        try {
            em.getTransaction().begin();
            em.merge(nuevaInscripcion);

            em.merge(salidaSeleccionada);
            em.getTransaction().commit();
        } catch (Exception e) {

            //em.getTransaction().rollback();
        }
    }

    public boolean AltaActividadTuristica(String correoProveedor, String nombreAct, String descripcion, Integer duracion, double costo, String depto, LocalDate fechaA, String ciudad) {

        Usuario u = Usuario.obtenerUsuario(correoProveedor);
        boolean flag = false;
        if (u != null) {
            if (u instanceof Proveedor) {
                Proveedor proveedor = (Proveedor) u;
                Collection<ActividadTuristica> act = proveedor.getActTuristica();
                for (ActividadTuristica actividad : act) {
                    if (actividad.getNombre() == nombreAct) {//castea la coleccion de actividades turisticas para conseguir una actividad y comparar su nombre
                        flag = true;
                    }
                }
                if (!flag) {//Si no encontro otra actividad en la coleccion con el mismo nombre lo añade
                    ActividadTuristica nuevaAct = new ActividadTuristica(nombreAct, descripcion, duracion, costo, ciudad, fechaA);

                    proveedor.addActTuristica(nuevaAct);//Lo añade a la coleccion de actividades turisticas (creo)
                    Departamento dep = selectDepartamento(depto);
                    //this.listarActividadesDeptoArreglo(depto);
                    dep.addActTurisica(nuevaAct);
                    nuevaAct.setProveedor(proveedor);
                    nuevaAct.setDepartamento(dep);

                    try {
                        em.getTransaction().begin();
                        em.merge(nuevaAct);
                        em.merge(deptoSeleccionado);
                        em.getTransaction().commit();
                    } catch (Exception e) {
                    }
                }
            }
        }
        return flag;
    }

    public DataActividad ConsultaActTuristica(String ciudad, String nombreAct) {
        ActividadTuristica act = selectActividad(nombreAct);
        return act.devolverData();
    }

    public void AltaSalidaTuristica(String correoProveedor, String nombreSalida, String lugar, Integer cantTuristas, LocalDate fechaSalida, LocalDate fechaAlta) {

        nombre = nombreSalida;
        this.descripcion = lugar;
        this.alta = fechaAlta;
        this.fechaSalida = fechaSalida;
        this.cantidad = cantTuristas;

    }

    public void confirmarAltaSalida() {
        SalidasTuristicas nuevaS = actividadSeleccionada.crearSalida(nombre, descripcion, cantidad, alta, fechaSalida);
        try {
            em.getTransaction().begin();
            em.merge(nuevaS);
            em.merge(actividadSeleccionada);
            em.getTransaction().commit();
        } catch (Exception e) {

        }
    }

    //LISTA TODOS LOS PROVEEDORES
    public DataUsuario[] listarProveedores() {
        if (Usuario.usuariosMail.isEmpty()) {
            return null;
        }
        Proveedor[] proveedoresArr = Proveedor.getProveedores();
        DataUsuario[] dataProveedor = new DataUsuario[proveedoresArr.length];
        for (int i = 0; i < proveedoresArr.length; i++) {
            dataProveedor[i] = proveedoresArr[i].devolverData();
        }
        return dataProveedor;
    }

    //LISTA TODOS LOS TURISTAS
    //LISTA TODOS LOS DEPARTAMENTOS
    /*
        public void listarDepartamentos(){
            Usuario mu = Usuario.getinstance();
            Map<String, Usuario> usuariosMap=mu.getUsuariosMail();
            for (Map.Entry<String, Usuario> entry1 : usuariosMap.entrySet()){
                Usuario usuario = entry1.getValue();
                if (usuario instanceof Proveedor) {
                    System.out.println(((Proveedor)usuario).getDepartamento().getNombreDepto());  
                } 
            } 
        } */
    public String[] listarDepartamentos() {
        Collection<Departamento> deptos = Departamento.departamentos.values();
        String[] nombresDepto = new String[deptos.size()];
        int i = 0;
        for (Departamento d : deptos) {
            nombresDepto[i] = d.getNombreDepartamento();
            i++;
        }
        return nombresDepto;
    }

    // VERSION DE REGISTRAR USUARIO QUE USA MAPS Y PERMITE TESTEO ACTUALIZADA
    public void registrarUsuario(String nick, String name, String ap, String mail, LocalDate fecNac, String tipoUsuario, String nacionalidad, String descripcion, String web, String imagenPerfil) throws UsuarioRepetidoException {

        Usuario u = Usuario.obtenerUsuario(mail);
        if (u != null) {
            throw new UsuarioRepetidoException("El usuario con el correo electrónico " + mail + " ya está registrado");
        } else if (Usuario.obtenerUsuarioPorNick(nick) != null) {
            throw new UsuarioRepetidoException("El nickname " + nick + " ya está en uso");
        } else {
            Usuario nuevoUsuario = new Usuario(nick, name, ap, mail, fecNac, imagenPerfil);

            try {
                em.getTransaction().begin();
                em.persist(nuevoUsuario);
                em.getTransaction().commit();
            } catch (Exception e) {
            }

            if (tipoUsuario.equals("turista")) {
                Turista turista = new Turista(nick, name, ap, mail, fecNac, nacionalidad, imagenPerfil);
                Usuario.addUsuario(turista);

                try {
                    em.getTransaction().begin();
                    em.persist(turista);
                    em.getTransaction().commit();
                } catch (Exception e) {
                }

            } else if (tipoUsuario.equals("proveedor")) {
                Proveedor proveedor = new Proveedor(nick, name, ap, mail, fecNac, descripcion, web, imagenPerfil);
                Usuario.addUsuario(proveedor);

                try {
                    em.getTransaction().begin();
                    em.persist(proveedor);
                    em.getTransaction().commit();
                } catch (Exception e) {
                }
            }
        }
    }

    /* VERSION DE REGISTRAR USUARIO QUE USA LA BD (TERMINADA)
    
     public void registrarUsuario(String nick, String name, String ap, String mail, LocalDate fecNac, String tipoUsuario, String nacionalidad, String descripcion, String web, String imagenPerfil) throws UsuarioRepetidoException {
        
    // Verificar si el usuario ya existe en la base de datos por nickname
    if (usuarioExisteBDPorNick(nick)) {
        throw new UsuarioRepetidoException("El nickname " + nick + " ya está en uso");
    }
    // Verificar si el usuario ya existe en la base de datos por correo
    if (usuarioExisteBD(mail)) {
        throw new UsuarioRepetidoException("El correo " + mail + " ya está en uso");
    }
    

    // Crear un nuevo usuario
    Usuario nuevoUsuario = new Usuario(nick, name, ap, mail, fecNac, imagenPerfil);

    
    if (imagenPerfil != null && !imagenPerfil.isEmpty()) {
        nuevoUsuario.setImagenPerfil(imagenPerfil);
    }
    
    try {
        em.getTransaction().begin();
        em.persist(nuevoUsuario);
        em.getTransaction().commit();
    } catch (Exception e) {
        // Manejar excepciones si es necesario
    }

    if (tipoUsuario.equals("turista")) {
        Turista turista = new Turista(nick, name, ap, mail, fecNac, nacionalidad, imagenPerfil);
        Usuario.addUsuario(turista);

        try {
            em.getTransaction().begin();
            em.persist(turista);
            em.getTransaction().commit();
        } catch (Exception e) {
            // Manejar excepciones si es necesario
        }

    } else if (tipoUsuario.equals("proveedor")) {
        Proveedor proveedor = new Proveedor(nick, name, ap, mail, fecNac, descripcion, web, imagenPerfil);
        Usuario.addUsuario(proveedor);

        try {
            em.getTransaction().begin();
            em.persist(proveedor);
            em.getTransaction().commit();
        } catch (Exception e) {
            // Manejar excepciones si es necesario
        }
    }
}
     */
    public void modificarDatosUsuario(String mail, String nuevoNombre, String nuevoApellido, LocalDate nuevaFechaNacimiento, String nuevaNacionalidad, String nuevaDescripcion, String nuevoSitioWeb) throws UsuarioNoExisteException {

        Usuario usuario = Usuario.obtenerUsuario(mail);

        if (usuario == null) {
            throw new UsuarioNoExisteException("El usuario con el correo electrónico " + mail + " no existe");
        }

        // actualiza datos menos nick y correo
        usuario.setNombre(nuevoNombre);
        usuario.setApellido(nuevoApellido);
        usuario.setFecha(nuevaFechaNacimiento);

        // si es turista actualiza nacionalidad
        if (usuario instanceof Turista) {
            Turista turista = (Turista) usuario;
            turista.setNacionalidad(nuevaNacionalidad);
        } else if (usuario instanceof Proveedor) {
            // proveedor actualiza descripcion y la web
            Proveedor proveedor = (Proveedor) usuario;
            proveedor.setDescripcion(nuevaDescripcion);
            proveedor.setWeb(nuevoSitioWeb);
        }

        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
        }

        // ACTUALIZA LOS MAPS DESPUES DE AGREGARLOS A LA BD
        if (usuario instanceof Turista) {

            // ACTUALIZA EL MAP DE TURISTA
            Turista turista = (Turista) usuario;
            DataUsuario dataTurista = new DataUsuario(turista.getNick(), turista.getNombre(), turista.getApellido(), turista.getCorreo(), turista.getFecnac(), turista.getNacionalidad(), null, null);
            turistaMail.put(turista.getCorreo(), dataTurista);

        } else if (usuario instanceof Proveedor) {

            // ACTUALIZA EL OTRO MAP
            Proveedor proveedor = (Proveedor) usuario;
            DataUsuario dataProveedor = new DataUsuario(proveedor.getNick(), proveedor.getNombre(), proveedor.getApellido(), proveedor.getCorreo(), proveedor.getFecnac(), null, proveedor.getWeb(), proveedor.getDescripcion());
            usuariosMail.put(proveedor.getCorreo(), dataProveedor);
        }
    }

    public String[] listarCorreosProveedores() {
        Proveedor[] proveedoresArr = Proveedor.getProveedores();
        String[] correosProveedores = new String[proveedoresArr.length];
        for (int i = 0; i < proveedoresArr.length; i++) {
            correosProveedores[i] = (String) proveedoresArr[i].getCorreo(); // Supongo que existe un método getCorreo en la clase Proveedor
        }
        return correosProveedores;
    }

    /*
    // Función para verificar si el usuario existe en la base de datos por correo
    public boolean usuarioExisteBD(String correo) {
        try {
            // Intenta obtener un usuario por su correo utilizando el EntityManager existente
            Usuario usuario = em.find(Usuario.class, correo);

            // Si el usuario no es nulo, significa que existe en la base de datos
            return usuario != null;
        } catch (Exception e) {
            // Manejar excepciones si es necesario
            return false; // O retorna false en caso de error
        }
    }

    // Función para verificar si el usuario existe en la base de datos por nickname
    public boolean usuarioExisteBDPorNick(String nick) {
        try {
            // Intenta obtener un usuario por su nickname utilizando el EntityManager existente
            Usuario usuario = em.find(Usuario.class, nick);

            // Si el usuario no es nulo, significa que existe en la base de datos
            return usuario != null;
        } catch (Exception e) {
            // Manejar excepciones si es necesario
            return false; // O retorna false en caso de error
        }
    }
     */
    public String[] listarPaquetesArreglo() {
        Collection<Paquete> paquetesjovani = Paquete.paquetes.values();
        String[] paq = new String[paquetesjovani.size()];
        int i = 0;
        for (Paquete p : paquetesjovani) {
            paq[i] = p.getNombre_paquete();
            i++;
        }
        return paq;
    }

    public String[] listarPaquetesActArreglo(String actnombre) {
        ActividadTuristica actur = this.selectActividad(actnombre);
        Collection<Paquete> paquetes = Paquete.paquetes.values();
        Collection<ActividadTuristica> ActsPaquete;
        String[] paq = new String[paquetes.size()];
        int i = 0;
        int j = 0;
        for (Paquete p : paquetes) {
            ActsPaquete = p.getActTuristica();
            for (ActividadTuristica act : ActsPaquete) {
                if (act == actur) {
                    paq[j] = p.getNombre_paquete();
                    j++;
                }
            }
            i++;
        }
        return paq;
    }

    public void modificarDatosUsuario(String mail, String nuevoNombre, String nuevoApellido, LocalDate nuevaFechaNacimiento) throws UsuarioNoExisteException {
        // Obtén el usuario directamente de la base de datos
        Usuario usuario = Usuario.obtenerUsuario(mail); // Asegúrate de obtener el EntityManager de manera adecuada

        if (usuario == null) {
            throw new UsuarioNoExisteException("El usuario con el correo electrónico " + mail + " no existe");
        }

        // Actualiza los datos del usuario
        usuario.setNombre(nuevoNombre);
        usuario.setApellido(nuevoApellido);
        usuario.setFecha(nuevaFechaNacimiento);

        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            // Maneja la excepción apropiadamente
        }

        // No es necesario actualizar los mapas en este punto, ya que los datos en la base de datos se han actualizado correctamente.
    }

    /*
    public boolean DepartamentoExisteBD(String nombre) {
        try {
            Departamento depto = em.find(Departamento.class, nombre);
            return depto != null;
        } catch (Exception d) {
            return false;
        }
    }*/
    public boolean confirmarCreacionPaquete(String nombre, String descripcion, int validez, int descuento, LocalDate alta) {
        Paquete nuevoPaquete = new Paquete(nombre, descripcion, descuento, validez, alta);
        Paquete.paquetes.put(nombre, nuevoPaquete);
        try {
            em.getTransaction().begin();
            em.persist(nuevoPaquete);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception e) {
        }
        return true;
    }

    /*
    public boolean crearPaquete(String nombre, String descripcion, int validez, int descuento, LocalDate alta) {

        if (selectPaquete(nombre) != null) {
            return false;
        } else {
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.descuento = descuento;
            this.validez = validez;
            this.alta = alta;

            return true;
        }

    }*/
    public boolean AgregarActividadPaquete(ActividadTuristica act, Paquete paq) {
        paq.addActividad(act);
        //System.out.print(act.getNombre()+paq.getNombre_paquete());
        try {
            em.getTransaction().begin();
            em.persist(paq);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception e) {
        }
        return true;
    }

    public void getObjectsFromDB() {

        em.getTransaction().begin();
        //PROVEEDOR
        List<Proveedor> proveequery = em.createQuery("SELECT p FROM Proveedor p", Proveedor.class).getResultList();

        for (Proveedor p : proveequery) {
            Proveedor.addUsuario(p);
        }
        //TURISTA
        List<Turista> turquery = em.createQuery("SELECT t FROM Turista t", Turista.class).getResultList();
        for (Turista t : turquery) {
            Turista.addUsuario(t);
        }

        // DEPARTAMENTOS
        List<Departamento> deptosquery = em.createQuery("SELECT d FROM Departamento d", Departamento.class).getResultList();
        for (Departamento d : deptosquery) {
            //AltaDepartamento(d.getNombreDepartamento(),d.getDescripcion()   , d.getWeb());
            Departamento.departamentos.put(d.getNombreDepartamento(), d);
            // Realiza una consulta para obtener las actividades asociadas a este departamento
            for (ActividadTuristica act : d.getActTuristica()) {
                TypedQuery<SalidasTuristicas> salidasQuery = em.createQuery(
                        "SELECT s FROM SalidasTuristicas s WHERE s.actividadAsociada = :act", SalidasTuristicas.class);
                salidasQuery.setParameter("act", act);
                List<SalidasTuristicas> actividades = salidasQuery.getResultList();

                List<Paquete> paqquery = em.createQuery("SELECT paq FROM Paquete paq", Paquete.class).getResultList();

                for (Paquete p : paqquery) {
                    Paquete.paquetes.put(p.getNombre_paquete(), p);
                }
                //PAQUETES

                // Aquí puedes hacer lo que necesites con la lista de actividades obtenidas
                // Por ejemplo, puedes agregarlas al departamento o realizar alguna otra operación.
                // Luego, puedes utilizar las actividades como sea necesario, por ejemplo, agregarlas al departamento:
                //  d.getActTuristica().addAll(actividades);
            }

        }

        em.getTransaction().commit();

//entityManager.close();
    }

    public boolean existePaquete(String nombre) {
        return (selectPaquete(nombre) != null);
    }

    public void modificarPaquete(String nombre, String descripcion, int validez, int descuento, LocalDate alta) {

        Paquete paq = selectPaquete(nombre);
        paq.setDescri(descripcion);
        paq.setValidez(validez);
        paq.setDescuento(descuento);
        paq.setAlta(alta);
    }

    public void cargarDatosPrueba() throws UsuarioRepetidoException {
        registrarUsuario("lachiqui", "Rosa María", "Martínez", "mirtha.legrand.ok@hotmail.com.ar", LocalDate.parse("1927-02-23"), "turista", "argentina", null, null, null);
        registrarUsuario("isabelita", "Elizabeth", "Windsor", "isabelita@thecrown.co.uk", LocalDate.parse("1926-04-21"), "turista", "argentina", null, null, null);
        registrarUsuario("anibal", "Aníbal", "Lecter", "anibal@fing.edu.uy", LocalDate.parse("1937-12-31"), "turista", "lituania", null, null, null);
        registrarUsuario("waston", "Emma", "Waston", "e.waston@gmail.com", LocalDate.parse("1990-04-15"), "turista", "inglesa", null, null, null);
        registrarUsuario("elelvis", "Elvis", "Lacio", "suavemente@hotmail.com", LocalDate.parse("1971-07-30"), "turista", "estadounidense", null, null, null);
        registrarUsuario("eleven11", "Eleven", "Once", "eleven11@gmail.com", LocalDate.parse("2004-02-19"), "turista", "española", null, null, null);
        registrarUsuario("bobesponja", "Bob", "Esponja", "bobesponja@nickelodeon.com", LocalDate.parse("1999-05-01"), "turista", "japonesa", null, null, null);
        registrarUsuario("tony", "Antonio", "Pacheco", "eltony@manya.org.uy", LocalDate.parse("1976-04-11"), "turista", "uruguaya", null, null, null);
        registrarUsuario("chino", "Álvaro", "Recoba", "chino@trico.org.uy", LocalDate.parse("1976-03-17"), "turista", "uruguaya", null, null, null);
        registrarUsuario("mastropiero", "Johann Sebastian", "Mastropiero", "johann.sebastian@gmail.com", LocalDate.parse("1922-02-07"), "turista", "austriaca", null, null, null);
        registrarUsuario("washington", "Washington", "Rocha", "washington@turismorocha.gub.uy", LocalDate.parse("1970-09-14"), "proveedor", "uruguayo", "Hola! me llamo Washington y soy el encargado del portal de turismo del departamento de Rocha Uruguay", "http://turismorocha.gub.uy/", null);
        registrarUsuario("eldiez", "Pablo", "Bengoechea", "eldiez@socfomturriv.org.uy", LocalDate.parse("1965-06-27"), "proveedor", "uruguayo", "Pablo es el presidente de la Sociedad de Fomento Turıstico de Rivera (conocida como Socfomturriv)", "http://wwww.socfomturriv.org.uy", null);
        registrarUsuario("meche", "Mercedes", "Venn", "meche@colonia.gub.uy", LocalDate.parse("1990-12-31"), "proveedor", "uruguayo", "Departamento de Turismo del Departamento de Colonia", "https://colonia.gub.uy/turismo/", null);

        // Completar la carga de datos de departamentos
        AltaDepartamento("Canelones", "División Turismo de la Intendencia", "https://www.imcanelones.gub.uy/es");
        AltaDepartamento("Maldonado", "División Turismo de la Intendencia", "https://www.maldonado.gub.uy/");
        AltaDepartamento("Rocha", "La Organización de Gestión del Destino (OGD) Rocha es un ámbito de articulación público – privada en el sector turístico que integran la Corporación Rochense de Turismo y la Intendencia de Rocha a través de su Dirección de Turismo.", "www.turismorocha.gub.uy");
        AltaDepartamento("Treinta y Tres", "División Turismo de la Intendencia", "https://treintaytres.gub.uy/");
        AltaDepartamento("Cerro Largo", "División Turismo de la Intendencia", "https://www.gub.uy/intendencia-cerro-largo/");
        AltaDepartamento("Rivera", "Promociona e implementa proyectos e iniciativas sostenibles de interés turístico con la participación institucional público – privada en bien del desarrollo socioeconómico de la comunidad.", "www.rivera.gub.uy/social/turismo/");
        AltaDepartamento("Artigas", "División Turismo de la Intendencia", "http://www.artigas.gub.uy");
        AltaDepartamento("Salto", "División Turismo de la Intendencia", "https://www.salto.gub.uy");
        AltaDepartamento("Paysandú", "División Turismo de la Intendencia", "https://www.paysandu.gub.uy");
        AltaDepartamento("Río Negro", "División Turismo de la Intendencia", "https://www.rionegro.gub.uy");
        AltaDepartamento("Soriano", "División Turismo de la Intendencia", "https://www.soriano.gub.uy");
        AltaDepartamento("Colonia", "La propuesta del Departamento de Colonia divide en cuatro actos su espectáculo anual. Cada acto tiene su magia. Desde su naturaleza y playas hasta sus tradiciones y el patrimonio mundial. Todo el año se disfruta.", "https://colonia.gub.uy/turismo/");
        AltaDepartamento("San José", "División Turismo de la Intendencia", "https://sanjose.gub.uy");
        AltaDepartamento("Flores", "División Turismo de la Intendencia", "https://flores.gub.uy");
        AltaDepartamento("Florida", "División Turismo de la Intendencia", "http://www.florida.gub.uy");
        AltaDepartamento("Lavalleja", "División Turismo de la Intendencia", "http://www.lavalleja.gub.uy");
        AltaDepartamento("Durazno", "División Turismo de la Intendencia", "https://durazno.uy");
        AltaDepartamento("Tacuarembó", "División Turismo de la Intendencia", "https://tacuarembo.gub.uy");
        AltaDepartamento("Montevideo", "División Turismo de la Intendencia", "https://montevideo.gub.uy/areas-tematicas/turismo");

        AltaActividadTuristica("washington@turismorocha.gub.uy", "Degusta", "Festival gastronómico de productos locales en Rocha", 3, 800.0, "Rocha", LocalDate.of(2022, 07, 20), "Rocha");
        AltaActividadTuristica("washington@turismorocha.gub.uy", "Teatro con Sabores", "En el mes aniversario del Club Deportivo Unión de Rocha te invitamos a una merienda deliciosa.", 3, 500.0, "Rocha", LocalDate.of(2022, 07, 21), "Rocha");
        AltaActividadTuristica("meche@colonia.gub.uy", "Tour por Colonia del Sacramento", "Con guía especializado y en varios idiomas. Varios circuitos posibles.", 2, 400.0, "Colonia", LocalDate.of(2022, 8, 1), "Colonia del Sacramento");
        AltaActividadTuristica("meche@colonia.gub.uy", "Almuerzo en el Real de San Carlos", "Restaurante en la renovada Plaza de Toros con menú internacional", 2, 800.0, "Colonia", LocalDate.of(2022, 8, 1), "Colonia del Sacramento");
        AltaActividadTuristica("eldiez@socfomturriv.org.uy", "Almuerzo en Valle del Lunarejo", "Almuerzo en la Posada con ticket fijo. Menú que incluye bebida y postre casero.", 2, 300.0, "Rivera", LocalDate.of(2022, 8, 1), "Tranqueras");
        AltaActividadTuristica("eldiez@socfomturriv.org.uy", "Cabalgata en Valle del Lunarejo", "Cabalgata por el área protegida. Varios recorridos para elegir.", 2, 150.0, "Rivera", LocalDate.of(2022, 8, 1), "Tranqueras");

// Seleccionar actividad por nombre antes de agregar salidas
        selectActividad("Degusta");
        AltaSalidaTuristica("washington@turismorocha.gub.uy", "Degusta Agosto", "Sociedad Agropecuaria de Rocha", 20, LocalDate.of(2022, 8, 20), LocalDate.of(2022, 7, 21));
        confirmarAltaSalida();

        selectActividad("Degusta");
        AltaSalidaTuristica("washington@turismorocha.gub.uy", "Degusta Setiembre", "Sociedad Agropecuaria de Rocha", 20, LocalDate.of(2022, 9, 3), LocalDate.of(2022, 7, 22));
        confirmarAltaSalida();

        selectActividad("Teatro con Sabores");
        AltaSalidaTuristica("washington@turismorocha.gub.uy", "Teatro con Sabores 1", "Club Deportivo Union", 30, LocalDate.of(2022, 9, 4), LocalDate.of(2022, 7, 23));
        confirmarAltaSalida();

        selectActividad("Teatro con Sabores");
        AltaSalidaTuristica("washington@turismorocha.gub.uy", "Teatro con Sabores 2", "Club Deportivo Union", 30, LocalDate.of(2022, 9, 11), LocalDate.of(2022, 7, 22));
        confirmarAltaSalida();

        selectActividad("Tour por Colonia del Sacramento");
        AltaSalidaTuristica("meche@colonia.gub.uy", "Tour Colonia del Sacramento 11-09", "Encuentro en la base del Faro", 5, LocalDate.of(2022, 9, 11), LocalDate.of(2022, 8, 5));
        confirmarAltaSalida();

        selectActividad("Tour por Colonia del Sacramento");
        AltaSalidaTuristica("meche@colonia.gub.uy", "Tour Colonia del Sacramento 18-09", "Encuentro en la base del Faro", 5, LocalDate.of(2022, 9, 18), LocalDate.of(2022, 8, 5));
        confirmarAltaSalida();

        selectActividad("Almuerzo en el Real de San Carlos");
        AltaSalidaTuristica("eldiez@socfomturriv.org.uy", "Almuerzo 1", "Restaurante de la Plaza de Toros", 5, LocalDate.of(2022, 9, 18), LocalDate.of(2022, 8, 4));
        confirmarAltaSalida();

        selectActividad("Almuerzo en el Real de San Carlos");
        AltaSalidaTuristica("eldiez@socfomturriv.org.uy", "Almuerzo 2", "Restaurante de la Plaza de Toros", 5, LocalDate.of(2022, 9, 25), LocalDate.of(2022, 8, 4));
        confirmarAltaSalida();

        selectActividad("Almuerzo en Valle del Lunarejo");
        AltaSalidaTuristica("eldiez@socfomturriv.org.uy", "Almuerzo 3", "Posada Del Lunarejo", 4, LocalDate.of(2022, 9, 10), LocalDate.of(2022, 8, 15));
        confirmarAltaSalida();

        selectActividad("Almuerzo en Valle del Lunarejo");
        AltaSalidaTuristica("eldiez@socfomturriv.org.uy", "Almuerzo 4", "Posada Del Lunarejo", 4, LocalDate.of(2022, 9, 11), LocalDate.of(2022, 8, 15));
        confirmarAltaSalida();

        selectActividad("Cabalgata en Valle del Lunarejo");
        AltaSalidaTuristica("eldiez@socfomturriv.org.uy", "Cabalgata 1", "Posada Del Lunarejo", 4, LocalDate.of(2022, 9, 10), LocalDate.of(2022, 8, 15));
        confirmarAltaSalida();

        selectActividad("Cabalgata en Valle del Lunarejo");
        AltaSalidaTuristica("eldiez@socfomturriv.org.uy", "Cabalgata 2", "Posada Del Lunarejo", 4, LocalDate.of(2022, 9, 11), LocalDate.of(2022, 8, 15));
        confirmarAltaSalida();

        // Inscripciones
        inscripcionSalida("mirtha.legrand.ok@hotmail.com.ar", "Degusta Agosto", 3, LocalDate.of(2022, 8, 15));
        confirmarInscripcion(3, 2400);

        inscripcionSalida("suavemente@hotmail.com", "Degusta Agosto", 5, LocalDate.of(2022, 8, 16));
        confirmarInscripcion(5, 4000);

        inscripcionSalida("mirtha.legrand.ok@hotmail.com.ar", "Tour Colonia del Sacramento 18-09", 3, LocalDate.of(2022, 8, 18));
        confirmarInscripcion(3, 1200);

        inscripcionSalida("isabelita@thecrown.co.uk", "Tour Colonia del Sacramento 18-09", 1, LocalDate.of(2022, 8, 19));
        confirmarInscripcion(1, 400);

        inscripcionSalida("johann.sebastian@gmail.com", "Almuerzo 2", 2, LocalDate.of(2022, 8, 19));
        confirmarInscripcion(2, 1600);

        inscripcionSalida("chino@trico.org.uy", "Teatro con Sabores 1", 1, LocalDate.of(2022, 8, 19));
        confirmarInscripcion(1, 500);

        inscripcionSalida("chino@trico.org.uy", "Teatro con Sabores 2", 10, LocalDate.of(2022, 8, 20));
        confirmarInscripcion(10, 5000);

        inscripcionSalida("bobesponja@nickelodeon.com", "Teatro con Sabores 2", 2, LocalDate.of(2022, 8, 20));
        confirmarInscripcion(2, 1000);

        inscripcionSalida("anibal@fing.edu.uy", "Teatro con Sabores 2", 1, LocalDate.of(2022, 8, 21));
        confirmarInscripcion(1, 500);

        inscripcionSalida("eltony@manya.org.uy", "Degusta Setiembre", 11, LocalDate.of(2022, 8, 21));
        confirmarInscripcion(11, 8800);
// Ingresar paquetes
// Ingresar paquetes
        confirmarCreacionPaquete("Disfrutar Rocha", "Actividades para hacer en familia y disfrutar arte y gastronomía", 60, 20, LocalDate.of(2022, 8, 10));

        confirmarCreacionPaquete("Un día en Colonia", "Paseos por el casco histórico y se puede terminar con Almuerzo en la Plaza de Toros", 45, 15, LocalDate.of(2022, 8, 1));

        AgregarActividadPaquete(selectActividad("Degusta"), selectPaquete("Disfrutar Rocha"));
        AgregarActividadPaquete(selectActividad("Teatro con Sabores"), selectPaquete("Disfrutar Rocha"));

        AgregarActividadPaquete(selectActividad("Tour por Colonia del Sacramento"), selectPaquete("Un día en Colonia"));
        AgregarActividadPaquete(selectActividad("Almuerzo en el Real de San Carlos"), selectPaquete("Un día en Colonia"));
    }

    public void AltaSalidaTuristicaDepto(String nombre, String lugar, int cantidad, LocalDate fecSal, LocalDate alta, String nombreAct, String nombreDepto) {
        ActividadTuristica actividadSel = this.selectActividad(nombreAct);
        //Departamento dept = this.selectDepartamento(nombreDepto);
        //dept.addActTurisica(actividadSel);
        SalidasTuristicas nuevaS = actividadSel.crearSalida(nombre, lugar, cantidad, fecSal, alta);
        em.getTransaction().begin();
        em.merge(nuevaS);
        em.merge(actividadSel);
        em.getTransaction().commit();
    }

    public ActividadTuristica[] listarActPaquete(Paquete paquete) {
        Collection<ActividadTuristica> actividadesdePaquete = new ArrayList<>();
        actividadesdePaquete = paquete.getActTuristica();
        ActividadTuristica[] acts = new ActividadTuristica[actividadesdePaquete.size()];
        int i = 0;
        for (ActividadTuristica a : actividadesdePaquete) {
            acts[i] = a;
            i++;
        }
        return acts;

    }

    public String[] listarActividadesProveedor(Proveedor proveedor) {
        int i = 0;
        String[] actsarr = new String[proveedor.getActTuristica().size()];
        for (ActividadTuristica actividad : proveedor.getActTuristica()) {
            actsarr[i] = actividad.getNombre();
            i++;
        }
        return actsarr;
    }
}
