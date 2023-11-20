/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


  // Función para mostrar el mensaje de error
  function mostrarError(mensaje) {
    var errorMessage = document.getElementById("error-message");
    errorMessage.innerText = mensaje;
  }

  // Esta función se ejecutará cuando se envíe el formulario
  function onSubmitForm() {
    // Realiza una petición AJAX para enviar las credenciales al servidor
    // ...

    // Cuando se reciba la respuesta del servidor
    // Si se recibe "Credenciales incorrectas", muestra el mensaje de error
    if (respuestaDelServidor === "Credenciales incorrectas") {
      mostrarError("Credenciales incorrectas. Inténtalo de nuevo.");
    } else {
      // Procesa la respuesta exitosa
      // ...
    }
  }
