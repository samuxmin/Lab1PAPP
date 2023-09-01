package logica;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Inscripcion_general;
import logica.Inscripcion_paquete;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-01T19:34:51", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Turista.class)
public class Turista_ extends Usuario_ {

    public static volatile CollectionAttribute<Turista, Inscripcion_paquete> inscripcionpaquete;
    public static volatile CollectionAttribute<Turista, Inscripcion_general> inscripciongeneral;
    public static volatile SingularAttribute<Turista, String> nacionalidad;

}