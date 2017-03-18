package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QVisita is a Querydsl query type for Visita
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QVisita extends EntityPathBase<Visita> {

    private static final long serialVersionUID = -1609003838L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVisita visita = new QVisita("visita");

    public final DateTimePath<java.sql.Timestamp> fhEntradaVis = createDateTime("fhEntradaVis", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> fhSalidaVis = createDateTime("fhSalidaVis", java.sql.Timestamp.class);

    public final NumberPath<Integer> idVis = createNumber("idVis", Integer.class);

    public final NumberPath<Integer> tiempoVis = createNumber("tiempoVis", Integer.class);

    public final QUso uso;

    public QVisita(String variable) {
        this(Visita.class, forVariable(variable), INITS);
    }

    public QVisita(Path<? extends Visita> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVisita(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QVisita(PathMetadata<?> metadata, PathInits inits) {
        this(Visita.class, metadata, inits);
    }

    public QVisita(Class<? extends Visita> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.uso = inits.isInitialized("uso") ? new QUso(forProperty("uso"), inits.get("uso")) : null;
    }

}

