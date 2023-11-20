<%@page import="java.time.LocalDate"%>
<%@page import="java.util.List"%>
<%@page import="webservice.Categoria"%>
<%@page import="webservice.DataActividad"%>
<%@page import="java.time.format.DateTimeFormatter"%>


<%@page import="webservice.DataUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Components/header.jsp"%>
<%@include file="Components/bodyPrincipal.jsp"%>
<%
    String nombreActividad = (String) request.getParameter("actNombre");
    DataActividad act = null;
    try {
        act = sys.infoActividad(nombreActividad);
    } catch (Exception e) {
        response.sendRedirect("actividadestur");
        return;
    }
    LocalDate fecha = LocalDate.parse(sys.getFechaAltaActividad(act.getNombre()));
    List<String> actsFav = null;
    sys.aumentarVisitasActividad(nombreActividad);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actividad <%= nombreActividad%></title>
    </head>
    <body>
        <h3>Actividad: <%= nombreActividad%>
            <% if (logged && sys.getTipo(usuarioLoggeado.getNick()).equals("turista")) {
                    String actNombre = nombreActividad;
                    actsFav = sys.getActsFavoritasUsr(usuarioLoggeado.getCorreo());
                    boolean esFavorita = actsFav.contains(nombreActividad);

                    String clase = "fas fa-regular fa-heart";
                    String color = "";
                    if (esFavorita) {
                        clase = "fas fa-solid fa-heart";
                        color = "style=\"color: #e01b24;\"";
                    }
            %>
            <button onclick="toggleFav('<%=actNombre%>',<%=esFavorita%>)" id="toggleFav-<%=actNombre%>" ><i class="<%= clase%>" <%=color%>></i></button>
                <% } %>
        </h3>
        <br>
        <% if (act.getImagen() != null) {%>
        
            <div class="col-md-6">
                <img class="imgPerfil" src="<%= act.getImagen()%>"/>
            </div>
            <div class="col-md-6" style="margin:12px;">
                <% if (act.getUrlVideo() != null && !act.getUrlVideo().isEmpty()) {%>
                <div class="embed-responsive embed-responsive-16by9">
                    <iframe class="embed-responsive-item"  src="<%= act.getUrlVideo()%>" allowfullscreen></iframe>
                </div>
                <% } %>
            </div>
      
        <% }%>
        <p>Departamento: <%= (act.getDepartamento())%></p>
        <p>Ciudad: <%= act.getCiudad()%></p>
        <p>Nombre: <%= act.getNombre()%></p>
        <p>Descripcion: <%= act.getDescripcion()%></p>
        <p>Duracion: <%= act.getDuracionHoras()%></p>
        <p>Costo por Turista: <%= act.getCostoPorTurista()%></p>
        <p>Fecha de Alta: <%= sys.getFechaAltaActividad(act.getNombre())%></p>
        <br>
        <h4>Categorias:</h4>
        <ol>
            <%
                List<String> cat = sys.getAllCatsdeActFromBD(nombreActividad);
                for (String categoria : cat) {
            %>
            <li><%= categoria%></li>
                <%
                    }
                %>
        </ol> 
        <h4>Paquetes:</h4>    
        <ol>
            <%
                List<String> paqs = sys.getAllPaqsdeActFromBD(nombreActividad);
                for (String paquetes : paqs) {
            %>
            <li><%= paquetes%></li><a href="paquetes?nombrePaq=<%= paquetes%>">+Info</a>
                <%
                    }
                %>
        </ol>
        <h4>Salidas:</h4>    
        <ol>
            <%
                List<String> sal = sys.getAllSaldeActFromBD(nombreActividad);
                for (String salidas : sal) {
            %>
            <li><%= salidas%></li><a href="salida?nombre=<%= salidas%>">+Info</a>
                <%
                    }
                %>
        </ol>       
        <% if (logged) {
                if (usuarioLoggeado.getCorreo().equals(act.getProveedor())) {
                    if (!sys.tieneSalidasActivasAct(act.getNombre())) {
        %>
        <form method="POST" action="SvFinalizarActividad">
            <input type="text" name="nombreAct" hidden value="<%= nombreActividad%>">
            <button class="btn btn-danger" >Finalizar Actividad</button>
        </form>
        <% }
                }
            }%>
        <script>
            function toggleFav(nombreAct, favorito) {
                favorito = !favorito;
                var toggleFavElement = document.getElementById("toggleFav-" + nombreAct);
                var clase = "fas fa-regular fa-heart";
                var color = "";

                if (favorito) {
                    clase = "fas fa-solid fa-heart";
                    color = "style=\"color: #e01b24;\"";
                }

                toggleFavElement.innerHTML = '<i class="' + clase + '" ' + color + '></i>';
                // Aquí puedes agregar la lógica adicional para el envío de la solicitud AJAX si es necesario.
            }
        </script>
    </body>
</html>
<%@include file="Components/bodyFinal.jsp"%>