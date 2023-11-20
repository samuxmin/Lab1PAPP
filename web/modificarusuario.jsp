<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>

<%@include file="Components/header.jsp"%>
<%@include file="Components/bodyPrincipal.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Modificar Usuario</title>
</head>
<body>
    <h1>Modificar Usuario</h1>
    <form action="modificarusuario" method="post" enctype="multipart/form-data">
        <input type="hidden" name="correo" value="${usr3.correo}">
        
        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" value="${usr3.nombre}"><br>

        <label for="apellido">Apellido:</label>
        <input type="text" name="apellido" value="${usr3.apellido}"><br>
        
        <label for="fecNac">Fecha de Nacimiento:</label>
        <input type="date" name="fecNac" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${fechaNacimiento}'/>"><br>

        <label for="imagenPerfil">Imagen de Perfil:</label>
        <input type="file" name="imagenPerfil"><br>
        
        <input type="submit" value="Guardar Cambios">
    </form>
        <%@include file="Components/bodyFinal.jsp"%>
</body>
</html>

