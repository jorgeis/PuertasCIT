package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMembresiaOrganizacion is a Querydsl query type for MembresiaOrganizacion
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMembresiaOrganizacion extends EntityPathBase<MembresiaOrganizacion> {

    private static final long serialVersionUID = 923715939L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMembresiaOrganizacion membresiaOrganizacion = new QMembresiaOrganizacion("membresiaOrganizacion");

    public final QMembresiaOrganizacionId pk;

    public final StringPath statusMO = createString("statusMO");

    public QMembresiaOrganizacion(String variable) {
        this(MembresiaOrganizacion.class, forVariable(variable), INITS);
    }

    public QMembresiaOrganizacion(Path<? extends MembresiaOrganizacion> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMembresiaOrganizacion(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMembresiaOrganizacion(PathMetadata<?> metadata, PathInits inits) {
        this(MembresiaOrganizacion.class, metadata, inits);
    }

    public QMembresiaOrganizacion(Class<? extends MembresiaOrganizacion> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pk = inits.isInitialized("pk") ? new QMembresiaOrganizacionId(forProperty("pk"), inits.get("pk")) : null;
    }

}

