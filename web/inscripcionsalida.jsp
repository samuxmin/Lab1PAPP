<%@page import="webservice.SalidasTuristicas"%>
<%@page import="java.util.List"%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>

<%@include file="Components/header.jsp"%>
<%@include file="Components/bodyPrincipal.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String nombreS = request.getParameter("nombre");
    String touristName = request.getParameter("touristName");
    if (!(logged && sys.getTipo(usuarioLoggeado.getNick()).equals("turista"))) {
        response.sendError(401, "Debes estar logeado como turista para usar esta pagina");
        return;
    }
    if (nombreS == null) {
        response.sendRedirect("actividadestur");
        return;
    }
    SalidasTuristicas salida = sys.getSalidaFromBD(nombreS);
    if (salida == null) {
        response.sendError(404);
        return;
    }
    String pdf = request.getParameter("action");
    System.out.println(pdf);
    if (pdf == null) {
        if (sys.estaInscritoTuristaSalida(usuarioLoggeado.getCorreo(), nombreS)) {
%>
<h1>Ya estas inscrito a esa salida</h1>
<a href="actividadestur" class="btn btn-primary mt-3">Seleccionar otra salida</a>
<%
        return;
    }
    if (salida.getCantidadMaximaTuristas() <= salida.getCantInscritos()) {

%>
<h1>Ya se alcanzó la capacidad máxima de la salida</h1>
<a href="actividadestur" class="btn btn-primary mt-3">Seleccionar otra salida</a>
<% return;
    }
    LocalDate fechaSal = LocalDate.parse(sys.getFechaSalida(nombreS));

    if (fechaSal.isBefore(LocalDate.now()) ) {
%>
<h1>La salida no esta vigente</h1>
<a href="actividadestur" class="btn btn-primary mt-3">Seleccionar otra salida</a>
<% return;
    }

%>


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Inscripcion salida</title>
</head>

<h1>Inscribirse a <%= nombreS%> </h1>
<form action="inscripcionsalida" method="post" id="formInscr">
    <input hidden name="nombreSalida" value="<%= nombreS%>">
    <input hidden name="mailTur" value="<%= usuarioLoggeado.getCorreo()%>">

    <input hidden name="tipoInscr" id="tipoInscr" value="">
    <input hidden name="paqueteSeleccionado" id="paqueteSeleccionado" value="">
    <input required type="number" min="1" value="1" placeholder="Cantidad de turistas" name="cantidad" id="cantidad" class="form-control" >

    <select class="custom-select" name="tipoPago" id="tipoPago">
        <option value="general">General</option>
        <option value="paquete">Por paquete</option>
    </select>

    <select  class="custom-select" name="paquetes" id="paquetes">
        <option value="">Seleccione un paquete</option>
        <%
            List<String> paqs = sys.listarPaqInscritoTurContieneAct(usuarioLoggeado.getCorreo(), salida.getActividadAsociada().getNombre());

            for (String p : paqs) {

        %>
        <option value="<%=p%>"><%=p%></option>
        <%}%>
    </select>
    <button >Enviar</button>
</form>
<% }
    if (pdf != null) {  %> 
<div class="alert alert-warning" role="alert">Inscrito Exitosamente</div>
<button type="button" onclick="downloadPDF()">Descargar PDF</button>
<%}%> 





<!-- Tu código JSP continúa aquí -->



<script>
    var selectedValue = "<%= nombreS%>";
    function downloadPDF() {
        let touristName = "<%= touristName%>";
        window.location.href = "inscripcionsalida?nombre=" + selectedValue + "&touristName=" + touristName + "&action=generatePDF";

        // Generar la solicitud para descargar el PDF
        fetch('/inscripcionsalida?nombre=' + tourName + '&touristName=' + touristName + '&action=generatePDF')
                .then(response => {
                    return response.blob();
                })
                .then(blob => {
                    const url = window.URL.createObjectURL(new Blob([blob]));
                    const a = document.createElement('a');
                    a.href = url;
                    a.download = 'turist_information.pdf';
                    document.body.appendChild(a);
                    a.click();
                    window.URL.revokeObjectURL(url);
                });
    }
</script>



<script>
    let tipoPago = document.getElementById("tipoPago");
    let paqSelect = $("#paquetes");
    paqSelect.hide();
    tipoPago.addEventListener("change", function (e) {
        $("#tipoInscr").val(tipoPago.value);
        if (tipoPago.value === "paquete") {
            paqSelect.show();
        } else {
            paqSelect.hide();
        }

    });

    document.getElementById("paquetes").addEventListener("change", function (e) {

        $("#paqueteSeleccionado").val(paqSelect.val());

    });

    document.getElementById("formInscr").addEventListener("submit", function (e) {
        if (tipoPago.value === "paquete" && paqSelect.val() === "") {
            alert("Selecciona un paquete por favor");
            e.preventDefault();
            return;
        }

    });
</script>
<%@include file="Components/bodyFinal.jsp"%>