
<%@page import="webservice.Paquete"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Components/header.jsp"%>
<%@include file="Components/bodyPrincipal.jsp"%>

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
                String paqu=null;
                List<String> paq = sys.getAllPaqsConActFromBD();
               
                for (String paquete: paq) {
                    paqu=paquete;
            %>
            <option value="<%= paqu %>"><%= paqu %></option>
            <%
                }
                
            %>
        </select>
        <br>
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
             window.location.href = "comprapaquete?nombrePaq=" + selectedValue;
            //contenidoOculto.style.display = "block";
            // Redirige a la página actual con el nombre del paquete seleccionado como parámetro
           
        }
    </script>
    
    <form class="user" method="POST" id="compra">
  
    <%
    String var=request.getParameter("nombrePaq");
        
        List<String>turpaq=null;
        HttpSession sesion = request.getSession();  
        Object loggeadoAtributousr = request.getSession().getAttribute("logeado");
        DataUsuario usuarioLoggeadousr = null;
       
        boolean loggedusr = false;
        String nickusr = null;

        if (loggeadoAtributousr != null) {
            loggedusr = (boolean) loggeadoAtributousr;
        }

        if (loggedusr) {
            nickusr = (String) sesion.getAttribute("nick");
            usuarioLoggeadousr = sys.verInfoUsuarioNick(nickusr);
        }
        if(usuarioLoggeadousr != null){
        turpaq= sys.turConPaqFromBD(usuarioLoggeadousr.getCorreo(),nombrePaquete);
        }
    if(var != null && !var.isEmpty() && turpaq!=null && !turpaq.isEmpty()){
    %>
    <h6>El paquete seleccionado ya ha sido comprado por el turista</h6>
    <h6>Desea comprar otro paquete o cancelar?</h6>
    
        <div class="form-group">
               <button type="button" onclick="window.location.href='index.jsp'">Cancelar</button>
        
        </div>
    
    <%
        }else if(var != null && !var.isEmpty()){
     Paquete paquetes=sys.getPaqFromBD(nombrePaquete);
     
    %>
       
         <div class="form-group">
             <h6>Fecha de compra:</h6>
                <input type="date" class="form-control form-control-user" id="Fecha" name="Fecha" placeholder="Fecha de compra">
                
        </div>
    
        <br>
        <h6>Descuento del Paquete: <%=paquetes.getDescuento() %></h6>
        <br>
           
         <div class="form-group">
                <input type="number" class="form-control form-control-user" id="Cantidad" name="Cantidad" placeholder="Cantidad de turistas">
        </div>
         
        <button type="submit" class="btn btn-primary btn-user btn-block">CompraPaquete</button>
            
         <%}     
         %>  
    </form> 
    
</body>
</html>

<%@include file="Components/bodyFinal.jsp"%>