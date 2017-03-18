package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMembresia is a Querydsl query type for Membresia
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMembresia extends EntityPathBase<Membresia> {

    private static final long serialVersionUID = 1770494175L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMembresia membresia = new QMembresia("membresia");

    public final QAdmin admin;

    public final QCMembresia cMembresia;

    public final StringPath codigoMem = createString("codigoMem");

    public final ListPath<Contratacion, QContratacion> contratacionList = this.<Contratacion, QContratacion>createList("contratacionList", Contratacion.class, QContratacion.class, PathInits.DIRECT2);

    public final DateTimePath<java.sql.Timestamp> fhCreaMem = createDateTime("fhCreaMem", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> fhValidaMem = createDateTime("fhValidaMem", java.sql.Timestamp.class);

    public final NumberPath<Integer> idMem = createNumber("idMem", Integer.class);

    public final SetPath<MembresiaCliente, QMembresiaCliente> membresiaClienteSet = this.<MembresiaCliente, QMembresiaCliente>createSet("membresiaClienteSet", MembresiaCliente.class, QMembresiaCliente.class, PathInits.DIRECT2);

    public final SetPath<MembresiaOrganizacion, QMembresiaOrganizacion> membresiaOrganizacionSet = this.<MembresiaOrganizacion, QMembresiaOrganizacion>createSet("membresiaOrganizacionSet", MembresiaOrganizacion.class, QMembresiaOrganizacion.class, PathInits.DIRECT2);

    public final StringPath statusMem = createString("statusMem");

    public QMembresia(String variable) {
        this(Membresia.class, forVariable(variable), INITS);
    }

    public QMembresia(Path<? extends Membresia> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMembresia(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMembresia(PathMetadata<?> metadata, PathInits inits) {
        this(Membresia.class, metadata, inits);
    }

    public QMembresia(Class<? extends Membresia> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admin = inits.isInitialized("admin") ? new QAdmin(forProperty("admin"), inits.get("admin")) : null;
        this.cMembresia = inits.isInitialized("cMembresia") ? new QCMembresia(forProperty("cMembresia")) : null;
    }

}

