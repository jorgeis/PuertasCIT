package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrganizacionClienteId is a Querydsl query type for OrganizacionClienteId
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QOrganizacionClienteId extends BeanPath<OrganizacionClienteId> {

    private static final long serialVersionUID = 1746965605L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrganizacionClienteId organizacionClienteId = new QOrganizacionClienteId("organizacionClienteId");

    public final QCliente cliente;

    public final QOrganizacion organizacion;

    public QOrganizacionClienteId(String variable) {
        this(OrganizacionClienteId.class, forVariable(variable), INITS);
    }

    public QOrganizacionClienteId(Path<? extends OrganizacionClienteId> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrganizacionClienteId(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrganizacionClienteId(PathMetadata<?> metadata, PathInits inits) {
        this(OrganizacionClienteId.class, metadata, inits);
    }

    public QOrganizacionClienteId(Class<? extends OrganizacionClienteId> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cliente = inits.isInitialized("cliente") ? new QCliente(forProperty("cliente"), inits.get("cliente")) : null;
        this.organizacion = inits.isInitialized("organizacion") ? new QOrganizacion(forProperty("organizacion"), inits.get("organizacion")) : null;
    }

}

