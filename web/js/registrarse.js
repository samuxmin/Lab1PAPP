


var radioTurista = document.getElementById("flexRadioDefault1");
var radioProveedor = document.getElementById("flexRadioDefault2");
let inputsAdicionales = document.getElementById("inputsAdicionales");

document.getElementById("inputGroupFile01").addEventListener("change", function () {
    var input = this;
    var label = input.nextElementSibling;
    var fileName = input.files[0].name;

    if (fileName) {
        label.textContent = fileName;
    } else {
        label.textContent = "Seleccionar archivo...";
    }
});

radioTurista.addEventListener("change", (e) => {
    if (radioTurista.checked) {
        mostrarInputTuristas();

        document.getElementById("tipoUsuario").value = "turista";
    }
});

radioProveedor.addEventListener("change", (e) => {
    if (radioProveedor.checked) {
        mostrarInputProveedor();

        document.getElementById("tipoUsuario").value = "proveedor";
    }
});

function mostrarInputTuristas() {

    let htmltur = '<div class="form-group"><input required type="text" class="form-control form-control-user" id="nacionalidad" name="nacionalidad"placeholder="Nacionalidad"></div>';
    inputsAdicionales.innerHTML = htmltur;
}

function mostrarInputProveedor() {
    let htmlpro = '<div class="form-group"><input required type="text" class="form-control form-control-user" id="sitioweb" name="sitioweb"placeholder="Sitio Web"></div> <div class="input-group"><div class="input-group-prepend"><span class="input-group-text">Descripción</span></div><textarea class="form-control" aria-label="With textarea" id="descripcion" name="descripcion"></textarea></div>'; //<div class="form-group"><input type="text" class="form-control form-control-user" id="descripcion" name="descripcion"placeholder="Descripcion"></div>';
    inputsAdicionales.innerHTML = htmlpro;

}

function registrar() {
    let nick, email, nombre, apellido, tipo, nacionalidad, sitio, descripcion, password, repetirPassword, fecNac;

    nick = document.getElementById("nick").value;
    email = document.getElementById("email").value;
    nombre = document.getElementById("nombre").value;
    apellido = document.getElementById("apellido").value;
    fecNac = new Date(document.getElementById("fecha").value);
    password = document.getElementById("password").value;
    repetirPassword = document.getElementById("repetirpassword").value;
    // Obtén una referencia a los elementos de radio por su nombre
    radioTurista = document.getElementById("flexRadioDefault1");
    radioProveedor = document.getElementById("flexRadioDefault2");


// Verifica si el radio "Turista" está marcado
    if (radioTurista.checked) {
        tipo = "Turista";
    } else if (radioProveedor.checked) {
        // Verifica si el radio "Proveedor" está marcado

        tipo = "Proveedor";

    } else {
        alert("Debes marcar si es proveedor o turista");
        return false;
    }

    if (nick === "" || email === "" || nombre === "" || apellido === "" || password === "" || repetirPassword === "") {
        alert("Faltan campos por completar");
        return false;
    }
    if (tipo === "Turista") {
        nacionalidad = document.getElementById("nacionalidad").value;
        if (nacionalidad === "") {

            alert("Faltan campos por completar");
            return false;
        }
    } else if (tipo === "Proveedor") {
        sitio = document.getElementById("sitioweb").value;
        descripcion = document.getElementById("descripcion").value;
        if (sitio === "" || descripcion === "") {

            alert("Faltan campos por completar");
            return false;
        }


    }

    if (password !== repetirPassword) {
        alert("Las contraseñas no coinciden");
        return false;
    }
    return true;

}

let registroForm = document.getElementById("registroForm");

registroForm.addEventListener("submit", (e) => {
    if (!registrar()) {
        e.preventDefault();
        return;
    }

});