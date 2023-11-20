/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import controladores.Sistema;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Arrays;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


/**
 *
 * @author samuel
 */
@WebServlet(name = "SvAltaActividad", urlPatterns = {"/crearactividad"})
@MultipartConfig
public class SvAltaActividad extends HttpServlet {

    Sistema sys = new Sistema();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession sesion = request.getSession();
        
            request.getRequestDispatcher("/crearactividad.jsp").forward(request, response);


    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        // nombre costo duracion ciudad descripcion imagen
        HttpSession sesion = request.getSession();
        Object proveedor = sesion.getAttribute("usuario");
        if (proveedor == null) {
            response.sendError(401, "Debes estar logeado como Proveedor para utilizar esta pagina");
        }
        String correoProv = (String) proveedor;

        String nombre = request.getParameter("nombre");
        if (nombre == null) {
            response.sendError(400, "no hay nombre");
        }
        if (sys.selectActividad(nombre) != null) {
            response.sendRedirect("crearactividad?err=nombre");
        }

        String[] arrCats = {};
        String categorias = request.getParameter("catInput");
        if (categorias != null && !categorias.isEmpty()) {
            arrCats = categorias.split(",");
        }
        ArrayList<String> listCats = new ArrayList<>(Arrays.asList(arrCats));

        
        String urlVideo=null; //AVANZAR ACÁ POLPO
        
        String descripcion = request.getParameter("descripcion");
        String ciudad = request.getParameter("ciudad");
        String duracion = request.getParameter("duracion");
        String costo = request.getParameter("costo");
        String depto = request.getParameter("departamento");
        System.out.println(depto);
        if (descripcion == null || ciudad == null || duracion == null || costo == null || depto == null) {
            response.sendRedirect("crearactividad?err=campos");
        } else {
            // Obtén la parte del archivo de la solicitud
            String uploadPath = null;
            String filePath = null;
            String rutaimg = null;
            Part filePart = request.getPart("imagen");

            if (filePart != null) {
// Define la ruta donde deseas guardar el archivo en el servidor
//! IMPORTANTE LEER
                //CAMBIAR LA RUTA EN LA CLASE RutaProyecto en logica
                uploadPath = getServletContext().getRealPath("") + "imagenes_actividad";   // +"../.." +File.separator +"imagenes_perfil";

// Obtén el nombre original del archivo
                String fileName = nombre;

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
                rutaimg = "imagenes_actividad/" + nombre + "." + imageFormat;
// Escribe el archivo en el directorio de carga
                try (InputStream input = filePart.getInputStream()) {
                    Files.copy(input, new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println(filePath );
                }
            }
// Ahora la imagen se ha guardado correctamente en el servidor

// En este punto, la imagen se ha guardado en la carpeta "uploads" en el servidor

            try {
                sys.AltaActividadTuristica(correoProv, nombre, descripcion, Integer.parseInt(duracion), Integer.parseInt(costo), depto, LocalDate.now(), ciudad, listCats, rutaimg, urlVideo);
            }catch(Exception e){}
            response.sendRedirect("index.jsp?msg=altaactividad");
        }
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
