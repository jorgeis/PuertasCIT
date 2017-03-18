package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMembresiaOrganizacionId is a Querydsl query type for MembresiaOrganizacionId
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QMembresiaOrganizacionId extends BeanPath<MembresiaOrganizacionId> {

    private static final long serialVersionUID = -1367210530L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMembresiaOrganizacionId membresiaOrganizacionId = new QMembresiaOrganizacionId("membresiaOrganizacionId");

    public final QMembresia membresia;

    public final QOrganizacion organizacion;

    public QMembresiaOrganizacionId(String variable) {
        this(MembresiaOrganizacionId.class, forVariable(variable), INITS);
    }

    public QMembresiaOrganizacionId(Path<? extends MembresiaOrganizacionId> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMembresiaOrganizacionId(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMembresiaOrganizacionId(PathMetadata<?> metadata, PathInits inits) {
        this(MembresiaOrganizacionId.class, metadata, inits);
    }

    public QMembresiaOrganizacionId(Class<? extends MembresiaOrganizacionId> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.membresia = inits.isInitialized("membresia") ? new QMembresia(forProperty("membresia"), inits.get("membresia")) : null;
        this.organizacion = inits.isInitialized("organizacion") ? new QOrganizacion(forProperty("organizacion"), inits.get("organizacion")) : null;
    }

}

