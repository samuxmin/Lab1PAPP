<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="es">
    <%@include file="Components/header.jsp"%>
    <%@include file="Components/bodyPrincipal.jsp"%>
    <%
        String msg = request.getParameter("msg");
        String mensaje = "";

        if (msg != null) {
            switch (msg) {
                case "altaactividad":
                    mensaje = "Actividad creada con exito";

                    break;
                case "altasalida":

                    mensaje = "Salida creada con exito";
                    break;
                case "inscripcionsalida":

                    mensaje = "Inscripcion realizada con exito";
                    break;

                case "usrmodificado":

                    mensaje = "Usuario modificado con exito";
                    break;
                case "actfinalizada":
                    mensaje = "Actividad finalizada con exito";
                    break;
                default:
                    mensaje = msg;
            }
    %>
    <div class="alert alert-warning" role="alert"><%=mensaje%></div>
    <%}%>


    <%@include file="Components/bodyFinal.jsp"%>