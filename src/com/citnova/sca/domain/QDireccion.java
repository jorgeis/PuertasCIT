package com.citnova.sca.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QDireccion is a Querydsl query type for Direccion
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDireccion extends EntityPathBase<Direccion> {

    private static final long serialVersionUID = -546461340L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDireccion direccion = new QDireccion("direccion");

    public final StringPath calleDir = createString("calleDir");

    public final SetPath<Cliente, QCliente> clienteSet = this.<Cliente, QCliente>createSet("clienteSet", Cliente.class, QCliente.class, PathInits.DIRECT2);

    public final StringPath coloniaDir = createString("coloniaDir");

    public final StringPath cpDir = createString("cpDir");

    public final NumberPath<Integer> idDir = createNumber("idDir", Integer.class);

    public final QMunicipio municipio;

    public final StringPath numExtDir = createString("numExtDir");

    public final StringPath numIntDir = createString("numIntDir");

    public final SetPath<Organizacion, QOrganizacion> organizacionSet = this.<Organizacion, QOrganizacion>createSet("organizacionSet", Organizacion.class, QOrganizacion.class, PathInits.DIRECT2);

    public QDireccion(String variable) {
        this(Direccion.class, forVariable(variable), INITS);
    }

    public QDireccion(Path<? extends Direccion> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDireccion(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QDireccion(PathMetadata<?> metadata, PathInits inits) {
        this(Direccion.class, metadata, inits);
    }

    public QDireccion(Class<? extends Direccion> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.municipio = inits.isInitialized("municipio") ? new QMunicipio(forProperty("municipio"), inits.get("municipio")) : null;
    }

}

