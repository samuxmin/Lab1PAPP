/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import controladores.Sistema;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import webservice.DataUsuario;
import webservice.Paquete;

import webservice.SalidasTuristicas;


/**
 *
 * @author samuel
 */
@WebServlet(name = "SvInscripcionSalida", urlPatterns = {"/inscripcionsalida"})
public class SvInscripcionSalida extends HttpServlet {

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
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    
    if (action != null && action.equals("generatePDF")) {
        String touristName = request.getParameter("touristName");
        String tourName = request.getParameter("nombre");
        SalidasTuristicas sal = sys.getSalidaFromBD(tourName);
        
        if (sal != null) {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=turist_information.pdf");
            
            sys.generatePDF(response.getOutputStream(), touristName, sal.getActividadAsociada().getNombre(), tourName, LocalDate.parse(sys.getFechaSalida(sal.getNombreSalida())),LocalTime.parse( sys.getHoraSalida(sal.getNombreSalida())), sal.getCantInscritos());
        }
    } else {
        request.getRequestDispatcher("/inscripcionsalida.jsp").forward(request, response);
    }
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
        
        String mailTurista = request.getParameter("mailTur");
        String cant = request.getParameter("cantidad");
        String nombre = request.getParameter("nombreSalida");

        if (mailTurista == null || cant == null || nombre == null) {
            response.sendError(400, "Campos no validos");
            return;
        }
        try {
            DataUsuario usr = sys.verInfoUsuario(mailTurista);
        } catch (Exception ex) {
            response.sendError(400, "usuario no encontrado");
            return;
        }
        SalidasTuristicas salida = sys.getSalidaFromBD(nombre);
        
        if (salida == null) {
            response.sendError(400, "Salida no encontrada");
            return;
        }
        
        String actividad = salida.getActividadAsociada().getNombre();
        
        if (sys.estaInscritoTuristaSalida(mailTurista, nombre)) {
            response.sendError(400, "Ya estas inscrito");
            return;
        }
        if (salida.getCantidadMaximaTuristas() < (salida.getCantInscritos() + Integer.parseInt(cant))) {
            response.sendError(400, "Capacidad de salida maxima alcanzada");
            return;
        }
       
        int cantidad = Integer.parseInt(cant);
        String tipoInscr = request.getParameter("tipoInscr");
        String nombrePaq = request.getParameter("paqueteSeleccionado");
        double costo = cantidad * (salida.getActividadAsociada().getCostoPorTurista());
        if (tipoInscr.equals("paquete")) {

                if (sys.quedanPuestosPaqAct(mailTurista,nombrePaq,salida.getActividadAsociada().getNombre(),cantidad)) {
                    
                        response.sendError(400, "Capacidad del paquete maxima alcanzada para esa actividad");
                        
                        return;
                    }
                   
                
               
                
                    Paquete paq = sys.getPaqFromBD(nombrePaq);
                    costo = costo * paq.getDescuento();
                    
                    /*
                    ins.aumentarCantInscrUsadas(cantidad,actividad);

                    sys.mergeInscrPaq(ins);
*/
                


           
                sys.inscripcionSalida(mailTurista, nombre, cantidad, LocalDate.now());
                sys.confirmarInscripcion(Integer.parseInt(cant),  costo,"paquete");
                sys.aumentarUsosCompraPaq(Integer.parseInt(cant),mailTurista, nombrePaq);
                
        } else {
            sys.inscripcionSalida(mailTurista, nombre, cantidad, LocalDate.now());
            sys.confirmarInscripcion(cantidad, costo, "general");

        }
        response.sendRedirect("inscripcionsalida?nombre=" + nombre + "&touristName=" + mailTurista + "&action=generarPDF");

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