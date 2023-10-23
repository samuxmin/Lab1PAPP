package main;

import presentacion.Principal;
import controladores.Fabrica;
import controladores.ISistema;
import excepciones.UsuarioRepetidoException;
import java.util.List;
import logica.ActividadTuristica;


public class Lab1G2 {

    public static void main(String[] args) throws UsuarioRepetidoException {
        Fabrica f = new Fabrica();
        ISistema sys = f.getSistema();
        

        sys.getObjectsFromDB();



        Principal programa = new Principal();
        programa.setVisible(true);
        programa.setLocationRelativeTo(null);
        List<ActividadTuristica> actquery =sys.getAllActsFromBD();
    }

}
