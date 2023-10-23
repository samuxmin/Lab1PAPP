package controladores;

import datatypes.DataActividad;
import datatypes.DataPaquete;
import datatypes.DataSalida;
import datatypes.DataUsuario;
import logica.Usuario;
import java.time.LocalDate;
import excepciones.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Proveedor;
import logica.Turista;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import logica.Departamento;
import logica.Inscripcion_general;
import logica.Paquete;
import logica.SalidasTuristicas;
import logica.ActividadTuristica;
import logica.Categoria;

import logica.EstadoActividad;
import logica.Inscripcion_paquete;
import logica.Usuario_;

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
    LocalTime hora;
    String Imagen;
    Proveedor prov;

    public Sistema() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab1G2PU");
        em = emf.createEntityManager();
        //getObjectsFromDB();
    }

    public EntityManager getEntityManager() {
        return em;
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

        Usuario u = getUsuarioDB(mail);
        if (u != null) {
            return u.devolverData();
        } else {
            throw new UsuarioNoExisteException("El usuario " + mail + " no existe");
        }

    }

    @Override
    public DataUsuario[] getUsuarios() throws UsuarioNoExisteException {
        getAllUsersFromBD();
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
        getAllUsersFromBD();
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
        getAllUsersFromBD();
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
        Paquete paq = getPaqFromBD(nombrePaquete);
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
        String q = "SELECT act.* FROM ACTIVIDADTURISTICA act "
                + "WHERE act.departamento_nombre = ? "
                + "AND act.nombre NOT IN "
                + "(SELECT atp.Act_nombre FROM ActividadTuristica_Paquete atp "
                + "WHERE atp.Paquete_nombre = ?)";

        Query query = em.createNativeQuery(q, ActividadTuristica.class);
        query.setParameter(1, nombreDepto);
        query.setParameter(2, nombre_paquete);

        List<ActividadTuristica> actividadesNoEnPaquete = query.getResultList();

        return actividadesNoEnPaquete.toArray(new ActividadTuristica[0]);
    }

    public Departamento selectDepartamento(String nombreDepartamento) {
        deptoSeleccionado = Departamento.encontrarDepto(nombreDepartamento);
        return deptoSeleccionado;
    }

    public DataPaquete[] listarPaquetes() {
        Collection<Paquete> paquetesjovani = getAllPaqsFromBD();
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
        actividadSeleccionada = selectActFromDB(nombre);
        return actividadSeleccionada;
    }

    public ActividadTuristica selectActividadBD(String nombre) {
        ActividadTuristica act = em.find(ActividadTuristica.class, nombre);
        return act;
    }

    // UNION FUNCS
    public DataActividad[] listarActividadesDepto(String nombreDpto) {

        deptoSeleccionado = Departamento.encontrarDepto(nombreDpto);

        List<ActividadTuristica> actividades = getAllActsFromBD();
        DataActividad[] dataActividades = new DataActividad[actividades.size()];
        int i = 0;
        for (ActividadTuristica act : actividades) {
            dataActividades[i] = act.devolverData();
            i++;
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
        SalidasTuristicas s = getSalidaFromBD(salida);

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
        getAllUsersFromBD();
        Turista[] turistasArr = Turista.getTuristas();
        DataUsuario[] dataTurs = new DataUsuario[turistasArr.length];
        for (int i = 0; i < turistasArr.length; i++) {
            dataTurs[i] = turistasArr[i].devolverData();
        }
        return dataTurs;
    }

    public boolean estaInscritoTuristaSalida(String mailTurista, String nombreSalida) {
        Query query = em.createNativeQuery("SELECT COUNT(*) FROM INSCRIPCION_GENERAL i JOIN turista_inscripcion_general ti where i.ID = ti.nscripcion_general_id and i.salidaInscripcion = ? and iturista_correo = ?");
        query.setParameter(1, nombreSalida);
        query.setParameter(2, mailTurista);
        Long count = (Long) query.getSingleResult();

        return (count > 0);
    }

    public boolean excedeLimiteInscripcion(String nombreSalida, int cantTurista) {

        salidaSeleccionada = actividadSeleccionada.getSalidaByNombre(nombreSalida);
        return (salidaSeleccionada.getCantInscritos() + cantTurista > salidaSeleccionada.getCantidadMaximaTuristas());
    }

    public SalidasTuristicas getSalidaByNombre(String nombreSalida) {

        return getSalidaFromBD(nombreSalida);

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

    public void confirmarInscripcion(int cantTurista, double costogral) {
        Collection<Turista> turistas = new ArrayList<>();
        turistas.add(turistaSeleccionado);

        String jpql = "SELECT MAX(ig.id) FROM Inscripcion_general ig";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        Long maxId = query.getSingleResult();

// Paso 3: Calcular el nuevo ID
        long nuevoId = 1;
        if (maxId != null) {
            nuevoId = maxId + 1;
        }

        Inscripcion_general nuevaInscripcion = new Inscripcion_general(nuevoId, cantTurista, costogral, salidaSeleccionada, turistas);
        nuevaInscripcion.setFechaCompra(alta);
        salidaSeleccionada.agregarInscripcionGral(nuevaInscripcion);
        salidaSeleccionada.aumentarCantInscritos(cantTurista);

        if (turistaSeleccionado != null) {
            turistaSeleccionado.agregarInscripcionGral(nuevaInscripcion);  // Llamar a la función para cada turista en la colección
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

    public boolean AltaActividadTuristica(String correoProveedor, String nombreAct, String descripcion, Integer duracion, double costo, String depto, LocalDate fechaA, String ciudad, ArrayList<String> eleccionesCategoria, String imagen) {

        Usuario u = Usuario.obtenerUsuario(correoProveedor);
        boolean flag = false;
        if (u != null) {
            if (u instanceof Proveedor) {
                Proveedor proveedor = (Proveedor) u;
                Collection<ActividadTuristica> act = proveedor.getActTuristica();
                /*for (ActividadTuristica actividad : act) {
                    if (actividad.getNombre() == nombreAct) {//castea la coleccion de actividades turisticas para conseguir una actividad y comparar su nombre
                        flag = true;
                    }
                }*/
                
                Query query = em.createNativeQuery("SELECT NOMBRE FROM ACTIVIDADTURISTICA WHERE NOMBRE = ?");
                query.setParameter(1, nombreAct);

                // Obtiene el resultado de la consulta SQL
                List<String> estaACT = query.getResultList();
                if (!query.getResultList().isEmpty()) {
                    flag = true;
                }

                if (!flag) {//Si no encontro otra actividad en la coleccion con el mismo nombre lo añade
                    ActividadTuristica nuevaAct = new ActividadTuristica(nombreAct, descripcion, duracion, costo, ciudad, fechaA, imagen);

                    proveedor.addActTuristica(nuevaAct);//Lo añade a la coleccion de actividades turisticas (creo)
                    Departamento dep = selectDepartamento(depto);
                    //this.listarActividadesDeptoArreglo(depto);
                    dep.addActTurisica(nuevaAct);
                    nuevaAct.setProveedor(proveedor);
                    proveedor.addActTuristica(nuevaAct);
                    nuevaAct.setDepartamento(dep);

                    Set<Categoria> categoriasSeleccionadas = new HashSet<>();
                    for (int i = 0; i < eleccionesCategoria.size(); i++) {
                        String categoria = eleccionesCategoria.get(i);
                        Categoria cat = Categoria.obtenerCategoria(categoria);
                        nuevaAct.addCategoria(cat);

                    }

                    try {
                        em.getTransaction().begin();
                        em.merge(nuevaAct);
                        //em.merge(deptoSeleccionado);
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
        prov = (Proveedor) getUsuarioDB(correoProveedor);
        nombre = nombreSalida;
        this.descripcion = lugar;
        this.alta = fechaAlta;
        this.fechaSalida = fechaSalida;
        this.cantidad = cantTuristas;
    }

    public void confirmarAltaSalida() {
        SalidasTuristicas nuevaS = actividadSeleccionada.crearSalida(nombre, descripcion, cantidad, alta, fechaSalida, null, null);

        try {
            em.getTransaction().begin();
            em.merge(nuevaS);
            em.merge(actividadSeleccionada);
            em.getTransaction().commit();
        } catch (Exception e) {

        }
    }

    public void AltaSalidaTuristica2(String correoProveedor, String nombreSalida, String lugar, Integer cantTuristas, LocalDate fechaSalida, LocalDate fechaAlta, String Imagen, LocalTime hora) {

        nombre = nombreSalida;
        this.descripcion = lugar;
        this.alta = fechaAlta;
        this.fechaSalida = fechaSalida;
        this.cantidad = cantTuristas;
        this.hora = hora;
        this.Imagen = Imagen;
    }

    public void confirmarAltaSalida2() {

        SalidasTuristicas nuevaS = actividadSeleccionada.crearSalida(nombre, descripcion, cantidad, alta, fechaSalida, Imagen, hora);
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
        getAllUsersFromBD();
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
    public void registrarUsuario(String nick, String name, String ap, String mail, LocalDate fecNac, String tipoUsuario, String nacionalidad, String descripcion, String web, String imagenPerfil, String password) throws UsuarioRepetidoException {
        getAllUsersFromBD();
        Usuario u = Usuario.obtenerUsuario(mail);
        if (u != null) {
            throw new UsuarioRepetidoException("El usuario con el correo electrónico " + mail + " ya está registrado");
        } else if (Usuario.obtenerUsuarioPorNick(nick) != null) {
            throw new UsuarioRepetidoException("El nickname " + nick + " ya está en uso");
        } else {

            //Usuario nuevoUsuario = new Usuario(nick, name, ap, mail, fecNac, imagenPerfil, password);

            try {
                em.getTransaction().begin();
                String q = "INSERT INTO USUARIO(CORREO, APELLIDO, FECHANACIMIENTO,IMAGENPERFIL,NICKNAME,NOMBRE,PASSWORD) VALUES(?,?,?,?,?,?,?)";
                Query query = em.createNativeQuery(q);
                query.setParameter(1, mail);
                query.setParameter(2, ap);
                query.setParameter(3, fecNac);
                query.setParameter(4, imagenPerfil);
                query.setParameter(5, nick);
                query.setParameter(6, name);
                query.setParameter(7, password);
                query.executeUpdate();
                em.getTransaction().commit();
            } catch (Exception e) {
            }

            if (tipoUsuario.equals("turista") || tipoUsuario.equals("Turista")) {
                Turista turista = new Turista(nick, name, ap, mail, fecNac, nacionalidad, imagenPerfil, password);
                
                Usuario.addUsuario(turista);

                try {
                    em.getTransaction().begin();
                    em.persist(turista);
                    em.getTransaction().commit();
                } catch (Exception e) {
                }

            } else if (tipoUsuario.equals("proveedor") || tipoUsuario.equals("Proveedor")) {
                Proveedor proveedor = new Proveedor(nick, name, ap, mail, fecNac, descripcion, web, imagenPerfil, password);
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

    /* public String[] listarCorreosProveedores() {
        Proveedor[] proveedoresArr = Proveedor.getProveedores();
        String[] correosProveedores = new String[proveedoresArr.length];
        for (int i = 0; i < proveedoresArr.length; i++) {
            correosProveedores[i] = (String) proveedoresArr[i].getCorreo(); // Supongo que existe un método getCorreo en la clase Proveedor
        }
        return correosProveedores;
    }*/
    public String[] listarCorreosProveedores() {
        String[] correosP = null;

        // Modifica la consulta JPQL para obtener directamente los correos
        List<String> correosQuery = em.createQuery("SELECT p.correo FROM Proveedor p", String.class)
                .getResultList();

        correosP = correosQuery.toArray(new String[0]);

        return correosP;
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
    public boolean confirmarCreacionPaquete(String nombre, String descripcion, int validez, int descuento, LocalDate alta, String imagen) {
        Paquete nuevoPaquete = new Paquete(nombre, descripcion, descuento, validez, alta, imagen);
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
        Query query = em.createNativeQuery("SELECT nombre_categoria FROM Actividad_Categoria WHERE nombre_actividad = ?");
        query.setParameter(1, act.getNombre());

        // Obtiene el resultado de la consulta SQL
        List<String> nombreCategoria = query.getResultList();
        for (int i = 0; i < nombreCategoria.size(); i++) {
            Categoria cat = new Categoria(nombreCategoria.get(i));

            Query query2 = em.createNativeQuery("SELECT nombre_categoria FROM Paquete_Categoria WHERE nombre_categoria = ? && nombre_paquete = ?");
            query2.setParameter(1, cat.getNombreCat());
            query2.setParameter(2, paq.getNombre_paquete());

            // Obtén la lista de resultados de la consulta SQL
            List<String> resultados = query2.getResultList();

            // Verifica si la categoría ya existe antes de agregarla al paquete
            if (resultados.isEmpty()) {
                paq.addCategoria(cat);
            }
        }
        // Encuentra la entidad Categoria por su nombre

        paq.addActividad(act);

        try {
            em.getTransaction().begin();
            em.persist(paq);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception e) {
            // Maneja la excepción de manera adecuada, por ejemplo, registrando un mensaje de error o tomando una acción alternativa.
            e.printStackTrace();
            return false; // Retorna false para indicar que ocurrió un error.
        }
        return true;
    }

    public void getAllUsersFromBD() {
        em.getTransaction().begin();
        Usuario.usuariosMail.clear();
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

        em.getTransaction().commit();

    }

    public void getObjectsFromDB() {

        em.getTransaction().begin();

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
        registrarUsuario("lachiqui", "Rosa María", "Martínez", "mirtha.legrand.ok@hotmail.com.ar", LocalDate.parse("1927-02-23"), "turista", "argentina", null, null, "imagenes_perfil/lachiqui.jpeg", "123");
        registrarUsuario("isabelita", "Elizabeth", "Windsor", "isabelita@thecrown.co.uk", LocalDate.parse("1926-04-21"), "turista", "argentina", null, null, "imagenes_perfil/isabelita.webp", "123");
        registrarUsuario("anibal", "Aníbal", "Lecter", "anibal@fing.edu.uy", LocalDate.parse("1937-12-31"), "turista", "lituania", null, null, "imagenes_perfil/anibal.webp", "123");
        registrarUsuario("waston", "Emma", "Waston", "e.waston@gmail.com", LocalDate.parse("1990-04-15"), "turista", "inglesa", null, null, "imagenes_perfil/waston.jpg", "123");
        registrarUsuario("elelvis", "Elvis", "Lacio", "suavemente@hotmail.com", LocalDate.parse("1971-07-30"), "turista", "estadounidense", null, null, "imagenes_perfil/elelvis.jpeg", "123");
        registrarUsuario("eleven11", "Eleven", "Once", "eleven11@gmail.com", LocalDate.parse("2004-02-19"), "turista", "española", null, null, "imagenes_perfil/eleven11.jpg", "123");
        registrarUsuario("bobesponja", "Bob", "Esponja", "bobesponja@nickelodeon.com", LocalDate.parse("1999-05-01"), "turista", "japonesa", null, null, "imagenes_perfil/bobesponja.jpg", "123");
        registrarUsuario("tony", "Antonio", "Pacheco", "eltony@manya.org.uy", LocalDate.parse("1976-04-11"), "turista", "uruguaya", null, null, "imagenes_perfil/tony.jpg", "123");
        registrarUsuario("chino", "Álvaro", "Recoba", "chino@trico.org.uy", LocalDate.parse("1976-03-17"), "turista", "uruguaya", null, null, "imagenes_perfil/chino.jpg", "123");
        registrarUsuario("mastropiero", "Johann Sebastian", "Mastropiero", "johann.sebastian@gmail.com", LocalDate.parse("1922-02-07"), "turista", "austriaca", null, null, "imagenes_perfil/mastropiero.png", "123");
        registrarUsuario("washington", "Washington", "Rocha", "washington@turismorocha.gub.uy", LocalDate.parse("1970-09-14"), "proveedor", "uruguayo", "Hola! me llamo Washington y soy el encargado del portal de turismo del departamento de Rocha Uruguay", "http://turismorocha.gub.uy/", "imagenes_perfil/washington.jpeg", "123");
        registrarUsuario("eldiez", "Pablo", "Bengoechea", "eldiez@socfomturriv.org.uy", LocalDate.parse("1965-06-27"), "proveedor", "uruguayo", "Pablo es el presidente de la Sociedad de Fomento Turıstico de Rivera (conocida como Socfomturriv)", "http://wwww.socfomturriv.org.uy", "imagenes_perfil/eldiez.jpeg", "123");
        registrarUsuario("meche", "Mercedes", "Venn", "meche@colonia.gub.uy", LocalDate.parse("1990-12-31"), "proveedor", "uruguayo", "Departamento de Turismo del Departamento de Colonia", "https://colonia.gub.uy/turismo/", "imagenes_perfil/meche.jpeg", "123");
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

        ArrayList<String> cat = new ArrayList<>();

        registrarCategoria("Aventura y Deporte");
        registrarCategoria("Campo y Naturaleza");
        registrarCategoria("Cultura y Patrimonio");
        registrarCategoria("Gastronomia");
        registrarCategoria("Turismo Playas");
        //1     
        cat.add("Gastronomia");
        AltaActividadTuristica("washington@turismorocha.gub.uy", "Degusta", "Festival gastronómico de productos locales en Rocha", 3, 800.0, "Rocha", LocalDate.of(2022, 07, 20), "Rocha", cat, "imagenes_actividad/Degusta.jpg");
        cat.remove(0);
        //2     
        cat.add("Gastronomia");
        cat.add("Cultura y Patrimonio");
        AltaActividadTuristica("washington@turismorocha.gub.uy", "Teatro con Sabores", "En el mes aniversario del Club Deportivo Unión de Rocha te invitamos a una merienda deliciosa.", 3, 500.0, "Rocha", LocalDate.of(2022, 07, 21), "Rocha", cat, "imagenes_actividad/Teatro con Sabores.jpg");
        cat.remove(1);
        cat.remove(0);
        //3      
        cat.add("Cultura y Patrimonio");
        AltaActividadTuristica("meche@colonia.gub.uy", "Tour por Colonia del Sacramento", "Con guía especializado y en varios idiomas. Varios circuitos posibles.", 2, 400.0, "Colonia", LocalDate.of(2022, 8, 1), "Colonia del Sacramento", cat, "imagenes_actividad/Tour por Colonia del Sacramento.jpg");
        cat.remove(0);
        //4       
        cat.add("Gastronomia");
        AltaActividadTuristica("meche@colonia.gub.uy", "Almuerzo en el Real de San Carlos", "Restaurante en la renovada Plaza de Toros con menú internacional", 2, 800.0, "Colonia", LocalDate.of(2022, 8, 1), "Colonia del Sacramento", cat, "imagenes_actividad/Almuerzo en el Real de San Carlos.jpg");
        cat.remove(0);
        //5       
        cat.add("Cultura y Patrimonio");
        cat.add("Gastronomia");
        AltaActividadTuristica("eldiez@socfomturriv.org.uy", "Almuerzo en Valle del Lunarejo", "Almuerzo en la Posada con ticket fijo. Menú que incluye bebida y postre casero.", 2, 300.0, "Rivera", LocalDate.of(2022, 8, 1), "Tranqueras", cat, "imagenes_actividad/Almuerzo en Valle del Lunarejo.jpg");
        cat.remove(1);
        cat.remove(0);
        //6      
        cat.add("Campo y Naturaleza");
        AltaActividadTuristica("eldiez@socfomturriv.org.uy", "Cabalgata en Valle del Lunarejo", "Cabalgata por el área protegida. Varios recorridos para elegir.", 2, 150.0, "Rivera", LocalDate.of(2022, 8, 1), "Tranqueras", cat, "imagenes_actividad/Cabalgata en Valle del Lunarejo.jpg");
        cat.remove(0);
        //7       
        cat.add("Cultura y Patrimonio");
        AltaActividadTuristica("meche@colonia.gub.uy", "Bus turístico Colo-nia", "Recorrida por los principales atractivos de la ciudad", 3, 600.0, "Colonia", LocalDate.of(2022, 9, 1), "Colonia del Sacramento", cat, "imagenes_actividad/Bus turístico Colo-nia.jpg");
        cat.remove(0);
        //8            
        cat.add("Cultura y Patrimonio");
        AltaActividadTuristica("meche@colonia.gub.uy", "Colonia Premium Tour", "Visita lugares exclusivos y relevantes", 4, 2600.0, "Colonia", LocalDate.of(2022, 9, 3), "Colonia del Sacramento", cat, "imagenes_actividad/Colonia Premium Tour.jpg");
        cat.remove(0);
        //9     
        cat.add("Aventura y Deporte");
        cat.add("Turismo Playas");
        AltaActividadTuristica("washington@turismorocha.gub.uy", "Deportes náuticos sin uso de motor", "kitsurf - windsurf - kayakismo - canotaje en Rocha", 3, 1200.0, "Rocha", LocalDate.of(2022, 9, 5), "Rocha", cat, "imagenes_actividad/Deportes náuticos sin uso de motor.jpg");
        cat.remove(1);
        cat.remove(0);
        //10
        cat.add("Cultura y Patrimonio");
        cat.remove(0);
        AltaActividadTuristica("eldiez@socfomturriv.org.uy", "Descubre Rivera", "Rivera es un departamento de extraordinaria riqueza natural patrimonial y cultural con una ubi-cación geográfica privilegiada", 2, 650.0, "Rivera", LocalDate.of(2022, 9, 16), "Rivera", cat, "imagenes_actividad/Descubre Rivera.jpg");

        aceptarActividad("Degusta");
        aceptarActividad("Teatro con Sabores");
        aceptarActividad("Tour por Colonia del Sacramento");
        aceptarActividad("Almuerzo en el Real de San Carlos");
        aceptarActividad("Almuerzo en Valle del Lunarejo");
        aceptarActividad("Cabalgata en Valle del Lunarejo");

        //aceptarActividad("Bus turístico Colo-nia");
        rechazarActividad("Colonia Premium Tour");
        //aceptarActividad("Deportes náuticos sin uso de motor");
        rechazarActividad("Descubre Rivera");

// Seleccionar actividad por nombre antes de agregar salidas
        String vacio = null;
        selectActividad("Degusta");
        AltaSalidaTuristica2("washington@turismorocha.gub.uy", "Degusta Agosto", "Sociedad Agropecuaria de Rocha", 20, LocalDate.of(2022, 8, 20), LocalDate.of(2022, 7, 21), "imagenes_salidas/Degusta Agosto.jpg", LocalTime.of(17, 0));
        confirmarAltaSalida2();

        selectActividad("Degusta");
        AltaSalidaTuristica2("washington@turismorocha.gub.uy", "Degusta Setiembre", "Sociedad Agropecuaria de Rocha", 20, LocalDate.of(2022, 9, 3), LocalDate.of(2022, 7, 22), "imagenes_salidas/Degusta Setiembre.jpg", LocalTime.of(17, 0));
        confirmarAltaSalida2();

        selectActividad("Teatro con Sabores");
        AltaSalidaTuristica2("washington@turismorocha.gub.uy", "Teatro con Sabores 1", "Club Deportivo Union", 30, LocalDate.of(2022, 9, 4), LocalDate.of(2022, 7, 23), "imagenes_salidas/Teatro con Sabores 1.jpg", LocalTime.of(18, 0));
        confirmarAltaSalida2();

        selectActividad("Teatro con Sabores");
        AltaSalidaTuristica2("washington@turismorocha.gub.uy", "Teatro con Sabores 2", "Club Deportivo Union", 30, LocalDate.of(2022, 9, 11), LocalDate.of(2022, 7, 22), "imagenes_salidas/Teatro con Sabores 2.jpg", LocalTime.of(18, 0));
        confirmarAltaSalida2();

        selectActividad("Tour por Colonia del Sacramento");
        AltaSalidaTuristica2("meche@colonia.gub.uy", "Tour Colonia del Sacramento 11-09", "Encuentro en la base del Faro", 5, LocalDate.of(2022, 9, 11), LocalDate.of(2022, 8, 5), "imagenes_salidas/Tour Colonia del Sacramento 11-09.jpg", LocalTime.of(10, 0));
        confirmarAltaSalida2();

        selectActividad("Tour por Colonia del Sacramento");
        AltaSalidaTuristica2("meche@colonia.gub.uy", "Tour Colonia del Sacramento 18-09", "Encuentro en la base del Faro", 5, LocalDate.of(2022, 9, 18), LocalDate.of(2022, 8, 5), "imagenes_salidas/Tour Colonia del Sacramento 18-09.jpg", LocalTime.of(10, 0));
        confirmarAltaSalida2();

        selectActividad("Almuerzo en el Real de San Carlos");
        AltaSalidaTuristica2("eldiez@socfomturriv.org.uy", "Almuerzo 1", "Restaurante de la Plaza de Toros", 5, LocalDate.of(2022, 9, 18), LocalDate.of(2022, 8, 4), null, LocalTime.of(12, 0));
        confirmarAltaSalida2();

        selectActividad("Almuerzo en el Real de San Carlos");
        AltaSalidaTuristica2("eldiez@socfomturriv.org.uy", "Almuerzo 2", "Restaurante de la Plaza de Toros", 5, LocalDate.of(2022, 9, 25), LocalDate.of(2022, 8, 4), null, LocalTime.of(12, 0));
        confirmarAltaSalida2();

        selectActividad("Almuerzo en Valle del Lunarejo");
        AltaSalidaTuristica2("eldiez@socfomturriv.org.uy", "Almuerzo 3", "Posada Del Lunarejo", 4, LocalDate.of(2022, 9, 10), LocalDate.of(2022, 8, 15), null, LocalTime.of(12, 0));
        confirmarAltaSalida2();

        selectActividad("Almuerzo en Valle del Lunarejo");
        AltaSalidaTuristica2("eldiez@socfomturriv.org.uy", "Almuerzo 4", "Posada Del Lunarejo", 4, LocalDate.of(2022, 9, 11), LocalDate.of(2022, 8, 15), null, LocalTime.of(12, 0));
        confirmarAltaSalida2();

        selectActividad("Cabalgata en Valle del Lunarejo");
        AltaSalidaTuristica2("eldiez@socfomturriv.org.uy", "Cabalgata 1", "Posada Del Lunarejo", 4, LocalDate.of(2022, 9, 10), LocalDate.of(2022, 8, 15), "imagenes_salidas/Cabalgata 1.jpg", LocalTime.of(16, 0));
        confirmarAltaSalida2();

        selectActividad("Cabalgata en Valle del Lunarejo");
        AltaSalidaTuristica2("eldiez@socfomturriv.org.uy", "Cabalgata 2", "Posada Del Lunarejo", 4, LocalDate.of(2022, 9, 11), LocalDate.of(2022, 8, 15), null, LocalTime.of(16, 0));
        confirmarAltaSalida2();

        selectActividad("Degusta");
        AltaSalidaTuristica2("washington@turismorocha.gub.uy", "Degusta Octubre", "Sociedad Agropecuaria de Rocha", 20, LocalDate.of(2022, 10, 30), LocalDate.of(2022, 9, 22), "imagenes_salidas/Degusta Octubre.jpg", LocalTime.of(17, 0));
        confirmarAltaSalida2();

        selectActividad("Degusta");
        AltaSalidaTuristica2("washington@turismorocha.gub.uy", "Degusta Noviembre", "Sociedad Agropecuaria de Rocha", 20, LocalDate.of(2022, 11, 5), LocalDate.of(2022, 2, 10), "imagenes_salidas/Degusta Noviembre.jpg", LocalTime.of(17, 0));
        confirmarAltaSalida2();

        selectActividad("Teatro con Sabores");
        AltaSalidaTuristica2("washington@turismorocha.gub.uy", "Teatro con Sabores 3", "Club Deportivo Union", 30, LocalDate.of(2022, 11, 11), LocalDate.of(2022, 8, 25), null, LocalTime.of(18, 0));
        confirmarAltaSalida2();

        selectActividad("Tour por Colonia del Sacramento");
        AltaSalidaTuristica2("meche@colonia.gub.uy", "Tour Colonia del Sacramento 30-10", "Encuentro en la base del Faro", 10, LocalDate.of(2022, 10, 30), LocalDate.of(2022, 9, 7), "imagenes_salidas/Tour Colonia del Sacramento 30-10.jpg", LocalTime.of(10, 0));
        confirmarAltaSalida2();

        selectActividad("Cabalgata en Valle del Lunarejo");
        AltaSalidaTuristica2("eldiez@socfomturriv.org.uy", "Cabalgata Extrema", "Posada Del Lunarejo", 4, LocalDate.of(2022, 10, 30), LocalDate.of(2022, 9, 15), "imagenes_salidas/Cabalgata Extrema.jpg", LocalTime.of(16, 0));
        confirmarAltaSalida2();

        selectActividad("Almuerzo en el Real de San Carlos");
        AltaSalidaTuristica2("eldiez@socfomturriv.org.uy", "Almuerzo en el Real 1", "Restaurante de la Plaza de Toros", 10, LocalDate.of(2022, 10, 30), LocalDate.of(2022, 10, 10), null, LocalTime.of(12, 0));
        confirmarAltaSalida2();

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

        inscripcionSalida("mirtha.legrand.ok@hotmail.com.ar", "Degusta Noviembre", 2, LocalDate.of(2022, 10, 3));
        confirmarInscripcion(2, 1280);

        inscripcionSalida("mirtha.legrand.ok@hotmail.com.ar", "Teatro con Sabores 3", 2, LocalDate.of(2022, 10, 3));
        confirmarInscripcion(2, 800);

        inscripcionSalida("suavemente@hotmail.com", "Degusta Setiembre", 5, LocalDate.of(2022, 9, 2));
        confirmarInscripcion(5, 3200);

        inscripcionSalida("suavemente@hotmail.com", "Teatro con Sabores 1", 5, LocalDate.of(2022, 9, 2));
        confirmarInscripcion(5, 2000);

        inscripcionSalida("mirtha.legrand.ok@hotmail.com.ar", "Tour Colonia del Sacramento 11-09", 5, LocalDate.of(2022, 9, 3));
        confirmarInscripcion(5, 1700);

        inscripcionSalida("mirtha.legrand.ok@hotmail.com.ar", "Almuerzo 1", 5, LocalDate.of(2022, 9, 3));
        confirmarInscripcion(5, 3400);

        inscripcionSalida("e.watson@gmail.com", "Tour Colonia del Sacramento 18-09", 1, LocalDate.of(2022, 9, 5));
        confirmarInscripcion(1, 340);

        inscripcionSalida("e.watson@gmail.com", "Almuerzo 2", 1, LocalDate.of(2022, 9, 5));
        confirmarInscripcion(1, 680);

        inscripcionSalida("suavemente@hotmail.com", "Tour Colonia del Sacramento 30-10", 2, LocalDate.of(2022, 10, 2));
        confirmarInscripcion(2, 680);

        inscripcionSalida("suavemente@hotmail.com", "Almuerzo en el Real 1", 2, LocalDate.of(2022, 10, 2));
        confirmarInscripcion(2, 1360);

        inscripcionSalida("johann.sebastian@gmail.com", "Tour Colonia del Sacramento 30-10", 4, LocalDate.of(2022, 10, 12));
        confirmarInscripcion(4, 1360);

        inscripcionSalida("johann.sebastian@gmail.com", "Almuerzo en el Real 1", 4, LocalDate.of(2022, 10, 12));
        confirmarInscripcion(4, 2720);

// Ingresar paquetes
// Ingresar paquetes
        confirmarCreacionPaquete("Disfrutar Rocha", "Actividades para hacer en familia y disfrutar arte y gastronomía", 60, 20, LocalDate.of(2022, 8, 10), "imagenes paq/Disfrutar Rocha.jpg");

        confirmarCreacionPaquete("Un día en Colonia", "Paseos por el casco histórico y se puede terminar con Almuerzo en la Plaza de Toros", 45, 15, LocalDate.of(2022, 8, 1), "imagenes paq/Un día en Colonia.jpg");

        confirmarCreacionPaquete("Valle Del Lunarejo", "Visite un ´area protegida con un paisaje natural hermoso", 60, 15, LocalDate.of(2022, 9, 15), "imagenes paq/Valle Del Lunarejo.jpg");

        AgregarActividadPaquete(selectActividad("Degusta"), selectPaquete("Disfrutar Rocha"));
        AgregarActividadPaquete(selectActividad("Teatro con Sabores"), selectPaquete("Disfrutar Rocha"));

        AgregarActividadPaquete(selectActividad("Tour por Colonia del Sacramento"), selectPaquete("Un día en Colonia"));
        AgregarActividadPaquete(selectActividad("Almuerzo en el Real de San Carlos"), selectPaquete("Un día en Colonia"));

        AgregarActividadPaquete(selectActividad("Almuerzo en Valle del Lunarejo"), selectPaquete("Valle Del Lunarejo"));
        AgregarActividadPaquete(selectActividad("Cabalgata en Valle del Lunarejo"), selectPaquete("Valle Del Lunarejo"));

        InscripcionPaquete("Disfrutar Rocha", "mirtha.legrand.ok@hotmail.com.ar", 2080, 2, LocalDate.of(2022, 10, 14), LocalDate.of(2022, 8, 15));
        InscripcionPaquete("Un día en Colonia", "mirtha.legrand.ok@hotmail.com.ar", 5100, 5, LocalDate.of(2022, 10, 04), LocalDate.of(2022, 8, 20));

        InscripcionPaquete("Un día en Colonia", "e.waston@gmail.com", 1020, 1, LocalDate.of(2022, 10, 30), LocalDate.of(2022, 9, 15));
        InscripcionPaquete("Disfrutar Rocha", "suavemente@hotmail.com", 10400, 10, LocalDate.of(2022, 10, 31), LocalDate.of(2022, 9, 1));

        InscripcionPaquete("Un día en Colonia", "suavemente@hotmail.com", 2040, 2, LocalDate.of(2022, 11, 2), LocalDate.of(2022, 9, 18));
        InscripcionPaquete("Un día en Colonia", "johann.sebastian@gmail.com", 6120, 6, LocalDate.of(2022, 10, 17), LocalDate.of(2022, 9, 2));
    }

    public void AltaSalidaTuristicaDepto(String nombre, String lugar, int cantidad, LocalDate fecSal, LocalDate alta, String nombreAct, String nombreDepto) {
        ActividadTuristica actividadSel = this.selectActividad(nombreAct);
        //Departamento dept = this.selectDepartamento(nombreDepto);
        //dept.addActTurisica(actividadSel);
        SalidasTuristicas nuevaS = actividadSel.crearSalida(nombre, lugar, cantidad, fecSal, alta, null, null);
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

    /*
    public String[] listarActividadesProveedor(Proveedor proveedor) {
        int i = 0;
        String[] actsarr = new String[proveedor.getActTuristica().size()];
        for (ActividadTuristica actividad : proveedor.getActTuristica()) {
            actsarr[i] = actividad.getNombre();
            i++;
        }
        return actsarr;
    }
     */
    public boolean obtenerCorreoUsuario(String correo) {

        if (getUsuarioDB(correo) == null) {
            return false;
        }

        return true;

    }

    public String[] listarActividadesProveedor(String correo) {
       
        Proveedor proveedor = (Proveedor) getUsuarioDB(correo); // Obtener el proveedor a partir del correo
        List <ActividadTuristica> actis = getActDeProvFromBD(correo);
        if (proveedor != null) {
            int i = 0;
            String[] actsarr = new String[actis.size()];
            
            for (ActividadTuristica actividad : actis) {
                actsarr[i] = actividad.getNombre();
                i++;
            }
            return actsarr;
        } else {
            return new String[0]; // Devolver un arreglo vacío si no se encuentra el proveedor
        }
    }

    public String[] obtenerSalidasInscritasTurista(String correoTurista) {

        Usuario usr = getUsuarioDB(correoTurista);
        Turista turista = (Turista) usr; // Manejo de error en caso de que el usuario no exista
        if (turista != null) {
            Collection<SalidasTuristicas> salidasInscritas = turista.getSalidasInscritas();
            String[] arreglo = new String[salidasInscritas.size()];
            int i = 0;
            for (SalidasTuristicas salida : salidasInscritas) {
                arreglo[i] = salida.getNombreSalida();
                i++;
            }
            return arreglo;
        }
        return new String[0]; // Devuelve un arreglo vacío si no se encontraron salidas inscritas
    }

    public DataSalida[] DataSalidasTurista(String turistaCorreo) {

        Usuario usr = getUsuarioDB(turistaCorreo);
        Turista tur = (Turista) usr;
        DataSalida[] dataSalidas = new DataSalida[tur.getSalidasInscritas().size()];

        int i = 0;

        for (Object salida : tur.getSalidasInscritas()) {
            dataSalidas[i] = ((SalidasTuristicas) salida).devolverData();
            i++;
        }

        return dataSalidas;
    }

    public DataActividad[] dataActividadesProveedor(String proveedorCorreo) {
       

        Collection <ActividadTuristica> actsProv = getActDeProvFromBD(proveedorCorreo);

        DataActividad[] dataAct = new DataActividad[actsProv.size()];
        int i = 0;
        for (ActividadTuristica act : actsProv) {
            dataAct[i] = act.devolverData();
            i++;
        }

        return dataAct;
    }

    public DataSalida[] dataSalidasActividadesProveedor(String proveedorCorreo) {

        Usuario usr = getUsuarioDB(proveedorCorreo);
        Proveedor proveedor = (Proveedor) usr;
        Collection salidas = proveedor.getSalidasAsociadas();
        DataSalida[] datas = new DataSalida[salidas.size()];
        int i = 0;
        for (Object salida : salidas) {
            datas[i] = ((SalidasTuristicas) salida).devolverData();
            i++;
        }
        return datas;

    }

    public boolean validarCredenciales(String email, String password) {

        Usuario usr = getUsuarioDB(email);
        if (usr == null) {
            return false;
        }

        if (usr.tienePswd(password)) {
            return true;
        } else {
            return false;
        }
    }

    Usuario getUsuarioDB(String email) {

        Usuario usr = em.find(Usuario.class, email);
        return usr;
    }

    Proveedor getProveedorDB(String email) {

        Proveedor usr = em.find(Proveedor.class, email);
        return usr;
    }
    public String[] listarActividadesEnEspera() {
        String[] nombresAct = null;

        // Modifica la consulta JPQL para incluir la cláusula WHERE
        List<ActividadTuristica> actsQuery = em.createQuery("SELECT act FROM ActividadTuristica act WHERE act.estado = :estado", ActividadTuristica.class)
                .setParameter("estado", EstadoActividad.AGREGADA) // Cambia Estado.AGREGADA por el estado deseado
                .getResultList();

        nombresAct = new String[actsQuery.size()];
        int i = 0;
        for (ActividadTuristica a : actsQuery) {
            nombresAct[i] = a.getNombre();
            i++;
        }

        // No es necesario realizar una transacción aquí, a menos que la hayas iniciado previamente.
        return nombresAct;
    }

    public void aceptarActividad(String actSeleccionada) {

        try {
            em.getTransaction().begin(); // Iniciar la transacción

            ActividadTuristica act = em.find(ActividadTuristica.class, actSeleccionada);
            act.setEstado(EstadoActividad.CONFIRMADA);

            em.merge(act);
            em.getTransaction().commit(); // Confirmar la transacción
        } catch (Exception e) {

        }

    }

    public void rechazarActividad(String actSeleccionada) {
        try {
            em.getTransaction().begin(); // Iniciar la transacción

            ActividadTuristica act = em.find(ActividadTuristica.class, actSeleccionada);
            act.setEstado(EstadoActividad.RECHAZADA);

            em.merge(act);
            em.getTransaction().commit(); // Confirmar la transacción
        } catch (Exception e) {

        }

    }

    public boolean getCategoriaDB(String nombreCat) {

        Categoria cat = em.find(Categoria.class, nombreCat);
        if (cat == null) {
            return false;
        }
        return true;
    }

    public void registrarCategoria(String nombreCat) {

        Categoria cat = Categoria.obtenerCategoria(nombreCat);
        if (cat != null) {
            return;
        } else {
            Categoria nuevaCat = new Categoria(nombreCat);

            try {
                em.getTransaction().begin();
                em.persist(nuevaCat);
                em.getTransaction().commit();
            } catch (Exception e) {
            }

            Categoria.agregarCategoria(nuevaCat);

        }
    }

    public String[] listarCategorias() {
        String[] correosP = null;

        // Modifica la consulta JPQL para obtener directamente los correos
        List<String> nombresQuery = em.createQuery("SELECT c.nombreCat FROM Categoria c", String.class)
                .getResultList();

        correosP = nombresQuery.toArray(new String[0]);

        return correosP;
    }

    public void AltaCategoriaAct(String categoria, String actividad, String descripcion, int duracion, double costo, String nombreDepto, LocalDate fechaA, String ciudad, String imagen) {
        Categoria cat = Categoria.obtenerCategoria(categoria);
        if (cat == null) {
            // La categoría no existe, crea una nueva
            cat = new Categoria(categoria);
        }

        // Verifica si ya existe una actividad con el mismo nombre
        /*ActividadTuristica existingActividad = em.find(ActividadTuristica.class, actividad);
    if (existingActividad != null) {
        // Aquí puedes manejar el caso de actividad duplicada, por ejemplo, mostrando un mensaje de error.
        System.out.println("La actividad con nombre '" + actividad + "' ya existe en la base de datos.");
        return;
    }*/
        ActividadTuristica nuevaact = new ActividadTuristica(actividad, descripcion, duracion, costo, nombreDepto, fechaA, imagen);

        // Asigna la categoría a la actividad
        nuevaact.getCategorias().add(cat);

        try {
            em.getTransaction().begin();
            em.persist(nuevaact); // Persiste la actividad con la relación a la categoría
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public boolean AltaCategoriaAct2(String actividad, ArrayList<String> eleccionesCategoria) {

        ActividadTuristica nuevaAct = em.find(ActividadTuristica.class, actividad);

        Set<Categoria> categoriasSeleccionadas = new HashSet<>();
        for (int i = 0; i < eleccionesCategoria.size(); i++) {
            String categoria = eleccionesCategoria.get(i);
            Categoria cat = Categoria.obtenerCategoria(categoria);
            nuevaAct.addCategoria(cat);

        }
        //System.out.print(act.getNombre()+paq.getNombre_paquete());
        try {
            em.getTransaction().begin();
            em.persist(nuevaAct);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception e) {
        }
        return true;
    }

    /*public void AltaCategoriaPaq(String nombreact,String nombre_paquete) {
        
        Categoria cat = em.find(Actividad_Categoria.class, nombreact);
        Paquete paq = em.find(Paquete.class, nombre_paquete);
        paq.addCategoria(cat); 
            
        
        //System.out.print(act.getNombre()+paq.getNombre_paquete());
        try {
            em.getTransaction().begin();
            em.persist(paq);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception e) {
        }
    } */
    public void AltaCategoriaPaq(String nombreact, String nombre_paquete) {
        // Realiza una consulta SQL para obtener el nombre de la categoría
        Query query = em.createNativeQuery("SELECT nombre_categoria FROM Actividad_Categoria WHERE nombre_actividad = ?");
        query.setParameter(1, nombreact);

        // Obtiene el resultado de la consulta SQL
        String nombreCategoria = (String) query.getSingleResult();

        // Encuentra la entidad Categoria por su nombre
        Categoria cat = em.find(Categoria.class, nombreCategoria);

        // Encuentra la entidad Paquete por su nombre
        Paquete paq = em.find(Paquete.class, nombre_paquete);

        // Añade la categoría al paquete
        paq.addCategoria(cat);

        // Inicia una transacción y persiste el paquete
        em.getTransaction().begin();
        em.persist(paq);
        em.flush();
        em.getTransaction().commit();

    }

    public List<Paquete> getAllPaqsFromBD() {
        List<Paquete> paqquery = em.createQuery("SELECT paq FROM Paquete paq", Paquete.class).getResultList();
        return paqquery;
    }

    public List<ActividadTuristica> getAllActsFromBD() {

        EstadoActividad estado = EstadoActividad.CONFIRMADA;
        List<ActividadTuristica> actquery = em.createQuery("SELECT act FROM ActividadTuristica act WHERE act.estado = :estado", ActividadTuristica.class)
                .setParameter("estado", estado)
                .getResultList();

        return actquery;
    }

    public DataActividad infoActividad(String nombreActividad) {
        Query actquery = em.createNativeQuery("SELECT * FROM ACTIVIDADTURISTICA WHERE NOMBRE = ?", ActividadTuristica.class);
        actquery.setParameter(1, nombreActividad);
        ActividadTuristica actividad = (ActividadTuristica) actquery.getSingleResult();
        return actividad.devolverData();
    }

    public void InscripcionPaquete(String nombreP1, String email1, float costoPaquete1, int cantidad1, LocalDate vencimiento1, LocalDate fechaCompra1) {
        Paquete ctm = getPaqFromBD(nombreP1);
        Inscripcion_paquete inspaquete = new Inscripcion_paquete(email1, costoPaquete1, cantidad1, vencimiento1, fechaCompra1);
        //String nombreP,String email,float costoPaquete,int cantidad,LocalDate vencimiento,LocalDate fechaCompra

        //inspaquete= new Inscripcion_paquete(ctm,email1,costoPaquete1,cantidad1,vencimiento1,fechaCompra1);
        Turista tur;
        tur = em.find(Turista.class, email1);
        //inspaquete.setPaquete(ctm);

        inspaquete.setPaquete(ctm);
        inspaquete.iniciarMapaInscr();

        tur.agregarInscripcionPaq(inspaquete);
        try {
            em.getTransaction().begin();
            em.persist(inspaquete);
            em.persist(ctm);
            em.getTransaction().commit();
        } catch (Exception e) {
        }

        String consultaSql = "SELECT MAX(id) FROM INSCRIPCION_PAQUETE";
        Query query = em.createNativeQuery(consultaSql);

        Object resultado = query.getSingleResult();
        String idMasAlto = resultado.toString();

        em.getTransaction().begin();

        Query query2 = em.createNativeQuery("UPDATE INSCRIPCION_PAQUETE SET p_nombre_paquete = ? WHERE ID = ?");
        query2.setParameter(1, nombreP1);
        query2.setParameter(2, idMasAlto);
        int filasActualizadas = query2.executeUpdate();

        em.getTransaction().commit();

    }

    public Paquete getPaqFromBD(String nombrePaquete) {
        Query paqquery = em.createNativeQuery("SELECT * FROM PAQUETE WHERE NOMBRE_PAQUETE = ?", Paquete.class);
        paqquery.setParameter(1, nombrePaquete);
        Paquete paq = (Paquete) paqquery.getSingleResult();
        return paq;
    }

    public List<String> getAllCatFromPaqFromBD(String paquete) {
        Query query = em.createNativeQuery("SELECT nombre_categoria FROM Paquete_Categoria WHERE nombre_paquete= ?");
        query.setParameter(1, paquete);
        List<String> estaCat = query.getResultList();
        return estaCat;
    }

    public List<String> getAllPaqsConActFromBD() {
        Query query = em.createNativeQuery("SELECT DISTINCT Paquete_nombre FROM ActividadTuristica_Paquete");
        List<String> paqs = query.getResultList();
        return paqs;
    }

    public List<Categoria> getAllCatsFromBD() {
        List<Categoria> catsquery = em.createQuery("SELECT cat FROM Categoria cat", Categoria.class).getResultList();
        return catsquery;
    }

    public List<String> getAllActsdeCatFromBD(String categoria) {
        Query query = em.createNativeQuery("SELECT nombre_actividad FROM Actividad_Categoria WHERE nombre_categoria = ?");
        query.setParameter(1, categoria);
        List<String> estaACT = query.getResultList();
        return estaACT;
    }

    public List<String> getAllActsFromPaqFromBD(String paquete) {
        Query query = em.createNativeQuery("SELECT Act_nombre FROM ActividadTuristica_Paquete WHERE Paquete_nombre = ?");
        query.setParameter(1, paquete);
        List<String> estaACT = query.getResultList();
        return estaACT;
    }

    public DataPaquete infoPaquete(String nombrePaquete) {
        Query paqquery = em.createNativeQuery("SELECT * FROM PAQUETE WHERE NOMBRE_PAQUETE = ?", Paquete.class);
        paqquery.setParameter(1, nombrePaquete);
        Paquete paq = (Paquete) paqquery.getSingleResult();
        return paq.devolverData();
    }

    public List<Departamento> getAllDepFromBD() {
        List<Departamento> depquery = em.createQuery("SELECT dep FROM Departamento dep", Departamento.class).getResultList();
        return depquery;
    }

    public List<String> getAllActsdeDepFromBD(String depto) {
        Query query = em.createNativeQuery("SELECT NOMBRE FROM ACTIVIDADTURISTICA WHERE departamento_nombre = ? AND ESTADO=1");
        query.setParameter(1, depto);
        List<String> acts = query.getResultList();
        return acts;
    }

    public ActividadTuristica selectActFromDB(String nombreact) {
        Query paqquery = em.createNativeQuery("SELECT * FROM ACTIVIDADTURISTICA WHERE NOMBRE = ?", ActividadTuristica.class);
        paqquery.setParameter(1, nombreact);
        ActividadTuristica act = (ActividadTuristica) paqquery.getSingleResult();
        return act;
    }

    public List<String> getAllCatsdeActFromBD(String act) {
        Query query = em.createNativeQuery("SELECT nombre_categoria FROM Actividad_Categoria WHERE nombre_actividad = ?");
        query.setParameter(1, act);
        List<String> cats = query.getResultList();
        return cats;
    }

    public Categoria selectCatFromDB(String nombreact) {
        Query catquery = em.createNativeQuery("SELECT * FROM CATEGORIA WHERE NOMBRE = ?", Categoria.class);
        catquery.setParameter(1, nombreact);
        Categoria cat = (Categoria) catquery.getSingleResult();
        return cat;
    }

    public List<String> getAllPaqsdeActFromBD(String act) {
        Query query = em.createNativeQuery("SELECT Paquete_nombre FROM ActividadTuristica_Paquete WHERE Act_nombre = ?");
        query.setParameter(1, act);
        List<String> paqs = query.getResultList();
        return paqs;
    }

    public List<String> getAllSaldeActFromBD(String act) {
        Query query = em.createNativeQuery("SELECT NOMBRESALIDA FROM SALIDASTURISTICAS WHERE actividadAsociada = ?");
        query.setParameter(1, act);
        List<String> sal = query.getResultList();
        ActividadTuristica actv = selectActividad(act);

        return sal;
    }

    public SalidasTuristicas getSalidaFromBD(String nombre) {
        if (nombre == null) {
            return null;
        } else {
            return em.find(SalidasTuristicas.class, nombre);
        }
    }
    public List <ActividadTuristica> getActDeProvFromBD (String correoProv){
    String q = "SELECT act.* from ACTIVIDADTURISTICA act where correoProveedor = ?";
    Query query = em.createNativeQuery(q, ActividadTuristica.class);
    query.setParameter(1,correoProv);
    
    List <ActividadTuristica> acts = query.getResultList();
        return acts;
    }
}
