<%@page import="webservice.Paquete"%>
<%@page import="webservice.DataPaquete"%>

<%@page import="java.time.LocalDate"%>
<%@page import="java.util.List"%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Components/header.jsp"%>
<%@include file="Components/bodyPrincipal.jsp"%>
 
<%
boolean mostrarRadioButtons = "true".equals(request.getParameter("mostrarRadioButtons"));
%>

<html>
<head>
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    
    
</head>
<body>
    <form>
        <h4>Paquetes</h4>
        <select id="paquetes" name="nombrePaq">
            <%
                String nombrePaquete = request.getParameter("nombrePaq");
                DataPaquete pa = null;
                if (nombrePaquete != null) {
                    pa = sys.infoPaquete(nombrePaquete);
                }
                String paqNombre;
                List<Paquete> paq = sys.getAllPaqsFromBD();
                for (Paquete paquete : paq) {
                    paqNombre = paquete.getNombrePaquete();
            %>
            <option value="<%= paqNombre %>"><%= paqNombre %></option>
            <%
                }
            %>
        </select>
        <br>
        <br>
        <button type="button" onclick="mostrarSeleccion()">Seleccionar</button>
    
      </form>  
    <script>
        var selectedValue = "<%= nombrePaquete %>"; // Almacena el valor seleccionado

        function mostrarSeleccion() {
            // Obtiene el elemento select
            var select = document.getElementById("paquetes");
            // Obtiene el valor seleccionado
            selectedValue = select.options[select.selectedIndex].value;

            // Redirige a la página actual con el nombre del paquete seleccionado como parámetro
            window.location.href = "paquetes?nombrePaq=" + selectedValue + "&mostrarRadioButtons=true";
        }
    </script>
  
        
    <%
     if (pa != null) {
          List<String> actividad = sys.getAllActsFromPaqFromBD(nombrePaquete); // Usamos "nombrePaquete" en lugar de "pa.getNombre_paquete()"
          String actN;
          
          List<String> categorias=sys.getAllCatFromPaqFromBD(nombrePaquete);
          String catP;
          
    %>
    <img src="<%= pa.getImagen() %>"/>
    <hr>
    <h6>Nombre: <%= pa.getNombrePaquete()%></h6>
    <h6>Descripcion: <%= pa.getDescri() %></h6>
    <h6>Alta: <%= sys.getFechaAltaPaquete(pa.getNombrePaquete())%></h6>
    <h6>Validez: <%= pa.getValidez() %></h6>
    <h6>Descuento: <%= pa.getDescuento() %></h6>
    <hr>
    <h6>Actividad turisticas: </h6>
    <%
        for(String act : actividad){
            actN = act;
    %>  
    <h6><%=actN %></h6>
    <%
        }%> 
    <hr>    
    <h6>Categorias:</h6>  
    <ol>
       <%  for(String cat : categorias){
         catP = cat;
    %>  
   <li><%=catP %></li>
    <%     
    }
    }
  
    %>
   </ol>
    <hr>
    
    <div id="contenidoOpciones" <% if (mostrarRadioButtons) { %>style="display: block;"<% } else { %>style="display: none;"<% } %>>
    <h6>¿Desea listar los datos de una actividad turística?</h6>
    <input type="radio" id="si" name="opcion" value="si">
    <label for="si">Sí</label>

    <input type="radio" id="no" name="opcion" value="no">
    <label for="no">No</label>

    <div id="contenidoSi" style="display: none;">
        <!-- Contenido que se mostrará cuando elija "Sí" -->
   

    <form> 
        <select id="actividadesCombo" name="nombreAct">
            <%
                String nombreActividad = request.getParameter("nombreAct");
                List<String> actividad2 = sys.getAllActsFromPaqFromBD(nombrePaquete);
                String actN2;
                for (String act : actividad2) {
                    actN2 = act;
            %>
                <option value="<%= actN2 %>"><%= actN2 %></option>
            <%
                }
               
            %>
        </select>
        <br>
        <br>
       
        <button type="button" onclick="mostrarSeleccion2()">SeleccionarActividad</button>
       </form>   
         
    <script> 
    var selectedValue2 = "<%= nombreActividad %>";
    function mostrarSeleccion2() {
    var select2 = document.getElementById("actividadesCombo");
    selectedValue2 = select2.options[select2.selectedIndex].value;
     nombreActividad = selectedValue2;
     window.location.href = "actividad?actNombre=" + selectedValue2;
   // alert("nombreAct: " + selectedValue);
    
        }
    </script>
    
    </div>      
    
</div>
    
    
<script>
    var opcionSi = document.getElementById("si");
    var contenidoSi = document.getElementById("contenidoSi");

    opcionSi.addEventListener("change", function() {
        contenidoSi.style.display = opcionSi.checked ? "block" : "none";
    });

    var opcionNo = document.getElementById("no");
    opcionNo.addEventListener("change", function() {
        contenidoSi.style.display = opcionNo.checked ? "none" : "block";
    });
</script>

</body>
</html>

<%@include file="Components/bodyFinal.jsp"%>