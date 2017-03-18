package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QAdmin is a Querydsl query type for Admin
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAdmin extends EntityPathBase<Admin> {

    private static final long serialVersionUID = -348546685L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAdmin admin = new QAdmin("admin");

    public final StringPath areaAd = createString("areaAd");

    public final StringPath cargoAd = createString("cargoAd");

    public final StringPath creadoAd = createString("creadoAd");

    public final DateTimePath<java.sql.Timestamp> fhAccesoAd = createDateTime("fhAccesoAd", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> fhCreaAd = createDateTime("fhCreaAd", java.sql.Timestamp.class);

    public final SetPath<Gratuito, QGratuito> gratuitoSet = this.<Gratuito, QGratuito>createSet("gratuitoSet", Gratuito.class, QGratuito.class, PathInits.DIRECT2);

    public final NumberPath<Integer> idAd = createNumber("idAd", Integer.class);

    public final SetPath<Membresia, QMembresia> membresiaSet = this.<Membresia, QMembresia>createSet("membresiaSet", Membresia.class, QMembresia.class, PathInits.DIRECT2);

    public final SetPath<Notificacion, QNotificacion> notificacionSet = this.<Notificacion, QNotificacion>createSet("notificacionSet", Notificacion.class, QNotificacion.class, PathInits.DIRECT2);

    public final StringPath passAd = createString("passAd");

    public final QPersona persona;

    public final StringPath rolAd = createString("rolAd");

    public final StringPath statusAd = createString("statusAd");

    public final StringPath telefonoAd = createString("telefonoAd");

    public QAdmin(String variable) {
        this(Admin.class, forVariable(variable), INITS);
    }

    public QAdmin(Path<? extends Admin> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAdmin(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAdmin(PathMetadata<?> metadata, PathInits inits) {
        this(Admin.class, metadata, inits);
    }

    public QAdmin(Class<? extends Admin> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.persona = inits.isInitialized("persona") ? new QPersona(forProperty("persona"), inits.get("persona")) : null;
    }

}

