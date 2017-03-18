package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUso is a Querydsl query type for Uso
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUso extends EntityPathBase<Uso> {

    private static final long serialVersionUID = 1246582981L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUso uso = new QUso("uso");

    public final QCliente cliente;

    public final NumberPath<Integer> idUso = createNumber("idUso", Integer.class);

    public final NumberPath<Integer> tiempoRestanteUso = createNumber("tiempoRestanteUso", Integer.class);

    public final SetPath<Visita, QVisita> visitaSet = this.<Visita, QVisita>createSet("visitaSet", Visita.class, QVisita.class, PathInits.DIRECT2);

    public QUso(String variable) {
        this(Uso.class, forVariable(variable), INITS);
    }

    public QUso(Path<? extends Uso> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUso(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUso(PathMetadata<?> metadata, PathInits inits) {
        this(Uso.class, metadata, inits);
    }

    public QUso(Class<? extends Uso> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cliente = inits.isInitialized("cliente") ? new QCliente(forProperty("cliente"), inits.get("cliente")) : null;
    }

}

