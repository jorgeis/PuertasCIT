package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QEstado is a Querydsl query type for Estado
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QEstado extends EntityPathBase<Estado> {

    private static final long serialVersionUID = -2086442574L;

    public static final QEstado estado = new QEstado("estado");

    public final NumberPath<Integer> idEst = createNumber("idEst", Integer.class);

    public final SetPath<Municipio, QMunicipio> municipioSet = this.<Municipio, QMunicipio>createSet("municipioSet", Municipio.class, QMunicipio.class, PathInits.DIRECT2);

    public final StringPath nombreEst = createString("nombreEst");

    public QEstado(String variable) {
        super(Estado.class, forVariable(variable));
    }

    public QEstado(Path<? extends Estado> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEstado(PathMetadata<?> metadata) {
        super(Estado.class, metadata);
    }

}

