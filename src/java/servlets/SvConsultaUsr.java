package servlets;


import controladores.Sistema;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;


@WebServlet(name = "SvConsultaUsr", urlPatterns = {"/usuario"})
public class SvConsultaUsr extends HttpServlet {
    Sistema sys = new Sistema();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Tu lógica para manejar solicitudes GET
        request.getRequestDispatcher("/consultausuario.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recupera los datos enviados desde el cliente
    request.setCharacterEncoding("UTF-8");

    // Lee el JSON del cuerpo de la solicitud
    BufferedReader reader = request.getReader();
    StringBuilder jsonRequest = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
        jsonRequest.append(line);
    }

    // Analiza el JSON para obtener los datos
    JSONObject jsonData = new JSONObject(jsonRequest.toString());
    String accion = jsonData.getString("accion");
    String correoSeguidor = jsonData.getString("correoSeguidor");
    String correoSeguido = jsonData.getString("correoSeguido");

    
     if ("seguir".equals(accion)) {
            sys.seguirUsuario(correoSeguidor, correoSeguido);
        } else if ("dejarDeSeguir".equals(accion)) {
            sys.dejarDeSeguirUsuario(correoSeguidor,correoSeguido);
        }

        // Responde al cliente con un código de estado HTTP apropiado
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
