package servlets;



import controladores.Sistema;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import webservice.DataUsuario;
import webservice.Usuario;

@WebServlet(name = "SvModificarUsuario", urlPatterns = {"/modificarusuario"})
@MultipartConfig
public class SvModificarUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.getRequestDispatcher("/modificarusuario.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        Sistema sys3 = new Sistema();
        Object nickAtributo = request.getSession().getAttribute("nick");

        String nickUsuario = null;
        DataUsuario usr3 = null;
        LocalDate fechaNacimiento = null;

        if (nickAtributo != null) {
            nickUsuario = (String) nickAtributo;
            usr3 = sys3.verInfoUsuarioNick(nickUsuario);
        }

        if (usr3 != null) {
            try {
                String nuevoNombre = request.getParameter("nombre");
                String nuevoApellido = request.getParameter("apellido");
                String nuevaFechaNacimiento = request.getParameter("fecNac");

                // Procesa la parte del archivo (imagen de perfil)
                Part filePart = request.getPart("imagenPerfil");
                String uploadPath = null;
                String filePath = null;
                String rutaimg = null;
                if (filePart != null) {
                    // Define la ruta donde deseas guardar el archivo en el servidor
                    //! IMPORTANTE LEER
                    //CAMBIAR LA RUTA EN LA CLASE RutaProyecto en logica
                    uploadPath = getServletContext().getRealPath("") +"../.." +File.separator +"imagenes_perfil"; //RutaProyecto.getRuta() + "imagenes_perfil/";   //

                    uploadPath = getServletContext().getRealPath("") + File.separator + "imagenes_perfil";
                    // Obtén el nombre original del archivo
                    String fileName = nickUsuario;

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
                    //                   File uploadDir = new File(uploadPath);
                    //                  if (!uploadDir.exists()) {
                    //                    uploadDir.mkdirs();
                    //              }
                    rutaimg = "imagenes_perfil/" + nickUsuario + "." + imageFormat;

                    if (imageFormat.equals("octet-stream")) {
                        rutaimg = usr3.getImagenP();
                    }

                    File existingFile = new File(filePath);
                    if (existingFile.exists()) {
                        existingFile.delete();
                    }
// Escribe el archivo en el directorio de carga
                    try (InputStream input = filePart.getInputStream()) {
                        Files.copy(input, new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                }

                // Convierte la cadena de fecha en LocalDate
                if (nuevaFechaNacimiento != null && !nuevaFechaNacimiento.isEmpty()) {
                    fechaNacimiento = LocalDate.parse(nuevaFechaNacimiento);
                }

                // Establece los atributos en la solicitud
                request.setAttribute("usr3", usr3);
                request.setAttribute("fechaNacimiento", fechaNacimiento);

                System.out.println("\n");
                System.out.println(nuevoNombre);
                System.out.println("\n");
                if (nuevoNombre.isEmpty()) {
                    nuevoNombre = usr3.getNombre();
                }
                System.out.println(nuevoNombre);
                if (nuevoApellido.isEmpty()) {
                    nuevoApellido = usr3.getApellido();
                }
                if (nuevaFechaNacimiento.isEmpty()) {
                    fechaNacimiento = LocalDate.parse(sys3.getFecNacUsr(usr3.getCorreo()));
                }
                //if(rutaimg==null){
                //  rutaimg=usr3.getImagenP();
                //}

                // Llama a la función para modificar los datos del usuario
                sys3.modificarDatosUsuario(usr3.getCorreo(), nuevoNombre, nuevoApellido, fechaNacimiento, rutaimg);

                response.sendRedirect("index.jsp?msg=usrmodificado");

            } catch (Exception ex) {
                //response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
