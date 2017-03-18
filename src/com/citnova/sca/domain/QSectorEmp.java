package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSectorEmp is a Querydsl query type for SectorEmp
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSectorEmp extends EntityPathBase<SectorEmp> {

    private static final long serialVersionUID = -256502858L;

    public static final QSectorEmp sectorEmp = new QSectorEmp("sectorEmp");

    public final StringPath descripcionSE = createString("descripcionSE");

    public final NumberPath<Integer> idSE = createNumber("idSE", Integer.class);

    public final StringPath nombreSE = createString("nombreSE");

    public final SetPath<Organizacion, QOrganizacion> organizacionSet = this.<Organizacion, QOrganizacion>createSet("organizacionSet", Organizacion.class, QOrganizacion.class, PathInits.DIRECT2);

    public QSectorEmp(String variable) {
        super(SectorEmp.class, forVariable(variable));
    }

    public QSectorEmp(Path<? extends SectorEmp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSectorEmp(PathMetadata<?> metadata) {
        super(SectorEmp.class, metadata);
    }

}

