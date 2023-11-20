<%@page import="webservice.SalidasTuristicas"%>
<%@page import="java.time.LocalTime"%>

<%@page import="java.util.Collection"%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Components/header.jsp"%>
<%@include file="Components/bodyPrincipal.jsp"%>
<%
    String nombreS = request.getParameter("nombre");
    SalidasTuristicas salida = sys.getSalidaFromBD(nombreS);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Salida <%= nombreS%></title>
    </head>
    <body>

        <h1><%= nombreS%></h1>

        <% if (salida != null) { 
        
           sys.aumentarVisitasSalida(nombreS);
        %>
        <% if (salida.getImagenSalida() != null) {%>
        <img class="imgPerfil" src="<%= salida.getImagenSalida()%>"/>
        <%}%>
        <%
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fecha = LocalDate.parse(sys.getFechaSalida(nombreS)).format(formato);
        int cantInscritos = salida.getCantInscritos();
        int cantMax = salida.getCantidadMaximaTuristas();
        String lugar = salida.getLugar();
        String actividad = salida.getActividadAsociada().getNombre();
       LocalTime hora = LocalTime.parse(sys.getHoraSalida(nombreS));
        %>
        <ul>
            <li>Actividad: <%=actividad%></li>
            <li>Lugar: <%=lugar%></li>
            <li>Fecha: <%=fecha%></li>
            <li>Hora: <%=hora%></li>
            <li>Inscritos: <%=cantInscritos%> de <%=cantMax%></li>
        </ul>
        <%
    boolean estaVigente = LocalDate.parse(sys.getFechaSalida(nombreS)).isAfter(LocalDate.now()) ;
    if(logged && sys.getTipo(usuarioLoggeado.getNick()).equals("turista") && estaVigente){
        System.out.println("?????");%>
        <a href="inscripcionsalida?nombre=<%=nombreS%>">Inscribirse</a>
        <%}%>
        
        
        
        
        
        
        
        <% } else { %>
        La salida especificada no fue encontrada
        <%}%>

    </body>
</html>
<%@include file="Components/bodyFinal.jsp"%>
