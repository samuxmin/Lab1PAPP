
<%@page import="webservice.ActividadTuristica"%>
<%@page import="webservice.InscripcionPaquete"%>
<%@page import="webservice.InscripcionGeneral"%>
<%@page import="webservice.Turista"%>
<%@page import="webservice.Usuario"%>

<%@page import="webservice.EstadoActividad"%>
<%@page import="java.util.List"%>

<%@page import="java.util.Collection"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Components/header.jsp"%>
<%@include file="Components/bodyPrincipal.jsp"%>

<%
    String nickUsuario = (String) request.getParameter("nick");

    DataUsuario usr = null;
    try {
        usr = sys.verInfoUsuarioNick(nickUsuario);
    } catch (Exception e) {
    }
    LocalDate fecha = LocalDate.parse(sys.getFecNacUsr(usr.getCorreo()));
    // Define un formato personalizado "dia-mes-año"
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    // Formatea la fecha en el formato deseado
    String fecnac = fecha.format(formato);
%>


<script>
    function seguirUsuario() {
        var correoUsuarioLoggeado1 = document.getElementById("correoUsuarioLoggeado").value;
        var correoUsuarioSeguido1 = document.getElementById("correoUsuarioSeguido").value;
        // console.log("Valor de correoUsuarioLoggeado: " + correoUsuarioLoggeado1);
        // console.log("Valor de correoUsuarioSeguido " + correoUsuarioSeguido1);
        const data = {
            accion: 'seguir',
            correoSeguidor: correoUsuarioLoggeado1,
            correoSeguido: correoUsuarioSeguido1
        };

        // Ruta al servlet relativa a la raíz de tu aplicación web
        const servletURL = '<%= request.getContextPath()%>/usuario'; // Utiliza request.getContextPath() para obtener la ruta de la aplicación


        // Realizar una solicitud POST al servlet
        fetch(servletURL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
                .then(response => {
                    window.location.reload();
                })
                .catch(error => {
                    // Manejar errores aquí
                });
    }


    function dejarDeSeguirUsuario() {
        var correoUsuarioLoggeado1 = document.getElementById("correoUsuarioLoggeado").value;
        var correoUsuarioSeguido1 = document.getElementById("correoUsuarioSeguido").value;
        // console.log("Valor de correoUsuarioLoggeado: " + correoUsuarioLoggeado1);
        // console.log("Valor de correoUsuarioSeguido " + correoUsuarioSeguido1);
        const data = {
            accion: 'dejarDeSeguir',
            correoSeguidor: correoUsuarioLoggeado1,
            correoSeguido: correoUsuarioSeguido1
        };

        // Ruta al servlet relativa a la raíz de tu aplicación web
        const servletURL = '<%= request.getContextPath()%>/usuario'; // Utiliza request.getContextPath() para obtener la ruta de la aplicación


        // Realizar una solicitud POST al servlet
        fetch(servletURL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
                .then(response => {
                    if (response.ok) {
                        // La solicitud se completó con éxito
                        // Realiza acciones adicionales si es necesario
                        window.location.reload(); // Recarga la página
                    } else {
                        // La solicitud falló, maneja el error según tus necesidades
                        console.error('Error al dejar de seguir al usuario');
                    }
                })
                .catch(error => {
                    // Maneja errores de red u otros errores aquí
                    console.error('Error de red:', error);
                });
    }
</script>
<% String usrCorreo = null;
    if (usuarioLoggeado != null) {
        usrCorreo = usuarioLoggeado.getCorreo();
    }%>
<form id="formularioSeguirUsuario" method="post" action="/LABPAPP_WEB/usuario">
    <input type="hidden" id="correoUsuarioLoggeado" value="<%= usrCorreo%>">
    <input type="hidden" id="correoUsuarioSeguido" value="<%= usr.getCorreo()%>">
    <!-- Otros campos y elementos del formulario aquí -->
</form>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuario <%= nickUsuario%></title>
    </head>
    <body>
        <% if (usr.getImagenP() != null) {%>
        <img class="imgPerfil" src="<%= usr.getImagenP()%>"/>
        <% }%>
        <h1><%= nickUsuario%></h1>
        <p>Email: <%= usr.getCorreo()%></p>
        <p>Tipo: <%= usr.getTipo()%></p>
        <p>Nombre: <%= usr.getNombre()%></p>
        <p>Apellido: <%= usr.getApellido()%></p>
        <p>Fecha de Nacimiento: <%= fecnac%></p>
        <% if (usr.getTipo().equals("Turista")) {%>
        <p>Nacionalidad: <%= usr.getNacionalidad()%></p>
        <%}%>
        <hr>
        <% if (logged) {
                if (!usuarioLoggeado.getCorreo().equals(usr.getCorreo()) && !sys.estaSeguidofromDB(usuarioLoggeado.getCorreo(), usr.getCorreo())) {
        %>
        <input type="button" value="Seguir" id="seguirButton" class="btn btn-primary btn-user custom-small-button" style="margin-left: 285px;" onclick="seguirUsuario()">
        <% } %>

        <% if (!usuarioLoggeado.getCorreo().equals(usr.getCorreo()) && sys.estaSeguidofromDB(usuarioLoggeado.getCorreo(), usr.getCorreo())) { %>
        <input type="button" value="Dejar de Seguir" id="NoseguirButton" class="btn btn-primary btn-user custom-small-button" style="margin-left: 285px;" onclick="dejarDeSeguirUsuario()">
        <% }
            } %>

        <h5>Seguidores:</h5>
        <ul>
            <%
                List<String> seguidores = sys.getSeguidoresBD(usr.getCorreo());
                if (!seguidores.isEmpty()) {
                    for (String seg : seguidores) {
                        Usuario u = sys.getUsuarioDB(seg);
            %>    
            <li><%= seg%> <a href="usuario?nick=<%=u.getNick()%>">-Info</a></li>
                <%}
                    }%>
        </ul>

        <h5>Sigue:</h5>
        <ul>
            <%
                List<String> seguidos = sys.getSeguidosBD(usr.getCorreo());
                if (!seguidos.isEmpty()) {
                    for (String seg : seguidos) {
                        Usuario u = sys.getUsuarioDB(seg);
            %>    
            <li><%= seg%> <a href="usuario?nick=<%=u.getNick()%>">-Info</a></li>
                <%}
                    }%>
        </ul>
        <% if (usr.getTipo().equals("Turista")) {%>
        <%if (usr.getNick().equals(nick)) {%>
        <h5>Inscripciones Generales:</h5>
        <%}%>
        <% Turista t = sys.getTuristaDB(usr.getCorreo());
            Collection<InscripcionGeneral> inscripcion_general = t.getInscripciongeneral();
            for (InscripcionGeneral insGnl : inscripcion_general) {
                if (insGnl != null && usr.getNick().equals(nick)) {
        %>   
        <br>
        <p>Inscripcion a Salida: <%= insGnl.getSalida().getNombreSalida()%></p>
        <p>Costo: <%= insGnl.getCostoGeneral()%></p>
        <p>Cantidad de turistas: <%= insGnl.getCantidad()%></p>
        <p>Fecha compra: <%= sys.getFechaInscripcionGeneral(insGnl.getId())%></p>
        <p><a href="salida?nombre=<%=insGnl.getSalida().getNombreSalida()%>">-Info</a></p>
        <p><a href="inscripcionsalida?nombre=<%=insGnl.getSalida().getNombreSalida()%>&touristName=<%=usr.getCorreo()%>&action=generarPDF">-Descargar boleta</a></p>
        <%
                }
            }
        %>
        <%if (usr.getNick().equals(nick)) {%>
        <hr>
        <h5>Inscripciones a Paquetes:</h5>
        <%}%>
        <%
            Collection<InscripcionPaquete> inscripcion_paquete = t.getInscripcionpaquete();
            for (InscripcionPaquete insPaq : inscripcion_paquete) {
                if (insPaq != null && usr.getNick().equals(nick)) {
        %>   
        <br>
        <p>Paquete: <%= insPaq.getPaquete().getNombrePaquete()%></p>
        <p>Costo: <%= insPaq.getCostoPaquete()%></p>
        <p>Cantidad de turistas: <%= insPaq.getCantidad()%></p>

        <p>Fecha compra: <%= sys.getFechaInscripcionPaquete(insPaq.getId())%></p>
        <p>Vencimiento: <%= sys.getVencimientoInscripcionPaquete(insPaq.getId())%></p>
        <p><a href="paquetes?nombrePaq=<%=insPaq.getPaquete().getNombrePaquete()%>">-Info</a></p>
        <%
                    }
                }
            }
        %> 
        <%if (usr.getTipo().equals("Proveedor")) {%>
        <p>Descripcion: <%= usr.getDescripcion()%></p>
        <p>Sitio web: <a href="<%= usr.getWeb()%>" target="blank"> <%= usr.getWeb()%></a></p>
        <p>Actividades que provee:</p>
        <ul>
            <%
                List<ActividadTuristica> acts = sys.getAllActsProveedorBD(usr.getCorreo());
                String estado = "";
                for (ActividadTuristica act : acts) {
                    if (act.getEstado() == EstadoActividad.CONFIRMADA || usr.getNick().equals(nick)) {
                        if (usr.getNick().equals(nick)) {
                            estado = " -Estado: " + act.getEstado().toString();
                        }
            %>    

            <li><%= act.getNombre()%> <%=estado%> <a href="actividad?actNombre=<%=act.getNombre()%>">-Info</a></li>
                <%}
                    }%>
                <% }%>
        </ul>



    </body>
</html>
<%@include file="Components/bodyFinal.jsp"%>