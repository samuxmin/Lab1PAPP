/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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

/**
 *
 * @author samuel
 */
@WebServlet(name = "SvUsuario", urlPatterns = {"/login"})
public class SvLogin extends HttpServlet {

    Sistema sys = new Sistema();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        request.getRequestDispatcher("/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession sesion = request.getSession();

        if ( sys.validarCredenciales(email, password) ) {
            sesion.setAttribute("usuario", email);
            DataUsuario usr = null;
            try {
                usr = sys.verInfoUsuario(email);
            } catch (Exception ex) {
                Logger.getLogger(SvLogin.class.getName()).log(Level.SEVERE, null, ex);
                
            }
            sesion.setAttribute("tipoUsuario", usr.getTipo());
            sesion.setAttribute("nick", usr.getNick());
         
            sesion.setAttribute("logeado", true);
            response.setStatus(200);
            response.sendRedirect("index.jsp");

        } else {
            sesion.setAttribute("logeado", false);
            //response.sendError(401,"Credenciales_incorrectas");
            response.sendRedirect("login?msg="+"reintentar");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
