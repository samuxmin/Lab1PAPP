package logica;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.ActividadTuristica;
import logica.Departamento;
import logica.Paquete;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-09-03T18:21:35", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Proveedor.class)
public class Proveedor_ extends Usuario_ {

    public static volatile SingularAttribute<Proveedor, String> descripcion;
    public static volatile SingularAttribute<Proveedor, String> web;
    public static volatile CollectionAttribute<Proveedor, ActividadTuristica> actTuristica;
    public static volatile SingularAttribute<Proveedor, Departamento> departamento;
    public static volatile CollectionAttribute<Proveedor, Paquete> paquete;

}