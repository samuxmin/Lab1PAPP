
<%@page import="webservice.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Components/header.jsp"%>
<%@include file="Components/bodyPrincipal.jsp"%>

<body>
    <div class="container">
        <h1 class="mt-5">Crear actividad</h1>
        <form action="crearactividad" id="registroForm" method="POST" enctype="multipart/form-data">
            <div class="mt-3">
                <label for="imagen" class="input-group-text">Imagen (Opcional)</label>
                <input type="file" class="form-control" id="imagen" name="imagen" accept="image/*">
            </div>

            <div class="mt-3">
                <label for="departamento" class="form-label">Departamento</label>
                <select id="departamento" name="departamento" class="custom-select">
                    <%
                        List <String>  deptos = sys.listarDepartamentos();
                        for (String depto  : deptos) {
                    %>
                    <option value="<%= depto%>"><%= depto%></option>
                    <%
                        }
                    %>
                </select>
            </div>

            <div class="mt-3">
                <label for="nombre" class="form-label">Nombre</label>
                <input required type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre">
            </div>

            <div class="mt-3">
                <label for="ciudad" class="form-label">Ciudad</label>
                <input required type="text" class="form-control" id="ciudad" name="ciudad" placeholder="Ciudad">
            </div>

            <div class="mt-3">
                <label for="duracion" class="form-label">Duración en horas</label>
                <input required type="number" class="form-control" id="duracion" name="duracion" min="0">
            </div>

            <div class="mt-3">
                <label for="costo" class="form-label">Costo en $UYU</label>
                <input required type="number" class="form-control" id="costo" name="costo" min="0">
            </div>

            <div class="mt-3">
                <label for="descripcion" class="form-label">Descripción</label>
                <textarea required class="form-control" id="descripcion" name="descripcion"></textarea>
            </div>

            <div class="mt-3">
                <label for="categorias" class="form-label">Categorías</label>
                <select id="categorias" name="categorias" class="custom-select">
                    <%
                        String catNombre;
                        List<Categoria> cat = sys.getAllCatsFromBD();
                        for (Categoria categoria : cat) {
                            catNombre = categoria.getNombreCat();
                    %>
                    <option id="<%= catNombre%>" value="<%= catNombre%>"><%= catNombre%></option>
                    <%
                        }
                    %>
                </select>
            </div>

            <button type="button" class="btn btn-primary mt-3" onclick="addCategoria()">Agregar Categoría</button>

            <!-- Display selected categories here -->
            <h4 class="mt-3">Categorías:</h4>
            <ul id="selectedCategories"></ul>

            <input hidden name="catInput" id="catInput">
            <button type="submit" class="btn btn-primary mt-3">Registrar</button>
        </form>
    </div>

    <script>
        var selectedCategories = [];
        let categorias = "";

        function addCategoria() {
            let catAgregada = $("#categorias").val();
            if (selectedCategories.indexOf(catAgregada) === - 1) {
                selectedCategories.push(catAgregada);
                $("#" + catAgregada).hide();
                categorias = categorias + catAgregada + ",";
                
                // Add the selected category to the display area
                $("#selectedCategories").append('<li>' + catAgregada + '</li>');
                $("#catInput").val(categorias);
            }
        }
    </script>
</body>
</html>
  <%@include file="Components/bodyFinal.jsp"%>