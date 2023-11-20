<%@page import="controladores.Sistema"%>
<%@page import="webservice.DataUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Object loggeadoAtributo = session.getAttribute("logeado");
    DataUsuario usuarioLoggeado = null;
    Sistema sys2 = new Sistema();
    boolean logged = false;
    String nick = null;
    
    if (loggeadoAtributo != null) {
        logged = (boolean) loggeadoAtributo;
    }

    if (logged) {
        nick = (String) session.getAttribute("nick");
        usuarioLoggeado = sys2.verInfoUsuarioNick(nick);
  
    }
    
    String userAgent = request.getHeader("User-Agent");
    Boolean esDispositivoMovil = userAgent != null && (userAgent.contains("Mobile") || userAgent.contains("Android") || userAgent.contains("iPhone") || userAgent.contains("iPad") || userAgent.contains("Windows Phone"));
    
    Object dispositivoMovilAtributo = request.getSession().getAttribute("esDispositivoMovil");
    
    if (dispositivoMovilAtributo != null) {
        esDispositivoMovil = (boolean) dispositivoMovilAtributo;
        
    }
  
   if(esDispositivoMovil && !logged) { 
    response.sendRedirect("LoginMovil");
    }
%>
   
    

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">


            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.jsp">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-tree"></i>
                </div>
                <div class="sidebar-brand-text mx-3">Turismo.uy </div>

            </a>


            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Gestion
            </div>

            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
                 <%
                            if(esDispositivoMovil==false){
                            %>
                <!-- Usuarios -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUsuarios"
                       aria-expanded="true" aria-controls="collapseUsuarios">
                        <span>Usuarios</span>
                    </a>
                    <div id="collapseUsuarios" class="collapse" aria-labelledby="headingUsuarios"  data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            
                            <a class="collapse-item" href="usuarios">Ver usuarios</a>
                           
                        </div>
                    </div>
                </li>

                <hr class="sidebar-divider">
                 <%}%>
                 
                  <%
                            if(esDispositivoMovil==false){
                            %>
                <!-- Paquetes -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePaquetes"
                       aria-expanded="true" aria-controls="collapsePaquetes">
                        <span>Paquetes</span>
                    </a>
                    <div id="collapsePaquetes" class="collapse" aria-labelledby="headingPaquetes" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            
           
                                     <%
                                if (logged && sys2.getTipo(usuarioLoggeado.getNick()).equals("turista") && esDispositivoMovil==false){
                            %>
                            <a class="collapse-item" href="comprapaquete">Compra de paquete</a>
                            <%
                                }
                            %>
                             <%
                            if(esDispositivoMovil==false){
                            %>
                            <a class="collapse-item" href="paquetes">Consultas de paquete <br> de actividades turísticas</a>
                            <%}%>
                            
                        </div>
                    </div>
                </li>
                <hr class="sidebar-divider">
                <%}%>

                
                <!-- Salidas Turísticas -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseSalidas"
                       aria-expanded="true" aria-controls="collapseSalidas">
                        <span>Salidas Turísticas</span>
                    </a>
                    <div id="collapseSalidas" class="collapse" aria-labelledby="headingSalidas" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                    
                            
                                  <%
                                if (logged && sys2.getTipo(usuarioLoggeado.getNick()).equals("proveedor")&& esDispositivoMovil==false) {
                            %>
                            <a class="collapse-item" href="SvAltaSalidaTuristica">Alta de salida Turística</a>
                            <%
                                }
                            %>
                            
                           
                            <!-- MOVILaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa   
                           
                            -->
                             <%
                            if(esDispositivoMovil==false){
                            %>
                            <a class="collapse-item" href="salidasturisticas">Consulta de salida Turística</a>
                           <%}%>
                            
                            
                            <%
                                
                                System.out.println("esDispositivoMovil: " + esDispositivoMovil);
                                if (logged && sys2.getTipo(usuarioLoggeado.getNick()).equals("turista") && esDispositivoMovil == true) {
                                System.out.println("ENTRA AL IFF");
                            %>
                            <a class="collapse-item" href="salidasturisticas">Consulta de salida Turística</a>
                            <%
                                }
                            %>

                            
                            
                                                 <%
                                if (logged && sys2.getTipo(usuarioLoggeado.getNick()).equals("turista")&& esDispositivoMovil==false) {
                            %>
                            <a class="collapse-item" href="salidasturisticas">Inscripción a salida Turística</a>
                            <%
                                }
                            %>
                            
                            
                            
                            
                        </div>
                    </div>
                </li>

                <hr class="sidebar-divider">
                <!-- Actividades Turísticas -->
                <li class="nav-item">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseActividades"
                       aria-expanded="true" aria-controls="collapseActividades">
                        <span>Actividades Turísticas</span>
                    </a>
                    <div id="collapseActividades" class="collapse" aria-labelledby="headingActividades" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            
                            
                                                <%
                                if (logged && sys2.getTipo(usuarioLoggeado.getNick()).equals("proveedor")&& esDispositivoMovil==false) {
                            %>
                            <a class="collapse-item" href="crearactividad">Alta de actividades turísticas</a>
                            <%
                                }
                            if(esDispositivoMovil==false){
                            %>
                           
                              <a class="collapse-item" href="actividadestur">Consulta de actividades</a>
                           
                            <!-<!-- ACAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA -->
                            <%
                                }
                            if(logged && sys2.getTipo(usuarioLoggeado.getNick()).equals("turista")&& esDispositivoMovil==true){
                            %>
                            <a class="collapse-item" href="actividadestur">Consulta de actividades</a>
                           <%}%>

                        </div>
                    </div>
                </li>


                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">
            </ul>



        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

         <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <!-- Topbar Search -->
                    <div
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                        <div class="input-group">
                            <input type="text" id="searchInput" class="form-control bg-light border-0 small" placeholder="Buscar..."
                                   aria-label="Search" aria-describedby="basic-addon2" onkeypress="handleKeyPress(event)">
                            <div class="input-group-append">
                                <button class="btn btn-primary" onclick="buscar()" type="button">
                                    <i class="fas fa-search fa-sm"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <script>
                        function buscar() {
                            var searchInpurt = document.getElementById("searchInput");
                            let texto = searchInpurt.value;

                            window.location.href = "busqueda.jsp?texto=" + encodeURIComponent(texto);

                        }
                        function handleKeyPress(event) {
                            // Verifica si la tecla presionada es Enter (código 13)
                            if (event.keyCode === 13) {
                                // Llama a la función buscar() si se presiona Enter
                                buscar();
                            }
                        }
                    </script>
                    
                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                                            <!-- Nav Item - User Information -->
                          <li class="nav-item dropdown no-arrow">
                                <%
                                    
                                    String pfpUsuario = "img/user-not-verified.png";
                                   
                                
                                    if (logged) {
                                        if(usuarioLoggeado.getImagenP() != null){
                                        pfpUsuario = usuarioLoggeado.getImagenP();
                                    }
                                %>
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">
                                        <%=nick%>
                                        <%} else {%>
                                        <a class="nav-link" href="login" id="iniciarSesionBtn" role="button"
                                           aria-haspopup="true" aria-expanded="false">
                                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">
                                                Iniciar sesión<%}%>

                                            </span>
                                            <img class="img-profile rounded-circle"
                                                 src="<%if (logged) {%><%=pfpUsuario%><%} else {%>img/user-not-verified.png<%}%>">
                                        </a>
                                    <!-- Dropdown - User Information -->
                                    <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                         aria-labelledby="userDropdown">
                                        <a class="dropdown-item" href="modificarusuario">
                                            <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                            Modificar datos
                                        </a>
                                       
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                            <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                            Logout
                                        </a>
                                    </div>
                                    </li>
                                    <% if (!logged) {%>
                                    <li class="nav-item  no-arrow">


                                        <a class="nav-link" href="registrarse" id="iniciarSesionBtn" role="button"
                                           aria-haspopup="true" aria-expanded="false">
                                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">
                                                Registrarse
                                            </span>
                                        </a>
                                    </li>
                                    <%}%>
                                    </ul>

                                    </nav>
                                    <!-- Begin Page Content -->
                                    <div class="container-fluid">


                                        <!-- Footer -->
                                        <footer class="sticky-footer bg-white">
                                            <div class="container my-auto">
                                                <div class="copyright text-center my-auto">

                                                </div>
                                            </div>