package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QOrganizacionCliente is a Querydsl query type for OrganizacionCliente
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QOrganizacionCliente extends EntityPathBase<OrganizacionCliente> {

    private static final long serialVersionUID = 37572010L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrganizacionCliente organizacionCliente = new QOrganizacionCliente("organizacionCliente");

    public final StringPath cargoOC = createString("cargoOC");

    public final StringPath passOC = createString("passOC");

    public final QOrganizacionClienteId pk;

    public final StringPath statusOC = createString("statusOC");

    public QOrganizacionCliente(String variable) {
        this(OrganizacionCliente.class, forVariable(variable), INITS);
    }

    public QOrganizacionCliente(Path<? extends OrganizacionCliente> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrganizacionCliente(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QOrganizacionCliente(PathMetadata<?> metadata, PathInits inits) {
        this(OrganizacionCliente.class, metadata, inits);
    }

    public QOrganizacionCliente(Class<? extends OrganizacionCliente> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pk = inits.isInitialized("pk") ? new QOrganizacionClienteId(forProperty("pk"), inits.get("pk")) : null;
    }

}

