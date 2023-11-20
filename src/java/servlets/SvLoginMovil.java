package servlets;

import controladores.Sistema;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import webservice.DataUsuario;
import webservice.Proveedor;
import webservice.Usuario;

@WebServlet(name = "SvLoginMovil", urlPatterns = {"/LoginMovil"})
public class SvLoginMovil extends HttpServlet {

    Sistema sys = new Sistema();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.getRequestDispatcher("/LoginMovil.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession sesion = request.getSession();

        if (sys.validarCredenciales(email, password)) {
            sesion.setAttribute("usuario", email);

            DataUsuario usr = null;

            try {
                usr = sys.verInfoUsuario(email);
            } catch (Exception ex) {
                Logger.getLogger(SvLoginMovil.class.getName()).log(Level.SEVERE, null, ex);

            }
            String tipo = "";
            tipo = sys.getTipo(usr.getNick());
            if (tipo.equals("proveedor") || tipo.equals("Proveedor")) {
                sesion.setAttribute("logeado", false);
                //response.sendError(401,"Credenciales_incorrectas");
                response.sendRedirect("LoginMovil?msg=" + "reintentar");
                return;
            
            }
            sesion.setAttribute("nick", usr.getNick());

            sesion.setAttribute("tipoUsuario", usr.getTipo());
            System.out.println(usr.getTipo());
            sesion.setAttribute("nick", usr.getNick());

            sesion.setAttribute("logeado", true);

            response.setStatus(200);
            System.out.println("LLEGO AL LOGIMN");
            response.sendRedirect("index.jsp");

        } else {
            sesion.setAttribute("logeado", false);
            //response.sendError(401,"Credenciales_incorrectas");
            response.sendRedirect("LoginMovil?msg=" + "reintentar");
            System.out.println("email" + email);
            System.out.println(password);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
