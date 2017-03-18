package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QArea is a Querydsl query type for Area
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QArea extends EntityPathBase<Area> {

    private static final long serialVersionUID = -11230247L;

    public static final QArea area = new QArea("area");

    public final StringPath descripcionArea = createString("descripcionArea");

    public final SetPath<Gratuito, QGratuito> gratuitoSet = this.<Gratuito, QGratuito>createSet("gratuitoSet", Gratuito.class, QGratuito.class, PathInits.DIRECT2);

    public final NumberPath<Integer> idArea = createNumber("idArea", Integer.class);

    public final StringPath nombreArea = createString("nombreArea");

    public final StringPath statusArea = createString("statusArea");

    public final StringPath tipoArea = createString("tipoArea");

    public QArea(String variable) {
        super(Area.class, forVariable(variable));
    }

    public QArea(Path<? extends Area> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArea(PathMetadata<?> metadata) {
        super(Area.class, metadata);
    }

}

