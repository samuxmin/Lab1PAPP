/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;


import controladores.Sistema;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import logica.RutaProyecto;

/**
 *
 * @author samuel
 */
@MultipartConfig
@WebServlet(name = "SvRegistro", urlPatterns = {"/registrarse"})
public class SvRegistro extends HttpServlet {

    Sistema sys = new Sistema();

  

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.getRequestDispatcher("/registrarse.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String email = request.getParameter("email");

        String nick = request.getParameter("nick");
        System.out.println(nick + email);
        try {
            if (sys.verInfoUsuario(email) != null) {
                //response.sendError(409, "Email en uso");
                response.sendRedirect("registrarse?err=email");
                return;
            }
            if (sys.verInfoUsuarioNick(nick) != null) {
                //response.sendError(409, "Nick en uso");
                
                response.sendRedirect("registrarse?err=nick");
                return;
            }
        } catch (Exception ex) {

        }
        String tipo = request.getParameter("tipoUsuario");

        String password = request.getParameter("password");

        String nombre = request.getParameter("nombre");

        String apellido = request.getParameter("apellido");

        String fechaStr = request.getParameter("fecha");

        LocalDate fecha = LocalDate.parse(fechaStr);
        String descripcion = null, sitio = null, nacionalidad = null;
        switch (tipo) {
            case "turista":
                nacionalidad = request.getParameter("nacionalidad");
                break;
            case "Proveedor":
                tipo = "proveedor";
            case "proveedor":
                sitio = request.getParameter("sitioweb");
                descripcion = request.getParameter("descripcion");
                break;
            default:
                response.sendError(400, "tipo incorrecto de usuario");
                break;
        }
        // Obtén la parte del archivo de la solicitud
        String uploadPath = null;
        String filePath = null;
        String rutaimg = null;
        Part filePart = request.getPart("imagenP");
        
        if (filePart != null) {
// Define la ruta donde deseas guardar el archivo en el servidor
//! IMPORTANTE LEER
  //CAMBIAR LA RUTA EN LA CLASE RutaProyecto en logica
    uploadPath = RutaProyecto.getRuta()+"imagenes_perfil";   
    uploadPath = getServletContext().getRealPath("")  +File.separator +"imagenes_perfil";
            
            
// Obtén el nombre original del archivo
            String fileName = nick;

// Obtiene el tipo de contenido (MIME type) de la imagen
            String contentType = filePart.getContentType();

// A partir del tipo de contenido, puedes extraer el formato de la imagen
            String[] contentParts = contentType.split("/");
            String imageFormat = contentParts[1];

// Concatena el formato al nombre de archivo
            fileName = fileName + "." + imageFormat;

// Crea la ruta completa donde se guardará el archivo, incluyendo el nombre y formato
             filePath = uploadPath + File.separator + fileName;

// Crea el directorio si no existe
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            rutaimg = "imagenes_perfil/" + nick +"."+ imageFormat;
// Escribe el archivo en el directorio de carga
            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }
// Ahora la imagen se ha guardado correctamente en el servidor

// En este punto, la imagen se ha guardado en la carpeta "uploads" en el servidor
        try {
            sys.registrarUsuario(nick, nombre, apellido, email, fecha, tipo, nacionalidad, descripcion, sitio, rutaimg,password);

        } catch (Exception ex) {
            Logger.getLogger(SvRegistro.class.getName()).log(Level.SEVERE, null, ex);

            return;
        }
        response.setStatus(202);
        response.sendRedirect("login?msg=usrcreado");
        System.out.println("correcto");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
