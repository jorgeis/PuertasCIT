package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QContratacion is a Querydsl query type for Contratacion
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QContratacion extends EntityPathBase<Contratacion> {

    private static final long serialVersionUID = -1386464225L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContratacion contratacion = new QContratacion("contratacion");

    public final QCliente cliente;

    public final NumberPath<Float> costoCont = createNumber("costoCont", Float.class);

    public final DateTimePath<java.sql.Timestamp> fhCont = createDateTime("fhCont", java.sql.Timestamp.class);

    public final NumberPath<Integer> idCont = createNumber("idCont", Integer.class);

    public final NumberPath<Float> ivaCont = createNumber("ivaCont", Float.class);

    public final ListPath<Membresia, QMembresia> membresiaList = this.<Membresia, QMembresia>createList("membresiaList", Membresia.class, QMembresia.class, PathInits.DIRECT2);

    public final StringPath refCont = createString("refCont");

    public QContratacion(String variable) {
        this(Contratacion.class, forVariable(variable), INITS);
    }

    public QContratacion(Path<? extends Contratacion> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QContratacion(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QContratacion(PathMetadata<?> metadata, PathInits inits) {
        this(Contratacion.class, metadata, inits);
    }

    public QContratacion(Class<? extends Contratacion> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cliente = inits.isInitialized("cliente") ? new QCliente(forProperty("cliente"), inits.get("cliente")) : null;
    }

}

