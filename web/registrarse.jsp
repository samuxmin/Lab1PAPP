<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Registro</title>

        <!-- Custom fonts for this template-->
        <link href="ComponentsBoostrap/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="ComponentsBoostrap/css/sb-admin-2.min.css" rel="stylesheet">

    </head>
    <%
        String err = request.getParameter("err");
        String mensaje = "";
        if (err != null) {
            if (err.equals("email")) {
                mensaje = "Correo electrónico en uso";
            } else if (err.equals("nick")) {
                mensaje = "Nickname en uso";
            }
        }
    %>
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
                                    <h1 class="h4 text-gray-900 mb-4">Registrarse</h1>
                                </div>
                                <form action="registrarse" id="registroForm" class="user" method="POST"  enctype="multipart/form-data">
                                    <%if (err != null) {%>
                                    <div class="alert alert-warning" role="alert" id="alerta"><%=mensaje%></div>
                                    <%}%>
                                    <div class="form-group">
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text" id="inputGroupFileAddon01">Imagen de perfil</span>
                                            </div>
                                            <div class="custom-file">
                                                <input type="file" class="custom-file-input" id="inputGroupFile01" name="imagenP" aria-describedby="inputGroupFileAddon01" accept="image/*">
                                                <label class="custom-file-label" for="inputGroupFile01">Subir imagen</label>
                                            </div>
                                        </div>


                                        <div class="form-group">
                                            <div class="col-sm-6 mb-3 mb-sm-0">
                                                <input required type="text" class="form-control form-control-user" id="nick"
                                                       name="nick"
                                                       placeholder="Nick">
                                            </div>
                                            <span id="msgverificar"></span>


                                        </div>
                                        <div class="form-group row">
                                            <div class="col-sm-6 mb-3 mb-sm-0">
                                                <input required type="text" class="form-control form-control-user" id="nombre" name="nombre"
                                                       placeholder="Nombre">
                                            </div>
                                            <div class="col-sm-6">
                                                <input required type="text" class="form-control form-control-user" id="apellido" name="apellido"
                                                       placeholder="Apellido">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <input required type="email" class="form-control form-control-user" id="email" name="email"
                                                   placeholder="Email">
                                        </div>
                                        <span id="msgverificarmail"></span>
                                        <div class="form-group">

                                            <input required type="date" class="form-control form-control-user"
                                                   id="fecha"  name="fecha" placeholder="Fecha de nacimiento">
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-sm-6 mb-3 mb-sm-0">
                                                <input required type="password" class="form-control form-control-user"
                                                       id="password"  name="password" placeholder="Contraseña">
                                            </div>
                                            <div class="col-sm-6">
                                                <input required type="password" class="form-control form-control-user"
                                                       id="repetirpassword" name="repetirpassword" placeholder="Repetir contraseña">
                                            </div>
                                        </div>
                                        <input required type="hidden" id="tipoUsuario" name="tipoUsuario" value="">

                                        <div class="form-check">
                                            <input required class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1">
                                            <label class="form-check-label" for="flexRadioDefault1">
                                                Turista
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input required class="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" >
                                            <label class="form-check-label" for="flexRadioDefault2">
                                                Proveedor
                                            </label>
                                        </div>

                                        <div id="inputsAdicionales"></div>










                                        <button class="btn btn-primary btn-user btn-block" >
                                            Registrar
                                        </button>
                                        <hr>

                                        </form>
                                        <hr>

                                        <div class="text-center">
                                            <a class="small" href="login">Already have an account? Login!</a>
                                        </div>
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <!-- Bootstrap core JavaScript-->
            <script>
                // Obtén la fecha actual en formato ISO (YYYY-MM-DD)
                const fechaActual = new Date().toISOString().split('T')[0];

                // Asigna la fecha actual como el valor máximo del campo de fecha
                document.getElementById('fecha').max = fechaActual;
                var campoNick = document.getElementById("nick");
                campoNick.addEventListener("blur", () => {
                    verificarNick(campoNick.value);

                });

                var campoMail = document.getElementById("email");
                campoMail.addEventListener("blur", () => {
                    verificarMail(campoMail.value);

                });
                function verificarNick(nick) {
                    if (nick !== "") {
                        var data = {
                            nick
                        };
                        $.ajax({
                            type: "POST",
                            url: "SvVerificarNickUsuario",
                            data: data,
                            success: function (response) {
                                console.log(response);
                                document.getElementById("msgverificar").innerText = response;
                            },
                            error: function (xhr, status, error) {
                                console.error(error);
                            }
                        });
                    }
                }

                function verificarMail(mail) {
                    if (mail !== "") {
                        var data = {
                            mail
                        };
                        $.ajax({
                            type: "POST",
                            url: "SvVerificarNickUsuario",
                            data: data,
                            success: function (response) {
                                console.log(response);
                                document.getElementById("msgverificarmail").innerText = response;
                            },
                            error: function (xhr, status, error) {
                                console.error(error);
                            }
                        });
                    }
                }
            </script>
            <script src="ComponentsBoostrap/vendor/jquery/jquery.min.js"></script>
            <script src="ComponentsBoostrap/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

            <!-- Core plugin JavaScript-->
            <script src="ComponentsBoostrap/vendor/jquery-easing/jquery.easing.min.js"></script>

            <!-- Custom scripts for all pages-->
            <script src="ComponentsBoostrap/js/sb-admin-2.min.js"></script>
            <script src="js/registrarse.js"></script>

    </body>

</html>