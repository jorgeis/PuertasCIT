package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QCMembresia is a Querydsl query type for CMembresia
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCMembresia extends EntityPathBase<CMembresia> {

    private static final long serialVersionUID = 1113671124L;

    public static final QCMembresia cMembresia = new QCMembresia("cMembresia");

    public final NumberPath<Float> costoCMem = createNumber("costoCMem", Float.class);

    public final StringPath descripcionCMem = createString("descripcionCMem");

    public final DateTimePath<java.sql.Timestamp> fhCreaCMem = createDateTime("fhCreaCMem", java.sql.Timestamp.class);

    public final NumberPath<Integer> idCMem = createNumber("idCMem", Integer.class);

    public final SetPath<Membresia, QMembresia> membresiaSet = this.<Membresia, QMembresia>createSet("membresiaSet", Membresia.class, QMembresia.class, PathInits.DIRECT2);

    public final StringPath nombreCMem = createString("nombreCMem");

    public final StringPath statusCMem = createString("statusCMem");

    public final StringPath tipoCMem = createString("tipoCMem");

    public QCMembresia(String variable) {
        super(CMembresia.class, forVariable(variable));
    }

    public QCMembresia(Path<? extends CMembresia> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCMembresia(PathMetadata<?> metadata) {
        super(CMembresia.class, metadata);
    }

}

