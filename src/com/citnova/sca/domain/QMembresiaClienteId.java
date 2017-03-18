package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMembresiaClienteId is a Querydsl query type for MembresiaClienteId
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QMembresiaClienteId extends BeanPath<MembresiaClienteId> {

    private static final long serialVersionUID = -881784170L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMembresiaClienteId membresiaClienteId = new QMembresiaClienteId("membresiaClienteId");

    public final QCliente cliente;

    public final QMembresia membresia;

    public QMembresiaClienteId(String variable) {
        this(MembresiaClienteId.class, forVariable(variable), INITS);
    }

    public QMembresiaClienteId(Path<? extends MembresiaClienteId> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMembresiaClienteId(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMembresiaClienteId(PathMetadata<?> metadata, PathInits inits) {
        this(MembresiaClienteId.class, metadata, inits);
    }

    public QMembresiaClienteId(Class<? extends MembresiaClienteId> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cliente = inits.isInitialized("cliente") ? new QCliente(forProperty("cliente"), inits.get("cliente")) : null;
        this.membresia = inits.isInitialized("membresia") ? new QMembresia(forProperty("membresia"), inits.get("membresia")) : null;
    }

}

