<%@page import="java.util.Collections"%>
<%@page import="java.util.Comparator"%>
<%@page import="webservice.Paquete"%>
<%@page import="webservice.ActividadTuristica"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="es">
    <%@include file="Components/header.jsp"%>
    <%@include file="Components/bodyPrincipal.jsp"%>
    <%
        String texto = request.getParameter("texto");
        String orden = request.getParameter("orden");
        if (orden == null) {
            orden = "nombre";
        }
        if (texto == null) {
            response.sendRedirect("index.jsp");
        }

        List<ActividadTuristica> acts;
        List<Paquete> paqs;

    %>

    <h1>Busqueda de <%=texto%></h1>
    <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Ordenar
        </button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="busqueda.jsp?texto=<%=texto%>&orden=nombre">Por nombre</a>
            <a class="dropdown-item" href="busqueda.jsp?texto=<%=texto%>&orden=fecha">Por fecha</a>
        </div>
    </div>
    <h2>Actividades Turisticas</h2>
    <ul>

        <%
            acts = sys.buscarActividades(texto);
            if (orden.equals("fecha")) {
                Collections.sort(acts, new Comparator<ActividadTuristica>() {
                    @Override
                    public int compare(ActividadTuristica a1, ActividadTuristica a2) {
                        return sys.getFechaAltaActividad(a1.getNombre()).compareTo(sys.getFechaAltaActividad(a2.getNombre()));
                    }
                });
                Collections.reverse(acts);
            }

            for (ActividadTuristica a : acts) {
                String actNombre = a.getNombre();
        %>
        <li>
            <%=actNombre%><a href="actividad?actNombre=<%= actNombre%>">+Info</a>
        </li>
        <%}%>
    </ul>
    <h2>Paquetes</h2>
    <ul>

        <%

            paqs = sys.buscarPaquetes(texto);
            if (orden.equals("fecha")) {
                Collections.sort(paqs, new Comparator<Paquete>() {
                    @Override
                    public int compare(Paquete p1, Paquete p2) {
                        return sys.getFechaAltaPaquete(p1.getNombrePaquete()).compareTo(sys.getFechaAltaPaquete(p2.getNombrePaquete()));
                    }
                });
                Collections.reverse(paqs);
            }

            for (Paquete p : paqs) {
                String paqNombre = p.getNombrePaquete();
        %>
        <li>
            <%=paqNombre%><a href="paquetes?nombrePaq=<%=paqNombre%>&mostrarRadioButtons=true">+Info</a>
        </li>
        <%}%>
    </ul>

    <%@include file="Components/bodyFinal.jsp"%>