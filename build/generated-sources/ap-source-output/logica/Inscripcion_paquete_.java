package logica;

import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Paquete;
import logica.Turista;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-16T17:52:34", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Inscripcion_paquete.class)
public class Inscripcion_paquete_ { 

    public static volatile SingularAttribute<Inscripcion_paquete, LocalDate> vencimiento;
    public static volatile SingularAttribute<Inscripcion_paquete, LocalDate> fechaCompra;
    public static volatile CollectionAttribute<Inscripcion_paquete, Turista> turista;
    public static volatile SingularAttribute<Inscripcion_paquete, Float> costoPaquete;
    public static volatile SingularAttribute<Inscripcion_paquete, Long> id;
    public static volatile SingularAttribute<Inscripcion_paquete, Integer> cantidad;
    public static volatile SingularAttribute<Inscripcion_paquete, Paquete> paquete;
    public static volatile SingularAttribute<Inscripcion_paquete, String> email;

}