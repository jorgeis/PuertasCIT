package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QReservacion is a Querydsl query type for Reservacion
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QReservacion extends EntityPathBase<Reservacion> {

    private static final long serialVersionUID = -608393039L;

    public static final QReservacion reservacion = new QReservacion("reservacion");

    public final DateTimePath<java.sql.Timestamp> fhFinRes = createDateTime("fhFinRes", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> fhInicioRes = createDateTime("fhInicioRes", java.sql.Timestamp.class);

    public final NumberPath<Integer> idRes = createNumber("idRes", Integer.class);

    public final StringPath statusRes = createString("statusRes");

    public QReservacion(String variable) {
        super(Reservacion.class, forVariable(variable));
    }

    public QReservacion(Path<? extends Reservacion> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReservacion(PathMetadata<?> metadata) {
        super(Reservacion.class, metadata);
    }

}

