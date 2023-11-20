<%@page import="webservice.Departamento"%>
<%@page import="java.util.List"%>
<%@page import="webservice.ActividadTuristica"%>
<%@include file="Components/header.jsp"%>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Alta de Salida Turística</title>
        <!-- Custom fonts for this template-->
        <link href="ComponentsBoostrap/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
        <!-- Custom styles for this template-->
        <link href="ComponentsBoostrap/css/sb-admin-2.min.css" rel="stylesheet">
    </head>
    <body class="bg-gradient-primary">
        <div class="container">
            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                        <div class="col-lg-7">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Alta de Salida Turística</h1>
                                </div>
                                <div class="form-group">
                                    <label for="nombreDep" class="form-label">Departamento</label>
                                    <select id="departamentos" name="nombreDep"class="custom-select form-control w-25" onchange="mostrarSeleccion()"  onfocus="this.selectedIndex = -1;">
                                        <option value="" >Seleccione un departamento</option>
                                        <%                            String nombredep = request.getParameter("nombreDep");
                                            String depNombre = null;
                                            List<Departamento> dep = sys.getAllDepFromBD();

                                            for (Departamento deptos : dep) {
                                                depNombre = deptos.getNombreDepartamento();
                                        %>
                                        <option value="<%= depNombre%>" <% if (nombredep != null && nombredep.equals(depNombre)) { %>selected="selected"<% }%>><%= depNombre%></option>
                                        <%
                                            }
                                        %>               
                                    </select>
                                    <script>
                                        var selectedValue = "<%=nombredep%>";

                                        function mostrarSeleccion() {
                                             
                                            // Obtiene el elemento select
                                            var select = document.getElementById("departamentos");
                                            // Obtiene el valor seleccionado
                                            var selectedValue = select.options[select.selectedIndex].value;
                                            // Muestra una alerta con la opción seleccionada
                                            if (selectedValue === "") {
                                                return;
                                            } else
                                                window.location.href = "SvAltaSalidaTuristica?nombreDep=" + selectedValue;
                                        }
                                    </script>

                                    <br>
                                    <br>
                                    <!--
                                    <input type="button" value="Seleccionar Departamento" id="seleccionarDepartamentoButton" class="btn btn-primary btn-user custom-small-button" style="margin-left: 285px;" onclick="mostrarSeleccion()">
                                    -->



                                </div>
                                <form action="SvAltaSalidaTuristica" id="altaSalidaForm" class="user" method="POST" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text" id="inputGroupFileAddon01">Imagen de la salida</span>
                                            </div>

                                            <div class="custom-file">
                                                <input type="file" class="custom-file-input" id="inputGroupFile01" name="imagen" aria-describedby="inputGroupFileAddon01" accept="image/*">
                                                <label class="custom-file-label" for="inputGroupFile01">Subir imagen</label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <input required type="text" class="form-control form-control-user" id="nombre" name="nombre" placeholder="Nombre">
                                        </div>
                                        <div class="form-group">
                                            <input required type="text" class="form-control form-control-user" id="lugar" name="lugar" placeholder="Lugar">
                                        </div>
                                        <div class="form-group">
                                            <input required type="number" class="form-control form-control-user" id="cantidad" name="cantidad" placeholder="Cantidad">
                                        </div>
                                        <div class="form-group">
                                            <input required type="date" class="form-control form-control-user" id="fechaSalida" name="fechaSalida" placeholder="Fecha de Salida">
                                        </div>
                                        <label for="hora">Selecciona una hora:</label>
                                        <input required type="time" id="hora" name="hora">

                                        <div class="form-group">
                                            <label for="nombreActividad">Nombre de la Actividad</label>
                                            <select required class="form-control" id="nombreActividad" name="nombreActividad">
                                                <%

                                                    String depto = request.getParameter("nombreDep");
                                                    List<String> acts = sys.getAllActsdeDepFromBD(depto);

                                                    for (String actNombre : acts) {

                                                %>
                                                <option value="<%= actNombre%>"><%= actNombre%></option>
                                                <%
                                                    }
                                                %>
                                            </select>
                                        </div>

                                        <button class="btn btn-primary btn-user btn-block">Registrar Salida Turística</button>
                                    </div>
                                </form>
                                <hr>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Bootstrap core JavaScript-->
        <script src="ComponentsBoostrap/vendor/jquery/jquery.min.js"></script>
        <script src="ComponentsBoostrap/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <!-- Core plugin JavaScript-->
        <script src="ComponentsBoostrap/vendor/jquery-easing/jquery.easing.min.js"></script>
        <!-- Custom scripts for all pages-->
        <script src="ComponentsBoostrap/js/sb-admin-2.min.js"></script>
        <script src="js/altaSalida.js"></script>
    </body>
</html>
