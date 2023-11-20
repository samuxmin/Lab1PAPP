package servlets;


import controladores.Sistema;
import webservice.DataUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import webservice.ActividadTuristica;
import webservice.Paquete;


@WebServlet(name = "SvCompraPaquete", urlPatterns = {"/comprapaquete"})
public class SvCompraPaquete extends HttpServlet {
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
         request.getRequestDispatcher("/comprapaquete.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
         Sistema sys = new Sistema();
         
         HttpSession sesion = request.getSession();
         
        Object loggeadoAtributousr = request.getSession().getAttribute("logeado");
        DataUsuario usuarioLoggeadousr = null;
       
        boolean loggedusr = false;
        String nickusr = null;

        if (loggeadoAtributousr != null) {
            loggedusr = (boolean) loggeadoAtributousr;
        }

        if (loggedusr) {
            nickusr = (String) sesion.getAttribute("nick");
            usuarioLoggeadousr = sys.verInfoUsuarioNick(nickusr);

        }
      
       
        String cantidadParam = request.getParameter("Cantidad");
        int cantidad = 0;
        if (cantidadParam != null && !cantidadParam.isEmpty()) {
            
            cantidad = Integer.parseInt(cantidadParam);
            
        }
        
        
         //String vencimi = request.getParameter("Vencimiento");  
         String fechaSalida = request.getParameter("Fecha");
          
         String paquete=request.getParameter("nombrePaq");
 
        Paquete paq; 
        paq=sys.getPaqFromBD(paquete);
        int validez=paq.getValidez();
        int descuento=paq.getDescuento();
        
       LocalDate fechaSalidaDate = LocalDate.parse(fechaSalida);
       LocalDate vencimiento = fechaSalidaDate.plusDays(validez);
       
        double costoTotalActividades = 0;
        double total=0;
        List<String> actividades =  sys.getAllActsFromPaqFromBD(paquete);   
        ActividadTuristica act;
    
        System.out.println(cantidad);
         
        for(String actividad : actividades){
               act=sys.selectActFromDB(actividad);
               double costoPorTurista = act.getCostoPorTurista();
               costoTotalActividades += costoPorTurista;
 
        }
     
         
        total=(costoTotalActividades*cantidad)-((costoTotalActividades*cantidad)*descuento)/100.0;
        float totalD=(float) total;
       
       
      
        // sys.InscripcionPaquete(paquete,usuarioLoggeadousr.getCorreo(), paq.getDescuento(),cantidad,LocalDate.parse(vencimi), LocalDate.parse(fechaSalida));
         sys.InscripcionPaquete(paquete, usuarioLoggeadousr.getCorreo(),totalD, cantidad, vencimiento, fechaSalidaDate);      
           response.sendRedirect("comprapaquete.jsp");
      
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}