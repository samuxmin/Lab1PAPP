package logica;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.SalidasTuristicas;
import logica.Turista;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-16T19:48:10", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Inscripcion_general.class)
public class Inscripcion_general_ { 

    public static volatile SingularAttribute<Inscripcion_general, LocalDate> fechaCompra;
    public static volatile SingularAttribute<Inscripcion_general, Double> costoGeneral;
    public static volatile CollectionAttribute<Inscripcion_general, Turista> turista;
    public static volatile SingularAttribute<Inscripcion_general, Long> id;
    public static volatile SingularAttribute<Inscripcion_general, Integer> cantidad;
    public static volatile SingularAttribute<Inscripcion_general, SalidasTuristicas> salida;

}