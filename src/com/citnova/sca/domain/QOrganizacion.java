package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QOrganizacion is a Querydsl query type for Organizacion
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrganizacion extends EntityPathBase<Organizacion> {

    private static final long serialVersionUID = 724695600L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrganizacion organizacion = new QOrganizacion("organizacion");

    public final QDireccion direccion;

    public final StringPath giroOrg = createString("giroOrg");

    public final SetPath<Gratuito, QGratuito> gratuitoSet = this.<Gratuito, QGratuito>createSet("gratuitoSet", Gratuito.class, QGratuito.class, PathInits.DIRECT2);

    public final NumberPath<Integer> idOrg = createNumber("idOrg", Integer.class);

    public final SetPath<MembresiaOrganizacion, QMembresiaOrganizacion> membresiaOrganizacionSet = this.<MembresiaOrganizacion, QMembresiaOrganizacion>createSet("membresiaOrganizacionSet", MembresiaOrganizacion.class, QMembresiaOrganizacion.class, PathInits.DIRECT2);

    public final StringPath nombreOrg = createString("nombreOrg");

    public final NumberPath<Integer> numTrabajadoresOrg = createNumber("numTrabajadoresOrg", Integer.class);

    public final SetPath<OrganizacionCliente, QOrganizacionCliente> organizacionClienteSet = this.<OrganizacionCliente, QOrganizacionCliente>createSet("organizacionClienteSet", OrganizacionCliente.class, QOrganizacionCliente.class, PathInits.DIRECT2);

    public final StringPath rfcOrg = createString("rfcOrg");

    public final QSectorEmp sectorEmp;
    
    public final DateTimePath<java.sql.Timestamp> fhCreaOrg = createDateTime("fhCreaOrg", java.sql.Timestamp.class);

    public final StringPath siglasOrg = createString("siglasOrg");

    public final StringPath telefonoOrg = createString("telefonoOrg");

    public final StringPath webOrg = createString("webOrg");

    public QOrganizacion(String variable) {
        this(Organizacion.class, forVariable(variable), INITS);
    }

    public QOrganizacion(Path<? extends Organizacion> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrganizacion(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrganizacion(PathMetadata<?> metadata, PathInits inits) {
        this(Organizacion.class, metadata, inits);
    }

    public QOrganizacion(Class<? extends Organizacion> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.direccion = inits.isInitialized("direccion") ? new QDireccion(forProperty("direccion"), inits.get("direccion")) : null;
        this.sectorEmp = inits.isInitialized("sectorEmp") ? new QSectorEmp(forProperty("sectorEmp")) : null;
    }

}

