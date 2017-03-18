package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QNotificacion is a Querydsl query type for Notificacion
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QNotificacion extends EntityPathBase<Notificacion> {

    private static final long serialVersionUID = 141006312L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNotificacion notificacion = new QNotificacion("notificacion");

    public final QAdmin admin;

    public final StringPath contenidoNot = createString("contenidoNot");

    public final DateTimePath<java.sql.Timestamp> fhCreaNot = createDateTime("fhCreaNot", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> fhPubNot = createDateTime("fhPubNot", java.sql.Timestamp.class);

    public final NumberPath<Integer> idNot = createNumber("idNot", Integer.class);

    public final StringPath statusNot = createString("statusNot");

    public final StringPath tituloNot = createString("tituloNot");

    public final StringPath visibilidadNot = createString("visibilidadNot");

    public QNotificacion(String variable) {
        this(Notificacion.class, forVariable(variable), INITS);
    }

    public QNotificacion(Path<? extends Notificacion> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QNotificacion(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QNotificacion(PathMetadata<?> metadata, PathInits inits) {
        this(Notificacion.class, metadata, inits);
    }

    public QNotificacion(Class<? extends Notificacion> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admin = inits.isInitialized("admin") ? new QAdmin(forProperty("admin"), inits.get("admin")) : null;
    }

}

