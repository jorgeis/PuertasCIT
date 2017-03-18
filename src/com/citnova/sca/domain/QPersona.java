package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QPersona is a Querydsl query type for Persona
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QPersona extends EntityPathBase<Persona> {

    private static final long serialVersionUID = 515287264L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPersona persona = new QPersona("persona");

    public final QAdmin admin;

    public final StringPath apMatPer = createString("apMatPer");

    public final StringPath apPatPer = createString("apPatPer");

    public final QCliente cliente;

    public final StringPath curpPer = createString("curpPer");

    public final StringPath emailPer = createString("emailPer");

    public final DateTimePath<java.sql.Timestamp> fhCreaPer = createDateTime("fhCreaPer", java.sql.Timestamp.class);

    public final NumberPath<Integer> idPer = createNumber("idPer", Integer.class);

    public final StringPath nombrePer = createString("nombrePer");

    public QPersona(String variable) {
        this(Persona.class, forVariable(variable), INITS);
    }

    public QPersona(Path<? extends Persona> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPersona(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QPersona(PathMetadata<?> metadata, PathInits inits) {
        this(Persona.class, metadata, inits);
    }

    public QPersona(Class<? extends Persona> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admin = inits.isInitialized("admin") ? new QAdmin(forProperty("admin"), inits.get("admin")) : null;
        this.cliente = inits.isInitialized("cliente") ? new QCliente(forProperty("cliente"), inits.get("cliente")) : null;
    }

}

