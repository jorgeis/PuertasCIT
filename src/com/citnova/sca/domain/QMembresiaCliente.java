package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMembresiaCliente is a Querydsl query type for MembresiaCliente
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMembresiaCliente extends EntityPathBase<MembresiaCliente> {

    private static final long serialVersionUID = -421028837L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMembresiaCliente membresiaCliente = new QMembresiaCliente("membresiaCliente");

    public final QMembresiaClienteId pk;

    public final StringPath statusMC = createString("statusMC");

    public QMembresiaCliente(String variable) {
        this(MembresiaCliente.class, forVariable(variable), INITS);
    }

    public QMembresiaCliente(Path<? extends MembresiaCliente> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMembresiaCliente(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMembresiaCliente(PathMetadata<?> metadata, PathInits inits) {
        this(MembresiaCliente.class, metadata, inits);
    }

    public QMembresiaCliente(Class<? extends MembresiaCliente> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.pk = inits.isInitialized("pk") ? new QMembresiaClienteId(forProperty("pk"), inits.get("pk")) : null;
    }

}

