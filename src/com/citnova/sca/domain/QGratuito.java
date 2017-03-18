package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGratuito is a Querydsl query type for Gratuito
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGratuito extends EntityPathBase<Gratuito> {

    private static final long serialVersionUID = -1544339271L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGratuito gratuito = new QGratuito("gratuito");

    public final QAdmin admin;

    public final StringPath apMatRespGra = createString("apMatRespGra");

    public final StringPath apMatUsrGra = createString("apMatUsrGra");

    public final StringPath apPatRespGra = createString("apPatRespGra");

    public final StringPath apPatUsrGra = createString("apPatUsrGra");

    public final QArea area;

    public final StringPath cargoRespGra = createString("cargoRespGra");

    public final StringPath cargoUsrGra = createString("cargoUsrGra");

    public final StringPath comentariosGra = createString("comentariosGra");

    public final StringPath desicionGra = createString("desicionGra");

    public final StringPath emailUsrGra = createString("emailUsrGra");

    public final DateTimePath<java.sql.Timestamp> fhFinEveGra = createDateTime("fhFinEveGra", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> fhInicioEveGra = createDateTime("fhInicioEveGra", java.sql.Timestamp.class);

    public final NumberPath<Integer> idGra = createNumber("idGra", Integer.class);

    public final StringPath impactoEveGra = createString("impactoEveGra");

    public final StringPath nombreEveGra = createString("nombreEveGra");

    public final StringPath nombreRespGra = createString("nombreRespGra");

    public final StringPath nombreUsrGra = createString("nombreUsrGra");

    public final NumberPath<Integer> numAsistEveGra = createNumber("numAsistEveGra", Integer.class);

    public final StringPath objetivoEveGra = createString("objetivoEveGra");

    public final QOrganizacion organizacion;

    public final StringPath poblacionObjEveGra = createString("poblacionObjEveGra");

    public final StringPath sexoUsrGra = createString("sexoUsrGra");

    public final StringPath statusGra = createString("statusGra");

    public final StringPath telefonoUsrGra = createString("telefonoUsrGra");

    public QGratuito(String variable) {
        this(Gratuito.class, forVariable(variable), INITS);
    }

    public QGratuito(Path<? extends Gratuito> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGratuito(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QGratuito(PathMetadata<?> metadata, PathInits inits) {
        this(Gratuito.class, metadata, inits);
    }

    public QGratuito(Class<? extends Gratuito> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admin = inits.isInitialized("admin") ? new QAdmin(forProperty("admin"), inits.get("admin")) : null;
        this.area = inits.isInitialized("area") ? new QArea(forProperty("area")) : null;
        this.organizacion = inits.isInitialized("organizacion") ? new QOrganizacion(forProperty("organizacion"), inits.get("organizacion")) : null;
    }

}

